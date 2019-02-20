package com.odyssey.circle;

import android.graphics.Color;
import android.graphics.Matrix;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    RadioButtons radiocircle;
    TextView test;
    ImageView cursor;
    FrameLayout frame;
    ImageView button;

    private int dialerHeight, dialerWidth;
    float currentAngle; //The current angle of the dialer
    double clickedAngle; //The angle which user has clicked
    public int[] colors = new int[]{Color.GREEN, Color.GRAY, Color.BLUE, Color.CYAN, Color.DKGRAY, Color.RED, Color.YELLOW, Color.MAGENTA};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        radiocircle = findViewById(R.id.circle);
        test = findViewById(R.id.testText);
        button = findViewById(R.id.button2);
        radiocircle.setOnSliceClickListener(new RadioButtons.OnSliceClickListener() {
            @Override
            public void onSlickClick(int slicePosition, double angle) {


                Log.d("TapAngle", String.valueOf(angle)+ String.valueOf(slicePosition));


       if (angle < 45 && angle >= 1){
           // radiocircle.animate(angle, 295, 1500);
           radiocircle.setRotation(293);
           test.setBackgroundColor(Color.GRAY);
           //button.setBackgroundColor(Color.GRAY);
        }
           else if (angle < 90 && angle >= 45){
           //radiocircle.animate(angle, 335, 1500);
          radiocircle.setRotation(337);
           test.setBackgroundColor(Color.GREEN);
          // button.setBackgroundColor(Color.GREEN);
        }

       else if (angle < 135 && angle >= 90){
           // radiocircle.animate(angle, 380, 1500);
           radiocircle.setRotation(380);
           test.setBackgroundColor(Color.MAGENTA);
          // button.setBackgroundColor(Color.MAGENTA);
       }

       else if (angle < 180 && angle > 135){
           //radiocircle.animate(angle, 420, 1500);
           radiocircle.setRotation(427);
           test.setBackgroundColor(Color.YELLOW);
           //button.setBackgroundColor(Color.YELLOW);
       }

       else if (angle < 225 && angle >= 180){
           // radiocircle.animate(angle, 475, 1500);
           radiocircle.setRotation(472);
           test.setBackgroundColor(Color.RED);
           //button.setBackgroundColor(Color.RED);
       }

       else if (angle < 270 && angle >= 225){
           //radiocircle.animate(angle, 515, 1500);
           radiocircle.setRotation(380);
           test.setBackgroundColor(Color.DKGRAY);
           //button.setBackgroundColor(Color.DKGRAY);
       }

      else if (angle < 315 && angle >= 270){
           //radiocircle.animate(angle, 565, 1500);
           radiocircle.setRotation(565);
           test.setBackgroundColor(Color.CYAN);
          // button.setBackgroundColor(Color.CYAN);
       }

        else if (angle < 360 && angle > 315){
           //radiocircle.animate(angle, 605, 1500);
           radiocircle.setRotation(605);
           test.setBackgroundColor(Color.BLUE);
          // button.setBackgroundColor(Color.BLUE);
       }

/*                switch (slicePosition) {
                    case 0:
                        //Log.d("Rotation", String.valueOf(radiocircle.getRotation()));

                        Toast.makeText(MainActivity.this, "check:" + radiocircle.getRotation(), Toast.LENGTH_SHORT).show();

                       // radiocircle.animate(90, 360, 1500);
                        break;
                    case 1:
                        radiocircle.animate(90, 360, 1500);
                        break;
                    case 2:
                       radiocircle.animate(90, 360, 1500);
                        break;
                    case 3:
                        radiocircle.animate(90, 360, 1500);
                    case 4:
                        radiocircle.animate(90, 360, 1500);

                        break;
                    case 5:

                        radiocircle.animate(90, 360, 1500);
                        break;
                    case 6:

                        radiocircle.animate(90, 360, 1500);
                        break;
                    case 7:

                        radiocircle.animate(90, 360, 1500);
                        break;
                }*/

                //Toast.makeText(MainActivity.this, "Top Menu selected position:" + slicePosition, Toast.LENGTH_SHORT).show();
                //Toast.makeText(MainActivity.this, "angle position:" + angle, Toast.LENGTH_SHORT).show();
            }
        });


    }


    public void animate(double fromDegrees, double toDegrees, long durationMillis) {
        final RotateAnimation rotate = new RotateAnimation((float) fromDegrees, (float) toDegrees,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(durationMillis);
        rotate.setFillEnabled(true);
        rotate.setFillAfter(true);
        radiocircle.startAnimation(rotate);
    }
}
