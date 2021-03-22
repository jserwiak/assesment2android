package com.holmesglen.assesment2.Activities;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.holmesglen.assesment2.Models.Contact;
import com.holmesglen.assesment2.R;

import java.util.ArrayList;

public class MainListRecyclerViewAdapter extends RecyclerView.Adapter<MainListRecyclerViewAdapter.MainListItemViewHolder>{
    private ArrayList<Contact> contactList;
    public MainListRecyclerViewAdapter(ArrayList<Contact> contactList) {
        this.contactList = contactList;
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
    }

    @Override
    public int getItemCount() {
        return contactList == null? 0:contactList.size();

    }

    class MainListItemViewHolder extends RecyclerView.ViewHolder
    {
        private TextView txtViewLast;
        private TextView txtViewFirst;
        private TextView txtViewDoB;
        private TextView txtViewPhone;

        public MainListItemViewHolder(@NonNull View itemView) {
            super(itemView);
            this.txtViewLast = itemView.findViewById(R.id.txt_Main_Last);
            this.txtViewPhone = itemView.findViewById(R.id.txt_Main_Phone);
            this.txtViewFirst = itemView.findViewById(R.id.txt_Main_First);
            this.txtViewDoB = itemView.findViewById(R.id.txt_Main_DoB);
        }
    }
}
