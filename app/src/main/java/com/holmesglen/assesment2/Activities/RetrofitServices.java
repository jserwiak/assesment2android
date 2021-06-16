package com.holmesglen.assesment2.Activities;

import com.holmesglen.assesment2.Database.PhonebookDb;
import com.holmesglen.assesment2.Models.Contact;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitServices {
    // static variable retrofitServicesInstance of type Singleton
    private static RetrofitServices retrofitServicesInstance = null;
    private static RemoteContactDB service;

    // private constructor restricted to this class itself
    private RetrofitServices() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.111:5000/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(RemoteContactDB.class);
    }

    // static method to create instance of Singleton class
    public static RetrofitServices getInstance() {
        if (retrofitServicesInstance == null){
            retrofitServicesInstance = new RetrofitServices();
        }
        return retrofitServicesInstance;
    }

    public void ContactReadOne(int id, final ResultsHandler handler){
        Call<Contact> contactReadOne = service.Contact(id);
        contactReadOne.enqueue(new Callback<Contact>(){
            @Override
            public void onResponse(Call<Contact> call, Response<Contact> response) {
                Contact contact = response.body();
                handler.ReadOneOnResponseHandler(contact);
                return;
            }
            @Override
            public void onFailure(Call<Contact> call, Throwable t) {
                handler.OnFailureHandler();
                return;
            }
        });
        return;
    }

    public void ContactCreate(Contact contact, final ResultsHandler handler){
        // Prepare API call
        contact.setId(0);
        Call<Contact> contactCreate = service.ContactCreate(contact);
        // Call API
        contactCreate.enqueue(new Callback<Contact>(){

            // Call API - with successful results
            @Override
            public void onResponse(Call<Contact> call, Response<Contact> response) {
                Contact contact = response.body();
                handler.CreateOnResponseHandler(contact);
                return;
            }

            // Call API - with failed results
            @Override
            public void onFailure(Call<Contact> call, Throwable t) {
                handler.OnFailureHandler();
                return;
            }
        });

    }

    public void ContactReadAll(final ResultsHandler handler) {
        Call<List<Contact>> contactReadAll = service.ContactAll();
        contactReadAll.enqueue(new Callback<List<Contact>>(){

            @Override
            public void onResponse(Call<List<Contact>> call, Response<List<Contact>> response) {
                List<Contact> list = response.body();
                handler.ReadAllOnResponseHandler(list);
                return;
            }

            @Override
            public void onFailure(Call<List<Contact>> call, Throwable t) {
                handler.OnFailureHandler();
                return;
            }
        });
    }

    public void ContactUpdate(int id, Contact contact, final ResultsHandler handler) {
        contact.setId(id);
        Call<Void> contactUpdate = service.ContactUpdate(id, contact);
        contactUpdate.enqueue(new Callback<Void>(){

            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                handler.UpdateOnResponseHandler();
                return;
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                return;
            }
        });
    }

    public void ContactDelete(int id, final ResultsHandler handler) {
        Call<Contact> contactDelete = service.ContactDelete(id);
        contactDelete.enqueue(new Callback<Contact>() {
            @Override
            public void onResponse(Call<Contact> call, Response<Contact> response) {
                Contact contact = response.body();
                handler.DeleteOnResponseHandler(contact);
                return;
            }

            @Override
            public void onFailure(Call<Contact> call, Throwable t) {
                handler.OnFailureHandler();
                return;
            }
        });
    }

    public interface ResultsHandler {
        void CreateOnResponseHandler(Contact contact);
        void ReadOneOnResponseHandler(Contact contact);
        void ReadAllOnResponseHandler(List<Contact> contactList);
        void UpdateOnResponseHandler();
        void DeleteOnResponseHandler(Contact contact);
        void OnFailureHandler();

    }


}
