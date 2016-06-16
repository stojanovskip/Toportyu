package com.kerkyra.topapp.activities;

import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.kerkyra.topapp.AsyncTasks.AsyncResponse;
import com.kerkyra.topapp.AsyncTasks.PostTripTask;
import com.kerkyra.topapp.R;

import com.kerkyra.topapp.model.Trip;


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
                    postTrip();
                }
            });
        }

    }

    private void postTrip() {

        EditText tripName = (EditText) findViewById(R.id.newTrip_text);
        if (!isEmpty(tripName)) {


            Trip trip = new Trip();
            trip.setName(tripName.getText().toString());
            PostTripTask postTripTask = new PostTripTask();
            postTripTask.delegate = new AsyncResponse<Trip>() {
                @Override
                public void processFinish(Trip outTrip) {

                    Toast.makeText(NewTripActivity.this, "Success!", Toast.LENGTH_SHORT).show();
                    startMainActivity();
                }
            };
            postTripTask.start(trip);
        }
        else{
            Toast.makeText(NewTripActivity.this, "Trip name was empty!", Toast.LENGTH_SHORT).show();
        }
    }
    private boolean isEmpty(EditText myeditText) {
        return myeditText.getText().toString().trim().length() == 0;
    }
    private void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
