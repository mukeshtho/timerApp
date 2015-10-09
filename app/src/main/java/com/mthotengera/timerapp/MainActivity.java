package com.mthotengera.timerapp;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.mthotengera.timerapp.model.WorkoutInfo;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private final long REFRESH_RATE = 1000;
    private WorkoutInfo workoutInfo;
    private ArrayList<WorkoutInfo> workoutList = new ArrayList<>();
    private int counter = 0;
    private MyCountDownTimer countDownTimer;
    private View.OnClickListener handleButtonClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.start_btn:
                    showStopButton();
                    countDownTimer.start();
                    break;
                case R.id.stop_btn:
                    break;
                case R.id.reset_btn:
                    break;
                case R.id.pause_btn:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Initialise the workout PoJO here
        workoutInfo = new WorkoutInfo(10, "Workout 1", "WarmUp");
        workoutList.add(workoutInfo);
        workoutInfo = new WorkoutInfo(5, "Workout 2", "Workout");
        workoutList.add(workoutInfo);
        workoutInfo = new WorkoutInfo(4, "Workout 1", "Abs");
        workoutList.add(workoutInfo);
        Log.d("MainActivity", "List size " + workoutList.size() + " sample list " + workoutList.get(0).toString());
        findViewById(R.id.start_btn).setOnClickListener(handleButtonClick);
        findViewById(R.id.stop_btn).setOnClickListener(handleButtonClick);
        findViewById(R.id.pause_btn).setOnClickListener(handleButtonClick);
        findViewById(R.id.stop_btn).setOnClickListener(handleButtonClick);

//        findViewById(R.id.reset_btn).setOnClickListener(resetClick(this));
        updateUI();
//
//        mStart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.d("MainActivity", " Clicked on start button");
//                showStopButton();
////                if (stopped) {
////                    startTime = System.currentTimeMillis() - elapsedTime;
////                } else {
//////                    startTime = System.currentTimeMillis();
////                    startTime = (long) convertToMilliseconds(workoutList.get(counter).getTime());
////                }
////                mHandler.removeCallbacks(startTimer);
////                mHandler.postDelayed(startTimer, 0);
////                startTime = (long) convertToMilliseconds(workoutList.get(counter).getTime());
//                countDownTimer.start();
//            }
//        });
//        mPause.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.d("MainActivity", " Clicked on Pause button");
//            }
//        });
//        mStop.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.d("MainActivity", " Clicked on Stop button");
//                hideStopButton();
////                mHandler.removeCallbacks(startTimer);
////                stopped = true;
//                countDownTimer.cancel();
//            }
//        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void updateUI(){
        updateTimer(convertToMilliseconds(workoutList.get(counter).getTime()));
        ((TextView) findViewById(R.id.workout)).setText(workoutList.get(counter).getName());
        ((TextView) findViewById(R.id.type)).setText(workoutList.get(counter).getType());
        findViewById(R.id.pause_btn).setVisibility(View.GONE);
        findViewById(R.id.stop_btn).setVisibility(View.GONE);
        countDownTimer = new MyCountDownTimer(convertToMilliseconds(workoutList.get(counter).getTime()),REFRESH_RATE);
    }


    private void updateTimer(float time) {
        long secs,mins,hrs;
        String hours,minutes,seconds;
        secs = (long) (time / 1000);
        mins = (long) ((time / 1000) / 60);
        hrs = (long) (((time / 1000) / 60) / 60); /* Convert the seconds to String * and format to ensure it has * a leading zero when required */
        secs = secs % 60;
        seconds = String.valueOf(secs);
        if (secs == 0) {
            seconds = "00";
        }
        if (secs < 10 && secs > 0) {
            seconds = "0" + seconds;
        } /* Convert the minutes to String and format the String */
        mins = mins % 60;
        minutes = String.valueOf(mins);
        if (mins == 0) {
            minutes = "00";
        }
        if (mins < 10 && mins > 0) {
            minutes = "0" + minutes;
        } /* Convert the hours to String and format the String */
        hours = String.valueOf(hrs);
        if (hrs == 0) {
            hours = "00";
        }
        if (hrs < 10 && hrs > 0) {
            hours = "0" + hours;
        }
         /* Setting the timer text to the elapsed time */
        ((TextView) findViewById(R.id.timer)).setText(hours + ":" + minutes + ":" + seconds);
    }
    private long convertToMilliseconds(int seconds){
        return seconds * 1000;
    }
    private void showStopButton() {
        findViewById(R.id.start_btn).setVisibility(View.GONE);
        findViewById(R.id.pause_btn).setVisibility(View.GONE);
        findViewById(R.id.stop_btn).setVisibility(View.VISIBLE);
    }

    private void hideStopButton() {
        findViewById(R.id.start_btn).setVisibility(View.VISIBLE);
        findViewById(R.id.pause_btn).setVisibility(View.VISIBLE);
        findViewById(R.id.stop_btn).setVisibility(View.GONE);
    }
    // CountDownTimer class
    public class MyCountDownTimer extends CountDownTimer
    {

        public MyCountDownTimer(long startTime, long interval)
        {
            super(startTime, interval);
        }

        @Override
        public void onFinish()
        {
            Log.d("MainActivity", " Finished");
            counter++;
            if(counter <workoutList.size()){
                updateUI();
                countDownTimer.start();
            }
        }

        @Override
        public void onTick(long millisUntilFinished)
        {
            updateTimer(millisUntilFinished);
        }
    }
}

