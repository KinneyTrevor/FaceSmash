package com.jordanx.jordan.unnamedproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;

public class GameScreen extends Activity {
    private int userScore;
    Bitmap photo;
    TextView timerText;
    TextView scoreText;
    int timerValue = 60000; //change me back to 30,000
    final Context context = this;
    CountDownTimer mCountDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);
        //If this got started from activity_camera.java grab the photo that was passed with it
        if (getIntent().hasExtra("byteArray")) {
            photo = BitmapFactory.decodeByteArray(
                    getIntent().getByteArrayExtra("byteArray"), 0, getIntent().getByteArrayExtra("byteArray").length);
        }

        createCountDown(timerValue);
        timerText = (TextView) findViewById(R.id.timerText);
        //BitmapDrawable bdrawable = new BitmapDrawable(photo);
        //ImageButton derp = (ImageButton) findViewById(R.id.iconButton);


        //TIMER FOR MOVING THE BUTTON AUTOMATICALLY
        CountDownTimer z = new CountDownTimer(60000, 750) { //change me back 30,000/750 to make time reasonable
            public void onTick(long millisUntilFinished) {
                moveButton();
            }

            public void onFinish() {
            }
        }.start();
    }
//This is the main time count down at the top of the screen
    public void createCountDown(int timerVal){
       mCountDownTimer = new CountDownTimer(timerVal, 1000) {
            public void onTick(long millisUntilFinished) {
                timerText.setText("" + millisUntilFinished / 1000);
                String z = timerText.getText().toString();
                timerValue = (Integer.parseInt(z)) * 1000;
            }

            public void onFinish() {
                timerText.setText("" + 0);
                updateHS(userScore);
            }
        }.start();
    }
//Called at the end of the game, grabs the highscore and compares it to the current highscore, if bigger it updates, if less just goes to quit
    public void updateHS(int x) {
        SharedPreferences mypreferences = getSharedPreferences("App_preferences_file", Context.MODE_PRIVATE);
        int currentHS = mypreferences.getInt("highscore", 0);
        if (userScore > currentHS) {
            SharedPreferences.Editor editor = mypreferences.edit();
            editor.putInt("highscore", x);
            editor.commit();
            Toast.makeText(getApplicationContext(), "New High Score!", Toast.LENGTH_LONG).show();
            endGame();
        } else {
            endGame();
        }
    }
//Increments user score.
    public void btnClick(View v) {
        scoreText = (TextView) findViewById(R.id.score);
        userScore++;
        scoreText.setText(String.valueOf(userScore));
    }
//Moves the actual button
    public void moveButton() {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;
        ImageButton theButton = (ImageButton) findViewById(R.id.goodIcon);
        ImageButton altbutton = (ImageButton) findViewById(R.id.badIcon);

        //Button one
        Random r = new Random();
        int Button1H = r.nextInt(width - 400);
        int Button1W = r.nextInt(height - 1650);
        theButton.setX(Button1H);
        theButton.setY(Button1W);
        
        //Button two - button
        r = new Random();
        int Button2H = r.nextInt(width - 400);
        int Button2W = r.nextInt(height - 1650);
        altbutton.setX(Button2H);
        altbutton.setY(Button2W);

    }
    //Function to check overlap between buttons -
    public boolean checkOverlap(int x1, int x2, int y1, int y2){
        int Button1H = x1, Button1W = y1, Button2H = x2, Button2W = y2; //
        if(Button1H <= Button2H + 250 && Button1H >= Button2H - 250){
            return true;
        }
        return false;
    }

   //Called if user presses menu button or back button
    public void onPause(){
        super.onPause();
        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
        }
        new AlertDialog.Builder(this)
                .setTitle("Time Paused")
                .setMessage("Would you like to restart the game?")
                .setPositiveButton("Resume", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                createCountDown(timerValue);
                                dialog.cancel();
                            }
                        }
                )
                .setNegativeButton("Leave", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent hs = new Intent(getApplicationContext(), MainMenu.class);
                        startActivity(hs);
                        dialog.cancel();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

//Called when someone presses the pause button
    public void pauseClick(View a) {
        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
        }
        new AlertDialog.Builder(this)
                .setTitle("Time Paused")
                .setMessage("Would you like to restart the game?")
                .setPositiveButton("Resume", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                createCountDown(timerValue);
                                dialog.cancel();
                            }
                        }
                )
                .setNegativeButton("Leave", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent hs = new Intent(getApplicationContext(), MainMenu.class);
                        startActivity(hs);
                        dialog.cancel();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
//Allows user to restart the game or go to main menu
    public void endGame(){
        new AlertDialog.Builder(this)
                .setTitle("Game Over!")
                .setMessage("Would you like to play again?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent hs = new Intent(getApplicationContext(), GameScreen.class);
                                startActivity(hs);
                                dialog.cancel();
                            }
                        }
                )
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        Intent hs = new Intent(getApplicationContext(), MainMenu.class);
                        startActivity(hs);
                        dialog.cancel();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

}