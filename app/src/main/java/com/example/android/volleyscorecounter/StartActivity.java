package com.example.android.volleyscorecounter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static android.R.attr.name;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);


        Button gameBegin = (Button) findViewById(R.id.gameBegin);
        final EditText teamAname= (EditText) findViewById(R.id.teamAname);
        final EditText teamBname= (EditText) findViewById(R.id.teamBname);

        // Set a click listener on the button let the game begin and pass team names to mainActivity
        gameBegin.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the colors View is clicked on.
            @Override
            public void onClick(View view) {
                // passes team names to MainActivity
                Intent buttonIntent = new Intent(StartActivity.this, MainActivity.class);
                if(teamAname.getText().toString().equals("")){
                buttonIntent.putExtra("teamA", "Team A");
                }else{
                    buttonIntent.putExtra("teamA", teamAname.getText().toString());
                }
                if(teamBname.getText().toString().equals("")){
                    buttonIntent.putExtra("teamB", "Team B");
                }else{
                    buttonIntent.putExtra("teamB", teamBname.getText().toString());
                }
                startActivity(buttonIntent);
            }
        });
    }
}
