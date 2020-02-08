package com.example.mdbproject1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.example.mdbproject1.MainActivity;

import static android.graphics.Color.GRAY;
import static android.graphics.Color.RED;

public class GameActivity extends AppCompatActivity implements  View.OnClickListener{

    Handler h = new Handler();

    Drawable btnColor;
    String correctName;
    String guess;
    CountDownTimer timer;
    int timeLeft;
    int pauseTime;
    int score;
    AlertDialog alert11;
    ImageView img;
    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
    Button btnExit;
    TextView txtTime;
    TextView txtScore;
    ArrayList<String> allNames = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //themeUtils.onActivityCreateSetTheme(this);
        //themeUtils.changeToTheme(this, themeUtils.DARK);
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("Return to start?");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        newIntent();
                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });





        //generating game data (something in here is broken)
        try{ allNames = Utils.namesFromFile(this);}catch(IOException E){ E.printStackTrace();}

        //allNames = Utils.namesToList(names);


        alert11 = builder1.create();




        //instantiate Text Views
        txtTime = findViewById(R.id.txtTime);
        txtScore = findViewById(R.id.txtScore);


        //instantiate Button Views
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btnExit = findViewById(R.id.btnExit);
        img = findViewById(R.id.img);

        Log.d("btn color",btn1.getBackgroundTintList().toString());



        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btnExit.setOnClickListener(this);

        //image magic
        img.setImageResource(R.drawable.willvavrik);




        //upond very first start of game
        correctName = nextTurn();

    }

    //TODO: figure out the issue with this
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn1:
                guess = btn1.getText().toString();
                if(guess.equals(correctName)){
                    score+=1;
                    btn1.setBackgroundTintList(getResources().getColorStateList(R.color.green));
                }else{
                    btn1.setBackgroundTintList(getResources().getColorStateList(R.color.red));
                }
               break;
            case R.id.btn2:
                guess = btn2.getText().toString();
                if(guess.equals(correctName)){
                    score+=1;
                    btn2.setBackgroundTintList(getResources().getColorStateList(R.color.green));
                }else {
                    btn2.setBackgroundTintList(getResources().getColorStateList(R.color.red));
                }
                break;
            case R.id.btn3:
                guess = btn3.getText().toString();
                if(guess.equals(correctName)){
                    score+=1;
                    btn3.setBackgroundTintList(getResources().getColorStateList(R.color.green));
                }else{
                    btn3.setBackgroundTintList(getResources().getColorStateList(R.color.red));
                }
                break;
            case R.id.btn4:
                guess = btn4.getText().toString();
                if(guess.equals(correctName)){
                    score+=1;
                    btn4.setBackgroundTintList(getResources().getColorStateList(R.color.green));
                }else{
                    btn4.setBackgroundTintList(getResources().getColorStateList(R.color.red));
                }
                break;
            case R.id.btnExit:
                alert11.show();

                break;
            default:
                break;
        }
        correctName = nextTurn();

    }



    //TODO: pause timer
    public void onPause(){

        pauseTime = timeLeft;
        super.onPause();
    }
    //TODO: resume timer
    public void onResume(){
        if(timer != null) {
            timer.cancel();
            timer = null;
        }
        timer = newTime(pauseTime);
        timer.start();
        super.onResume();


    }

    public String nextTurn(){
        if(timer != null) {
            timer.cancel();
            timer = null;
        }
        timer = newTime(5500);
        txtScore.setText("Score: "+Integer.toString(score));
        final ArrayList<String> fourNames = Utils.numberNamesFromList(allNames,4);
        correctName = Utils.numberNamesFromList(fourNames, 1).get(0);
        Runnable r = new Runnable() {
            @Override
            public void run() {
                btn1.setBackgroundTintList(getResources().getColorStateList(R.color.secondaryColor));
                btn2.setBackgroundTintList(getResources().getColorStateList(R.color.secondaryColor));
                btn3.setBackgroundTintList(getResources().getColorStateList(R.color.secondaryColor));
                btn4.setBackgroundTintList(getResources().getColorStateList(R.color.secondaryColor));
                btn1.setBackground(getDrawable(R.drawable.button_style));
                btn2.setBackground(getDrawable(R.drawable.button_style));
                btn3.setBackground(getDrawable(R.drawable.button_style));
                btn4.setBackground(getDrawable(R.drawable.button_style));

                btn1.setText(fourNames.get(0));
                btn2.setText(fourNames.get(1));
                btn3.setText(fourNames.get(2));
                btn4.setText(fourNames.get(3));

                timer.start();
            }
        };
        Handler h = new Handler();
        h.postDelayed(r,1000);


        img.setImageDrawable(Utils.getImage(this, correctName));


        return correctName;


    }

    public CountDownTimer newTime(int time) {
        return new CountDownTimer(time, 1000) {

            public void onTick(long millisUntilFinished) {
                timeLeft = (int)millisUntilFinished;
                txtTime.setText(""+(millisUntilFinished / 1000));
            }

            public void onFinish() {
                txtTime.setText("-");
                txtTime.setBackgroundTintList(getResources().getColorStateList(R.color.red));

                nextTurn();

            }
        };


    }
    public void newIntent()
    {
        Intent intent = new Intent(this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }


}
