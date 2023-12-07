package com.example.dig4634.Lightspeed_Eric;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.TextView;

import javax.microedition.khronos.opengles.GL10;

public class MainActivity extends AppCompatActivity implements SensorEventListener, SurfaceHolder.Callback {

    MyRenderer my_renderer;
    SurfaceHolder holder = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GLSurfaceView surfaceView=findViewById(R.id.surfaceView);
        surfaceView.setEGLContextClientVersion(3);
        surfaceView.setZOrderOnTop(true);
        surfaceView.setEGLConfigChooser(8,8,8,8,16,0);
        surfaceView.getHolder().setFormat(PixelFormat.RGBA_8888);


        my_renderer=new MyRenderer(this);
        // my_renderer.showCamera(findViewById(R.id.textureView));

        surfaceView.setRenderer(my_renderer);
        surfaceView.setOnTouchListener(my_renderer);

        scoreTextView = findViewById(R.id.scoreText);


    }

    static int score = 0;
    static String scoreText;
    float sensor_acc_x=0;
    static TextView scoreTextView;

    public static void updateScore()
    {
        score++;
        final int temp = score;
        scoreText = "Score: " + temp;

        // Run the update on the UI thread
        scoreTextView.post(new Runnable() {
            @Override
            public void run() {
                scoreTextView.setText(scoreText);
            }
        });
    }


    @Override
    public void onPause(){

        my_renderer.pauseCamera();
        super.onPause();
    }

    @Override
    public void onResume(){
        super.onResume();
        my_renderer.resumeCamera();
    }

    @Override
    public void onDestroy(){
        SensorManager manager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        manager.unregisterListener((SensorEventListener) this, manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER));
        super.onDestroy();
    }


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        sensor_acc_x=sensorEvent.values[0];
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    /*

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }

     */

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        holder=surfaceHolder;

        //draw();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {
        holder=surfaceHolder;
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
        holder=null;
    }
}
