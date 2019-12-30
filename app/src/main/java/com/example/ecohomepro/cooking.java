package com.example.ecohomepro;

import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class cooking extends AppCompatActivity {
    EditText food;
    Switch useEco;
    NumberPicker picker1;

    private static final int SWIPE_MIN_DISTANCE = 100;
    private static final int SWIPE_MAX_OFF_PATH = 100;
    private static final int SWIPE_THRESHOLD_VELOCITY = 100;
    int step = 1;
    private GestureDetector gestureDetector;
    View.OnTouchListener gestureListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cooking);



        useEco=findViewById(R.id.prodswitch);
        gestureDetector = new GestureDetector(this, new MyGestureDetector());
        gestureListener = new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }
        };
        findViewById(R.id.linearLayout2).setVisibility(View.VISIBLE);
        findViewById(R.id.prod).setVisibility(View.GONE);
        findViewById(R.id.number).setVisibility(View.GONE);

        findViewById(R.id.profile_photo).setOnTouchListener(gestureListener);

        food = findViewById(R.id.food);
//        others.setEnabled(false);
        picker1 = findViewById(R.id.occ2);
        picker1.setMaxValue(24);
        picker1.setMinValue(1);
        String pickerVals[] = new String[]
                {"1", "2", "3", "4", "5",
                "6", "7", "8", "9", "10",
                "11", "12", "13", "14", "15",
                        "16", "17", "18", "19", "20",
                "20 to 30",
                "30 to 40",
                "40 to 50"
                , "More than 50"


        };
        picker1.setDisplayedValues(pickerVals);
//
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
                        findViewById(R.id.linearLayout2).setVisibility(View.GONE);
                        findViewById(R.id.prod).setVisibility(View.GONE);
                        findViewById(R.id.number).setVisibility(View.VISIBLE);
                        step = 2;
                    } else if (step == 2) {
                        findViewById(R.id.linearLayout2).setVisibility(View.GONE);
                        findViewById(R.id.number).setVisibility(View.GONE);
                        findViewById(R.id.prod).setVisibility(View.VISIBLE);
                        step = 3;
                    } else if (step == 3) {
                        Intent i = new Intent(getApplicationContext(), request.class);
                        i.putExtra("service", "cooking");
                       i.putExtra("info",getInfo());
                       startActivity(i);
                    }


                } else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    if (step == 2) {
                        findViewById(R.id.linearLayout2).setVisibility(View.VISIBLE);
                        findViewById(R.id.number).setVisibility(View.GONE);
                        findViewById(R.id.prod).setVisibility(View.GONE);
                        step = 1;
                    }
                    if (step == 3) {
                        findViewById(R.id.prod).setVisibility(View.GONE);
                        findViewById(R.id.linearLayout2).setVisibility(View.GONE);
                        findViewById(R.id.number).setVisibility(View.VISIBLE);
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
        return "Number of People : "+picker1.getValue()+
               "\n "+ ((useEco.isActivated()) ? "Use ech friendly products" : " No ech friendly products"+
                        "\n food"+food.getText().toString()
                        );
    }
}
