package com.holmesglen.assesment2.Models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "contact")
public class Contact implements Comparable
{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;
    @ColumnInfo(name = "firstName")
    private String FirstName;
    @ColumnInfo(name = "lastName")
    private String LastName;
    @ColumnInfo(name = "phone")
    private String Phone;
    @ColumnInfo(name = "dateOfBirth")
    private String DateOfBirth;

    public Contact()
    {

    }
    @Ignore
    public Contact(String firstName, String lastName, String phone, String dateOfBirth) {
        FirstName = firstName;
        LastName = lastName;
        Phone = phone;
        DateOfBirth = dateOfBirth;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getDateOfBirth() {
        return DateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        DateOfBirth = dateOfBirth;
    }

    @Override
    public int compareTo(Object o) {
        return this.LastName.compareToIgnoreCase(((Contact)o).LastName);
    }
}
