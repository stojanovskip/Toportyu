package com.kerkyra.topapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.kerkyra.topapp.model.Order;
import com.kerkyra.topapp.model.Trip;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Comparator;

import static java.lang.Integer.parseInt;

public class MainActivity extends AppCompatActivity {

    final String url = "http://10.0.2.2:8000/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LoadTask<Trip[]> loadTripsTask = new LoadTask<Trip[]>(Trip[].class,"trips",(Spinner)findViewById(R.id.tripSpinner));

        LoadTask<Order[]> loadOrdersTask = new LoadTask<Order[]>(Order[].class,"orders",(ListView)findViewById(R.id.orderGetView));

        loadTripsTask.execute();
        loadOrdersTask.execute();

        final View loadButton = findViewById(R.id.load_button);
        if (loadButton != null) {
            loadButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    LoadTask<Order[]> loadOrderTask = new LoadTask<Order[]>(Order[].class,"orders",(ListView)findViewById(R.id.orderGetView));
                    loadOrderTask.execute();
                }
            });
        }

        View sendButton = findViewById(R.id.send_button);
        if (sendButton != null) {
            sendButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if(!(((EditText)findViewById(R.id.contentText)).getText().toString().equals("")
                            &&!(((EditText)findViewById(R.id.costText)).getText().toString().equals(""))))
                    new PostOrderTask().execute();
                }
            });
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private class PostOrderTask extends  AsyncTask<Void,Void,Order>
    {
        private Order order;
        public PostOrderTask()
        {
            order = new Order();
            EditText contentText = (EditText) findViewById(R.id.contentText);
            EditText costText = (EditText) findViewById(R.id.costText);
            order.setContent(contentText.getText().toString());
            order.setCost(parseInt(costText.getText().toString()));
            Trip t = new Trip();
            t.setId((long) 1);
            order.setTrip(t);

        }
        @Override
        protected Order doInBackground(Void... params) {
            try {
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                restTemplate.postForObject(url+"orders",order, Order.class);
                return order;
            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }
            return null;
        }
    }

    private class LoadTask<T> extends AsyncTask<Void,Void,T>
    {
        Class<T> clazz;
        String path;
        AdapterView view;
        public LoadTask(Class<T> clazz, String path,AdapterView view) {
        this.clazz = clazz;
        this.path = path;
        this.view = view;
        }

        @Override
        protected T doInBackground(Void... params) {
            try {
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                ResponseEntity<T> responseEntity = restTemplate.getForEntity(url+path, clazz);
                return responseEntity.getBody();
            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(T trips) {
            if (trips != null) {

                ArrayAdapter<T> adapter = new ArrayAdapter<T>(MainActivity.this,android.R.layout.simple_list_item_1,(T[])trips);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                view.setAdapter(adapter);
            }
        }
    }

}


