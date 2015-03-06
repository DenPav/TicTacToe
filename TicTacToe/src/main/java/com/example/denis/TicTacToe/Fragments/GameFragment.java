package com.example.denis.TicTacToe.Fragments;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.denis.TicTacToe.Helpers.Counter;
import com.example.denis.TicTacToe.Helpers.RecordsHelper;
import com.example.denis.TicTacToe.Models.RecordModel;
import com.example.denis.TicTacToe.R;
import com.example.denis.TicTacToe.Models.TicTacToeModel;

/**
 * Created by denis on 13.02.15.
 */
public class GameFragment extends Fragment implements View.OnClickListener, DialogInterface.OnClickListener {

    private static TicTacToeModel model = TicTacToeModel.getInstance();


    private Button[] buttons;
    private TextView humanScore;
    private TextView droidScore;

    private static String playerName;


    private void initListeners() {
        buttons = new Button[9];
        buttons[0] = (Button) getActivity().findViewById(R.id.button_11);
        buttons[1] = (Button) getActivity().findViewById(R.id.button_12);
        buttons[2] = (Button) getActivity().findViewById(R.id.button_13);
        buttons[3] = (Button) getActivity().findViewById(R.id.button_21);
        buttons[4] = (Button) getActivity().findViewById(R.id.button_22);
        buttons[5] = (Button) getActivity().findViewById(R.id.button_23);
        buttons[6] = (Button) getActivity().findViewById(R.id.button_31);
        buttons[7] = (Button) getActivity().findViewById(R.id.button_32);
        buttons[8] = (Button) getActivity().findViewById(R.id.button_33);

        for (int i = 0;i < buttons.length;i++){
            buttons[i].setOnClickListener(this);
        }

//        for (Button btn : buttons) {
//            btn.setOnClickListener(this);
//        }

        getActivity().findViewById(R.id.human_vs_droid).setOnClickListener(this);
    }



    private void doMove(Button btn) {
        switch (btn.getId()) {
            case R.id.button_11:
                model.doMove(0, 0);
                break;
            case R.id.button_12:
                model.doMove(0, 1);
                break;
            case R.id.button_13:
                model.doMove(0, 2);
                break;
            case R.id.button_21:
                model.doMove(1, 0);
                break;
            case R.id.button_22:
                model.doMove(1, 1);
                break;
            case R.id.button_23:
                model.doMove(1, 2);
                break;
            case R.id.button_31:
                model.doMove(2, 0);
                break;
            case R.id.button_32:
                model.doMove(2, 1);
                break;
            case R.id.button_33:
                model.doMove(2, 2);
                break;
        }
    }

    private void newRound() {
        model.newRound();
        refreshGame();
    }

    private void newGame() {
        Counter.startTime();
        model.newGame();
        refreshGame();


        final EditText input = new EditText(getActivity());
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
                input.setLayoutParams(lp);

        new AlertDialog.Builder(getActivity())
        .setTitle("Player name")
        .setMessage("Enter Your name, please")
        .setView(input)
        .setPositiveButton("YES",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        playerName = input.getText().toString();
                    }
                })
        .show();
    }

    private void showAlertDialog(int status) {
        new AlertDialog.Builder(getActivity()).setTitle(R.string.message_title)
                .setMessage(status)
                .setPositiveButton("Yes", this)
                .setNegativeButton("No", this)
                .show();
    }

    private void showRestartDialog() {
        new AlertDialog.Builder(getActivity()).setTitle(R.string.question_title)
                .setMessage(R.string.restart_game)
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dlg, int something) {
                                newGame();
                            }
                        }).setNegativeButton("No", null).show();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.game_fragment, null);

    }

    @Override
    public void onResume() {
        super.onResume();

        initListeners();
        injectionController();
        refreshGame();

    }

    @Override
    public void onClick(View v) {
        if (v instanceof Button) {
            doMove((Button) v);
            refreshGame();
            if (model.getState() == TicTacToeModel.STATE_DRAW)
                showAlertDialog(R.string.draw_game);
            else if (model.getState() == TicTacToeModel.STATE_WIN) {
                if (model.getWinner() == TicTacToeModel.NOUGHT)
                    showAlertDialog(R.string.nought_win_game);
                else if (model.getWinner() == TicTacToeModel.CROSS)
                    showAlertDialog(R.string.cross_win_game);
            }

        } else if (v instanceof ImageView) {
            showRestartDialog();
        }
    }

    private void injectionController() {

        humanScore = (TextView) getActivity().findViewById(R.id.human_score);
        droidScore = (TextView) getActivity().findViewById(R.id.droid_score);
    }

    private void drawButton(Button btn, int state) {
        if ( TicTacToeModel.NOUGHT == state)
            btn.setBackgroundResource(R.drawable.o);
        else if (TicTacToeModel.CROSS == state)
            btn.setBackgroundResource(R.drawable.x);
        else
            btn.setBackgroundResource(R.drawable.clear);
    }

    public void refreshGame() {
        for (int i = 0; i < buttons.length; i++)
            drawButton(buttons[i], model.getGameField()[i / 3][i % 3]);
        humanScore.setText(model.getHumanScore() + "");
        droidScore.setText(model.getDroidScore() + "");
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {

        switch (which){
            case 1:
                newRound();
                break;
            case 2:
                showRecordsDialog();
                break;
        }

    }

    private void showRecordsDialog (){

        new AlertDialog.Builder(getActivity()).setTitle(R.string.message_title)
                .setMessage("Do You want to save your record?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        RecordsHelper.save(getActivity(), new RecordModel(playerName, model.getHumanScore(), model.getDroidScore(), Counter.giveTime() ));
                    }
                })
                .setNegativeButton("No",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        closeSelf();
                    }
                }).show();
    }



    private void closeSelf(){

        getActivity().getFragmentManager().beginTransaction().remove(this).commit();
    }

}