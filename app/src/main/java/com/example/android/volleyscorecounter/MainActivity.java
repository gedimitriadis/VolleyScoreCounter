package com.example.android.volleyscorecounter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.attr.button;
import static android.R.attr.contextClickable;
import static android.R.attr.name;
import static com.example.android.volleyscorecounter.R.id.pointsTeamA;
import static com.example.android.volleyscorecounter.R.id.setsTeamA;
import static com.example.android.volleyscorecounter.R.string.teamA;
import static com.example.android.volleyscorecounter.R.string.teamB;

public class MainActivity extends AppCompatActivity {

    Button addPointA, addPointB, resetAll;
    TextView pointsTeamA, pointsTeamB, setsTeamA, setsTeamB, score1, score2, score3, score4, score5, teamNameA, teamNameB;
    int scoreTeamA = 0;
    int scoreTeamB = 0;
    int setTeamA = 0;
    int setTeamB = 0;
    int setCounter = 1;
    String resultSet1 = "0 - 0";
    String resultSet2 = "0 - 0";
    String resultSet3 = "0 - 0";
    String resultSet4 = "0 - 0";
    String resultSet5 = "0 - 0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* get team names from StartActivity*/
        teamNameA = (TextView) findViewById(R.id.textViewTeamA);
        teamNameB = (TextView) findViewById(R.id.textViewTeamB);
        final Intent myIntent = this.getIntent();
        teamNameA.setText(myIntent.getStringExtra("teamA"));
        teamNameB.setText(myIntent.getStringExtra("teamB"));

        /* Buttons*/
        addPointA = (Button) findViewById(R.id.buttonA);
        addPointB = (Button) findViewById(R.id.buttonB);
        resetAll = (Button) findViewById(R.id.buttonReset);

        /* Textviews for  points and sets*/
        pointsTeamA = (TextView) findViewById(R.id.pointsTeamA);
        pointsTeamB = (TextView) findViewById(R.id.pointsTeamB);
        setsTeamA = (TextView) findViewById(R.id.setsTeamA);
        setsTeamB = (TextView) findViewById(R.id.setsTeamB);

        /* Set scores at the bottom of the screen */
        score1 = (TextView) findViewById(R.id.set1Score);
        score2 = (TextView) findViewById(R.id.set2Score);
        score3 = (TextView) findViewById(R.id.set3Score);
        score4 = (TextView) findViewById(R.id.set4Score);
        score5 = (TextView) findViewById(R.id.set5Score);

