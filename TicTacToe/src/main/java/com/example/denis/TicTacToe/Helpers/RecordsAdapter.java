package com.example.denis.TicTacToe.Helpers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.denis.TicTacToe.Models.RecordModel;
import com.example.denis.TicTacToe.R;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

/**
 * Created by denis on 16.02.15.
 */
public class RecordsAdapter extends ArrayAdapter<RecordModel> {

    private final LayoutInflater mInflater;
    List mList;

    public RecordsAdapter(Context context, int resource, List<RecordModel> objects) {
        super(context, resource, objects);

        mInflater = LayoutInflater.from(context);
        mList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View view = mInflater.inflate(R.layout.records_list_item, null);

        RecordModel model = getItem(position);

        TextView playerScore = (TextView) view.findViewById(R.id.humanScoreText);
        playerScore.setText(String.format("%s score - (%d)", model.getPlayerName(), model.getPlayerScore()));

        TextView droidScore = (TextView) view.findViewById(R.id.droidScoreText);
        droidScore.setText(String.format("Droid score - (%d)", model.getPlayerScore()));

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss", new Locale("ru"));
        String timeString =  simpleDateFormat.format(model.getGameTime());

        TextView time = (TextView) view.findViewById(R.id.time);
        time.setText(timeString);

        return view;
    }
}
