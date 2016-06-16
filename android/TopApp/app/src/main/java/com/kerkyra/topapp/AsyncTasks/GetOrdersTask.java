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
public class GetOrdersTask extends AsyncTask<Void,Void,Order[]> {
    final String url = "http://10.0.2.2:8000/api/";
    private Order order;
    public AsyncResponse delegate;
    private Trip selectedTrip;
    public void start(Trip selectedTrip){
        this.selectedTrip = selectedTrip;
        this.execute();
    }

    @Override
    protected Order[] doInBackground(Void... params) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            if(selectedTrip!=null) {
                ResponseEntity<Order[]> responseEntity = restTemplate.getForEntity(url + "trips/" + selectedTrip.getId() + "/orders", Order[].class);
                return responseEntity.getBody();
            }
        } catch (Exception e) {
            Log.e("MainActivity", e.getMessage(), e);
        }
        return null;
    }

    @Override
    protected void onPostExecute(Order[] orders){
        delegate.processFinish(orders);
    }
}
