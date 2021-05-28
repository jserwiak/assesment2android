package com.holmesglen.assesment2.Activities;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

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


        TextView addFirst = findViewById(R.id.txtAddFirst);
        TextView addLast = findViewById(R.id.txtAddLast);
        TextView addPhone = findViewById(R.id.txtAddPhone);
        TextView addEmail = findViewById(R.id.txtAddDob);

        findViewById(R.id.btnAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phonebookDb.getDBInstance(getApplicationContext()).contactDao().insert(
                        new Contact(addFirst.getText().toString(),addLast.getText().toString(),
                                addPhone.getText().toString(),addEmail.getText().toString())
                );
                Intent intent = new Intent( AddActivity.this,ListPageActivity.class);
                startActivity(intent);
            }
        });

    }
}
