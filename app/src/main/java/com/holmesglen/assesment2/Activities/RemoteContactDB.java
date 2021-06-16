/**
 * This is a RemoteContact class for API Calls
 * @author Jerzy_Serwiak
 * @version 1.0
 *
 */

package com.holmesglen.assesment2.Activities;

import com.holmesglen.assesment2.Models.Contact;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface RemoteContactDB {
    //for adding
    @POST("Contacts")
    Call<Contact> ContactCreate(@Body Contact contact);
    //for displaying all contacts
    @GET("Contacts")
    Call<List<Contact>> ContactAll();
    //for displaying one contact
    @GET("Contacts/{id}")
    Call<Contact> Contact(@Path("id") int id);
    //for editing contact
    @PUT("Contacts/{id}")
    Call<Void> ContactUpdate(@Path("id") int id,@Body Contact contact);
    //for deleting contact
    @DELETE("Contacts/{id}")
    Call<Contact> ContactDelete(@Path("id") int id);

}
