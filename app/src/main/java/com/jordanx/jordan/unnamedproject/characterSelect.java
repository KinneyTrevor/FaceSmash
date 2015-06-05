package com.jordanx.jordan.unnamedproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class characterSelect extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_select);
    }
    Intent x;

    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.icon1:
                Animation animScale = AnimationUtils.loadAnimation(this, R.anim.scale);
                v.startAnimation(animScale);
                x = new Intent(getApplicationContext(), GameScreen.class);
                x.putExtra("image", "icon1.png");
                startActivity(x);
                break;
            case R.id.icon2:
                x = new Intent(getApplicationContext(), GameScreen.class);
                x.putExtra("image","icon2.png");
                startActivity(x);
                break;
            case R.id.icon3:
                x = new Intent(getApplicationContext(), GameScreen.class);
                x.putExtra("image","icon3.png");
                startActivity(x);
                break;
            case R.id.icon4:
                x = new Intent(getApplicationContext(), GameScreen.class);
                x.putExtra("image","icon4.png");
                startActivity(x);
                break;
            case R.id.icon5:
                x = new Intent(getApplicationContext(), GameScreen.class);
                x.putExtra("image","icon5.png");
                startActivity(x);
                break;
        }
    }

}