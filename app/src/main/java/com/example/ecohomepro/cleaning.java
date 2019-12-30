package com.example.ecohomepro;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class cleaning extends AppCompatActivity {
    private NumberPicker picker1, occasion;
    Switch usePro;

    private static final int SWIPE_MIN_DISTANCE = 100;
    private static final int SWIPE_MAX_OFF_PATH = 100;
    private static final int SWIPE_THRESHOLD_VELOCITY = 100;
    int step = 1;
    private GestureDetector gestureDetector;
    View.OnTouchListener gestureListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cleaning);
        gestureDetector = new GestureDetector(this, new MyGestureDetector());
        gestureListener = new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }
        };
        findViewById(R.id.step1).setVisibility(View.VISIBLE);
        findViewById(R.id.step2).setVisibility(View.GONE);
        findViewById(R.id.step3).setVisibility(View.GONE);

        findViewById(R.id.cleaning).setOnTouchListener(gestureListener);


        picker1 = findViewById(R.id.occ1);
        occasion = findViewById(R.id.occ);
        usePro = findViewById(R.id.proswitch);
        picker1.setMaxValue(10);
        picker1.setMinValue(1);
        String pickerVals[] = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        picker1.setDisplayedValues(pickerVals);
        NumberPicker picker = findViewById(R.id.occ);
        picker.setMinValue(1);
        picker.setMaxValue(4);
        picker.setDisplayedValues(new String[]{"Regular cleaning",
                "Move in/out", "After a party",
                "Before a party"});
    }

    class MyGestureDetector extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            try {
                if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH)
                    return false;
                // right to left swipe
                if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {

                    if (step == 1) {
                        findViewById(R.id.step1).setVisibility(View.GONE);
                        findViewById(R.id.step3).setVisibility(View.GONE);
                        findViewById(R.id.step2).setVisibility(View.VISIBLE);
                        step = 2;
                    } else if (step == 2) {
                        findViewById(R.id.step1).setVisibility(View.GONE);
                        findViewById(R.id.step2).setVisibility(View.GONE);
                        findViewById(R.id.step3).setVisibility(View.VISIBLE);
                        step = 3;
                    } else if (step == 3) {
                        Intent i = new Intent(getApplicationContext(), request.class);
                        i.putExtra("service", "cleaning");
                        Log.d("test", "onFling: ------------");
                        i.putExtra("info", getInfo());
                        startActivity(i);
                    }

                } else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    if (step == 2) {
                        findViewById(R.id.step2).setVisibility(View.GONE);
                        findViewById(R.id.step3).setVisibility(View.GONE);
                        findViewById(R.id.step1).setVisibility(View.VISIBLE);
                        step = 1;
                    }
                    if (step == 3) {
                        findViewById(R.id.step3).setVisibility(View.GONE);
                        findViewById(R.id.step1).setVisibility(View.GONE);
                        findViewById(R.id.step2).setVisibility(View.VISIBLE);
                        step = 2;
                    }
                }
            } catch (Exception e) {
                // nothing
            }
            return false;
        }

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }
    }

    @NonNull
    public String getInfo() {
        String occ = "";
        switch (occasion.getValue()) {

            case 1:
                occ = "Regular cleaning";
                break;
            case 2:
                occ = "Move in/out";
                break;

            case 3:
                occ = "After a party";

                break;
            case 4:
                occ = "Before a party";

                break;
            default:
                break;
        }

        return "" + ("Number of room: " + picker1.getValue() +
                "\n occasion: " + occ
                +
                ((usePro.isActivated()) ? "\n Use ech products" : "\n No eco products")
        );
    }
}


