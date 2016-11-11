package com.esp.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.esp.constants.G;
import com.esp.util.StringUtil;

public class CreateEventName extends AppCompatActivity {

    private EditText eventNameText;
    private TextView saveEventTextBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event_name);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Event Name");

        eventNameText = (EditText) findViewById(R.id.eventNameTextId);
        if (!StringUtil.isNullOrEmpty(G.EVENT_NAME_VALUE)) {
            eventNameText.setText(G.EVENT_NAME_VALUE);
            eventNameText.setSelection(eventNameText.getText().length());
        }
        saveEventTextBtn = (TextView) findViewById(R.id.saveEventTextBtnId);
        saveEventTextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!StringUtil.isNullOrEmpty(eventNameText.getText().toString())) {
                    showAlert(CreateEventName.this, "Save", "Are you sure you want to save " + eventNameText.getText().toString() + "?");

                } else {
                    Toast.makeText(CreateEventName.this, "Event name cannot be empty!", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showAlert(Context context, String title, String message) {
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        G.EVENT_NAME_VALUE = eventNameText.getText().toString();
                        finish();
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

            }
        })
                .show();
    }
}
