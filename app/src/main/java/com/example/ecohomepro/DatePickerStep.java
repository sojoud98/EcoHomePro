package com.example.ecohomepro;

import android.view.View;
import android.widget.DatePicker;

import ernestoyaquello.com.verticalstepperform.Step;

public class DatePickerStep extends Step<String> {
    DatePicker datePicker;
    static int day = 1, month = 1, year = 2020;

    public DatePickerStep(String stepTitle) {
        super(stepTitle);
    }

    @Override
    protected View createStepContentLayout() {
        // Here we generate the view that will be used by the library as the content of the step.
        // In this case we do it programmatically, but we could also do it by inflating an XML layout.
        datePicker = new DatePicker(getContext());
        datePicker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                markAsCompletedOrUncompleted(true);

            }
        });
        return datePicker;

    }

    @Override
    protected IsDataValid isStepDataValid(String stepData) {
        boolean isNumberValid = stepData.length() >= 10;
        String errorMessage = !isNumberValid ? "" : "";
        return new IsDataValid(true, errorMessage);
    }

    @Override
    public String getStepData() {
//        Editable userName = MobileView.getText();
//        return userName != null ? userName.toString() : "";
        return "";
    }

    @Override
    public String getStepDataAsHumanReadableString() {
//        String Number = getStepData();
//        return !Number.isEmpty() ? Number : "(Empty)";
        return "";
    }

    @Override
    protected void onStepOpened(boolean animated) {
    }

    @Override
    protected void onStepClosed(boolean animated) {
        day = datePicker.getDayOfMonth();
        month = datePicker.getMonth();
        year = datePicker.getYear();
    }

    @Override
    protected void onStepMarkedAsCompleted(boolean animated) {
    }

    @Override
    protected void onStepMarkedAsUncompleted(boolean animated) {
    }

    @Override
    public void restoreStepData(String stepData) {
//        MobileView.setText(stepData);
    }

    public  int getyear() {
        return datePicker.getYear();
    }

    public  int getMonth() {
        return datePicker.getMonth();
    }

    public  int getday() {
        return datePicker.getDayOfMonth();
    }

}