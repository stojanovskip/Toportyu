package com.kerkyra.topapp.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.kerkyra.topapp.R;
import com.kerkyra.topapp.model.Order;
import com.kerkyra.topapp.model.Trip;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;


import static java.lang.Integer.parseInt;

public class MainActivity extends AppCompatActivity {

    final String url = "http://10.0.2.2:8000/api/";
    Trip selectedTrip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LoadTrips loadTripsTask = new LoadTrips("trips",(Spinner)findViewById(R.id.tripSpinner));
        loadTripsTask.execute();

        final View loadButton = findViewById(R.id.load_button);
        if (loadButton != null) {
            loadButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    LoadOrders loadOrdersTask = new LoadOrders((ListView)findViewById(R.id.orderGetView));
                    loadOrdersTask.execute();
                }
            });
        }

        final Spinner tripSpinner = (Spinner) findViewById(R.id.tripSpinner);
        if (tripSpinner != null) {
            tripSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    selectedTrip = (Trip) tripSpinner.getSelectedItem();
                    LoadOrders loadOrdersTask = new LoadOrders((ListView)findViewById(R.id.orderGetView));
                    loadOrdersTask.execute();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

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
        View logoutButton = findViewById(R.id.logOut_button);
        if (logoutButton != null) {
            logoutButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    LogOut out = new LogOut();
                    out.execute();
                    logOut();
                }
            });
        }
    }

    private void logOut(){
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
    }
    public void openNewTripWindow(View view)
    {
        Intent intent = new Intent(this,NewTripActivity.class);
        startActivity(intent);
    }
    @Override
    protected void onStart() {
        super.onStart();
    }

    private class PostOrderTask extends  AsyncTask<Void,Void,Order>
    {
        private Order order;
        EditText contentText;
        EditText costText;
        public PostOrderTask()
        {
                order = new Order();
                contentText = (EditText) findViewById(R.id.contentText);
                costText = (EditText) findViewById(R.id.costText);
            if(!(costText==null||contentText.getText()==null)) {
                order.setContent(contentText.getText().toString());
                order.setCost(parseInt(costText.getText().toString()));}
                Spinner spinner = (Spinner) findViewById(R.id.tripSpinner);
                order.setTrip(selectedTrip);
        }
        @Override
        protected Order doInBackground(Void... params) {
            try {
                if(!(order.getContent().equals(""))) {

                    RestTemplate restTemplate = new RestTemplate();
                    restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                    ResponseEntity<Order> response = restTemplate.postForEntity(
                            url + "orders",order,Order.class);
                    return response.getBody();
                }
            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }
            return null;
        }
        @Override
        protected void onPostExecute(Order o){
            costText.setText("");
            contentText.setText("");
        }
    }
    private class LoadOrders extends AsyncTask<Void,Void,Order[]>
    {
        String path;
        AdapterView<android.widget.ListAdapter> view;
        public LoadOrders(AdapterView<android.widget.ListAdapter> view) {
            this.view = view;
        }

        @Override
        protected Order[] doInBackground(Void... params) {
            try {
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                ResponseEntity<Order[]> responseEntity = restTemplate.getForEntity(url+"trips/"+selectedTrip.getId()+"/orders", Order[].class);
                return responseEntity.getBody();
            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Order[] orders) {

            ArrayAdapter<Order> adapter = new ArrayAdapter<Order>(MainActivity.this,android.R.layout.simple_list_item_1,orders);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            view.setAdapter(adapter);

        }
    }
    private class LoadTrips extends AsyncTask<Void,Void,Trip[]>
    {
        String path;
        AdapterView<android.widget.SpinnerAdapter> view;
        public LoadTrips(String path, AdapterView<android.widget.SpinnerAdapter> view) {
            this.path = path;
            this.view = view;
        }

        @Override
        protected Trip[] doInBackground(Void... params) {
            try {
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                ResponseEntity<Trip[]> responseEntity = restTemplate.getForEntity(url+path, Trip[].class);
                return responseEntity.getBody();
            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Trip[] trips) {

            if(trips!=null) {
                selectedTrip = trips[0];
                LoadOrders loadOrdersTask = new LoadOrders((ListView)findViewById(R.id.orderGetView));
                loadOrdersTask.execute();
            }
            ArrayAdapter<Trip> adapter = new ArrayAdapter<Trip>(MainActivity.this,android.R.layout.simple_list_item_1,trips);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            view.setAdapter(adapter);


        }
    }
    private class LogOut extends AsyncTask<Void,Void,Void>
    {
        AdapterView<android.widget.Adapter> view;

        @Override
        protected Void doInBackground(Void... params) {
            try {
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                HttpEntity<?> httpEntity = new HttpEntity<Order>(new HttpHeaders());
                restTemplate.postForObject(url+"users/logout",HttpMethod.POST,Object.class);
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
        AdapterView<ArrayAdapter<T>> view;
        public LoadTask(Class<T> clazz, String path, AdapterView<ArrayAdapter<T>> view) {
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
        protected void onPostExecute(T response) {

                ArrayAdapter<T> adapter = new ArrayAdapter<T>(MainActivity.this,android.R.layout.simple_list_item_1,(T[]) response);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                view.setAdapter(adapter);

        }
    }

}


