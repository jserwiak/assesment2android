/**
 * This is a AddActivity class for adding new contacts
 * @author Jerzy_Serwiak
 * @version 1.0
 *
 */

package com.holmesglen.assesment2.Activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.holmesglen.assesment2.Database.PhonebookDb;
import com.holmesglen.assesment2.Models.Contact;
import com.holmesglen.assesment2.R;

import java.security.AccessController;

public class AddActivity extends AppCompatActivity {
    PhonebookDb phonebookDb;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_page);
        //instantiating the textView elements
        TextView addFirst = findViewById(R.id.txtAddFirst);
        TextView addLast = findViewById(R.id.txtAddLast);
        TextView addPhone = findViewById(R.id.txtAddPhone);
        TextView addEmail = findViewById(R.id.txtAddDob);

        //add button logic for adding a contact
        findViewById(R.id.btnAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phonebookDb.getDBInstance(getApplicationContext()).contactDao().insert(
                        new Contact(addFirst.getText().toString(),addLast.getText().toString(),
                                addPhone.getText().toString(),addEmail.getText().toString())
                );
                //starting a ListPageActivity after adding the contact
                Intent intent = new Intent( AddActivity.this,ListPageActivity.class);
                startActivity(intent);
                //toast message for client feedback after adding a contact
                Toast.makeText(getApplicationContext(), "Contact added to database"
                        ,Toast.LENGTH_SHORT).show();
            }
        });

    }
}
