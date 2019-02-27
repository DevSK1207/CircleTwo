package com.odyssey.circle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import static android.view.accessibility.AccessibilityEvent.INVALID_POSITION;

public class RadioButtons extends View {

    //the number of slice
    private int mSlices = 8;

    private int startAngle;

    //test
    private double mCurrAngle = 0;
    private double mPrevAngle = 0;
    private int mRootDiameter;

    private Matrix matrix;
    private int wheelHeight, wheelWidth;


    //rotate matrix
    private Matrix mBgMatrix = new Matrix();

    //the angle of each slice
    private int degreeStep = 360 / mSlices;


    private int quarterDegreeMinus = -90;


    private float mOuterRadius;
    private float mInnerRadius;

    //using radius square to prevent square root calculation
    private float outerRadiusSquare;
    private float innerRadiusSquare;

    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private RectF mSliceOval = new RectF();

    private static final double quarterCircle = Math.PI / 2;

    private float innerRadiusRatio = 0.3F;

    //color for your slice
    public int[] colors = new int[]{Color.GREEN, Color.GRAY, Color.BLUE, Color.CYAN, Color.DKGRAY, Color.RED, Color.YELLOW, Color.MAGENTA};

    private int mCenterX;
    private int mCenterY;

    private OnSliceClickListener mOnSliceClickListener;
    private int mTouchSlop;

    private boolean mPressed;
    private float mLatestDownX;
    private float mLatestDownY;
    private Context context;
    private int selectedPosition;

    public interface OnSliceClickListener{
        void onSlickClick(int slicePosition, double angle);
    }

    public RadioButtons(Context context){
        this(context, null);
    }

    public RadioButtons(Context context, AttributeSet attrs){
        this(context, attrs, 0);
    }


    public RadioButtons(Context context, AttributeSet attrs, int defStyle){
        super(context, attrs, defStyle);

        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        mTouchSlop = viewConfiguration.getScaledTouchSlop();

        mPaint.setStrokeWidth(0); //white color margin width
    }

    public void setOnSliceClickListener(OnSliceClickListener onSliceClickListener){
        mOnSliceClickListener = onSliceClickListener;
    }





    @Override
    public void onSizeChanged(int w, int h, int oldw, int oldh){
        mCenterX = w / 2;
        mCenterY = h / 2;

        mOuterRadius = mCenterX > mCenterY ? mCenterY : mCenterX;
        mInnerRadius = mOuterRadius * innerRadiusRatio;

        outerRadiusSquare = mOuterRadius * mOuterRadius;
        innerRadiusSquare = mInnerRadius * mInnerRadius;

        mSliceOval.left = mCenterX - mOuterRadius;
        mSliceOval.right = mCenterX + mOuterRadius;
        mSliceOval.top = mCenterY - mOuterRadius;
        mSliceOval.bottom = mCenterY + mOuterRadius;

    }

    @Override
    public boolean onTouchEvent(MotionEvent event){

        final float xc = getWidth() / 2.0f;
        final float yc = getHeight() / 2.0f;

        float currX = event.getX();
        float currY = event.getY();



        switch(event.getActionMasked()){
            case MotionEvent.ACTION_DOWN:
                mLatestDownX = currX;
                mLatestDownY = currY;
                mPressed = true;
                mCurrAngle = Math.toDegrees(Math.atan2(currX - xc, yc - currY));

                break;

            case MotionEvent.ACTION_MOVE:

                if(Math.abs(currX - mLatestDownX) > mTouchSlop || Math.abs(currY - mLatestDownY) > mTouchSlop) mPressed = false;
                mPrevAngle = mCurrAngle;
                mCurrAngle = Math.toDegrees(Math.atan2(currX - xc, yc - currY));
                //animate(mPrevAngle, mCurrAngle, 0);

                break;


            case MotionEvent.ACTION_UP:


                if(mPressed){
                    int dx = (int) currX - mCenterX;
                    int dy = (int) currY - mCenterY;
                    int distanceSquare = dx * dx + dy * dy;

                    //if the distance between touchpoint and centerpoint is smaller than outerRadius and longer than innerRadius, then we're in the clickable area
                    if(distanceSquare > innerRadiusSquare && distanceSquare < outerRadiusSquare){

                        //get the angle to detect which slice is currently being click
                        double angle = Math.atan2(dy, dx);

                        if(angle >= -quarterCircle && angle < 0){
                            angle += quarterCircle;
                        }else if(angle >= -Math.PI && angle < -quarterCircle){
                            angle += Math.PI + Math.PI + quarterCircle;
                        }else if(angle >= 0 && angle < Math.PI){
                            angle += quarterCircle;
                        }

                        double rawSliceIndex = angle / (Math.PI * 2) * mSlices;

                        if(mOnSliceClickListener != null){
                            mOnSliceClickListener.onSlickClick((int) rawSliceIndex, getAngle(event.getX(), event.getY()));
                        }

                    }
                }

               mPrevAngle = mCurrAngle = 0;


                break;
        }

        return true;
    }




