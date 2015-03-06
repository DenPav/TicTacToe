package com.example.denis.TicTacToe.Helpers;

import android.util.Log;

/**
 * Created by denis on 08.02.15.
 */
public class Counter extends Thread {

    private static final String TAG = Counter.class.getSimpleName();
    private long mStep;
    private static boolean isStopCounting = false;

    private static long startTime;

    public static boolean count (long step){
        try {
            Counter a = new Counter(step);
            a.run();
            if (isStopCounting){
                isStopCounting = false;
                return true;
            }

        }catch (Exception e){
            Log.e(TAG, "Counter exception" + e);
        }
        return false;
    }

    private Counter(long step) {
    mStep = step;
    }

    public static void startTime (){
        startTime = System.currentTimeMillis() / 1000L;
    }

    public static long giveTime (){

        return (startTime - System.currentTimeMillis() / 1000L);
    }

    @Override
    public void run() {
         try {
             Thread.sleep(mStep);
             isStopCounting = true;
         }catch (InterruptedException e){
             Log.e(TAG, "Thread exception" + e);
         }
    }
}
