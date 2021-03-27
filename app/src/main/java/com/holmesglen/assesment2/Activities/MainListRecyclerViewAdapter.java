package com.holmesglen.assesment2.Activities;

import android.content.Intent;
import android.os.Parcelable;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.holmesglen.assesment2.Models.Contact;
import com.holmesglen.assesment2.R;

import java.util.ArrayList;

public class MainListRecyclerViewAdapter extends RecyclerView.Adapter<MainListRecyclerViewAdapter.MainListItemViewHolder>{
    private ArrayList<Contact> contactList;
    private AppCompatActivity activity;
    public MainListRecyclerViewAdapter(ArrayList<Contact> contactList,AppCompatActivity activity) {
        this.contactList = contactList;
        this.activity = activity;
    }
    @NonNull
    @Override
    public MainListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item,parent,false);
        MainListItemViewHolder vh = new MainListItemViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MainListItemViewHolder holder, int position) {


        holder.txtViewLast.setText(contactList.get(position).getLastName());
        holder.txtViewPhone.setText(contactList.get(position).getPhone());
        holder.txtViewFirst.setText(contactList.get(position).getFirstName());
        holder.txtViewDoB.setText(contactList.get(position).getDateOfBirth());
        holder.id = position;
    }

    @Override
    public int getItemCount() {
        return contactList == null? 0:contactList.size();

    }

    class MainListItemViewHolder extends RecyclerView.ViewHolder{

        private TextView txtViewLast;
        private TextView txtViewFirst;
        private TextView txtViewDoB;
        private TextView txtViewPhone;
        private int id;

        public TextView getTxtViewLast() {
            return txtViewLast;
        }

        public void setTxtViewLast(TextView txtViewLast) {
            this.txtViewLast = txtViewLast;
        }

        public TextView getTxtViewFirst() {
            return txtViewFirst;
        }

        public void setTxtViewFirst(TextView txtViewFirst) {
            this.txtViewFirst = txtViewFirst;
        }

        public TextView getTxtViewDoB() {
            return txtViewDoB;
        }

        public void setTxtViewDoB(TextView txtViewDoB) {
            this.txtViewDoB = txtViewDoB;
        }

        public TextView getTxtViewPhone() {
            return txtViewPhone;
        }

        public void setTxtViewPhone(TextView txtViewPhone) {
            this.txtViewPhone = txtViewPhone;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public MainListItemViewHolder(@NonNull View itemView) {
            super(itemView);
            this.txtViewLast = itemView.findViewById(R.id.txt_Main_Last);
            this.txtViewPhone = itemView.findViewById(R.id.txt_Main_Phone);
            this.txtViewFirst = itemView.findViewById(R.id.txt_Main_First);
            this.txtViewDoB = itemView.findViewById(R.id.txt_Main_DoB);
            itemView.findViewById(R.id.main_item_constraintID).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int x = id;

                    Contact c = contactList.get(x);
                    Intent intent = new Intent(activity, DetailActivity.class);
                    intent.putExtra("contact_index", x);
                    /*intent.putExtra("first", c.getFirstName());
                    intent.putExtra("last", c.getLastName());
                    intent.putExtra("phone", c.getPhone());
                    intent.putExtra("dob", c.getDateOfBirth());*/
                    activity.startActivity(intent);
                    return;
                }

            });
        }
    }
}
