package com.example.sandeepbhambad.easysolutiondemo.activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.sandeepbhambad.easysolutiondemo.R;

public class Feedback extends ActionBarActivity {

    private Toolbar mtoolbar;
    private EditText edittext;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        mtoolbar = (Toolbar) findViewById(R.id.toolbar);
        edittext = (EditText) findViewById(R.id.feedback_textare);
        button = (Button) findViewById(R.id.submit);
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String editText = edittext.getText().toString();

                if (editText.length() == 0) {

                    edittext.setError("Field cannot be left blank.");
                } else {

                    edittext.setText("");


                }


            }
        });

    }


}
