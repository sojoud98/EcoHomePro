package com.example.ecohomepro;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import ernestoyaquello.com.verticalstepperform.VerticalStepperFormView;
import ernestoyaquello.com.verticalstepperform.listener.StepperFormListener;

public class request extends AppCompatActivity implements StepperFormListener {
    TextView res;
    private NoteStep noteStep;
    private MobileNumberStep mobileNumberStepStep;
    private DatePickerStep DateStep;
    private TimePickerStep TimeStep;
    private AddressStep addressStep;
    private VerticalStepperFormView verticalStepperForm;
    String service, info;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new DataBase(getApplicationContext()).getWritableDatabase();

        setContentView(R.layout.activity_request);
        res = findViewById(R.id.res);
        Intent i = getIntent();
        service = i.getStringExtra("service");
        info = i.getStringExtra("info");
        noteStep = new NoteStep("Notes");
        mobileNumberStepStep = new MobileNumberStep("Mobile Number");
        DateStep = new DatePickerStep("Select Date");
        TimeStep = new TimePickerStep("Select Time");
        addressStep = new AddressStep("Address");
        verticalStepperForm = findViewById(R.id.stepper_form);
        verticalStepperForm.setup(this,
                mobileNumberStepStep,
                DateStep, TimeStep, addressStep, noteStep)
                .init();
    }


    @Override
    public void onCompletedForm() {
        String note = noteStep.getNote();
        String mobile = mobileNumberStepStep.getMobile();
        int day = DateStep.getday();
        int year = DateStep.getyear();
        int month = DateStep.getMonth();
        int hours = TimeStep.getHours();
        int minutes = TimeStep.getMinutes();
        String address = addressStep.toString();

        ContentValues vals = new ContentValues();
        vals.put("mobile", mobile);
        vals.put("service", service);
        vals.put("address", address);
        vals.put("note", note);
        vals.put("info", info);
        vals.put("date", "" + day + "- " + month + "- " + year);
        vals.put("time", hours + ":" + minutes);
        db.insert("request", null, vals);
        res.setText("Your request has been sent, we will get back to you soon");

    }

    @Override
    public void onCancelledForm() {

    }

}
