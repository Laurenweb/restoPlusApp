package fr.aston.restoplus.ui.restaurant;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.ArrayList;

import fr.aston.restoplus.R;
import fr.aston.restoplus.ui.home.HomeActivity;
import fr.aston.restoplus.ui.restaurant.model.Records;
import fr.aston.restoplus.ui.restaurant.model.Restaurant;

/**
 * A simple {@link Fragment} subclass.
 */
public class RestaurantFragment extends Fragment {

    private ListView listViewFastFood;
    private Records records;
    public RestaurantFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_restaurant, container, false);
        listViewFastFood = view.findViewById(R.id.listViewFastFood);
        // Inflate the layout for this fragment
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        String[] stringList = { "aaa","bbb","ccc","ddd","eee","fff" };
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://opendata.paris.fr/api/records/1.0/search/?dataset=costo-commercants-connectes&facet=cp&facet=categorie&facet=quartier&refine.categorie=Caf%C3%A9s%2FH%C3%B4tels%2FRestaurants",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String json) {

                        // Parsing du flux json
                        Gson myGson = new Gson();

                        // Recupération du flux json
                        records = myGson.fromJson(json, Records.class);
                        //Restaurant restaurants = myGson.fromJson(json, Restaurant.class);


                        RestaurantAdapter restaurantAdapter  = new RestaurantAdapter(getContext(), records.getRestaurants());

                        Log.d("TAGTEST", records.getRestaurants().get(0).getRecordid());

                        listViewFastFood.setAdapter(restaurantAdapter);


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        listViewFastFood.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i = new Intent(getContext(), DetailActivity.class);
                //If you wanna send any data to nextActicity.class you can use
                i.putExtra("restaurant", records.getRestaurants().get(position));

                startActivity(i);
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);

    }

}
