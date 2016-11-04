package com.esp.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.esp.constants.G;
import com.esp.fragment.DatePickerFragment;
import com.esp.model.CountryDetails;
import com.esp.request.RequestService;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Locale;
import java.util.StringTokenizer;

public class Registration extends AppCompatActivity {

    private EditText fNameField, lNameField, userNameField, emailField, passwordField, confirmPasswordField;
    private TextView dobTitle, datePicker, addressTitle;
    private RadioGroup genderRadioGroup;
    private RadioButton male, female;
    private SearchableSpinner countryCodeSpinner;
    private EditText mobileNumberField, cityField, stateField, zipField, countryField;
    private ProgressDialog progressDialog;
    private DialogFragment dateFragment;
    private boolean isFirstLoad = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrations);
        isFirstLoad = true;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Register");

        fNameField = (EditText) findViewById(R.id.firstNameField);
        lNameField = (EditText) findViewById(R.id.lastNameField);
        userNameField = (EditText) findViewById(R.id.userNameField);
        emailField = (EditText) findViewById(R.id.emailField);
        passwordField = (EditText) findViewById(R.id.passwordField);
        confirmPasswordField = (EditText) findViewById(R.id.confirmPasswordField);

        dobTitle = (TextView) findViewById(R.id.dobTitleId);
        datePicker = (TextView) findViewById(R.id.datePicker);

        genderRadioGroup = (RadioGroup) findViewById(R.id.myRadioGroupId);


        final String[] countryCode = getResources().getStringArray(R.array.CountryCodes);

        countryCodeSpinner = (SearchableSpinner) findViewById(R.id.countryCodeSpinner);
        countryCodeSpinner.setTitle("Select Country");
        countryCodeSpinner.setPositiveButton("OK");
        countryCodeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (isFirstLoad) {
                    isFirstLoad = false;
                    for (int j = 0; j < countryCode.length; j++) {
                        StringTokenizer st = new StringTokenizer(countryCode[j], ",");
                        while (st.hasMoreTokens()) {
                            String a = st.nextToken();
                            if (a.toUpperCase().trim().equals(G.CODE.toUpperCase().trim())) {
                                countryCodeSpinner.setSelection(j);
                            }
                            st.nextToken();
                        }
                    }
                }
                String countryDetails = countryCode[i].toString();
                ((TextView) view).setText("+"+countryDetails.substring(3, countryDetails.length()));

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, countryCode);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        countryCodeSpinner.setAdapter(dataAdapter);
        mobileNumberField = (EditText) findViewById(R.id.mobileNumberField);
        addressTitle = (TextView) findViewById(R.id.addressTitle);

        cityField = (EditText) findViewById(R.id.cityField);
        stateField = (EditText) findViewById(R.id.stateField);
        zipField = (EditText) findViewById(R.id.zipField);
        countryField = (EditText) findViewById(R.id.countryField);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait, Registering...");
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(false);


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
            case R.id.registerBtnId:
                String firstName = fNameField.getText().toString();
                String lastName = lNameField.getText().toString();
                String userName = userNameField.getText().toString();
                String email = emailField.getText().toString();
                String password = passwordField.getText().toString();
                String confirmPassword = confirmPasswordField.getText().toString();
//                String dob = dobMonth.getText().toString() + "/" + dobDay.getText().toString() + "/" + dobYear.getText().toString();
                String dob = datePicker.getText().toString();
                int selectedId = genderRadioGroup.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton) findViewById(selectedId);
                String gender = radioButton.getText().toString();
//                String phone = mobileCode.getText().toString() + mobileNumberField.getText().toString();
                String city = cityField.getText().toString();
                String state = stateField.getText().toString();
                String zipCode = zipField.getText().toString();
                String country = countryField.getText().toString();


                JSONObject obj = new JSONObject();
                try {
                    obj.put("firstName", firstName);
                    obj.put("lastName", lastName);
                    obj.put("emailAddr", email);
                    obj.put("username", userName);
                    obj.put("password", password);
                    obj.put("confirmPassword", confirmPassword);
                    obj.put("dOb", dob);
                    obj.put("gender", gender);
                    obj.put("phone", countryCodeSpinner.getSelectedItem().toString());
                    obj.put("address", "NA");
                    obj.put("city", city);
                    obj.put("state", state);
                    obj.put("zipCode", zipCode);
                    obj.put("country", country);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                RequestService.makePost(this, progressDialog, G.BASE_URL + G.REGISTRATION_URL, obj, mHandler);
                break;
            case R.id.datePicker:
                dateFragment = new DatePickerFragment();
                dateFragment.show(getSupportFragmentManager(), "datePicker");
                break;
            default:
                break;
        }
    }

    Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                successAlert();
                finish();
            } else if (msg.what == 1) {
                successAlert();
            }
        }
    };


    private void successAlert() {
        fNameField.setText("");
        lNameField.setText("");
        userNameField.setText("");
        emailField.setText("");
        passwordField.setText("");
        confirmPasswordField.setText("");
//        mobileCode
        datePicker.setText("YYYY/MM/DD");
        RadioButton radioButtonMale = (RadioButton) findViewById(R.id.male);
        radioButtonMale.setChecked(true);
        RadioButton radioButtonFemale = (RadioButton) findViewById(R.id.female);
        radioButtonFemale.setChecked(false);
        mobileNumberField.setText("");
        cityField.setText("");
        stateField.setText("");
        zipField.setText("");
        countryField.setText("");

    }

}