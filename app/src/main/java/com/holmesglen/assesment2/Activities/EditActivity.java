/**
 * This is a EditActivity class for editing contacts
 * @author Jerzy_Serwiak
 * @version 1.0
 *
 */

package com.holmesglen.assesment2.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.holmesglen.assesment2.Database.PhonebookDb;
import com.holmesglen.assesment2.Models.Contact;
import com.holmesglen.assesment2.R;

import java.util.ArrayList;
import java.util.Collections;

public class EditActivity extends AppCompatActivity {
    Contact c = new Contact();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_page);
        TextView txtFirst = findViewById(R.id.txtEditFirst);
        TextView txtLast = findViewById(R.id.txtEditLast);
        TextView txtPhone = findViewById(R.id.txtEditPhone);
        TextView txtDoB = findViewById(R.id.txtEditDob);

        int index = -1;

        Bundle bundle = getIntent().getExtras();
        if (bundle != null && bundle.containsKey("contact_id")) {
            index = bundle.getInt("contact_id");

            c = (Contact) PhonebookDb.getDBInstance(this).contactDao().getContactById(index);
            txtFirst.setText(c.getFirstName());
            txtLast.setText(c.getLastName());
            txtPhone.setText(c.getPhone());
            txtDoB.setText(c.getDateOfBirth());

            findViewById(R.id.btnUpdate).setOnClickListener(new View.OnClickListener() {
                //update button listener
                @Override
                public void onClick(View v) {
                    c.setFirstName(txtFirst.getText().toString());
                    c.setLastName(txtLast.getText().toString());
                    c.setPhone(txtPhone.getText().toString());
                    c.setDateOfBirth(txtDoB.getText().toString());

                    PhonebookDb.getDBInstance(getApplicationContext()).contactDao().update(c);
                    Intent intent = new Intent( EditActivity.this,ListPageActivity.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "Contact has been updated"
                            ,Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
}
