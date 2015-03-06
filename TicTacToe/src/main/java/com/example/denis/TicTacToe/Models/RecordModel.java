package com.example.denis.TicTacToe.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by denis on 16.02.15.
 */
public class RecordModel {

    @SerializedName("PlayerName")
    private String PlayerName;
    @SerializedName("PlayerScore")
    private int PlayerScore;
    @SerializedName("AndroidScore")
    private int DroidScore;
    @SerializedName("GameTimeDuration")
    private long GameTime;

    public RecordModel(String playerName, int playerScore, int droidScore, long gameTime) {

        PlayerName = playerName;
        PlayerScore = playerScore;
        DroidScore = droidScore;
        GameTime = gameTime;
    }

    public void setPlayerScore(int playerScore) {
        PlayerScore = playerScore;
    }

    public void setDroidScore(int droidScore) {
        DroidScore = droidScore;
    }

    public void setGameTime(long gameTime) {
        GameTime = gameTime;
    }

    public void setPlayerName(String playerName) {
        PlayerName = playerName;
    }

    public int getPlayerScore() {
        return PlayerScore;
    }

    public int getDroidScore() {
        return DroidScore;
    }

    public long getGameTime() {
        return GameTime;
    }

    public String getPlayerName() {
        return PlayerName;
    }
}
