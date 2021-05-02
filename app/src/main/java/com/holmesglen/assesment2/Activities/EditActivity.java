package com.holmesglen.assesment2.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.holmesglen.assesment2.Database.PhonebookDb;
import com.holmesglen.assesment2.Models.Contact;
import com.holmesglen.assesment2.R;

import java.util.ArrayList;
import java.util.Collections;

public class EditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_page);
        TextView txtFirst = findViewById(R.id.txtEditFirst);
        TextView txtLast = findViewById(R.id.txtEditLast);
        TextView txtPhone = findViewById(R.id.txtEditPhone);
        TextView txtDoB = findViewById(R.id.txtEditEmail);

        ArrayList<Contact> contactList;
        contactList = PhonebookDb.getInstance().getAll();
        Collections.sort(contactList);
        int index = -1;

        Bundle bundle = getIntent().getExtras();
        if(bundle != null && bundle.containsKey("contact_index"))
        {
            index = bundle.getInt("contact_index"); // this is getting the index of sorted array list from hashtable,
            //to get proper data we need to sort contacts.
            if(index >= 0 && index <contactList.size()) {


                Contact c = contactList.get(index);

                txtFirst.setText(c.getFirstName());
                txtLast.setText(c.getLastName());
                txtPhone.setText(c.getPhone());
                txtDoB.setText(c.getDateOfBirth());
            }

        }
    }

}