package com.odyssey.circle;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;

public class Custom_ColorActivity extends AppCompatActivity {

    Button button;
    SeekBar s1, s2, s3;
    int color, saturation, brightness;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom__color);

        color = 255;
        saturation = 255;
        brightness = 255;


        button = findViewById(R.id.button_color_change);
        s1 = findViewById(R.id.seekBar);
        s2 = findViewById(R.id.seekBar2);
        s3 = findViewById(R.id.seekBar3);

        s1.setMax(color);
        s1.setProgress(color);


        s2.setMax(saturation);
        s2.setProgress(saturation);


        s3.setMax(brightness);
        s3.setProgress(brightness);


        button.setBackgroundColor(Color.argb(255, color, saturation, brightness));


        s1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                color = progress;
                button.setBackgroundColor(Color.argb(255, color, saturation, brightness));


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        s2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                saturation = progress;
                button.setBackgroundColor(Color.argb(255, color, saturation, brightness));


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



        s3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                brightness = progress;
                button.setBackgroundColor(Color.argb(255, color, saturation, brightness));


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}
