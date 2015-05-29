package com.jordanx.jordan.unnamedproject;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class MainMenu extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }
    public void launchCamera(View view){
       Intent launchCam = new Intent(view.getContext(),GameScreen.class);
       startActivity(launchCam);
    }
    public void quitApp(View view){
        finish();
        System.exit(0);
    }

    public void highScores(View view){
        //Intent hs = new Intent(view.getContext(),HighScores.class);
      //  startActivity(hs);
    }
}