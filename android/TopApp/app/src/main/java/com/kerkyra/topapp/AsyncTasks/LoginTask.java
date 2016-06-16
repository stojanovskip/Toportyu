package com.kerkyra.topapp.AsyncTasks;

import android.os.AsyncTask;
import android.util.Log;

import com.kerkyra.topapp.model.Credentials;
import com.kerkyra.topapp.model.User;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.net.CookieHandler;
import java.net.CookieManager;

/**
 * Created by andras.timar on 6/15/2016.
 */
public class LoginTask extends AsyncTask<Void,Void,User>{

    final String url = "http://10.0.2.2:8000/api/";
    private Credentials cred;

    public LoginTask() {
        if (CookieHandler.getDefault() == null) CookieHandler.setDefault(new CookieManager());

    }

    @Override
    protected User doInBackground(Void... params) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            ResponseEntity<User> responseEntity = restTemplate.postForEntity(url+"users/login", getCred(), User.class);
            User user = responseEntity.getBody();
            return user;
        } catch (Exception e) {
            Log.e("MainActivity", e.getMessage(), e);
        }
        return null;
    }

    @Override
    protected void onPostExecute(User params){
        delegate.processFinish(params);
    }
    public AsyncResponse delegate = null;

    public Credentials getCred() {
        return cred;
    }

    public void setCred(Credentials cred) {
        this.cred = cred;
    }
}
