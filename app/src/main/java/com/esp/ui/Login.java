package com.esp.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.esp.constants.G;
import com.esp.request.RequestService;
import com.esp.util.StringUtil;

import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity {

    private EditText loginField, passwordField;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginField = (EditText) findViewById(R.id.loginField);
        loginField.setText("eduardo.delito@gmail.com");
        passwordField = (EditText) findViewById(R.id.passwordField);
        passwordField.setText("123456");
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait, Logging in...");
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(false);

//        RequestService.getCountryCode(this, progressDialog, "http://ip-api.com/json");
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginBtn:
                String userName = loginField.getText().toString();
                String password = passwordField.getText().toString();

                if (!StringUtil.isNullOrEmpty(userName) &&
                        !StringUtil.isNullOrEmpty(password)) {
                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put("emailAddr", userName);
                        jsonObject.put("password", password);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    RequestService.makePost(true, this, progressDialog, G.BASE_URL + G.LOGIN_URL, jsonObject, mHandler);
                } else {
                    Toast.makeText(this, "Username and Password cannot be empty!", Toast.LENGTH_LONG).show();
                }

                break;
            case R.id.forgotPasswordBtn:
                Intent forgotIntent = new Intent(Login.this, Forgot.class);
                startActivity(forgotIntent);
                break;
            case R.id.signUpBtn:
                Intent regIntent = new Intent(Login.this, Registration.class);
                startActivity(regIntent);
                break;
            default:
                break;
        }
    }


    Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                successAlert();
                Intent homeIntent = new Intent(Login.this, Main.class);
                startActivity(homeIntent);
            } else if (msg.what == 1) {
                successAlert();
            }
        }
    };

    private void successAlert() {
        loginField.setText("");
        passwordField.setText("");
    }
}
