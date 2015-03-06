package com.example.denis.TicTacToe.Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.denis.TicTacToe.Models.TicTacToeModel;
import com.example.denis.TicTacToe.R;

import java.util.ArrayList;

/**
 * ${PROJECT_NAME} created by Denis Pavlovsky on 17.02.15.
 */
public class GamePreferenceActivity  extends ActionBarActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preference_fragment);

        final ArrayList<String> diffTypes = new ArrayList<>(3);
        diffTypes.add("Difficult");
        diffTypes.add("Medium");
        diffTypes.add("Easy");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, diffTypes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setAdapter(adapter);

        spinner.setPrompt(getString(R.string.difficult_spinner_title));

        spinner.setSelection(1);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 1:
                        TicTacToeModel.setDifficulty (TicTacToeModel.HARD_DIF);
                        break;
                    case 2:
                        TicTacToeModel.setDifficulty (TicTacToeModel.MEDIUM_DIF);
                        break;
                    case 3:
                        TicTacToeModel.setDifficulty (TicTacToeModel.EASY_DIF);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        final ArrayList<String> picTypes = new ArrayList<>(3);
        diffTypes.add("Nought");
        diffTypes.add("Cross");


        ArrayAdapter<String> choose_pic_adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, picTypes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        final Spinner spinner_choose_pic = (Spinner) findViewById(R.id.picture_spinner);
        spinner_choose_pic.setAdapter(choose_pic_adapter);

        spinner_choose_pic.setPrompt(getString(R.string.choose_picture_title));

        spinner_choose_pic.setSelection(1);

        spinner_choose_pic.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 1:
                        TicTacToeModel.setPLAYER_PIC_CHOOSE(position);
                        break;
                    case 2:
                        TicTacToeModel.setPLAYER_PIC_CHOOSE(position);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}
