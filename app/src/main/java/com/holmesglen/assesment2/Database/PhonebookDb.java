/**
 * This is a PhonebookDB class for database logic and data initialization
 * @author Jerzy_Serwiak
 * @version 1.0
 *
 */

package com.holmesglen.assesment2.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.holmesglen.assesment2.Dao.ContactDao;
import com.holmesglen.assesment2.Models.Contact;

@Database(
        entities = {Contact.class},
        version = 1,
        exportSchema = false
)
public abstract class PhonebookDb extends RoomDatabase {

    public abstract ContactDao contactDao();

    private static PhonebookDb phonebookDb;

    //getting the instance of the database
    public static PhonebookDb getDBInstance(final Context context) {
        if(phonebookDb == null) {
            phonebookDb = Room.databaseBuilder(
                    context.getApplicationContext(),
                    PhonebookDb.class,
                    "phonebook_room.db").allowMainThreadQueries().build();
        }

        return phonebookDb;
    }

    public static int initData(final Context context){
        PhonebookDb db = getDBInstance(context);
        if(db.contactDao().getAllContacts().size() == 0) {
            db.contactDao().insert(
                    // initialize data
                    new Contact("aohn", "Smith", "123 456 789", "01/01/2020"),
                    new Contact("Aane", "Doe", "987 654 321","01/01/2020"),
                    new Contact("Bason", "Bourne", "0534684", "01/01/2020"),
                    new Contact("Cason", "Courne", "0534684", "01/01/2020"),
                    new Contact("Dason", "Dourne", "0534684", "01/01/2020"),
                    new Contact("Eason", "Kurne", "0534684", "01/01/2020"),
                    new Contact("Fason", "Wourne", "0534684", "01/01/2020"),
                    new Contact("Gason", "Fourne", "0534684", "01/01/2020"),
                    new Contact("Hason", "Yourne", "0534684", "01/01/2020"),
                    new Contact("Jason", "Vourne", "0534684", "01/01/2020"),
                    new Contact("Kason", "Mourne", "0534684", "01/01/2020"),
                    new Contact("Lason", "Nourne", "0534684", "01/01/2020"),
                    new Contact("Pason", "Zourne", "0534684", "01/01/2020"),
                    new Contact("Oason", "Journe", "0534684", "01/01/2020"),
                    new Contact("Tason", "Lourne", "0534684", "01/01/2020"),
                    new Contact("Rason", "Rourne", "0534684", "01/01/2020"),
                    new Contact("Wason", "Uourne", "0534684", "01/01/2020"),
                    new Contact("Sason", "Oourne", "0534684", "01/01/2020"),
                    new Contact("Zason", "Pourne", "0534684", "01/01/2020"),
                    new Contact("Xason", "Iourne", "0534684", "01/01/2020")
            );
        }
        return db.contactDao().getAllContacts().size();
    }
}
