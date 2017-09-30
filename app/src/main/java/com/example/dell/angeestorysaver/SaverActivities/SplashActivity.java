package com.example.dell.angeestorysaver.SaverActivities;

import Helper.SharedPref;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import com.example.dell.angeestorysaver.R;

/**
 * Created by emmanuel on 27/09/2017.
 */

public class SplashActivity extends AppCompatActivity{
    private static final long DELAY = 2000;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        writeIntoSharedPref(getApplicationContext());

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
            }
        }, DELAY);
    }

    private void writeIntoSharedPref(Context applicationContext) {
        SharedPref.writeIntoSharedPref(applicationContext, "Entered");
    }

}
