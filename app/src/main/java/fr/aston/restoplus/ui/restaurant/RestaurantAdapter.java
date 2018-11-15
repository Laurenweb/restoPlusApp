package fr.aston.restoplus.ui.restaurant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import fr.aston.restoplus.R;
import fr.aston.restoplus.ui.restaurant.model.Records;
import fr.aston.restoplus.ui.restaurant.model.Restaurant;

public class RestaurantAdapter extends ArrayAdapter<Restaurant> {
    public RestaurantAdapter(Context context, List<Restaurant> restaurants) {
        super(context, 0, restaurants);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Restaurant restaurant = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.restaurant_adapter, parent, false);
        }
        // Lookup view for data population
        TextView enseigne = (TextView) convertView.findViewById(R.id.enseigne);
        TextView cartegorie = (TextView) convertView.findViewById(R.id.categorie);
        // Populate the data into the template view using the data object
        enseigne.setText(restaurant.getFields().getEnseigne());
        cartegorie.setText(restaurant.getFields().getCategorie());
        // Return the completed view to render on screen
        return convertView;
    }
}
