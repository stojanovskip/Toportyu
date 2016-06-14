package com.kerkyra.topapp.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.kerkyra.topapp.R;
import com.kerkyra.topapp.model.Order;
import com.kerkyra.topapp.model.Trip;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import static java.lang.Integer.parseInt;

public class NewTripActivity extends AppCompatActivity {

    final String url = "http://10.0.2.2:8000/api/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_trip);
        Intent intent = getIntent();

        View sendButton = findViewById(R.id.newTrip_button);
        if (sendButton != null) {
            sendButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                        new PostTripTask().execute();
                }
            });
        }

    }
    private HttpHeaders getHttpHeaders(){
        SharedPreferences pref = getSharedPreferences("MyPref", 0); // 0 - for private mode
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cookie","sessionId="+pref.getString("sessionId",""));
        headers.add("Content-Type","application/json");
        return headers;
    }
    private void startMainActivity(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    private class PostTripTask extends  AsyncTask<Void,Void,Trip>
    {
        private Trip trip;
        EditText tripName;
        public PostTripTask()
        {
            trip = new Trip();
            tripName = (EditText) findViewById(R.id.newTrip_text);
            trip.setName(tripName.getText().toString());
        }
        @Override
        protected Trip doInBackground(Void... params) {
            try {
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                HttpEntity<Trip> httpEntity = new HttpEntity<Trip>(trip,getHttpHeaders());
                HttpEntity<Trip> response = restTemplate.exchange(
                        url+"trips", HttpMethod.POST, httpEntity, Trip.class);
                return response.getBody();
            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }
            return null;
        }
        @Override
        protected void onPostExecute(Trip t){
                startMainActivity();
        }
    }
}
