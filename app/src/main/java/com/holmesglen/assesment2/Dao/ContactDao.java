package com.holmesglen.assesment2.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.holmesglen.assesment2.Models.Contact;

import java.util.List;
@Dao
public interface ContactDao {
    //create
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(Contact... contacts);

    //update
    @Update
    public void update(Contact... contacts);

    //delete
    @Delete
    public void delete(Contact... contacts);

    //delete all
    @Query("DELETE FROM contact")
    public void clearTable();

    //read all
    @Query("SELECT * FROM contact")
    public List<Contact> getAllContacts();

    //read one by id
    @Query("SELECT * FROM contact WHERE id = :contactId")
    public Contact getContactById(int contactId);
}