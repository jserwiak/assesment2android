/**
 * This is a MainListRecyclerViewAdapter class that holds the logic for the RecyclerView and adapter
 * It also holds logic for several buttons logic
 * @author Jerzy_Serwiak
 * @version 1.0
 *
 */

package com.holmesglen.assesment2.Activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.chauthai.swipereveallayout.ViewBinderHelper;
import com.holmesglen.assesment2.Database.PhonebookDb;
import com.holmesglen.assesment2.Models.Contact;
import com.holmesglen.assesment2.R;
import com.holmesglen.assesment2.ViewModels.MyHashViewModel;

import java.util.ArrayList;

public class MainListRecyclerViewAdapter extends RecyclerView.Adapter<MainListRecyclerViewAdapter.MainListItemViewHolder>{
    private ArrayList<Contact> contactList;
    private AppCompatActivity activity;
    private final ViewBinderHelper viewBinderHelper = new ViewBinderHelper();

    public MainListRecyclerViewAdapter(ArrayList<Contact> contactList,AppCompatActivity activity) {
        this.contactList = contactList;
        this.activity = activity;

    }
    public void reloadContactList (ArrayList<Contact> contactList) {
        this.contactList = contactList;
        this.notifyDataSetChanged();
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
        //for the swipe left to reveal buttons
        viewBinderHelper.setOpenOnlyOne(true);
        viewBinderHelper.bind(holder.swipeRevealLayout, String.valueOf(contactList.get(position)));
        viewBinderHelper.closeLayout(String.valueOf(contactList.get(position)));

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
        private SwipeRevealLayout swipeRevealLayout;

        //for swipe
        private TextView txtEdit;

        private int id;
        public TextView getTxtEdit() {
            return txtEdit;
        }

        public void setTxtEdit(TextView txtEdit) {
            this.txtEdit = txtEdit;
        }
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
            swipeRevealLayout = itemView.findViewById(R.id.swipelayout);

            //onClick listener for detail view
            itemView.findViewById(R.id.main_item_constraintID).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int x = id;
                    int y = 0;
                    Contact c = contactList.get(x);
                    y = c.getId();
                    Intent intent = new Intent(activity, DetailActivity.class);
                    intent.putExtra("contact_id",y);
                    activity.startActivity(intent);
                    return;
                }
            });

            //edit button functionality
            itemView.findViewById(R.id.Swipe_btn_edit).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int x = id;
                    int y = 0;
                    Contact c = contactList.get(x);
                    y = c.getId();
                    Intent intent = new Intent(activity, EditActivity.class);
                    intent.putExtra("contact_id",y);
                    //intent.putExtra("contact_list",contactList);
                    activity.startActivity(intent);
                    return;

                }
            });
            //delete button functionality
            itemView.findViewById(R.id.Swipe_btn_delete).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int x = id;
                    Contact c = contactList.get(x);
                    //popup window "Are you sure you want to delete?" after clicking delete button
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(itemView.getContext());
                    alertDialog.setTitle("Are you sure you want to delete this contact?");
                    alertDialog.setMessage(c.getFirstName() + " " + c.getLastName());
                    alertDialog.setPositiveButton("CANCEL", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    alertDialog.setNegativeButton("DELETE", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            PhonebookDb.getDBInstance(activity.getApplicationContext()).contactDao().delete(c);
                            Intent intent = new Intent( activity,ListPageActivity.class);
                            activity.startActivity(intent);
                            //toast for client feedback
                            Toast.makeText(activity.getApplicationContext(), "Contact has been deleted"
                                    ,Toast.LENGTH_SHORT).show();
                        }
                    });

                    AlertDialog dialog = alertDialog.create();
                    dialog.show();
                }

            });

        }
    }
}

