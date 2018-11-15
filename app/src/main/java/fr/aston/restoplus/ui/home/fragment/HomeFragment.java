package fr.aston.restoplus.ui.home.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import fr.aston.restoplus.R;


/**
 * A simple {@link Fragment} subclass
 */
public class HomeFragment extends Fragment {

    private ListView listViewFastFood;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        //listViewFastFood = view.findViewById(R.id.listViewFastFood);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        String[] stringList = { "aaa","bbb","ccc","ddd","eee","fff" };

        //listViewFastFood.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, stringList));
    }
}
