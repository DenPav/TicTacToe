package com.example.denis.TicTacToe;

import android.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.denis.TicTacToe.Fragments.MenuFragment;
import com.example.denis.TicTacToe.Fragments.SplashWindowFragment;
import com.example.denis.TicTacToe.Helpers.Counter;


public class MainActivity extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        SplashWindowFragment splashWindow = new SplashWindowFragment();

        fragmentTransaction.add(R.id.FragmentPlace, splashWindow);
        fragmentTransaction.commit();

        if (Counter.count(3000)){
            MenuFragment menuFragment = new MenuFragment();
            FragmentTransaction fragmentTransaction2 = getFragmentManager().beginTransaction();
            fragmentTransaction2.replace(R.id.FragmentPlace, menuFragment);
            fragmentTransaction2.addToBackStack(null);
            fragmentTransaction2.commit();
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
