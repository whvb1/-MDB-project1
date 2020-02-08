package com.example.mdbproject1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.graphics.Movie;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnStart;
    ConstraintLayout mainBackground;
    AnimationDrawable myAnimation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //instantiating the buttons
        btnStart = findViewById(R.id.btnStart);

        //onCLickLIsteners
        btnStart.setOnClickListener(this);
        btnStart.setBackground(getDrawable(R.drawable.button_style));

        //background stuff
        //Log.d("background dev: ",""+R.id.mainBackground);
        //mainBackground = findViewById(R.id.mainBackground);
        //myAnimation = (AnimationDrawable)mainBackground.getBackground();
        //myAnimation.setEnterFadeDuration(4500);
        //myAnimation.setExitFadeDuration(4500);
        //myAnimation.start();








    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnStart:
                //take to start screen
                Intent startIntent = new Intent(this, GameActivity.class);
                this.startActivity(startIntent);
                break;
            default:
                break;
        }


    }
}


