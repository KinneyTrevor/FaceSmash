package com.jordanx.jordan.unnamedproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import java.util.Random;

public class timedGameScreen extends Activity {
    private int userScore;
    Bitmap photo;
    TextView timerText;
    TextView scoreText;
    int timerValue = 30000;
    final Context context = this;
    CountDownTimer mCountDownTimer;
    CountDownTimer coinTimer;
    boolean isRunning = false;
    Drawable x;
    String value;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        ImageButton charButton = (ImageButton) findViewById(R.id.goodIcon);
        x = getResources().getDrawable(R.drawable.icon1);
        charButton.setImageDrawable(x);
        CountDownTimer createCoin = new CountDownTimer(600000, 5000) { //change me back 30,000/750 to make time reasonable
            public void onTick(long millisUntilFinished) {
                if (timerValue > 0) {
                    createCoin();
                    //moveCoin();
                }
            }

            public void onFinish() {
            }
        }.start();
        //If this got started from activity_camera.java grab the photo that was passed with it
        if (getIntent().hasExtra("image")) {
            // photo = BitmapFactory.decodeByteArray(
            // getIntent().getByteArrayExtra("byteArray"), 0, getIntent().getByteArrayExtra("byteArray").length);

            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                value = extras.getString("image");
                switch(value) {
                    case "icon1.png":
                        x = getResources().getDrawable(R.mipmap.icon1);
                        charButton.setImageDrawable(x);
                        break;
                    case "icon2.png":
                        x = getResources().getDrawable(R.mipmap.icon2);
                        charButton.setImageDrawable(x);
                        //   charButton.setBackgroundResource(R.drawable.icon2);
                        break;
                    case "icon3.png":
                        x = getResources().getDrawable(R.mipmap.icon3);
                        charButton.setImageDrawable(x);
                        //   charButton.setBackgroundResource(R.drawable.icon3);
                        break;
                    case "icon4.png":
                        x = getResources().getDrawable(R.mipmap.icon4);
                        charButton.setImageDrawable(x);
                        //   charButton.setBackgroundResource(R.drawable.icon4);
                        break;
                    case "icon5.png":
                        x = getResources().getDrawable(R.mipmap.icon5);
                        charButton.setImageDrawable(x);
                        //  charButton.setBackgroundResource(R.drawable.icon5);
                        break;
                }
            }
        }

        createCountDown(timerValue);
        createBadTimer(timerValue);
        timerText = (TextView) findViewById(R.id.timerText);
        ImageButton stupidButton = (ImageButton) findViewById(R.id.coinButton);
        //TIMER FOR MOVING THE BUTTON AUTOMATICALLY
        CountDownTimer z = new CountDownTimer(60000, 650) { //change me back 30,000/750 to make time reasonable
            public void onTick(long millisUntilFinished) {
                if (timerValue > 0) {
                    moveButton();
                    //moveCoin();
                }
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

    public void createBadTimer(int timerVal){
        mCountDownTimer = new CountDownTimer(timerVal, 2500) {
            public void onTick(long millisUntilFinished) {
                badCreate();
            }
            public void onFinish() {
            }
        }.start();
    }//1
    public void badCreate(){
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;
        ImageButton altbutton = (ImageButton) findViewById(R.id.badIcon);
        altbutton.setVisibility(View.VISIBLE);
        altbutton.setClickable(true);
        Random r2 = new Random();
        int Button2H = r2.nextInt(width - 400);
        int Button2W = r2.nextInt(height - 400);
        altbutton.setX(Button2H);
        altbutton.setY(Button2W);
        new CountDownTimer(3000, 1000) {
            public void onTick(long millisUntilFinished) {
            }
            public void onFinish() {
                destroyBad();
            }
        }.start();
    }
    //Creates the coin
    public void createCoin(){
        if (isRunning == true){
            coinTimer.cancel();}
        ImageButton coinButton = (ImageButton) findViewById(R.id.coinButton);
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;
        Random r3 = new Random();
        int Button3H = r3.nextInt(width - 400);
        int Button3W = r3.nextInt(height - 400);
        coinButton.setX(Button3H);
        coinButton.setY(Button3W);
        coinButton.setVisibility(View.VISIBLE);
        coinButton.setClickable(true);
        coinTimer = new CountDownTimer(4000, 1000){
            public void onTick(long millisUntilFinished){
                isRunning = true;
            }
            public void onFinish(){
                destroyCoin();
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
        Drawable z = getResources().getDrawable(R.mipmap.splat);
        ImageButton charButton = (ImageButton) findViewById(R.id.goodIcon);
        charButton.setImageDrawable(z);
        scoreText = (TextView) findViewById(R.id.score);
        userScore++;
        scoreText.setText(String.valueOf(userScore));
    }

    public void destroyBad(){
        ImageButton altbutton = (ImageButton) findViewById(R.id.badIcon);
        altbutton.setVisibility(View.GONE);
        altbutton.setClickable(false);
    }

    public void destroyCoin(){
        ImageButton coinButton = (ImageButton) findViewById(R.id.badIcon);
        coinButton.setVisibility(View.GONE);
        coinButton.setClickable(false);
    }

    //Lowers score, time, flashes red
    public void badClick(View v){
        ImageButton altbutton = (ImageButton) findViewById(R.id.badIcon);
        altbutton.setVisibility(View.GONE);
        altbutton.setClickable(false);
        userScore=userScore-3;
        scoreText = (TextView) findViewById(R.id.score);
        scoreText.setText(String.valueOf(userScore  ));
        timerValue = timerValue-2000;
    }
    public void coinClick(View v){
        ImageButton coin = (ImageButton) findViewById(R.id.coinButton);
        SharedPreferences mypreferences = getSharedPreferences("App_preferences_file", Context.MODE_PRIVATE);
        int coinAmt = mypreferences.getInt("coinCount", 0);
        coinAmt++;
        SharedPreferences.Editor editor = mypreferences.edit();
        editor.putInt("coinCount", coinAmt);
        editor.commit();
        coin.setVisibility(View.GONE);
    }
    //Moves the actual button
    public void moveButton() {
        ImageButton charButton = (ImageButton) findViewById(R.id.goodIcon);
        charButton.setImageDrawable(x);
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;
        ImageButton theButton = (ImageButton) findViewById(R.id.goodIcon);

        Random r1 = new Random();
        int Button1H = r1.nextInt(width - 400);
        int Button1W = r1.nextInt(height - 400);
        theButton.setX(Button1H);
        theButton.setY(Button1W);

//Drug addict code here...
        /*
        if(checkOverlap(Button1H, Button1W, Button2H, Button2W) == true) {
            theButton.setX(Button1H+500);
            theButton.setY(Button1W+500);
            altbutton.setX(Button2H+500);
            altbutton.setY(Button2W+500);
        }
        */
    }

    //Function to check overlap between buttons - Is kind of working but need to fix them crossing the border.. damn Mexicans
    public boolean checkOverlap(int x1, int x2, int y1, int y2){
        int Button1H = x1, Button1W = y1, Button2H = x2, Button2W = y2; //
        if(Button1H <= Button2H + 250 && Button1H >= Button2H - 250  ){
            return true;
        }
        else if (Button2H <= Button2H + 250 && Button2H >= Button2H - 250){
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
                .setMessage("Would you like to resume the game?")
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
                .setCancelable(false)
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
                .setCancelable(false)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    //Allows user to restart the game or go to main menu
    public void endGame(){
        mCountDownTimer.cancel();
        timerValue = 0;
        new AlertDialog.Builder(this)
                .setTitle("Game Over!")
                .setMessage("Would you like to play again?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent hs = new Intent(getApplicationContext(), GameScreen.class);
                                hs.putExtra("image", value);
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