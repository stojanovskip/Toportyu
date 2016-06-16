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
import android.widget.Toast;

import com.kerkyra.topapp.AsyncTasks.AsyncResponse;
import com.kerkyra.topapp.AsyncTasks.GetOrdersTask;
import com.kerkyra.topapp.AsyncTasks.GetTripsTask;
import com.kerkyra.topapp.AsyncTasks.LogoutTask;
import com.kerkyra.topapp.AsyncTasks.PostOrderTask;
import com.kerkyra.topapp.R;
import com.kerkyra.topapp.model.Order;
import com.kerkyra.topapp.model.Trip;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;



public class MainActivity extends AppCompatActivity{

    final String url = "http://10.0.2.2:8000/api/";
    Trip selectedTrip;
    EditText contentText;
    EditText costText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadTrips((Spinner)findViewById(R.id.tripSpinner));
        final View loadButton = findViewById(R.id.load_button);
        if (loadButton != null) {
            loadButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    loadOrders((ListView)findViewById(R.id.orderGetView));
                }
            });
        }

        final Spinner tripSpinner = (Spinner) findViewById(R.id.tripSpinner);
        if (tripSpinner != null) {
            tripSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    selectedTrip = (Trip) tripSpinner.getSelectedItem();
                    loadOrders((ListView)findViewById(R.id.orderGetView));
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
                            &&!(((EditText)findViewById(R.id.costText)).getText().toString().equals("")))){
                        postOrder();
                    }
                }
            });
        }
        final View logoutButton = findViewById(R.id.logOut_button);
        if (logoutButton != null) {
            logoutButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    logout();
                }
            });
        }
    }

    private void logout() {
        LogoutTask logoutTask= new LogoutTask();
        logoutTask.delegate = new AsyncResponse() {
            @Override
            public void processFinish(Object output) {
                openLoginWindow();
            }
        };
        logoutTask.execute();
    }

    private void postOrder() {
        Order order = new Order();
        contentText = (EditText) findViewById(R.id.contentText);
        costText = (EditText) findViewById(R.id.costText);
        order.setContent(contentText.getText().toString());
        order.setCost(Integer.parseInt(costText.getText().toString()));
        order.setTrip(selectedTrip);
        PostOrderTask postOrderTask = new PostOrderTask();
        postOrderTask.delegate = new AsyncResponse<Order>(){

            @Override
            public void processFinish(Order output) {
                costText.setText("");
                contentText.setText("");
                Toast.makeText(MainActivity.this,"JUHHU",Toast.LENGTH_LONG).show();
            }
        };
        postOrderTask.start(order);
    }
    private void loadOrders(AdapterView<android.widget.ListAdapter> inview){
        final AdapterView<android.widget.ListAdapter> view = inview;
        GetOrdersTask getOrdersTask = new GetOrdersTask();
        getOrdersTask.delegate = new AsyncResponse<Order[]>() {
            @Override
            public void processFinish(Order[] orders) {
                if(orders!=null) {
                    ArrayAdapter<Order> adapter = new ArrayAdapter<Order>(MainActivity.this, android.R.layout.simple_list_item_1, orders);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    view.setAdapter(adapter);
                }
            }

        };
        getOrdersTask.start(selectedTrip);
    }

    public void loadTrips(AdapterView<android.widget.SpinnerAdapter> viewTwo) {
        final AdapterView<android.widget.SpinnerAdapter> view;
        view = viewTwo;
        GetTripsTask getTripsTask = new GetTripsTask();
        getTripsTask.delegate = new AsyncResponse<Trip[]>() {
            @Override
            public void processFinish(Trip[] trips) {
                if (trips != null) {
                    loadOrders((ListView) findViewById(R.id.orderGetView));
                    ArrayAdapter<Trip> adapter = new ArrayAdapter<Trip>(MainActivity.this, android.R.layout.simple_list_item_1, trips);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    view.setAdapter(adapter);
                }
            }
        };
        getTripsTask.execute();

    }


    private void openLoginWindow(){
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


}


