package com.example.ecohomepro;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class TestDrawer extends AppCompatActivity {

    private final static String sKEY_ACTIONBAR_TITLE = "actionBarTitle";
    private static final int sDELAY_MILLIS = 250;

    private Toolbar mToolbar;
    private Context mContext;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_drawer);
        init(savedInstanceState);
    }

    private void init(@Nullable final Bundle savedInstanceState) {
        bindResources();
        setUpToolbar();
        setUpDrawer();
        restoreState(savedInstanceState);
    }

    private void bindResources() {
        mContext = this;
        mToolbar = findViewById(R.id.toolbar);
        mDrawerLayout =  findViewById(R.id.main_activity_DrawerLayout);
        mNavigationView =  findViewById(R.id.activity_main_navigation_view);
    }

    private void setUpToolbar() {
        setSupportActionBar(mToolbar);

        final ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.menu);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void setUpDrawer() {
        mNavigationView
                .getHeaderView(0)
                .findViewById(R.id.navigation_drawer_header_clickable)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mDrawerLayout.closeDrawer(GravityCompat.START);
                        startActivityWithDelay(TestDrawer.class);
                    }
                });

        mNavigationView.setNavigationItemSelectedListener
                (
                        new NavigationView.OnNavigationItemSelectedListener() {
                            @Override
                            public boolean onNavigationItemSelected(final MenuItem item) {
                                mDrawerLayout.closeDrawer(GravityCompat.START);

                                switch (item.getItemId()) {

                                    case R.id.navigation_view_item_help:

                                        startActivityWithDelay(Suggestions.class);
                                        break;

                                    case R.id.navigation_view_item_about:
                                        startActivityWithDelay(AboutActivity.class);
                                        break;
                                }

                                return true;
                            }
                        }
                );
    }

    private void restoreState(final @Nullable Bundle savedInstanceState) {
        // This allow us to know if the activity was recreated
        // after orientation change and restore the Toolbar title
        if (savedInstanceState == null) {
            showDefaultFragment();
        } else {
            setToolbarTitle((String) savedInstanceState.getCharSequence(sKEY_ACTIONBAR_TITLE));
        }
    }

    private void showDefaultFragment() {
        setToolbarTitle("EcoHome");
    }



    private void setToolbarTitle( final String title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }

    private String getToolbarTitle() {
        if (getSupportActionBar() != null) {
            return (String) getSupportActionBar().getTitle();
        }

        return getString(R.string.app_name);
    }

    private void setToolbarTitle(@StringRes final int string) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(string);
        }
    }

    /**
     * We start this activities with delay to avoid junk while closing the drawer
     */
    private void startActivityWithDelay(@NonNull final Class activity) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(mContext, activity));
            }
        }, sDELAY_MILLIS);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(final Bundle outState) {
        outState.putCharSequence(sKEY_ACTIONBAR_TITLE, getToolbarTitle());
        super.onSaveInstanceState(outState);
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