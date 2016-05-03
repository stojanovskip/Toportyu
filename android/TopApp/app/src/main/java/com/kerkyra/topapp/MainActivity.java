package com.kerkyra.topapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View button = findViewById(R.id.load_button);
        if (button != null) {
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    new LoadOrdersTask().execute();
                }
            });
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        new LoadOrdersTask().execute();
    }

    private class LoadOrdersTask extends AsyncTask<Void, Void, Order[]> {
        @Override
        protected Order[] doInBackground(Void... params) {
            try {
                final String url = "http://10.248.70.119:8000/orders";
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                ResponseEntity<Order[]> responseEntity = restTemplate.getForEntity(url, Order[].class);
                return responseEntity.getBody();
            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Order[] orders) {
            if (orders != null) {
                RecyclerView recyclerView = (RecyclerView) findViewById(R.id.orderGetView);
                Arrays.sort(orders, new Comparator<Order>(){
                    public int compare(Order o1, Order o2){
                        return (o1.getId()).compareTo(o2.getId());
                    }
                });
                ArrayAdapter<Order> adapter = new ArrayAdapter<Order>(MainActivity.this,android.R.layout.simple_list_item_1,orders);
                listView.setAdapter(adapter);
             }
        }
    }
}


