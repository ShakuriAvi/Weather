package com.example.weather;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import android.view.*;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> {

private  ArrayList< CityWheater> mData;
private LayoutInflater mInflater;


        // data is passed into the constructor
        WeatherAdapter(Context context, ArrayList< CityWheater> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        }

// inflates the row layout from xml when needed
@Override
public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.rescycleview_weather, parent, false);
        return new ViewHolder(view);
        }



    // binds the data to the TextView in each row
@Override
public void onBindViewHolder(ViewHolder holder, int position) {
         //mData.get(position).getCityName();
         holder.weather_TXT_cityName.setText(holder.weather_TXT_cityName.getText()+ " "+ mData.get(position).getCityName());
         holder.weather_TXT_countryName.setText(holder.weather_TXT_countryName.getText()+ " "+ mData.get(position).getCountry());
         holder.weather_TXT_temp.setText(holder.weather_TXT_temp.getText()+ " "+ mData.get(position).getTemperature());
         holder.weather_TXT_descr.setText(holder.weather_TXT_descr.getText()+ " "+ mData.get(position).getDescription());
         holder.weather_TXT_localTime.setText(holder.weather_TXT_localTime.getText()+ " "+ mData.get(position).getLocalTime());
        }

// total number of rows
@Override
public int getItemCount() {
        return mData.size();
        }


// stores and recycles views as they are scrolled off screen
public class ViewHolder extends RecyclerView.ViewHolder  {
    private TextView weather_TXT_cityName;
    private TextView weather_TXT_countryName;
    private TextView weather_TXT_temp;
    private TextView weather_TXT_descr;
    private TextView weather_TXT_localTime;

    ViewHolder(View itemView) {
        super(itemView);
        weather_TXT_cityName = itemView.findViewById(R.id.weather_TXT_cityName);
        weather_TXT_countryName = itemView.findViewById(R.id.weather_TXT_countryName);
        weather_TXT_temp = itemView.findViewById(R.id.weather_TXT_temp);
        weather_TXT_descr = itemView.findViewById(R.id.weather_TXT_descr);
        weather_TXT_localTime = itemView.findViewById(R.id.weather_TXT_localTime);
    }


}

}