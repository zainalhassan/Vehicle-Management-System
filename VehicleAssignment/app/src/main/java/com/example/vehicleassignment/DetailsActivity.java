package com.example.vehicleassignment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;

public class DetailsActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        // get the intent
        Bundle extras = getIntent().getExtras();
        //gets the information about the vehicle that was clicked upon
        final Vehicle theVehicle = (Vehicle) extras.get("vehicle");

        System.out.println("received from: " + theVehicle.getMake() + " " + theVehicle.getModel());
        System.out.println(theVehicle.getVehicle_id());

        TextView heading = findViewById(R.id.textViewVehicle);
        TextView details = findViewById(R.id.textViewDetails);

        //display make and model of car
        heading.setText(theVehicle.getMake() + " " + theVehicle.getModel());

        //other information about vehicle is displayed
        details.setText("Vehicle id:" +theVehicle.getVehicle_id() + "\n" + "Year: "+theVehicle.getYear() + "\n" +"Price: "+  theVehicle.getPrice() + "\n" +"License number: "+ theVehicle.getLicense_number() + "\n" + "Colour: "+theVehicle.getColour() + "\n" +"Number of doors: "+ theVehicle.getNumber_doors() + "\n" +"Transmission:"+ theVehicle.getTransmission() + "\n" + "Mileage: "+theVehicle.getMileage() + "\n" +"Fuel type: "+ theVehicle.getFuel_type() + "\n" + "Engine size: "+ theVehicle.getEngine_size() + "\n" +"Body Style: "+ theVehicle.getBody_style() + "\n" + "Condition: "+theVehicle.getCondition() + "\n" + "Notes: "+ theVehicle.getNotes());

        Button updateButton = findViewById(R.id.buttonUpdateSave);

        final HashMap<String, String> params = new HashMap<>();

        //button that takes user to update activity
        updateButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getApplicationContext(), UpdateActivity.class);

                intent.putExtra("vehicle", theVehicle);
                // launch the activity
                startActivity(intent);
            }
        });
    }
}
