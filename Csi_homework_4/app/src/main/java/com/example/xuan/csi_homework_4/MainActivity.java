package com.example.xuan.csi_homework_4;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.lzyzsd.circleprogress.DonutProgress;

import org.w3c.dom.Text;

import java.util.Random;
import java.util.StringTokenizer;
import java.util.Timer;
import java.util.TimerTask;

import static android.R.attr.data;

public class MainActivity extends AppCompatActivity {
    final Handler handler = new Handler();
    private boolean IS_GAME_STOPPED = false;
    Random random = new Random();

    ProgressBar firstMonsterEatenProgressBar;
    ProgressBar secondMonsterEatenProgressBar;
    ProgressBar countDownProgressBar;
    DonutProgress bakingTimeProgressBar;

    Button cancelButton;
    Button startOverButton;

    TextView totalCookiesInJarTextView;
    TextView timePassedTextView;
    TextView bakedCookiesTextView;
    TextView firstMonsterEatenCookiesTextView;
    TextView firstMonsterEatTimeLeftTextView;
    TextView firstMonsterEatingCookiesTextView;
    TextView secondMonsterEatenCookiesTextView;
    TextView secondMonsterEatTimeLeftTextView;
    TextView secondMonsterEatingCookiesTextView;

    int firstMonsterEatenCookies;
    int secondMonsterEatenCookies;
    int totalCookiesInJar;
    int countDownClock;
    int grandmaMonsterCookingTime;
    int gameDuration;
    int bakingTime;

    @Override
    protected void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        setContentView(R.layout.activity_main);

