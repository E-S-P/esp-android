package com.esp.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.esp.helper.EmailValidator;
import com.esp.util.StringUtil;

public class Forgot extends AppCompatActivity {

    private EditText forgotField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Forgot Password");

        forgotField = (EditText) findViewById(R.id.emailForgotField);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.submitEmailBtn:
                if (!StringUtil.isNullOrEmpty(forgotField.getText().toString())) {
                    if(EmailValidator.isValidEmail(forgotField.getText().toString())){
                        Toast.makeText(Forgot.this, "Coming soon!", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(Forgot.this, "Invalid email!", Toast.LENGTH_LONG).show();
                    }
                }
                break;
            default:
                break;
        }
    }
}
