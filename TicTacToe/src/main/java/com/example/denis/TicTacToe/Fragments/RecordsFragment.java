package com.example.denis.TicTacToe.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.denis.TicTacToe.Helpers.RecordsAdapter;
import com.example.denis.TicTacToe.Helpers.RecordsHelper;
import com.example.denis.TicTacToe.Models.RecordModel;
import com.example.denis.TicTacToe.R;

import java.util.ArrayList;

/**
 * Created by denis on 16.02.15.
 */
public class RecordsFragment extends Fragment {

    private static final String TAG = RecordsFragment.class.getSimpleName();
    private ArrayList<RecordModel> models;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (RecordsHelper.isHaveRecords) {
            try {
                models = RecordsHelper.get();

                RecordsAdapter recordsAdapter = new RecordsAdapter(getActivity(), R.layout.records_list_item, models);

                final ListView listView = (ListView) getActivity().findViewById(R.id.listView);

                listView.setAdapter(recordsAdapter);
            } catch (NullPointerException e) {
                Log.e(TAG, "RecordsFragment Exception in RecordsHelper");
            }
        }


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (RecordsHelper.isHaveRecords) {
            return inflater.inflate(R.layout.records_fragment, null);
        } else {
            return inflater.inflate(R.layout.null_records_fragment, null);
        }


    }
}
