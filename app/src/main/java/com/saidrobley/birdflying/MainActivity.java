package com.saidrobley.birdflying;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    DrawBird mDrawBird;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDrawBird = new DrawBird(this);
        setContentView(mDrawBird);
        Intent intent = new Intent(this, BirdMover.class);
        startActivity(intent);
    }
}
