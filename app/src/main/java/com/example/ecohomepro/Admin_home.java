package com.example.ecohomepro;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Admin_home extends AppCompatActivity {

    private void setFragment(Fragment fragment) {
        FragmentTransaction tr = getSupportFragmentManager().beginTransaction();
        tr.replace(R.id.frameLayout, fragment);
        tr.commit();
    }

    private BottomNavigationView bottomNavigationView;
    private FrameLayout frameLayout;
    private Fragment f1, f2, f3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        frameLayout = findViewById(R.id.frameLayout);
        bottomNavigationView = findViewById(R.id.navigation);
        f1 = new TabOne();
        f2 = new TabTow();
        f3 = new TabThree();
        setFragment(f1);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.item1:
                        setFragment(f1);
                        return true;
                    case R.id.item2:
                        setFragment(f2);
                        return true;
                    case R.id.item3:
                        setFragment(f3);
                        return true;
                    default:
                        return false;


                }
            }
        });
    }
}
