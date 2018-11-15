package fr.aston.restoplus.ui.restaurant;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import fr.aston.restoplus.R;
import fr.aston.restoplus.ui.restaurant.model.Records;
import fr.aston.restoplus.ui.restaurant.model.Restaurant;

public class DetailActivity extends AppCompatActivity {
<<<<<<< HEAD
    private TextView textViewRestaurant;
=======
    private TextView test;
>>>>>>> 429c5832dc286dd86060aa8c6d553f8f40c6c2db
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

<<<<<<< HEAD
        textViewRestaurant = findViewById(R.id.textViewRestaurant);
        Restaurant restaurant = (Restaurant) getIntent().getExtras().get("restaurant");
        textViewRestaurant.setText(restaurant.getFields().getEnseigne());
=======
        test = findViewById(R.id.test);
        Restaurant restaurant = (Restaurant) getIntent().getExtras().get("restaurant");
        test.setText(restaurant.getFields().getEnseigne());
>>>>>>> 429c5832dc286dd86060aa8c6d553f8f40c6c2db

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

}
