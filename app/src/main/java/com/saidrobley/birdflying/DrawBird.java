package com.saidrobley.birdflying;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

/**
 * Created by saidrobley on 11/24/15.
 */
public class DrawBird extends SurfaceView {

    Bitmap bird;
    SurfaceHolder mHolder;

    public DrawBird(Context context) {
        super(context);
        bird = BitmapFactory.decodeResource(getResources(), R.drawable.bird1);
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        //canvas.drawBitmap(bird, 50, 100, null);

    }














}
