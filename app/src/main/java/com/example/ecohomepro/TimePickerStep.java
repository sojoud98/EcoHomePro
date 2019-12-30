package com.example.ecohomepro;
import android.view.View;
import android.widget.TimePicker;

import ernestoyaquello.com.verticalstepperform.Step;

public class TimePickerStep extends Step<String> {
       TimePicker timePicker;
      int hour=1,minutes=30;

    public TimePickerStep(String stepTitle) {
        super(stepTitle);
    }

    @Override
    protected View createStepContentLayout() {
        // Here we generate the view that will be used by the library as the content of the step.
        // In this case we do it programmatically, but we could also do it by inflating an XML layout.
        timePicker= new TimePicker(getContext());
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                markAsCompletedOrUncompleted(true);

            }
        });
//

        return  timePicker;

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
    protected  void onStepClosed(boolean animated) {
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
    public  int getHours(){
        return timePicker.getHour();
    }
    public  int getMinutes(){
        return timePicker.getMinute();
    }
}