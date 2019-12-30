package com.example.ecohomepro;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;

import ernestoyaquello.com.verticalstepperform.Step;

public class AddressStep extends Step<String> {
    private EditText CityView;
    private EditText BuildingView;
    private EditText StreetView;

    public AddressStep(String stepTitle) {
        super(stepTitle);
    }

    @Override
    protected View createStepContentLayout() {
        // Here we generate the view that will be used by the library as the content of the step.
        // In this case we do it programmatically, but we could also do it by inflating an XML layout.
        CityView = new EditText(getContext());
        CityView.setSingleLine(true);
        CityView.setHint("City");
        StreetView = new EditText(getContext());
        StreetView.setSingleLine(true);
        StreetView.setHint("Street");

        BuildingView = new EditText(getContext());
        BuildingView.setSingleLine(true);
        BuildingView.setHint("Building");
//        markAsCompletedOrUncompleted(true);


        CityView.addTextChangedListener(new TextWatcher() {
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
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.addView(CityView);
        linearLayout.addView(StreetView);
        linearLayout.addView(BuildingView);


        return linearLayout;

    }

    @Override
    protected IsDataValid isStepDataValid(String stepData) {
        boolean isNumberValid = true;
        String errorMessage = !isNumberValid ? "" : "";
        return new IsDataValid(isNumberValid, errorMessage);
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

    @NonNull
    @Override
    public String toString() {
        return "" + CityView.getText().toString() + " , " + StreetView.getText().toString() + " , " + BuildingView.getText().toString();
    }
}