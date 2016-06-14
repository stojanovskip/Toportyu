package com.kerkyra.topapp.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;

import com.kerkyra.topapp.R;
import com.kerkyra.topapp.model.Credentials;
import com.kerkyra.topapp.model.User;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import static java.lang.Integer.parseInt;

public class LoginActivity extends AppCompatActivity {

    final String url = "http://10.0.2.2:8000/api/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final View loginButton = findViewById(R.id.loginButton);
        if (loginButton != null) {
            loginButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Login loginTask = new Login();
                    loginTask.execute();
                }
            });
        }
    }
    public void openNewTripWindow()
    {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    private class Login extends AsyncTask<Void,Void,User>
    {
        Credentials cred;
        public Login() {
            cred = new Credentials();
            EditText userNameText = (EditText) findViewById(R.id.userNameText);
            EditText passwordText = (EditText) findViewById(R.id.passwordText);
            cred.setUsername(userNameText.getText().toString());
            cred.setPassword(passwordText.getText().toString());
        }

        @Override
        protected User doInBackground(Void... params) {
            try {
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                ResponseEntity<User> responseEntity = restTemplate.postForEntity(url+"users/login",cred, User.class);
                if(responseEntity.getHeaders().containsKey("set-cookie")) {
                    SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("sessionId", responseEntity.getHeaders().get("set-cookie").toString().split("=")[1].split(";")[0]);
                    editor.commit();
                }

                return responseEntity.getBody();
            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(User user) {
            if(user.getUsername()!=null) {
                openNewTripWindow();

            }
        }
    }
}
