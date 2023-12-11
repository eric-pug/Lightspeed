package com.example.dig4634.Lightspeed_Eric;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class StartScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);
    }

    public void onPlayPressed(View view) {
        Intent myIntent=new Intent(getBaseContext(),MainActivity.class);
        startActivity(myIntent);
    }

    public void onTutorialPressed(View view) {
        Intent myIntent=new Intent(getBaseContext(),Tutorial.class);
        startActivity(myIntent);
    }

    public void onLeaderboardPressed(View view) {
        Intent myIntent=new Intent(getBaseContext(),Leaderboard.class);
        startActivity(myIntent);
    }
}