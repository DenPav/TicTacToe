package com.example.denis.TicTacToe.Fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.denis.TicTacToe.R;

/**
 * Created by denis on 08.02.15.
 */
public class MenuFragment extends Fragment implements View.OnClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.menu_fragment, null);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        getActivity().findViewById(R.id.newGameButton).setOnClickListener(this);
        getActivity().findViewById(R.id.RecordsButton).setOnClickListener(this);
        getActivity().findViewById(R.id.PreferenceButton).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.newGameButton:
                GameFragment gameFragment = new GameFragment();
                getActivity().getFragmentManager().beginTransaction()
                        .replace(R.id.FragmentPlace, gameFragment).addToBackStack(null).commit();
                break;
            case R.id.RecordsButton:
                RecordsFragment recordsFragment = new RecordsFragment ();
                getActivity().getFragmentManager().beginTransaction()
                        .replace(R.id.FragmentPlace, recordsFragment).addToBackStack(null).commit();
                break;
            case R.id.PreferenceButton:
                Intent intent = new Intent(getActivity(), GamePreferenceActivity.class);
                startActivity(intent);
                break;
        }
    }
}
