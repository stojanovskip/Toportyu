package com.kerkyra.topapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.kerkyra.topapp.AsyncTasks.AsyncResponse;
import com.kerkyra.topapp.AsyncTasks.LoginTask;
import com.kerkyra.topapp.R;
import com.kerkyra.topapp.model.Credentials;
import com.kerkyra.topapp.model.User;


public class LoginActivity extends AppCompatActivity implements AsyncResponse<User> {

    final String url = "http://10.0.2.2:8000/api/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final LoginActivity loginActivity = this;
        final View loginButton = findViewById(R.id.loginButton);
        if (loginButton != null) {
            loginButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    login(loginActivity);

                }
            });
        }
    }

    private void login(LoginActivity loginActivity) {
        Credentials credentials = new Credentials();
        credentials.setUsername(((EditText)findViewById(R.id.userNameText)).getText().toString());
        credentials.setPassword(((EditText)findViewById(R.id.passwordText)).getText().toString());
        LoginTask loginTask = new LoginTask();
        loginTask.delegate = loginActivity;
        loginTask.setCred(credentials);
        loginTask.execute();
    }

    @Override
    public void processFinish(User output) {
        User loginUser = output;
        if(loginUser.getUsername()!=null) {
            Toast.makeText(LoginActivity.this,"Login successful",Toast.LENGTH_SHORT).show();
            openNewTripWindow();
        }
        else{
            Toast.makeText(LoginActivity.this,"Invalid credentials",Toast.LENGTH_SHORT).show();
        }
    }

    public void openNewTripWindow()
    {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

}
