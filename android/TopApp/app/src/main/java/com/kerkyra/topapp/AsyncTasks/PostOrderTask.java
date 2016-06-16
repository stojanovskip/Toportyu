package com.kerkyra.topapp.AsyncTasks;

import android.os.AsyncTask;
import android.util.Log;

import com.kerkyra.topapp.model.Order;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;


/**
 * Created by andras.timar on 6/15/2016.
 */
public class PostOrderTask extends AsyncTask<Void, Void, Order> {
    final String url = "http://10.0.2.2:8000/api/";
    private Order order;
    public AsyncResponse delegate;

    public void start(Order o) {
        this.order = o;
        this.execute();
    }

    @Override
    protected Order doInBackground(Void... params) {
        try {
            if (!(order.getContent().equals(""))) {

                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                ResponseEntity<Order> response = restTemplate.postForEntity(
                        url + "orders", order, Order.class);
                return response.getBody();
            }
        } catch (Exception e) {
            Log.e("MainActivity", e.getMessage(), e);
        }
        return null;
    }

    @Override
    protected void onPostExecute(Order o) {
        delegate.processFinish(o);
    }

}
