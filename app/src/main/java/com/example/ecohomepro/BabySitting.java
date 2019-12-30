package com.example.ecohomepro;

import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CalendarView;
import android.widget.NumberPicker;
import android.widget.Switch;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

public class BabySitting extends AppCompatActivity {
    private static final int SWIPE_MIN_DISTANCE = 100;
    private static final int SWIPE_MAX_OFF_PATH = 100;
    private static final int SWIPE_THRESHOLD_VELOCITY = 100;
    int step = 1;
    private GestureDetector gestureDetector;
    View.OnTouchListener gestureListener;
    NumberPicker children;
    TimePicker from, to;
    Switch useToys;
    CalendarView date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baby_sitting);
        children = findViewById(R.id.occ3);
        from = findViewById(R.id.fromtime);
        to = findViewById(R.id.totime);
        useToys = findViewById(R.id.toysswitch);
        date = findViewById(R.id.date);
        findViewById(R.id.done).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), request.class);
                i.putExtra("service", "babysitting");

                startActivity(i);
            }
        });

        gestureDetector = new GestureDetector(this, new MyGestureDetector());
        gestureListener = new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }
        };
        findViewById(R.id.children).setVisibility(View.VISIBLE);
        findViewById(R.id.from).setVisibility(View.GONE);
        findViewById(R.id.to).setVisibility(View.GONE);
        findViewById(R.id.toys).setVisibility(View.GONE);

        findViewById(R.id.babySit).setOnTouchListener(gestureListener);

        NumberPicker picker1 = findViewById(R.id.occ3);
        picker1.setMaxValue(6);
        picker1.setMinValue(1);
        String pickerVals[] = new String[]{"1", "2", "3", "4", "5", "More than 5"

        };
        picker1.setDisplayedValues(pickerVals);
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
                        findViewById(R.id.children).setVisibility(View.GONE);
                        findViewById(R.id.to).setVisibility(View.GONE);
                        findViewById(R.id.toys).setVisibility(View.GONE);
                        findViewById(R.id.from).setVisibility(View.VISIBLE);
                        step = 2;
                    } else if (step == 2) {
                        findViewById(R.id.children).setVisibility(View.GONE);
                        findViewById(R.id.from).setVisibility(View.GONE);
                        findViewById(R.id.to).setVisibility(View.VISIBLE);
                        findViewById(R.id.toys).setVisibility(View.GONE);

                        step = 3;
                    } else if (step == 3) {
                        findViewById(R.id.to).setVisibility(View.GONE);
                        findViewById(R.id.from).setVisibility(View.GONE);
                        findViewById(R.id.toys).setVisibility(View.VISIBLE);
                        findViewById(R.id.children).setVisibility(View.GONE);
                        step = 4;
                    } else if (step == 4) {
                        Intent i = new Intent(getApplicationContext(), request.class);
                        i.putExtra("service", "babySitting");
                        i.putExtra("info", getInfo());
                        startActivity(i);
                    }
                } else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    if (step == 2) {
                        findViewById(R.id.to).setVisibility(View.GONE);
                        findViewById(R.id.from).setVisibility(View.GONE);
                        findViewById(R.id.toys).setVisibility(View.GONE);
                        findViewById(R.id.children).setVisibility(View.VISIBLE);
                        step = 1;
                    }
                    if (step == 3) {
                        findViewById(R.id.to).setVisibility(View.GONE);
                        findViewById(R.id.toys).setVisibility(View.GONE);
                        findViewById(R.id.children).setVisibility(View.GONE);
                        findViewById(R.id.from).setVisibility(View.VISIBLE);
                        step = 2;
                    }
                    if (step == 4) {
                        findViewById(R.id.from).setVisibility(View.GONE);
                        findViewById(R.id.toys).setVisibility(View.GONE);
                        findViewById(R.id.children).setVisibility(View.GONE);
                        findViewById(R.id.to).setVisibility(View.VISIBLE);
                        step = 3;
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

    public String getInfo() {
        return "" + (

                "Number of children" + children.getValue() +
                        " \nDate:" + date.getDate() +
                        " \nFrom: " + from.getHour() + ":" + from.getMinute() +
                        "\n To: " + to.getHour() + ":" + to.getMinute() +
                        ((useToys.isActivated()) ? ", \nbring toys" : "\n Don't bring touys")

        );
    }

}

