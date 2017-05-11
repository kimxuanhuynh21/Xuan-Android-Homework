package com.example.xuan.csi_homework_1;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnWhite;
    private Button btnGrey1;
    private Button btnRed;
    private Button btnGreen;
    private Button btnBlue;
    Toast toast;
    private LinearLayout mainBackground;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Context context = getApplicationContext();
        toast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
        mainBackground = (LinearLayout)findViewById(R.id.mainBackground);
        btnWhite = (Button)findViewById(R.id.btnWhite);
        btnGrey1 = (Button)findViewById(R.id.btnGrey1);
        btnRed = (Button)findViewById(R.id.btnRed);
        btnGreen = (Button)findViewById(R.id.btnGreen);
        btnBlue = (Button)findViewById(R.id.btnBlue);
        btnWhite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainBackground.setBackgroundResource(R.color.colorWhite);
                toast.cancel();
                toast.setText("White");
                toast.show();
            }
        });
        btnGrey1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainBackground.setBackgroundResource(R.color.colorDarkGrey);
                toast.cancel();
                toast.setText("Grey");
                toast.show();
            }
        });
        btnRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainBackground.setBackgroundResource(R.color.colorRed);
                toast.cancel();
                toast.setText("Red");
                toast.show();
            }
        });
        btnGreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainBackground.setBackgroundResource(R.color.colorGreen);
                toast.cancel();
                toast.setText("Green");
                toast.show();
            }
        });
        btnBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainBackground.setBackgroundResource(R.color.colorBlue);
                toast.cancel();
                toast.setText("Blue");
                toast.show();
            }
        });
    }
}
