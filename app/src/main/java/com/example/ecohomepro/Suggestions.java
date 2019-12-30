package com.example.ecohomepro;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

public class Suggestions extends AppCompatActivity {
    TextView sugg,num;
    SQLiteDatabase db;
    ImageButton send;
    TextView res;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.suggestiona);
        init();

        num=findViewById(R.id.number);
        sugg=findViewById(R.id.sugg);
        send=findViewById(R.id.sendbtn);
         res = findViewById(R.id.res);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = new DataBase(getApplicationContext()).getWritableDatabase();

                ContentValues vals =new ContentValues();
                vals.put("suggestion",sugg.getText().toString());
                vals.put("sender",num.getText().toString());
              Long id=  db.insert("suggestions",null,vals);
                Log.d("test", "onClick: "+id);
                res.setText("Your message was sent, thank you!");

            }
        });

    }

    private void init() {
        setUpToolbar();
    }

    private void setUpToolbar() {
        final Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Suggestions");

        }
    }
}