        initVariables();
        setControls();
    }

    private void setControls(){
        firstMonsterEatenProgressBar = (ProgressBar) findViewById(R.id.first_monster_eaten_progress_bar);
        secondMonsterEatenProgressBar = (ProgressBar) findViewById(R.id.second_monster_eaten_progress_bar);
        countDownProgressBar = (ProgressBar) findViewById(R.id.count_down_progress_bar);
        bakingTimeProgressBar = (DonutProgress) findViewById(R.id.baking_time_progress_bar);
        countDownProgressBar.setMax(120);

        totalCookiesInJarTextView = (TextView) findViewById(R.id.total_cookies_in_jar);
        timePassedTextView = (TextView) findViewById(R.id.time_passed);
        bakedCookiesTextView = (TextView) findViewById(R.id.baked_cookies);
        bakedCookiesTextView.setText(String.valueOf(totalCookiesInJar));

        firstMonsterEatenCookiesTextView = (TextView) findViewById(R.id.first_monster_eaten_cookies);
        firstMonsterEatTimeLeftTextView = (TextView) findViewById(R.id.first_monster_eat_time_left);
        firstMonsterEatingCookiesTextView = (TextView) findViewById(R.id.first_monster_eating_cookies);

        secondMonsterEatenCookiesTextView = (TextView) findViewById(R.id.second_monster_eaten_cookies);
        secondMonsterEatTimeLeftTextView = (TextView) findViewById(R.id.second_monster_eat_time_left);
        secondMonsterEatingCookiesTextView = (TextView) findViewById(R.id.second_monster_eating_cookies);

        cancelButton = (Button) findViewById(R.id.cancel_button);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pauseGame();
            }
        });
        startOverButton = (Button) findViewById(R.id.start_over_button);
        startOverButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });
    }

    public void startGame(){
        handler.post(firstMonsterEating);
        handler.post(secondMonsterEating);
        handler.post(grandmaMonsterBaking);
        handler.postDelayed(countDown, 1000);
    }

    private void pauseGame(){
        Toast.makeText(MainActivity.this, "Pause game", Toast.LENGTH_SHORT).show();
        handler.removeCallbacks(firstMonsterEating);
        handler.removeCallbacks(secondMonsterEating);
        handler.removeCallbacks(grandmaMonsterBaking);
        handler.removeCallbacks(countDown);
    }

    private void resetGame(){
        initVariables();
        startGame();
    }

    private void initVariables(){
        firstMonsterEatenCookies = 0;
        secondMonsterEatenCookies = 0;
        totalCookiesInJar = 20;
        countDownClock = 0;
        grandmaMonsterCookingTime = 5000;
        gameDuration = 120;
        bakingTime = 0;
    }

    private boolean isGameStopped(){
        boolean isGameStopped = !(firstMonsterEatenCookies < 100 && secondMonsterEatenCookies < 100 && countDownClock < 120);
        if(IS_GAME_STOPPED == false && isGameStopped == true) {
            IS_GAME_STOPPED = true;
            Toast.makeText(this, getWinner(), Toast.LENGTH_SHORT).show();
        }
        return isGameStopped;
    }

    private String getWinner(){
        String winner = "The winner is ";
        if(firstMonsterEatenCookies > secondMonsterEatenCookies) {
            winner += "Monster 1";
        }
        else if(firstMonsterEatenCookies < secondMonsterEatenCookies) {
            winner += "Monster 2";
        }
        else if(firstMonsterEatenCookies == secondMonsterEatenCookies) {
            winner = "Drawwwww";
        }
        return winner;
    }

    private void resumeGame(){
        startGame();
    }

    Runnable firstMonsterEating = new Runnable() {
        @Override
        public void run() {
            if(!isGameStopped()){
                int randomEatenCookies = random.nextInt(totalCookiesInJar);
                int randomTime = random.nextInt(4)+1;
                firstMonsterEatenCookies += randomEatenCookies;
                totalCookiesInJar -= randomEatenCookies;

                firstMonsterEatenCookiesTextView.setText(String.valueOf(firstMonsterEatenCookies));
                firstMonsterEatingCookiesTextView.setText("+"+String.valueOf(randomEatenCookies));
                firstMonsterEatTimeLeftTextView.setText(String.valueOf(randomTime));
                totalCookiesInJarTextView.setText(String.valueOf(totalCookiesInJar));
                firstMonsterEatenProgressBar.setProgress(firstMonsterEatenCookies);

                handler.postDelayed(this, randomTime*1000);
            }
        }
    };
    Runnable secondMonsterEating = new Runnable() {
        @Override
        public void run() {
            if(!isGameStopped()){
                int randomEatenCookies = random.nextInt(totalCookiesInJar);
                int randomTime = random.nextInt(4)+1;
                secondMonsterEatenCookies += randomEatenCookies;
                totalCookiesInJar -= randomEatenCookies;

                secondMonsterEatenCookiesTextView.setText(String.valueOf(secondMonsterEatenCookies));
                secondMonsterEatingCookiesTextView.setText("+"+String.valueOf(randomEatenCookies));
                secondMonsterEatTimeLeftTextView.setText(String.valueOf(randomTime));
                totalCookiesInJarTextView.setText(String.valueOf(totalCookiesInJar));
                secondMonsterEatenProgressBar.setProgress(secondMonsterEatenCookies);

                handler.postDelayed(this, randomTime*1000);
            }
        }
    };
    Runnable grandmaMonsterBaking = new Runnable() {
        @Override
        public void run() {
            if(!isGameStopped()) {
                int randomCookies = random.nextInt(9) + 1;
                totalCookiesInJar += randomCookies;
                totalCookiesInJarTextView.setText(String.valueOf(totalCookiesInJar));
                bakedCookiesTextView.setText(String.valueOf(randomCookies));
                handler.postDelayed(this, grandmaMonsterCookingTime);
            }
        }
    };
    Runnable countDown = new Runnable() {
        @Override
        public void run() {
            if(!isGameStopped()){
                countDownClock++;
                if(bakingTime == 5) {
                    bakingTime = 1;
                }
                else {
                    bakingTime++;
                }
                timePassedTextView.setText(String.valueOf(countDownClock));
                countDownProgressBar.setProgress(countDownClock);
                bakingTimeProgressBar.setProgress(bakingTime);
                updateEatingTime(firstMonsterEatTimeLeftTextView);
                updateEatingTime(secondMonsterEatTimeLeftTextView);
                handler.postDelayed(this, 1000);
            }
        }
    };

    private void updateEatingTime(TextView timeTextView) {
        int time = Integer.valueOf(timeTextView.getText().toString());
        time--;
        timeTextView.setText(String.valueOf(time));
    }
}
