package com.kerkyra.topapp.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.kerkyra.topapp.R;
import com.kerkyra.topapp.model.Trip;

public class NewTripActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_trip);
        Intent intent = getIntent();
    }
    private class PostTripTask extends AsyncTask<Void,Void,Trip>{
        @Override
        protected Trip doInBackground(Void... params) {
            return null;
        }
    }
}
