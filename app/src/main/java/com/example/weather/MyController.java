package com.example.weather;

import android.app.Activity;
import android.os.StrictMode;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import javax.net.ssl.HttpsURLConnection;

public class MyController {

    private ArrayList<CityWheater> cities;
    private ICallback ic;
    private Activity activity;

    public MyController(ICallback ic,Activity activity) {
        cities = new ArrayList<CityWheater>();
        this.ic = ic;
    }
    public void makeServiceCall( ArrayList<String> citiesName) {
        new Thread(){
            @Override
            public void run() {

                String response = null;
                try {
                    for (int i = 0; i <citiesName.size() ; i++) {
                        URL url = new URL("https://api.weatherapi.com/v1/current.json?key=f39089163af14d0caeb124253213108%20&q="+citiesName.get(i)+"&aqi=no");
                        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
                        conn.setRequestMethod("GET");
                        // read the response
                        InputStream in = new BufferedInputStream(conn.getInputStream());
                        response = convertStreamToString(in);
                        CityWheater cityWheater = convertStringToCityWeather(response);
                       cities.add(cityWheater);
                    }
                    ic.callback(cities);
                } catch (MalformedURLException e) {
                    Log.e("TAG", "MalformedURLException: " + e.getMessage());
                } catch (ProtocolException e) {
                    Log.e("TAG", "ProtocolException: " + e.getMessage());
                } catch (IOException e) {
                    Log.e("TAG", "IOException: " + e.getMessage());
                } catch (Exception e) {
                    Log.e("TAG", "Exception: " + e.getMessage());
                }

            }

        }.start();

            // Request a string response from the provided URL.
    }

    private String convertStreamToString(InputStream in) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();

    }
    private CityWheater convertStringToCityWeather(String str){
        if (str != null) {
            try {
                JSONObject jsonObj = new JSONObject(str);

                JSONObject location = jsonObj.getJSONObject("location");



                    String city = location.getString("name");
                    String country = location.getString("country");
                    String localtime = location.getString("localtime");

                    JSONObject current = jsonObj.getJSONObject("current");

                    int temp = current.getInt("temp_c");

                JSONObject condition = current.getJSONObject("condition");

                String description = condition.getString("text");

                return new CityWheater(city,country,temp,localtime,description);


            } catch (final JSONException e) {
                Log.e("TAG", "Json parsing error: " + e.getMessage());


            }
            return null;
        } else {
            Log.e("TAG", "Couldn't get json from server.");
            return null;
        }


    }



}
