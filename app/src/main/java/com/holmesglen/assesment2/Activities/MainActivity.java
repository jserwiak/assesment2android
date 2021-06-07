package com.holmesglen.assesment2.Activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;
import androidx.core.view.GestureDetectorCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.holmesglen.assesment2.Database.PhonebookDb;
import com.holmesglen.assesment2.Lib.MyHash;
import com.holmesglen.assesment2.Models.Contact;
import com.holmesglen.assesment2.R;

import java.util.ArrayList;

import maes.tech.intentanim.CustomIntent;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {

    private GestureDetectorCompat detector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_page);

        detector = new GestureDetectorCompat(this, this);

        SharedPreferences mPrefs = getSharedPreferences("THEME", 0);
        final boolean[] theme_boolean = {mPrefs.getBoolean("theme_boolean", true)};
        if (theme_boolean[0]) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        findViewById(R.id.btnDark).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                if (theme_boolean[0]) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    theme_boolean[0] = false;
                }
                SharedPreferences mPrefs = getSharedPreferences("THEME", 0);
                SharedPreferences.Editor mEditor = mPrefs.edit();
                mEditor.putBoolean("theme_boolean", theme_boolean[0]).commit();
            }
        });
        findViewById(R.id.btnLight).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                if (!theme_boolean[0]) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    theme_boolean[0] = true;
                }
                SharedPreferences mPrefs = getSharedPreferences("THEME", 0);
                SharedPreferences.Editor mEditor = mPrefs.edit();
                mEditor.putBoolean("theme_boolean", theme_boolean[0]).commit();
            }
        });
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }
    private static final int SWIPE_MIN_DISTANCE = 100;
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        if(e1.getX() - e2.getX()>SWIPE_MIN_DISTANCE) {
            Intent intent = new Intent(MainActivity.this, ListPageActivity.class);
            startActivity(intent);

            CustomIntent.customType(MainActivity.this, "left-to-right");
            return true;
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        detector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
}


