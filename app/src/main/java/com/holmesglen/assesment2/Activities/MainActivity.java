/**
 * This is a MainActivity class that works as a Welcome Page
 * @author Jerzy_Serwiak
 * @version 1.0
 *
 */

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
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.holmesglen.assesment2.Database.PhonebookDb;
import com.holmesglen.assesment2.Lib.MyHash;
import com.holmesglen.assesment2.Models.Contact;
import com.holmesglen.assesment2.R;

import java.util.ArrayList;
import java.util.List;

import maes.tech.intentanim.CustomIntent;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Path;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener, RetrofitServices.ResultsHandler {

    private static final String TAG = "";
    private GestureDetectorCompat detector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_page);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.111:5000/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RemoteContactDB service = retrofit.create(RemoteContactDB.class);

        //Calling the API to display a contact by id
        Call<Contact> display = service.Contact(9);
        display.enqueue(new Callback<Contact>() {
            private static final String TAG = "";

            //Call API - success
            @Override
            public void onResponse(Call<Contact> call, Response<Contact> response) {
                Contact contact = response.body();
                Log.d(TAG, contact.toString());
                return;
            }
            //Call API - failed
            @Override
            public void onFailure(Call<Contact> call, Throwable t) {
                Log.d(TAG, "onFailure" + t.getCause());
                return;
            }
        });
        //contactList = (ArrayList<Contact>)PhonebookDb.getDBInstance(getApplicationContext()).contactDao().getAllContacts();
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
        findViewById(R.id.btn_update_Database).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //getting a contact details by ID from the API
                Call<Contact> display = service.Contact(1);
                display.enqueue(new Callback<Contact>() {
                    private static final String TAG = "";

                    //Call API - success
                    @Override
                    public void onResponse(Call<Contact> call, Response<Contact> response) {
                        Contact contact = response.body();
                        Log.d(TAG, contact.toString());
                        Toast.makeText(getApplicationContext(), "Details from API: "
                                 + contact.toString()
                                ,Toast.LENGTH_SHORT).show();
                        return;
                    }
                    //Call API - failed
                    @Override
                    public void onFailure(Call<Contact> call, Throwable t) {
                        Log.d(TAG, "onFailure" + t.getCause());
                        return;
                    }
                });
            }
        });
    }
    //GestureDetector.OnGestureListener methods implementation
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

    // RetrofitServices methods implementation
    @Override
    public void CreateOnResponseHandler(Contact contact) {

    }

    @Override
    public void ReadOneOnResponseHandler(Contact contact) {

    }

    @Override
    public void ReadAllOnResponseHandler(List<Contact> contactList) {

    }

    @Override
    public void UpdateOnResponseHandler() {

    }

    @Override
    public void DeleteOnResponseHandler(Contact contact) {

    }

    @Override
    public void OnFailureHandler() {

    }

}