    @Override
    public void onDraw(Canvas canvas){

        canvas.save();
        int startAngle = quarterDegreeMinus;
        //float angle = (float) ((tmpAngle + 360f / mLuckyItemList.size() / 2) * Math.PI / 180);

        //draw slice
        for(int i = 0; i < mSlices; i++){
            mPaint.setStyle(Paint.Style.FILL);
            mPaint.setColor(colors[i % colors.length]);
            canvas.drawArc(mSliceOval, startAngle, degreeStep, true, mPaint);


            //Stroke color work like margin of each slice
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setColor(Color.TRANSPARENT);
            canvas.drawArc(mSliceOval, startAngle, degreeStep, true, mPaint);

           startAngle += degreeStep;



        }

        //draw center circle (small one in middle)
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.WHITE);
        rotateCalc();
        canvas.drawCircle(mCenterX, mCenterY, mInnerRadius, mPaint);


        //main big circle (around)
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.TRANSPARENT);
        canvas.drawCircle(mCenterX, mCenterY, mInnerRadius, mPaint);
        wheelWidth = getWidth();
        wheelHeight = getHeight();


        canvas.restore();


    }

    private void rotateCalc() {
        matrix = new Matrix();

        startAngle++;
        matrix.postRotate(startAngle, 145, 8);
        matrix.postScale(0.5f, 0.5f);
        matrix.postTranslate(149, 337);

    }

    public void animate(double fromDegrees, double toDegrees, long durationMillis) {
        final RotateAnimation rotate = new RotateAnimation((float) fromDegrees, (float) toDegrees,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(durationMillis);
        rotate.setFillEnabled(true);
        rotate.setFillAfter(true);
        startAnimation(rotate);
    }

        /*
        set color list
     */


    public void setColors(int color1, int color2, int color3, int color4, int color5, int color6, int color7, int color8){

        this.colors = new int[]{color1, color2, color3, color4, color5, color6, color7, color8};
    }

    /**
     * @return The angle of the unit circle with the image view's center
     */
    private double getAngle(double xTouch, double yTouch) {

        double x = xTouch - (wheelWidth / 2d);
        double y = wheelHeight - yTouch - (wheelHeight / 2d);

        switch (getQuadrant(x, y)) {
            case 1:
                return Math.asin(y / Math.hypot(x, y)) * 180 / Math.PI;
            case 2:
            case 3:
                return 180 - (Math.asin(y / Math.hypot(x, y)) * 180 / Math.PI);
            case 4:
                return 360 + Math.asin(y / Math.hypot(x, y)) * 180 / Math.PI;
            default:
                return 0;// ignore, does not happen
        }
    }

    /**
     * @return The selected quadrant.
     */
    private static int getQuadrant(double x, double y) {
        if (x >= 0) {
            return y >= 0 ? 1 : 4;
        } else {
            return y >= 0 ? 2 : 3;
        }
    }
}