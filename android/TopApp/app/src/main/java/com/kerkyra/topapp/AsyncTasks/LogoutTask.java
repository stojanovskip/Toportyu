package com.kerkyra.topapp.AsyncTasks;

import android.os.AsyncTask;
import android.util.Log;

import org.springframework.http.HttpMethod;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Andras.Timar on 6/16/2016.
 */
public class LogoutTask extends AsyncTask<Void, Void, Void> {

    public AsyncResponse delegate = null;
    final String url = "http://10.0.2.2:8000/api/";

    @Override
    protected Void doInBackground(Void... params) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            restTemplate.postForObject(url + "users/logout", HttpMethod.POST, Object.class);
        } catch (Exception e) {
            Log.e("MainActivity", e.getMessage(), e);
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void none) {
        delegate.processFinish(none);
    }
}
