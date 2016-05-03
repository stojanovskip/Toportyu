package com.kerkyra.topapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Comparator;

import static java.lang.Integer.parseInt;

public class MainActivity extends AppCompatActivity {

    final String url = "http://10.248.70.234:8000/orders";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View loadButton = findViewById(R.id.load_button);
        if (loadButton != null) {
            loadButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    new LoadOrdersTask().execute();
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
        new LoadOrdersTask().execute();
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
                restTemplate.postForObject(url,order, Order.class);
                return order;
            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }
            return null;
        }
    }

    private class LoadOrdersTask extends AsyncTask<Void, Void, Order[]> {
        @Override
        protected Order[] doInBackground(Void... params) {
            try {
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
                ListView listView = (ListView) findViewById(R.id.orderGetView);
                Arrays.sort(orders, new Comparator<Order>(){
                    public int compare(Order o1, Order o2){
                        return (o1.getTrip().getId()).compareTo(o2.getTrip().getId());
                    }
                });
                ArrayAdapter<Order> adapter = new ArrayAdapter<Order>(MainActivity.this,android.R.layout.simple_list_item_1,orders);
                listView.setAdapter(adapter);
             }
        }
    }
}


