/**
 * This is a Contact class for Contact model
 * @author Jerzy_Serwiak
 * @version 1.0
 *
 */

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
    private String firstName;
    @ColumnInfo(name = "lastName")
    private String lastName;
    @ColumnInfo(name = "phone")
    private String phone;
    @ColumnInfo(name = "dateOfBirth")
    private String dateOfBirth;

    public Contact() {
    }
    //class constructor
    public Contact(String firstName, String lastName, String phone, String dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.dateOfBirth = dateOfBirth;
    }

    @Ignore
    //get, set  and toString methods
    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public int compareTo(Object o) {
        return this.lastName.compareToIgnoreCase(((Contact)o).lastName);
    }
}
