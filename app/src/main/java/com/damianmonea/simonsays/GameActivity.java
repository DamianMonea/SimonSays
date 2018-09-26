package com.damianmonea.simonsays;

import android.graphics.drawable.TransitionDrawable;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;

public class GameActivity extends AppCompatActivity{


    private Button[] buttons = new Button[5];
    private int[] btnStates = new int[5];
    private int animationSpeed;
    private double speed;
    private int delay;
    private int difficulty;
    private TextView score;
    private int timesRun;
    private Thread button1_thread;

    public int changeColor(Button btn, int state){
        TransitionDrawable transition = (TransitionDrawable) btn.getBackground();
        if(state==0) {
            transition.startTransition(animationSpeed);
            state = 1;
        }
        else{
            transition.reverseTransition(animationSpeed);
            state = 0;
        }
        return state;
    }

    private void startGame(final Button[] myButtons, final int[] btnStates){
        final Handler myHandler = new Handler();
        Random randomizer = new Random();

        int[] sequence = new int[5];
        for(int i = 0; i < difficulty; i++)
            sequence[i] = randomizer.nextInt(4);
        score.setText(String.valueOf(sequence[0]) + String.valueOf(sequence[1]) + String.valueOf(sequence[2]) + String.valueOf(sequence[3]) + String.valueOf(sequence[4]));
        final Runnable[] runnables = new Runnable[5];

        runnables[0] = new Runnable() {
            @Override
            public void run() {
                btnStates[0] = changeColor(myButtons[0], btnStates[0]);
            }
        };

        runnables[1] = new Runnable() {
            @Override
            public void run() {
                btnStates[1] = changeColor(myButtons[1], btnStates[1]);
            }
        };

        runnables[2] = new Runnable() {
            @Override
            public void run() {
                btnStates[2] = changeColor(myButtons[2], btnStates[2]);
            }
        };

        runnables[3] = new Runnable() {
            @Override
            public void run() {
                btnStates[3] = changeColor(myButtons[3], btnStates[3]);
            }
        };

        playSequence(myButtons, btnStates, sequence, runnables, 0, difficulty, myHandler);
    }

    private void playSequence(final Button[] myButtons,final int[] btnStates,final int[] sequence,final Runnable[] runnables,final int current,final int last,final Handler h) {
        btnStates[sequence[current]] = changeColor(myButtons[sequence[current]], btnStates[sequence[current]]);
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                btnStates[sequence[current]] = changeColor(myButtons[sequence[current]], btnStates[sequence[current]]);
                if(current < last - 1)
                    playSequence(myButtons, btnStates, sequence, runnables, current + 1, last, h);
            }
        }, 500);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        speed = 1.0;
        animationSpeed = (int) (300 / speed);
        delay = animationSpeed;
        difficulty = 5;

        final Handler handler = new Handler();
        score = findViewById(R.id.textView);
//        final MediaPlayer player = MediaPlayer.create(this,R.raw.a);
//        Button startBtn = findViewById(R.id.button9);

        buttons[0] = findViewById(R.id.button2);
        buttons[1] = findViewById(R.id.button3);
        buttons[2] = findViewById(R.id.button4);
        buttons[3] = findViewById(R.id.button5);

        for(int i = 0; i < 4; i++)
            btnStates[i] = 0;


        buttons[0].setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                btnStates[0] = changeColor(buttons[0], btnStates[0]);
//                player.start();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btnStates[0] = changeColor(buttons[0], btnStates[0]);
                    }
                },delay);
            }
        });

        buttons[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnStates[1] = changeColor(buttons[1], btnStates[1]);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btnStates[1] = changeColor(buttons[1], btnStates[1]);
                    }
                },delay);
            }
        });

        buttons[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnStates[2] = changeColor(buttons[2], btnStates[2]);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btnStates[2] = changeColor(buttons[2], btnStates[2]);
                    }
                },delay);
            }
        });

        buttons[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnStates[3] = changeColor(buttons[3], btnStates[3]);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btnStates[3] = changeColor(buttons[3], btnStates[3]);
                    }
                },delay);
            }
        });

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startGame(buttons, btnStates);

            }
        },1000);
    }

}

