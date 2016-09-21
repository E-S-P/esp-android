package com.esp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
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
