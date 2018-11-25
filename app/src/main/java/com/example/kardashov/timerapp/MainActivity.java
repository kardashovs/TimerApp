package com.example.kardashov.timerapp;

import android.content.Intent;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int milisec = 0;
    private boolean running;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null){
            milisec = savedInstanceState.getInt("milisec");
            running = savedInstanceState.getBoolean("running");
        }

        runTime();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.putInt("milisec", milisec);
        savedInstanceState.putBoolean("running", running);
    }

    public void onClickStart(View view) {
        Button button_stop = findViewById(R.id.button_stop);
        button_stop.setText(R.string.stop);

        running = true;
    }

    public void onClickStop(View view) {
        Button button_stop = findViewById(R.id.button_stop);

        button_stop.setText(R.string.reset);
        if(!running) {
            onClickReset(view);
            button_stop.setText(R.string.stop);
        }
        running = false;

    }

    public void onClickReset(View view) {
        milisec = 0;
        running = false;
    }

    private void runTime() {
        final TextView textView = findViewById(R.id.time_view);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int seconds = (milisec/10)%60;
                int minutes = ((milisec/10))/60;
                int hours = ((milisec/10))/60/60;
                String time = String.format("%d:%02d:%02d:%01d", hours, minutes, seconds, milisec%10);
                textView.setText(time);
                if (running) {
                    milisec++;
                }
                handler.postDelayed(this, 100);
            }
        });
    }
}
