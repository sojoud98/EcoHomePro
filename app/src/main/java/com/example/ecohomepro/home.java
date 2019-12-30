package com.example.ecohomepro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


    }

    public void clean(View view) {
      Intent i = new Intent(getApplicationContext(), cleaning.class);
        startActivity(i);
    }

    public void cooking(View view) {
        Intent i = new Intent(getApplicationContext(), cooking.class);
        startActivity(i);
    }

    public void babySitting(View view) {
        Intent i = new Intent(getApplicationContext(), BabySitting.class);
        startActivity(i);
    }

}
