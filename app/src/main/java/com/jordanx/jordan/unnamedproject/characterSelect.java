package com.jordanx.jordan.unnamedproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class characterSelect extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_select);
    }
    Intent x;
    public void buttonpush(){

    }
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.icon1:
                x = new Intent(getApplicationContext(), GameScreen.class);
                x.putExtra("imgsrc", "value");
                startActivity(x);
                break;
            case R.id.icon2:
                x = new Intent(getApplicationContext(), GameScreen.class);
                x.putExtra("imgsrc","value");
                startActivity(x);
                break;
            case R.id.icon3:
                x = new Intent(getApplicationContext(), GameScreen.class);
                x.putExtra("imgsrc","value");
                startActivity(x);
                break;
            case R.id.icon4:
                x = new Intent(getApplicationContext(), GameScreen.class);
                x.putExtra("imgsrc","value");
                startActivity(x);
                break;
            case R.id.icon5:
                x = new Intent(getApplicationContext(), GameScreen.class);
                x.putExtra("imgsrc","value");
                startActivity(x);
                break;
        }
    }

}