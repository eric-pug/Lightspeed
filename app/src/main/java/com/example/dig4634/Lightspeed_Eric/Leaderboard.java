package com.example.dig4634.Lightspeed_Eric;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Leaderboard extends AppCompatActivity {

    private DatabaseManager dbHelper;
    private SQLiteDatabase db;

    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.leaderboard);

        dbHelper = new DatabaseManager(this);

        boolean postGame = false;

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            score = extras.getInt("score", -1);
            if (score != -1)
            {
                postGame = true;
            }
        }

        if (!postGame) //We got here from the menu
        {
            findViewById(R.id.submitButton).setVisibility(View.GONE);
            findViewById(R.id.inputEditText).setVisibility(View.GONE);
            findViewById(R.id.score).setVisibility(View.GONE);
            findViewById(R.id.FinalScore).setVisibility(View.GONE);
        }
        else //doing normal stuff
        {

            /*String value="Hello world";
            Intent i = new Intent(CurrentActivity.this, NewActivity.class);
            i.putExtra("key",value);
            startActivity(i);
             */
            Log.e("TEST", String.valueOf(score));
            findViewById(R.id.submitButton).setVisibility(View.VISIBLE);
            findViewById(R.id.inputEditText).setVisibility(View.VISIBLE);
            findViewById(R.id.score).setVisibility(View.VISIBLE);
            findViewById(R.id.FinalScore).setVisibility(View.VISIBLE);
            TextView temp = findViewById(R.id.score);
            temp.setText(Integer.toString(score));
        }

        List<PlayerData> playerList = getLeaderboardData(); // Retrieve data from the database

        // Set up RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        LeaderboardAdapter adapter = new LeaderboardAdapter(playerList);
        recyclerView.setAdapter(adapter);
    }

    public void onSubmitClicked(View view)
    {
        db = dbHelper.getWritableDatabase();
        EditText editText = findViewById(R.id.inputEditText); // Reference to your EditText field

        String text = editText.getText().toString();

        editText.setText("");
        //Grab score from wherever it is on the field
        // Insert sample data into the leaderboard table
        ContentValues values = new ContentValues();
        values.put("player_name", text);
        values.put("score", score);
        db.insert("leaderboard", null, values);
        db.close();

        List<PlayerData> playerList = getLeaderboardData(); // Retrieve data from the database

        findViewById(R.id.submitButton).setVisibility(View.GONE);
        findViewById(R.id.inputEditText).setVisibility(View.GONE);

        // Set up RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        LeaderboardAdapter adapter = new LeaderboardAdapter(playerList);
        recyclerView.setAdapter(adapter);
    }

    public List<PlayerData> getLeaderboardData() {
        List<PlayerData> playerList = new ArrayList<>();

        // Assuming you have a SQLiteDatabase instance already initialized
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {"player_name", "score"};
        String sortOrder = "score DESC"; // Sort by score in descending order

        Cursor cursor = db.query("leaderboard", projection, null, null, null, null, sortOrder);

        while (cursor.moveToNext()) {
            @SuppressLint("Range") String playerName = cursor.getString(cursor.getColumnIndex("player_name"));
            @SuppressLint("Range") int score = cursor.getInt(cursor.getColumnIndex("score"));

            PlayerData player = new PlayerData(playerName, score); // Create a Player object
            playerList.add(player); // Add the player to the list
        }

        cursor.close();
        db.close();

        return playerList;
    }




}
