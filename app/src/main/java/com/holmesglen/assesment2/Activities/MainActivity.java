package com.holmesglen.assesment2.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.holmesglen.assesment2.Database.PhonebookDb;
import com.holmesglen.assesment2.Lib.MyHash;
import com.holmesglen.assesment2.Models.Contact;
import com.holmesglen.assesment2.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private MyHash hash;
    // for the recucler view instance in layout
    private RecyclerView recyclerViewMainList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_page);

        //getting data from database and hash it
        ArrayList<Contact> allContacts = PhonebookDb.getInstance().getAll();
        hash = new MyHash();
        hash.buildHashTable(allContacts);

        //setting data for recyclerView
        recyclerViewMainList = findViewById(R.id.recview1);

        //step 1 create adapter
        MainListRecyclerViewAdapter adapter = new MainListRecyclerViewAdapter(hash.toList(false));

        //step 2 set adapter
        recyclerViewMainList.setAdapter(adapter);

        //step 3 set layout manager
        recyclerViewMainList.setLayoutManager(new LinearLayoutManager(this));

        //setting click function for all buttons
        setAllNavBtnClickListener();
    }
    private void navBtnClick(int key)
    {
        if(key<0 || key>26)
        {
            return;
        }
        int offset = hash.calcOffsetByKey(key);
        //scroll the view
        ((LinearLayoutManager)recyclerViewMainList.getLayoutManager()).scrollToPositionWithOffset(offset, 0);
    }
    private void setAllNavBtnClickListener()
    {
        findViewById(R.id.btn_hash).setOnClickListener((v) -> {
            MainActivity.this.navBtnClick(0);});
        findViewById(R.id.btn_A).setOnClickListener((v) -> {
            MainActivity.this.navBtnClick(1);});
        findViewById(R.id.btn_B).setOnClickListener((v) -> {
            MainActivity.this.navBtnClick(2);});
        findViewById(R.id.btn_C).setOnClickListener((v) -> {
            MainActivity.this.navBtnClick(3);});
        findViewById(R.id.btn_D).setOnClickListener((v) -> {
            MainActivity.this.navBtnClick(4);});
        findViewById(R.id.btn_E).setOnClickListener((v) -> {
            MainActivity.this.navBtnClick(5);});
        findViewById(R.id.btn_F).setOnClickListener((v) -> {
            MainActivity.this.navBtnClick(6);});
        findViewById(R.id.btn_G).setOnClickListener((v) -> {
            MainActivity.this.navBtnClick(7);});
        findViewById(R.id.btn_H).setOnClickListener((v) -> {
            MainActivity.this.navBtnClick(8);});
        findViewById(R.id.btn_I).setOnClickListener((v) -> {
            MainActivity.this.navBtnClick(9);});
        findViewById(R.id.btn_J).setOnClickListener((v) -> {
            MainActivity.this.navBtnClick(10);});
        findViewById(R.id.btn_K).setOnClickListener((v) -> {
            MainActivity.this.navBtnClick(11);});
        findViewById(R.id.btn_L).setOnClickListener((v) -> {
            MainActivity.this.navBtnClick(12);});
        findViewById(R.id.btn_M).setOnClickListener((v) -> {
            MainActivity.this.navBtnClick(13);});
        findViewById(R.id.btn_N).setOnClickListener((v) -> {
            MainActivity.this.navBtnClick(14);});
        findViewById(R.id.btn_O).setOnClickListener((v) -> {
            MainActivity.this.navBtnClick(15);});
        findViewById(R.id.btn_P).setOnClickListener((v) -> {
            MainActivity.this.navBtnClick(16);});
        findViewById(R.id.btn_Q).setOnClickListener((v) -> {
            MainActivity.this.navBtnClick(17);});
        findViewById(R.id.btn_R).setOnClickListener((v) -> {
            MainActivity.this.navBtnClick(18);});
        findViewById(R.id.btn_S).setOnClickListener((v) -> {
            MainActivity.this.navBtnClick(19);});
        findViewById(R.id.btn_T).setOnClickListener((v) -> {
            MainActivity.this.navBtnClick(20);});
        findViewById(R.id.btn_U).setOnClickListener((v) -> {
            MainActivity.this.navBtnClick(21);});
        findViewById(R.id.btn_V).setOnClickListener((v) -> {
            MainActivity.this.navBtnClick(22);});
        findViewById(R.id.btn_W).setOnClickListener((v) -> {
            MainActivity.this.navBtnClick(23);});
        findViewById(R.id.btn_X).setOnClickListener((v) -> {
            MainActivity.this.navBtnClick(24);});
        findViewById(R.id.btn_Y).setOnClickListener((v) -> {
            MainActivity.this.navBtnClick(25);});
        findViewById(R.id.btn_Z).setOnClickListener((v) -> {
            MainActivity.this.navBtnClick(26);});

    }
}