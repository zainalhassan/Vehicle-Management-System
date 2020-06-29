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

public class InsertActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        //run network on the main thread hack
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        //input fields
        final EditText Make = findViewById(R.id.editTextMake);
        final EditText Model = findViewById(R.id.editTextModel);
        final EditText Year = findViewById(R.id.editTextYear);
        final EditText Price = findViewById(R.id.editTextPrice);
        final EditText License_number = findViewById(R.id.editTextLicense_number);
        final EditText Colour = findViewById(R.id.editTextColour);
        final EditText Number_doors = findViewById(R.id.editTextNumber_doors);
        final EditText Transmission = findViewById(R.id.editTextTransmission);
        final EditText Mileage = findViewById(R.id.editTextMileage);
        final EditText Fuel_type = findViewById(R.id.editTextFuel_type);
        final EditText Engine_size = findViewById(R.id.editTextEngine_size);
        final EditText Body_style = findViewById(R.id.editTextBody_style);
        final EditText Condition = findViewById(R.id.editTextCondition);
        final EditText Notes = findViewById(R.id.editTextNotes);

        Button insertButton = findViewById(R.id.buttonInsert);

        final HashMap<String, String> params = new HashMap<>();

        //insert button when, once clicked, inserts user information as a new car in the table
        insertButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Gson gson = new Gson();

                String  make = Make.getText().toString();
                String  model = Model.getText().toString();
                int year = Integer.parseInt(Year.getText().toString());
                int price = Integer.parseInt(Price.getText().toString());
                String  license_number = License_number.getText().toString();
                String  colour = Colour.getText().toString();
                int number_doors = Integer.parseInt(Number_doors.getText().toString());
                String  transmission = Transmission.getText().toString();
                int mileage = Integer.parseInt(Mileage.getText().toString());
                String  fuel_type = Fuel_type.getText().toString();
                int engine_size = Integer.parseInt(Engine_size.getText().toString());
                String  body_style = Body_style.getText().toString();
                String  condition = Condition.getText().toString();
                String  notes = Notes.getText().toString();

                Vehicle vehicle = new Vehicle(0, make, model, year, price, license_number, colour, number_doors, transmission, mileage, fuel_type, engine_size, body_style, condition, notes);

                String JSONVehicle = gson.toJson(vehicle);
                System.out.println(JSONVehicle);
                params.put("json", JSONVehicle);
                //url that is used to insert into the table using an api key for verification
                String url ="http://10.0.2.2:8005/VehiclesDB/api?api_key=fCo6TLB2sPSmnfG0";
                performInsertCall(url, params);

                //return back to main page once inserted
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                // launch the activity
                intent.putExtra("vehicle", vehicle);
                startActivity(intent);
            }
        });
    }

    public String performInsertCall(String requestURL, HashMap<String, String> postDataParams)
    {

        URL url;
        String response = "";
        try
        {
            url = new URL(requestURL);

            //create the connection object
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            //write/send.POST data to the connection using output stream and buffered writer

            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));

            //write/send/POST key/ value data (url encoded) to the server
            writer.write(getPostDataString(postDataParams));

            //Clear the writer
            writer.flush();
            writer.close();
            //close output stream
            os.close();

            //write/send/POSY key/value data (url encoded) to the server
            int responseCode = conn.getResponseCode();
            System.out.println("responseCode = " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) {
                Toast.makeText(this, "Vehicle inserted", Toast.LENGTH_LONG).show();
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line=br.readLine()) != null) {
                    response += line;
                }
            } else {
                Toast.makeText(this, "Vehicle not inserted", Toast.LENGTH_LONG).show();
                response = "";
            }


        } catch (Exception e) {
            e.printStackTrace();

        }

        System.out.println("response =" + response);
        return response;
    }

    private String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
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
