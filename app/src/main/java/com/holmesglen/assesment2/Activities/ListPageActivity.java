package com.holmesglen.assesment2.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.holmesglen.assesment2.Database.PhonebookDb;
import com.holmesglen.assesment2.Lib.MyHash;
import com.holmesglen.assesment2.Models.Contact;
import com.holmesglen.assesment2.R;
import com.holmesglen.assesment2.ViewModels.MyHashViewModel;

import java.util.ArrayList;

public class ListPageActivity extends AppCompatActivity {
    private MyHashViewModel hash;
    // for the recycler view instance in layout
    private RecyclerView recyclerViewMainList;
    private MainListRecyclerViewAdapter adapter;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_page);
        PhonebookDb.initData(this);

        hash = new ViewModelProvider(this).get(MyHashViewModel.class);
        doHash();
        // sort
        sortA2ZBtnClick();
        sortZ2ABtnClick();

        // search
        searchBtnClick();
        //add
        addBtnClick();


    }


    private void doHash()
    {
        //getting data from database and hash it
        ArrayList<Contact> allContacts = (ArrayList<Contact>)PhonebookDb.getDBInstance(this).contactDao().getAllContacts();
        hash.myHash = new MyHash();
        hash.myHash.buildHashTable(allContacts);

        //setting data for recyclerView
        recyclerViewMainList = findViewById(R.id.recview1);

        //step 1 create adapter
        adapter = new MainListRecyclerViewAdapter(hash.myHash.toList(false),this);

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
        int offset = hash.myHash.calcOffsetByKey(key);
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
    }
    private void addBtnClick() {
        findViewById(R.id.List_Page_btn_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( ListPageActivity.this,AddActivity.class);
                startActivity(intent);
            }
        });
    }
    // sort a~z
    private void sortA2ZBtnClick() {
        findViewById(R.id.List_Page_btn_az).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.reloadContactList(hash.myHash.toList(false));
            }
        });
    }

    // sort z~a
    private void sortZ2ABtnClick() {
        findViewById(R.id.List_Page_btn_za).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.reloadContactList(hash.myHash.toList(true));
            }
        });
    }
    //search
    private void searchBtnClick() {
        findViewById(R.id.List_Page_btn_search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.search_layout).setVisibility(View.VISIBLE);
            }});
                findViewById(R.id.searchBtn).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                String wantedStr = ((EditText)findViewById(R.id.searchtxt)).getText().toString();
                if(wantedStr != null && !wantedStr.isEmpty()) {
                    adapter.reloadContactList(hash.myHash.shortList(wantedStr));
                } else {
                    adapter.reloadContactList(hash.myHash.toList(false));
                }
            }
        });
    }

}

