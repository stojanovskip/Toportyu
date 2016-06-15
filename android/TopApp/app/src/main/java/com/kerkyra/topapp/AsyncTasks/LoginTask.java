package com.kerkyra.topapp.AsyncTasks;

import android.os.AsyncTask;

import com.kerkyra.topapp.model.Trip;

/**
 * Created by andras.timar on 6/15/2016.
 */
public class LoginTask extends AsyncTask<Void,Void,Trip[]>{


    @Override
    protected  void onPostExecute(Trip[] t){

    }

    @Override
    protected Trip[] doInBackground(Void... params) {
        return null;
    }

    // you may separate this or combined to caller class.
    public interface AsyncResponse {
        void processFinish(String output);
    }

    public AsyncResponse delegate = null;

    public LoginTask(AsyncResponse delegate){
        this.delegate = delegate;
    }

}
