package com.example.denis.TicTacToe.Helpers;

import android.content.Context;

import com.example.denis.TicTacToe.Models.RecordModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by denis on 16.02.15.
 */
public class RecordsHelper {

    private static FileOutputStream fos;
    private static Context mContext;
    private static FileInputStream fis;
    public static boolean isHaveRecords = false;

    public static void save (Context context , RecordModel model) {

        ArrayList<RecordModel> list = get();
        list.add(model);

        final String json = new Gson().toJson(list);
        mContext = context;

        try {
            fos = context.openFileOutput("RecordsList.json", Context.MODE_PRIVATE);
            fos.write(json.getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            isHaveRecords = true;
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static ArrayList<RecordModel> get(){
        ArrayList<RecordModel> list = null;

        try {
            fis = mContext.openFileInput("RecordsList.json");

            final InputStreamReader isr = new InputStreamReader(fis);

            final BufferedReader br = new BufferedReader(isr);

            final StringBuilder builder = new StringBuilder();

            String str;
            while ((str = br.readLine()) != null) {
                builder.append(str);
            }
            fis.close();

            final String json = builder.toString();

            final Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .create();

            list = gson.fromJson(json, ArrayList.class);


        }catch (IOException e){
            e.printStackTrace();
        }

        return list;
    }
}
