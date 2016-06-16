package com.kerkyra.topapp.AsyncTasks;

import android.os.AsyncTask;
import android.util.Log;

import com.kerkyra.topapp.model.Order;
import com.kerkyra.topapp.model.Trip;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Andras.Timar on 6/16/2016.
 */
public class PostTripTask extends AsyncTask<Void,Void,Trip> {
    final String url = "http://10.0.2.2:8000/api/";
    private Trip trip;
    public AsyncResponse delegate;
    public void start(Trip t){
        this.trip = t;
        this.execute();
    }
    @Override
    protected Trip doInBackground(Void... params) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            ResponseEntity<Trip> response = restTemplate.postForEntity(
                    url + "trips",trip,Trip.class);
            return response.getBody();
        } catch (Exception e) {
            Log.e("MainActivity", e.getMessage(), e);
        }
        return null;
    }
    @Override
    protected void onPostExecute(Trip t){
        delegate.processFinish(t);
    }
}
