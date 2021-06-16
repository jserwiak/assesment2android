/**
 * This is a DetailActivity class for displaying contact details
 * @author Jerzy_Serwiak
 * @version 1.0
 *
 */

package com.holmesglen.assesment2.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.holmesglen.assesment2.Database.PhonebookDb;
import com.holmesglen.assesment2.Models.Contact;
import com.holmesglen.assesment2.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DetailActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_page);

        TextView txtFirst = findViewById(R.id.txt_Detail_first);
        TextView txtLast = findViewById(R.id.txt_Detail_last);
        TextView txtPhone = findViewById(R.id.txt_Detail_phone);
        TextView txtEmail = findViewById(R.id.txt_Detail_dob);

        int index = -1;


        Bundle bundle = getIntent().getExtras();
        if(bundle != null && bundle.containsKey("contact_id"))
        {
            index = bundle.getInt("contact_id"); // this is getting the index of sorted array list from hashtable,

            Contact c = (Contact)PhonebookDb.getDBInstance(this).contactDao().getContactById(index);
                txtFirst.setText(c.getFirstName());
                txtLast.setText(c.getLastName());
                txtPhone.setText(c.getPhone());
                txtEmail.setText(c.getDateOfBirth());
        }
        //back button listener
        findViewById(R.id.btn_Detail_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this, ListPageActivity.class);
                startActivity(intent);
            }
        });
    }
}
