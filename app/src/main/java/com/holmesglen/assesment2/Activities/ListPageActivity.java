package com.holmesglen.assesment2.Activities;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.holmesglen.assesment2.Database.PhonebookDb;
import com.holmesglen.assesment2.Lib.MyHash;
import com.holmesglen.assesment2.Models.Contact;
import com.holmesglen.assesment2.R;

import java.util.ArrayList;

public class ListPageActivity extends AppCompatActivity {
    private MyHash hash;
    // for the recycler view instance in layout
    private RecyclerView recyclerViewMainList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_page);
        doHash();


    }

    private void doHash()
    {
        //setContentView(R.layout.list_page);
        //getting data from database and hash it
        ArrayList<Contact> allContacts = PhonebookDb.getInstance().getAll();
        hash = new MyHash();
        hash.buildHashTable(allContacts);

        //setting data for recyclerView
        recyclerViewMainList = findViewById(R.id.recview1);

        //step 1 create adapter
        MainListRecyclerViewAdapter adapter = new MainListRecyclerViewAdapter(hash.toList(false),this);

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
            ListPageActivity.this.navBtnClick(0);});
        findViewById(R.id.btn_A).setOnClickListener((v) -> {
            ListPageActivity.this.navBtnClick(1);});
        findViewById(R.id.btn_B).setOnClickListener((v) -> {
            ListPageActivity.this.navBtnClick(2);});
        findViewById(R.id.btn_C).setOnClickListener((v) -> {
            ListPageActivity.this.navBtnClick(3);});
        findViewById(R.id.btn_D).setOnClickListener((v) -> {
            ListPageActivity.this.navBtnClick(4);});
        findViewById(R.id.btn_E).setOnClickListener((v) -> {
            ListPageActivity.this.navBtnClick(5);});
        findViewById(R.id.btn_F).setOnClickListener((v) -> {
            ListPageActivity.this.navBtnClick(6);});
        findViewById(R.id.btn_G).setOnClickListener((v) -> {
            ListPageActivity.this.navBtnClick(7);});
        findViewById(R.id.btn_H).setOnClickListener((v) -> {
            ListPageActivity.this.navBtnClick(8);});
        findViewById(R.id.btn_I).setOnClickListener((v) -> {
            ListPageActivity.this.navBtnClick(9);});
        findViewById(R.id.btn_J).setOnClickListener((v) -> {
            ListPageActivity.this.navBtnClick(10);});
        findViewById(R.id.btn_K).setOnClickListener((v) -> {
            ListPageActivity.this.navBtnClick(11);});
        findViewById(R.id.btn_L).setOnClickListener((v) -> {
            ListPageActivity.this.navBtnClick(12);});
        findViewById(R.id.btn_M).setOnClickListener((v) -> {
            ListPageActivity.this.navBtnClick(13);});
        findViewById(R.id.btn_N).setOnClickListener((v) -> {
            ListPageActivity.this.navBtnClick(14);});
        findViewById(R.id.btn_O).setOnClickListener((v) -> {
            ListPageActivity.this.navBtnClick(15);});
        findViewById(R.id.btn_P).setOnClickListener((v) -> {
            ListPageActivity.this.navBtnClick(16);});
        findViewById(R.id.btn_Q).setOnClickListener((v) -> {
            ListPageActivity.this.navBtnClick(17);});
        findViewById(R.id.btn_R).setOnClickListener((v) -> {
            ListPageActivity.this.navBtnClick(18);});
        findViewById(R.id.btn_S).setOnClickListener((v) -> {
            ListPageActivity.this.navBtnClick(19);});
        findViewById(R.id.btn_T).setOnClickListener((v) -> {
            ListPageActivity.this.navBtnClick(20);});
        findViewById(R.id.btn_U).setOnClickListener((v) -> {
            ListPageActivity.this.navBtnClick(21);});
        findViewById(R.id.btn_V).setOnClickListener((v) -> {
            ListPageActivity.this.navBtnClick(22);});
        findViewById(R.id.btn_W).setOnClickListener((v) -> {
            ListPageActivity.this.navBtnClick(23);});
        findViewById(R.id.btn_X).setOnClickListener((v) -> {
            ListPageActivity.this.navBtnClick(24);});
        findViewById(R.id.btn_Y).setOnClickListener((v) -> {
            ListPageActivity.this.navBtnClick(25);});
        findViewById(R.id.btn_Z).setOnClickListener((v) -> {
            ListPageActivity.this.navBtnClick(26);});

    }
    public void backBtnClick(View view)
    {
        doHash();
    }}

