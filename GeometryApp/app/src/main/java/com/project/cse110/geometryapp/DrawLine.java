package com.project.cse110.geometryapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.Image;
import android.text.Layout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

/**
 * Created by devinhickey on 2/20/16.
 */
public class DrawLine extends View{
    Paint paint = new Paint();
    ImageButton button1;
    ImageButton button2;

    public DrawLine(Context context, ImageButton button1, ImageButton button2) {
        super(context);

        this.button1 = button1;
        this.button2 = button2;

        paint.setColor(Color.RED);
        paint.setStrokeWidth(10);

    }

    @Override
    public void onDraw(Canvas canvas) {
        float starty = button1.getY();
        float startx = button1.getX();

        float endy = button2.getY();
        float endx = button2.getX();

        canvas.drawLine(startx, starty, endx, endy, paint);
        canvas.drawLine(200, 0 ,0, 200, paint);

    }
}