        /* what happens when the +1p button for Team A is clicked */
        addPointA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if game is not over
                if (setTeamA < 3 && setTeamB < 3) {
                    scoreTeamA += 1;
                    pointsTeamA.setText(String.valueOf(scoreTeamA));
                    // if set is over
                    if (((scoreTeamA >= maxSetPoints(setCounter)) && (scoreTeamA - scoreTeamB) >= 2)) {
                        setTeamA = setTeamA + 1;
                        setsTeamA.setText(String.valueOf(setTeamA));
                        setFinalScores(setCounter);
                        setCounter += 1;
                        scoreTeamA = 0;
                        scoreTeamB = 0;
                        pointsTeamA.setText("0");
                        pointsTeamB.setText("0");
                    }
                } else {
                    //shows the winning team A
                    Toast.makeText(MainActivity.this, myIntent.getStringExtra("teamA") + " wins the game", Toast.LENGTH_SHORT).show();
                    //disable buttons for adding points
                    addPointB.setClickable(false);
                    addPointA.setClickable(false);
                }

            }
        });

        /* what happens when the +1p button for Team B is clicked */
        addPointB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if game is not over
                if (setTeamB < 3 && setTeamA < 3) {
                    scoreTeamB += 1;
                    pointsTeamB.setText(String.valueOf(scoreTeamB));
                    // if set is over
                    if (((scoreTeamB >= maxSetPoints(setCounter)) && (scoreTeamB - scoreTeamA) >= 2)) {
                        setTeamB = setTeamB + 1;
                        setsTeamB.setText(String.valueOf(setTeamB));
                        setFinalScores(setCounter);
                        setCounter += 1;
                        scoreTeamA = 0;
                        scoreTeamB = 0;
                        pointsTeamA.setText("0");
                        pointsTeamB.setText("0");
                    }
                } else {
                    //shows the winning team B
                    Toast.makeText(MainActivity.this, myIntent.getStringExtra("teamB") + " wins the game", Toast.LENGTH_SHORT).show();
                    //disable buttons for adding points
                    addPointA.setClickable(false);
                    addPointB.setClickable(false);
                }

            }
        });

        /* reset sets, points, team names */
        resetAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scoreTeamA = 0;
                scoreTeamB = 0;
                setTeamA = 0;
                setTeamB = 0;
                setCounter = 1;
                pointsTeamA.setText("0");
                pointsTeamB.setText("0");
                setsTeamA.setText("0");
                setsTeamB.setText("0");
                resultSet1 = "0 - 0";
                resultSet2 = "0 - 0";
                resultSet3 = "0 - 0";
                resultSet4 = "0 - 0";
                resultSet5 = "0 - 0";
                score1.setText("0 - 0");
                score2.setText("0 - 0");
                score3.setText("0 - 0");
                score4.setText("0 - 0");
                score5.setText("0 - 0");
                teamNameA.setText("Team A");
                teamNameB.setText("Team B");
                addPointB.setClickable(true);
                addPointA.setClickable(true);
            }
        });

    }

    /* sets the set score at the bottom of the screen when a set is complete */
    public void setFinalScores(int setCounter) {
        if (setCounter == 1) {
            resultSet1 = pointsTeamA.getText().toString() + " - " + pointsTeamB.getText().toString();
            score1.setText(resultSet1);
        } else if (setCounter == 2) {
            resultSet2 = pointsTeamA.getText().toString() + " - " + pointsTeamB.getText().toString();
            score2.setText(resultSet2);
        } else if (setCounter == 3) {
            resultSet3 = pointsTeamA.getText().toString() + " - " + pointsTeamB.getText().toString();
            score3.setText(resultSet3);
        } else if (setCounter == 4) {
            resultSet4 = pointsTeamA.getText().toString() + " - " + pointsTeamB.getText().toString();
            score4.setText(resultSet4);
        } else if (setCounter == 5) {
            resultSet5 = pointsTeamA.getText().toString() + " - " + pointsTeamB.getText().toString();
            score5.setText(resultSet5);
        }
    }

    // sets the maximum points for a set (25 for regular and 15 for tie break)
    public int maxSetPoints(int setCounter) {
        int setPoints;
        if (setCounter <= 4) {
            setPoints = 25;
        } else setPoints = 15;
        return setPoints;
    }


    //Save current values when rotating screen
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.putInt("points_team_a", scoreTeamA);
        savedInstanceState.putInt("points_team_b", scoreTeamB);
        savedInstanceState.putInt("set_team_a", setTeamA);
        savedInstanceState.putInt("set_team_b", setTeamB);
        savedInstanceState.putInt("set_counter", setCounter);
        savedInstanceState.putString("result_set1", resultSet1);
        savedInstanceState.putString("result_set2", resultSet2);
        savedInstanceState.putString("result_set3", resultSet3);
        savedInstanceState.putString("result_set4", resultSet4);
        savedInstanceState.putString("result_set5", resultSet5);
    }


    //Restore current values when rotating screen
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        // Restore state from saved instance
        scoreTeamA = savedInstanceState.getInt("points_team_a");
        scoreTeamB = savedInstanceState.getInt("points_team_b");
        setTeamA = savedInstanceState.getInt("set_team_a");
        setTeamB = savedInstanceState.getInt("set_team_b");
        setCounter = savedInstanceState.getInt("set_counter");
        resultSet1 = savedInstanceState.getString("result_set1");
        resultSet2 = savedInstanceState.getString("result_set2");
        resultSet3 = savedInstanceState.getString("result_set3");
        resultSet4 = savedInstanceState.getString("result_set4");
        resultSet5 = savedInstanceState.getString("result_set5");

        pointsTeamA.setText(String.valueOf(scoreTeamA));
        pointsTeamB.setText(String.valueOf(scoreTeamB));
        setsTeamA.setText(String.valueOf(setTeamA));
        setsTeamB.setText(String.valueOf(setTeamB));
        score1.setText(resultSet1);
        score2.setText(resultSet2);
        score3.setText(resultSet3);
        score4.setText(resultSet4);
        score5.setText(resultSet5);
    }
}