package com.example.ecohomepro;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import ernestoyaquello.com.verticalstepperform.Step;

public class MobileNumberStep extends Step<String> {
//private Switch switcher;
    private EditText MobileView;
    static String mobile="";

    public MobileNumberStep(String stepTitle) {
        super(stepTitle);
    }

    @Override
    protected View createStepContentLayout() {
        // Here we generate the view that will be used by the library as the content of the step.
        // In this case we do it programmatically, but we could also do it by inflating an XML layout.
        MobileView = new EditText(getContext());
        MobileView.setSingleLine(true);
//        switcher=new Switch(getContext());
//        switcher.setText("Use default phone number");
        MobileView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
               markAsCompletedOrUncompleted(true);
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        LinearLayout linearLayout=new LinearLayout(getContext());
        linearLayout.setOrientation(LinearLayout.VERTICAL);
//        linearLayout.addView(switcher);
        linearLayout.addView(MobileView);


        return  linearLayout;

    }

    @Override
    protected IsDataValid isStepDataValid(String stepData) {
        boolean isNumberValid = stepData.length() >= 10;
        String errorMessage = !isNumberValid ? "please enter a valid phone number" : "";
        return new IsDataValid(isNumberValid, errorMessage);
    }

    @Override
    public String getStepData() {
        Editable userName = MobileView.getText();
        return userName != null ? userName.toString() : "";
    }

    @Override
    public String getStepDataAsHumanReadableString() {
       String Number = getStepData();
        return !Number.isEmpty() ? Number : "(Empty)";
    }

    @Override
    protected void onStepOpened(boolean animated) {
    }

    @Override
    protected void onStepClosed(boolean animated) {
        mobile=MobileView.getText().toString();
    }

    @Override
    protected void onStepMarkedAsCompleted(boolean animated) {
    }

    @Override
    protected void onStepMarkedAsUncompleted(boolean animated) {
    }

    @Override
    public void restoreStepData(String stepData) {
        MobileView.setText(stepData);
    }

    public  String getMobile() {

        return MobileView.getText().toString();
    }
}
