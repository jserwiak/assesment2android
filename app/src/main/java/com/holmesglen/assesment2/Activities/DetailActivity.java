package com.holmesglen.assesment2.Activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.holmesglen.assesment2.Database.PhonebookDb;
import com.holmesglen.assesment2.Models.Contact;
import com.holmesglen.assesment2.R;

import java.util.ArrayList;
import java.util.Collections;

public class DetailActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_page);

        TextView txtFirst = findViewById(R.id.txt_Detail_first);
        TextView txtLast = findViewById(R.id.txt_Detail_last);
        TextView txtPhone = findViewById(R.id.txt_Detail_phone);
        TextView txtDoB = findViewById(R.id.txt_Detail_email);

        ArrayList<Contact> contactList;
        int index = -1;
        /*String first;
        String last;
        String phone;
        String dob;*/
        Bundle bundle = getIntent().getExtras();
        if(bundle != null && bundle.containsKey("first"))
        {
            index = bundle.getInt("contact_index"); // this is getting the index of sorted array list from hashtable,
            //to get proper data we need to sort contacts.
            contactList = PhonebookDb.getInstance().getAll();
            Collections.sort(contactList);
            Contact c = contactList.get(index);

            txtFirst.setText(c.getFirstName());
            txtLast.setText(c.getLastName());
            txtPhone.setText(c.getPhone());
            txtDoB.setText(c.getDateOfBirth());

            /*first = bundle.getString("first");
            last = bundle.getString("last");
            phone = bundle.getString("phone");
            dob = bundle.getString("dob");*/
        }
    }
}