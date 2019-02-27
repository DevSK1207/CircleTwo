package com.odyssey.circle;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
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

import com.drawablecolorchange.DrawableColorChange;

public class MainActivity extends AppCompatActivity {
    RadioButtons radiocircle;
    Button custom;
    TextView test, recycleView;
    ImageView cursor;
    FrameLayout frame;
    ImageView button, middle_circle;
    DrawableColorChange drawableColorChange;
    Bitmap bitmap;
    int StartAngle = 90;
    int mainAngle = 180;
    int rotateAngle = 360;


    private int dialerHeight, dialerWidth;
    float currentAngle; //The current angle of the dialer
    double clickedAngle; //The angle which user has clicked
    public int[] colors = new int[]{Color.GREEN, Color.GRAY, Color.BLUE, Color.CYAN, Color.DKGRAY, Color.RED, Color.YELLOW, Color.MAGENTA};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        drawableColorChange = new DrawableColorChange(this);


        button = findViewById(R.id.button2);
        button.setImageDrawable(drawableColorChange.changeColorByColor(R.drawable.ttt, Color.MAGENTA));
        /*Bitmap src = Bitmap.createBitmap(500, 500, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(src);
        canvas.drawColor(Color.MAGENTA);//fill src bitmap with blue color
        button.setImageBitmap(applyPieMask(src, -120, 60));*/
        radiocircle = findViewById(R.id.circle);
        test = findViewById(R.id.testText);


        //added
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null && bundle.get("color1") != null){
            int color1 = (int) bundle.get("color1");
            int color2 = (int) bundle.get("color2");
            int color3 = (int) bundle.get("color3");
            int color4 = (int) bundle.get("color4");
            int color5 = (int) bundle.get("color5");
            int color6 = (int) bundle.get("color6");
            int color7 = (int) bundle.get("color7");
            int color8 = (int) bundle.get("color8");

            //set color
            radiocircle.setColors(color1,color2,color3,color4,color5,color6,color7,color8);
        }


                findViewById(R.id.middle_circle).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(MainActivity.this, ColorListActivity.class));
                        overridePendingTransition(R.anim.slide_up_info,R.anim.no_change);
                    }
                });

             findViewById(R.id.custom_color).setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 startActivity(new Intent(MainActivity.this, Custom_ColorActivity.class));

             }
         });

        radiocircle.setOnSliceClickListener(new RadioButtons.OnSliceClickListener() {
            @Override
            public void onSlickClick(int slicePosition, double angle) {


                Log.d("TapAngle", String.valueOf(angle)+ String.valueOf(slicePosition));


       if (angle < 45 && angle >= 1){
           //radiocircle.animate(angle, 270, 1000);
           radiocircle.setRotation(mainAngle + StartAngle + 23);
           test.setBackgroundColor(Color.GRAY);
           button.setImageDrawable(drawableColorChange.changeColorByColor(R.drawable.ttt, Color.GRAY));

       }
           else if (angle < 90 && angle >= 45){
          //radiocircle.animate(angle, 315, 1000);
           radiocircle.setRotation(mainAngle + StartAngle + 67);
           test.setBackgroundColor(Color.GREEN);
           button.setImageDrawable(drawableColorChange.changeColorByColor(R.drawable.ttt, Color.GREEN));

        }

       else if (angle < 135 && angle >= 90){
           //radiocircle.animate(angle, 360, 1000);
           radiocircle.setRotation(rotateAngle + 20);
           test.setBackgroundColor(Color.MAGENTA);
           button.setImageDrawable(drawableColorChange.changeColorByColor(R.drawable.ttt, Color.MAGENTA));


       }

       else if (angle < 180 && angle > 135){
          // radiocircle.animate(angle, 406, 1000);
           radiocircle.setRotation(rotateAngle + 67);
           test.setBackgroundColor(Color.YELLOW);
           button.setImageDrawable(drawableColorChange.changeColorByColor(R.drawable.ttt, Color.YELLOW));
       }

       else if (angle < 225 && angle >= 180){
           // radiocircle.animate(angle, 453, 1000);
            radiocircle.setRotation(rotateAngle + StartAngle + 22);
           test.setBackgroundColor(Color.RED);
           button.setImageDrawable(drawableColorChange.changeColorByColor(R.drawable.ttt, Color.RED));

       }

       else if (angle < 270 && angle >= 225){
         // radiocircle.animate(angle, 497, 1000);
           radiocircle.setRotation(rotateAngle + StartAngle + 65);
           test.setBackgroundColor(Color.DKGRAY);
           button.setImageDrawable(drawableColorChange.changeColorByColor(R.drawable.ttt, Color.DKGRAY));
       }

      else if (angle < 315 && angle >= 270){
          //radiocircle.animate(angle, 541, 1000);
           radiocircle.setRotation(rotateAngle + mainAngle + 23);
           test.setBackgroundColor(Color.CYAN);
           button.setImageDrawable(drawableColorChange.changeColorByColor(R.drawable.ttt, Color.CYAN));

       }

        else if (angle < 360 && angle > 315){
           //radiocircle.animate(angle, 585, 1000);
           radiocircle.setRotation(rotateAngle + mainAngle + 65);
           test.setBackgroundColor(Color.BLUE);
           button.setImageDrawable(drawableColorChange.changeColorByColor(R.drawable.ttt, Color.BLUE));

       }


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

    public Bitmap applyPieMask(Bitmap src, float startAngle, float sweepAngle) {
        int width = src.getWidth();
        int height = src.getHeight();

        //create bitmap mask with the same dimension of the src bitmap
        Bitmap mask = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(mask);
        canvas.drawColor(0x00000000);//fill mask bitmap with transparent black!

        //init mask paint
        Paint maskPaint = new Paint();
        maskPaint.setColor(0xFFFFFFFF);//pick highest value for bitwise AND operation
        maskPaint.setAntiAlias(true);

        //choose entire bitmap as a rect
        RectF rect = new RectF(0, 0, width, height);
        canvas.drawArc(rect, startAngle, sweepAngle, true, maskPaint);//mask the pie


        Bitmap result = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                //combine src color and mask to gain the result color
                int color = mask.getPixel(i, j) & src.getPixel(i, j);
                result.setPixel(i, j, color);
            }
        }
        return result;
    }
}
