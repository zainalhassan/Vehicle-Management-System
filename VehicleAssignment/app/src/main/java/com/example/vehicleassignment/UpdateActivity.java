package com.example.vehicleassignment;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class UpdateActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        //run network on the main thread hack
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        // get the intent
        Bundle extras = getIntent().getExtras();
        //get information about the vehicle to be updated.
        final Vehicle theVehicle = (Vehicle) extras.get("vehicle");

        //populate fields with vehicle information
        final EditText Make = findViewById(R.id.editTextMakeUpdate);
        Make.setText(theVehicle.getMake());
        final EditText Model = findViewById(R.id.editTextModelUpdate);
        Model.setText(theVehicle.getModel());
        final EditText Year = findViewById(R.id.editTextYearUpdate);
        Year.setText("" + theVehicle.getYear());
        final EditText Price = findViewById(R.id.editTextPriceUpdate);
        Price.setText("" + theVehicle.getPrice());
        final EditText License_number = findViewById(R.id.editTextLicense_numberUpdate);
        License_number.setText(theVehicle.getLicense_number());
        final EditText Colour = findViewById(R.id.editTextColourUpdate);
        Colour.setText(theVehicle.getColour());
        final EditText Number_doors = findViewById(R.id.editTextNumber_doorsUpdate);
        Number_doors.setText("" + theVehicle.getNumber_doors());
        final EditText Transmission = findViewById(R.id.editTextTransmissionUpdate);
        Transmission.setText(theVehicle.getTransmission());
        final EditText Mileage = findViewById(R.id.editTextMileageUpdate);
        Mileage.setText("" + theVehicle.getMileage());
        final EditText Fuel_type = findViewById(R.id.editTextFuel_typeUpdate);
        Fuel_type.setText(theVehicle.getFuel_type());
        final EditText Engine_size = findViewById(R.id.editTextEngine_sizeUpdate);
        Engine_size.setText("" + theVehicle.getEngine_size());
        final EditText Body_style = findViewById(R.id.editTextBody_styleUpdate);
        Body_style.setText(theVehicle.getBody_style());
        final EditText Condition = findViewById(R.id.editTextConditionUpdate);
        Condition.setText(theVehicle.getCondition());
        final EditText Notes = findViewById(R.id.editTextNotesUpdate);
        Notes.setText(theVehicle.getNotes());

        Button saveUpdateButton = findViewById(R.id.buttonUpdateSave);
        final HashMap<String, String> params = new HashMap<>();

        //update button, if clicked, uodates vehicle.
        saveUpdateButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                System.out.println("Update vehicle save pressed");

                Gson gson = new Gson();

                String makeUpdate = Make.getText().toString();
                String modelUpdate = Model.getText().toString();
                int yearUpdate = Integer.parseInt(Year.getText().toString());
                int priceUpdate = Integer.parseInt(Price.getText().toString());
                String license_numberUpdate = License_number.getText().toString();
                String colourUpdate = Colour.getText().toString();
                int number_doorsUpdate = Integer.parseInt(Number_doors.getText().toString());
                String transmission = Transmission.getText().toString();
                int mileage = Integer.parseInt(Mileage.getText().toString());
                String fuel_type = Fuel_type.getText().toString();
                int engine_size = Integer.parseInt(Engine_size.getText().toString());
                String body_style = Body_style.getText().toString();
                String condition = Condition.getText().toString();
                String notes = Notes.getText().toString();

                //object of vehicle with data inputted
                Vehicle vehicle = new Vehicle(theVehicle.getVehicle_id(),makeUpdate,modelUpdate,yearUpdate,priceUpdate,license_numberUpdate,colourUpdate,number_doorsUpdate,transmission,mileage,fuel_type,engine_size,body_style,condition, notes);

                String vehicleJSON = gson.toJson(vehicle);
                System.out.println(vehicleJSON);
                params.put("json", vehicleJSON);
                //url that does update and passes hardcoded api key
                String url ="http://10.0.2.2:8005/VehiclesDB/api?api_key=fCo6TLB2sPSmnfG0";
                performPutCall(url, params);

                //return back to main page
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                // launch the activity
                intent.putExtra("vehicle", vehicle);
                startActivity(intent);
            }
        });
    }
    public String performPutCall(String requestURL, HashMap<String, String> PutDataParams) {

        URL url;
        String response = "";
        try {
            url = new URL(requestURL);

            //create the connection object
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("PUT");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            //write/send.Put data to the connection using output stream and buffered writer

            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));

            //write/send/Put key/ value data (url encoded) to the server
            writer.write(getPutDataString(PutDataParams));

            //Clear the writer
            writer.flush();
            writer.close();
            //close output stream
            os.close();

            //write/send/POSY key/value data (url encoded) to the server
            int responseCode = conn.getResponseCode();
            System.out.println("responseCode = " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) {
                Toast.makeText(this, "Vehicle Updated", Toast.LENGTH_LONG).show();
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line=br.readLine()) != null) {
                    response += line;
                }
            } else {
                Toast.makeText(this, "Vehicle not updated ", Toast.LENGTH_LONG).show();
                response = "";
            }


        } catch (Exception e) {
            e.printStackTrace();

        }

        System.out.println("response =" + response);
        return response;
    }

    private String getPutDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for(Map.Entry<String, String> entry : params.entrySet()) {
            if(first) {
                first = false;
            } else
                result.append("&");
            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }
        return result.toString();
    }
}
