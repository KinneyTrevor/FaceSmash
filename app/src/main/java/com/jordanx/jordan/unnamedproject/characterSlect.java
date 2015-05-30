package com.jordanx.jordan.unnamedproject;

import android.app.Activity;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.PathShape;
import android.graphics.drawable.shapes.Shape;
import android.os.Bundle;
import android.widget.ImageButton;

public class characterSlect extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_slect);
        ImageButton tv = (ImageButton) findViewById(R.id.icon1);

        Path path = new Path();
        float stdW = 100;
        float stdH = 100;
        float w3 = stdW / 3;
        float h2 = stdH / 2;
        path.moveTo(0, h2);
        h2 -= 6 / 2;
        path.rLineTo(w3, -h2);         path.rLineTo(w3, 0); path.rLineTo(w3, h2);
        path.rLineTo(-w3, h2); path.rLineTo(-w3, 0); path.rLineTo(-w3, -h2);
        Shape s = new PathShape(path, stdW, stdH);
        ShapeDrawable d = new ShapeDrawable(s);
        Paint p = d.getPaint();
        p.setColor(0xffeeeeee);
        p.setStyle(Paint.Style.STROKE);
        p.setStrokeWidth(6);

        tv.setBackgroundDrawable(d);
    }

}