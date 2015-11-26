package com.saidrobley.birdflying;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by saidrobley on 11/24/15.
 */
public class BirdMover extends Activity implements SensorEventListener {
    Bitmap bird;
    SensorManager sm;
    DrawBird brdView;
    float x, y, sensorX, sensorY;

    @Override
    public void onSensorChanged(SensorEvent event) {
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sensorX = event.values[0];
        sensorY = event.values[1];

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public class DrawBird extends SurfaceView implements Runnable{
        SurfaceHolder mHolder;
        Thread mThread = null;
        boolean isRunning = true;

        public DrawBird(Context context) {
            super(context);
            mHolder = getHolder();
        }
        public void pause(){
            isRunning = false;
            while (true){
                try {
                    mThread.join();
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
                break;
            }
            mThread = null;
        }

        public void resume(){
            isRunning = true;
            mThread = new Thread(this);
            mThread.start();
        }

        @Override
        public void run() {
            while (isRunning){
                if(!mHolder.getSurface().isValid())
                    continue;
                Canvas canvas = mHolder.lockCanvas();
                canvas.drawColor(Color.WHITE);
                 float startX = 250;
                 float startY = 250;
                 canvas.drawBitmap(bird, startX + sensorX*30, startY + sensorY*30, null);
                //canvas.drawBitmap(bird, sensorX*20, sensorY*20, null);
                mHolder.unlockCanvasAndPost(canvas);
            }

        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if(sm.getSensorList(Sensor.TYPE_ACCELEROMETER).size() !=0){
            Sensor s = sm.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);
            sm.registerListener(this, s, SensorManager.SENSOR_DELAY_NORMAL);
        }
        bird = BitmapFactory.decodeResource(getResources(), R.drawable.bird1);
        x = y = sensorX = sensorY = 0;
        brdView = new DrawBird(this);
        brdView.resume();
        setContentView(brdView);

    }
    @Override
    protected void onPause(){
        sm.unregisterListener(this);
        super.onPause();
    }
}
