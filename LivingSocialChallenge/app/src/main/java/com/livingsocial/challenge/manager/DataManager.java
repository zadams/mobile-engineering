package com.livingsocial.challenge.manager;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.livingsocial.challenge.activity.SplashActivity;
import com.livingsocial.challenge.model.Invoice;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ale on 1/11/15.
 */
public class DataManager {

    private static DataManager dataManager = null;
    private Context context;
    private List<Invoice> invoices;
    private List<DataListener> listeners;

    private DataManager(Context context){
        this.context = context;
        listeners = new ArrayList<DataListener>();
        invoices = new ArrayList<Invoice>();
    }

    public static DataManager getInstance(Context context){
        if(dataManager == null){
            dataManager = new DataManager(context);
        }
        return dataManager;
    }

    public List<Invoice> getInvoices() {
        List<Invoice> list = new ArrayList<Invoice>();
        try {
            AssetManager assetManager = context.getResources().getAssets();
            InputStream is = assetManager.open("feed.json");
            Gson gson = new Gson();
            Reader reader = new InputStreamReader(is);
            Type listType = new TypeToken<ArrayList<Invoice>>() {}.getType();
            list =gson.fromJson(reader, listType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void loadInvoice(String filename, DataListener listener){
        if(listener != null){
            listeners.add(listener);
        }
        new FileReadingAsyncTask().execute(filename);
    }

    private void setInvoices(List<Invoice> invoices){
        if(invoices != null){
            this.invoices = invoices;
        }
    }

    public void clearListeners() {
        listeners.clear();
    }


    private void onDataSuccess(List<Invoice> invoices){
       if(invoices != null) {
           for (DataListener listener : listeners) {
               listener.onSuccess();
           }
           listeners.clear();
       }else {
           onDataError("Error: There is no invoices.");
       }
    }

    private void onDataError(String message){
        for(DataListener listener:listeners){
            listener.onError(message);
        }
        listeners.clear();
    }



    private class FileReadingAsyncTask extends AsyncTask<String, Integer, List<Invoice>> {

        @Override
        protected List<Invoice> doInBackground(String... params) {
            List<Invoice> list = new ArrayList<Invoice>();
            try {
                AssetManager assetManager = context.getResources().getAssets();
                InputStream is = assetManager.open(params[0]);
                Gson gson = new Gson();
                Reader reader = new InputStreamReader(is);
                Type listType = new TypeToken<ArrayList<Invoice>>() {
                }.getType();
                list = gson.fromJson(reader, listType);
            } catch (IOException e) {
                onDataError(e.getMessage());
                e.printStackTrace();
            }
            return list;
        }

        @Override
        protected void onPostExecute(List<Invoice> result) {
            onDataSuccess(result);
        }
    }

}
