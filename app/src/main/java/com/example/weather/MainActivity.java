package com.example.weather;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements ICallback {
    private ArrayList<String> citiesName;
    private ArrayList< CityWheater> cities;
    private WeatherAdapter weatherAdapter;
    private MyController myController;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        Log.d("TAG","here");

       // myController = new MyController(this,this);
//        myController.makeServiceCall(citiesName);
        ApiController apiController = new ApiController("test",citiesName);
        Thread thread = new Thread(apiController);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        cities = apiController.getCities();
        weatherAdapter = new WeatherAdapter(this, cities);
        recyclerView = findViewById(R.id.activity_RCV_cities);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(
                new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(weatherAdapter);


    }


    private void initView() {
        citiesName= new ArrayList<>();
        citiesName.add("New York");
        citiesName.add("Tel Aviv");
        citiesName.add("Berlin");
        citiesName.add("London");
        citiesName.add("Rome");
        citiesName.add("Paris");

    }


    @Override
    public void callback(ArrayList<CityWheater> cities) {
        this.cities = cities;
        if( cities!=null ){
            MainActivity mainActivity = MainActivity.this;
            mainActivity.weatherAdapter = new WeatherAdapter(this, cities);
            mainActivity.recyclerView = findViewById(R.id.activity_RCV_cities);
            mainActivity.recyclerView.setHasFixedSize(true);
            mainActivity.recyclerView.setLayoutManager(
                    new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false));
            mainActivity.recyclerView.setAdapter(weatherAdapter);
        }

    }

}