package com.nexus.communicator.UI;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.nexus.communicator.R;
import com.nexus.communicator.UI.Fragments.DashboardFragment;
import com.nexus.communicator.UI.Fragments.HomeFragment;
import com.nexus.communicator.UI.Fragments.SettingsFragment;

public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";

    private Toolbar mToolbar;
    private TextView mToolbarTitle;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    private void setupToolbar() {
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mToolbarTitle = findViewById(R.id.toolbar_title);
        mToolbarTitle.setText(getString(R.string.actionbar_title));
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Log.d(TAG,"onNavigationItemSelected " + item.getItemId());
            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    selectedFragment = HomeFragment.newInstance();
                    getSupportFragmentManager().beginTransaction().replace(R.id.container_main, selectedFragment).commit();
                    break;
                case R.id.navigation_dashboard:
                    selectedFragment = DashboardFragment.newInstance();
                    getSupportFragmentManager().beginTransaction().replace(R.id.container_main, selectedFragment).commit();
                    break;
                case R.id.navigation_settings:
                    selectedFragment = SettingsFragment.newInstance();
                    getSupportFragmentManager().beginTransaction().replace(R.id.container_main, selectedFragment).commit();
                    break;
            }
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG,"onCreate ");
        setupToolbar();

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }
}
