package com.esp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class Login extends AppCompatActivity {

    private EditText loginField, passwordField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginField = (EditText) findViewById(R.id.loginField);
        passwordField = (EditText) findViewById(R.id.passwordField);

    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginBtn:
                Intent homeIntent = new Intent(Login.this, Main.class);
                startActivity(homeIntent);
                break;
            case R.id.forgotPasswordBtn:
                break;
            case R.id.signUpBtn:
                Intent regIntent = new Intent(Login.this, Registration.class);
                startActivity(regIntent);
                break;
            default:
                break;
        }
    }
}
