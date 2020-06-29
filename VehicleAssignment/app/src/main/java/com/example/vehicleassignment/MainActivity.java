package com.example.vehicleassignment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity
{
    //array of vehicles with their names
    String[] vehicleNames;
    ArrayList<Vehicle> allVehicles = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //run network on main thread hack
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        ListView vehicleList = findViewById(R.id.vehicleList);
        Button insertButton = findViewById(R.id.insertVehicle);

        //Making a http call
        HttpURLConnection urlConnection;
        InputStream in = null;

        try
        {
            // the url we wish to connect to
            URL url = new URL("http://10.0.2.2:8005/VehiclesDB/api?api_key=fCo6TLB2sPSmnfG0");
            // open the connection to the specified URL
            urlConnection = (HttpURLConnection) url.openConnection();
            // get the response from the server in an input stream
            in = new BufferedInputStream(urlConnection.getInputStream());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        // covert the input stream to a string
        String response = convertStreamToString(in);
        // print the response to android monitor/log cat
        System.out.println("Server response = " + response);

        //following code will convert JSON string  returned from the server to an android JSONArray
        try {
            // declare a new json array and pass it the string response from the server
            // this will convert the string into a JSON array which we can then iterate
            // over using a loop
            JSONArray jsonArray = new JSONArray(response);
            // instantiate the cheeseNames array and set the size
            // to the amount of cheese object returned by the server
            vehicleNames = new String[jsonArray.length()];

            // use a for loop to iterate over the JSON array
            for (int i=0; i < jsonArray.length(); i++)
            {
                // the following line of code will get the name of the cheese from the
                // current JSON object and store it in a string variable called name
                int vehicle_id = jsonArray.getJSONObject(i).get("vehicle_id").hashCode();
                String make = jsonArray.getJSONObject(i).get("make").toString();
                String model = jsonArray.getJSONObject(i).get("model").toString();
                int year = jsonArray.getJSONObject(i).get("year").hashCode();
                int price = jsonArray.getJSONObject(i).get("price").hashCode();
                String license_number = jsonArray.getJSONObject(i).get("license_number").toString();
                String colour = jsonArray.getJSONObject(i).get("colour").toString();
                int number_doors = jsonArray.getJSONObject(i).get("number_doors").hashCode();
                String transmission = jsonArray.getJSONObject(i).get("transmission").toString();
                int mileage = jsonArray.getJSONObject(i).get("mileage").hashCode();
                String fuel_type = jsonArray.getJSONObject(i).get("fuel_type").toString();
                int engine_size = jsonArray.getJSONObject(i).get("engine_size").hashCode();
                String body_style = jsonArray.getJSONObject(i).get("body_style").toString();
                String condition = jsonArray.getJSONObject(i).get("condition").toString();
                String notes = jsonArray.getJSONObject(i).get("notes").toString();

                // print the name to log cat
                System.out.println("vehicle id = " + vehicle_id);
                System.out.println("make = " + make);
                System.out.println("model = " + model);
                System.out.println("year = " + year);
                System.out.println("price = " + price);
                System.out.println("license_number = " + license_number);
                System.out.println("colour = " + colour);
                System.out.println("number_doors = " + number_doors);
                System.out.println("transmission = " + transmission);
                System.out.println("mileage = " + mileage);
                System.out.println("fuel_type = " + fuel_type);
                System.out.println("engine_size = " + engine_size);
                System.out.println("body_style = " + body_style);
                System.out.println("condition = " + condition);
                System.out.println("notes = " + notes);

                // add the names details of the vehicles in the vehicleNames array
                vehicleNames[i] = make + " " + model + "(" + license_number + ") " + year;
                Vehicle vehicle = new Vehicle(vehicle_id,make, model, year, price, license_number, colour, number_doors, transmission, mileage, fuel_type, engine_size, body_style, condition, notes);
                allVehicles.add(vehicle);

            }
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
        // an array adapter to do all the hard work just tell it the (context, the layout, and the data)
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, vehicleNames);
        //set the adapter to the listview
        vehicleList.setAdapter(arrayAdapter);


        vehicleList.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Toast.makeText(MainActivity.this, "you pressed " + allVehicles.get(position).getMake(),Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(), DetailsActivity.class);

                intent.putExtra("vehicle", allVehicles.get(position));
                // launch the activity
                startActivity(intent);
            }
        });

        //set a long click listener that listens to which vehicle is clicked and takes it to the details page of that car
        vehicleList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
        {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id)
            {
                final int  vehicle_id = allVehicles.get(position).getVehicle_id();

                //pop up that asks if you wish to delete a vehicle
                AlertDialog.Builder deleteAlert = new AlertDialog.Builder(MainActivity.this);
                deleteAlert.setTitle("Delete Confirmation");
                deleteAlert.setMessage("Are you sure you want to delete : " + allVehicles.get(position).getMake() + " " + allVehicles.get(position).getModel());
                deleteAlert.setCancelable(false);

                final HashMap<String, String> params = new HashMap<>();

                //deletes car
                deleteAlert.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        Toast.makeText(MainActivity.this, "Delete pressed",Toast.LENGTH_SHORT).show();

                        //url that deletes
                        String url ="http://10.0.2.2:8005/VehiclesDB/api?api_key=fCo6TLB2sPSmnfG0&vehicle_id="+vehicle_id;
                        performDeleteCall(url, params);
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        // launch the activity
                        startActivity(intent);
                    }
                });

                //cancel delete operation
                deleteAlert.setNegativeButton("Do not delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        Toast.makeText(MainActivity.this, "Do not delete pressed",Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog alert = deleteAlert.create();
                alert.show();

                return true;
            }
        });
    }


    //if clicked on insert button, then take user to insert page
    public void insertOnClick(View v)
    {
        Intent intent = new Intent(getApplicationContext(), InsertActivity.class);
        // launch the activity
        startActivity(intent);
    }

    //method converts the input stream to a string
    public String convertStreamToString(InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

    public String performDeleteCall(String requestURL, HashMap<String, String> postDataParams)
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
            conn.setRequestMethod("DELETE");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            int responseCode = conn.getResponseCode();
            System.out.println("responseCode = " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK)
            {
                //successful toast message
                Toast.makeText(this, "Vehicle deleted", Toast.LENGTH_LONG).show();
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line=br.readLine()) != null)
                {
                    response += line;
                }
            }
            else
                {
                    //unsuccessful message
                    Toast.makeText(this, "Vehicle not deleted", Toast.LENGTH_LONG).show();
                    response = "";
                }


        }
        catch (Exception e)
        {
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