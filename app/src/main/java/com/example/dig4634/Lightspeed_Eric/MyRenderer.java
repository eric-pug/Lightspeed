package com.example.dig4634.Lightspeed_Eric;

import static com.example.dig4634.Lightspeed_Eric.MainActivity.score;

import android.app.Activity;
import android.opengl.GLES30;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import javax.microedition.khronos.opengles.GL10;

import gl.Texture;
import gl.renderers.ThirdEyeRenderer;

public class MyRenderer extends ThirdEyeRenderer implements View.OnTouchListener {

    Player player;

    Level row1;
    Level row2;
    Level row3;
    Level row4;
    Level row5;

    //Texture wooden_texture;
    Texture red_texture;
    Texture green_texture;
    Texture blue_texture;

    Texture space_texture;

    public MyRenderer(Activity activity){
        super(activity);
    }




    @Override
    public void setup() {

        red_texture=new Texture(getContext(),"red.png");
        green_texture=new Texture(getContext(),"green.jpg");
        blue_texture=new Texture(getContext(), "blue.jpg");
        space_texture =new Texture(getContext(), "space.png");


        player =new Player();
        player.setTexture(blue_texture);
        player.positionZ=-4;


        row1 =new Level(10, -2, space_texture,green_texture, red_texture);
        row2 =new Level(10, -1, space_texture,green_texture, red_texture);
        row3 =new Level(10, 0, space_texture,green_texture, red_texture);
        row4 =new Level(10, 1, space_texture,green_texture, red_texture);
        row5 =new Level(10, 2, space_texture,green_texture, red_texture);


        //background(153/255f,	204/255f,	255/255f);
        setLightDir(0,-1,-1);

    }



    double lastTime=0;

    @Override
    public void simulate(double elapsedDisplayTime) {

        float perSec=(float)(elapsedDisplayTime-lastTime);
        lastTime=elapsedDisplayTime;

        player.simulate(perSec);
        row1.simulate(perSec);
        row2.simulate(perSec);
        row3.simulate(perSec);
        row4.simulate(perSec);
        row5.simulate(perSec);


        //collision detection logic
        for(int i = 0; i< row1.level_segments.length; i++){
            if(row1.level_segments[i].collectible !=null){

                if(Math.abs(row1.level_segments[i].collectible.positionX- player.positionX)<0.5 &&
                        Math.abs(row1.level_segments[i].collectible.positionY- player.positionY)<0.5
                        && Math.abs(row1.level_segments[i].collectible.positionZ- player.positionZ)<0.5)
                    row1.level_segments[i].collectible.shown=false;
                score++;
            }

//            if(row1.level_segments[i].obstacle !=null){
//
//                if(Math.abs(row1.level_segments[i].obstacle.positionX- player.positionX)<5 &&
//                        Math.abs(row1.level_segments[i].obstacle.positionY- player.positionY)<5
//                        && Math.abs(row1.level_segments[i].obstacle.positionZ- player.positionZ)<5)
//                    player.shown=false;
//            }
        }

        for(int i = 0; i< row2.level_segments.length; i++){
            if(row2.level_segments[i].collectible !=null){

                if(Math.abs(row2.level_segments[i].collectible.positionX- player.positionX)<0.5 &&
                        Math.abs(row2.level_segments[i].collectible.positionY- player.positionY)<0.5
                        && Math.abs(row2.level_segments[i].collectible.positionZ- player.positionZ)<0.5)
                    row2.level_segments[i].collectible.shown=false;
                score++;
            }

//            if(row2.level_segments[i].obstacle !=null){
//
//                if(Math.abs(row2.level_segments[i].obstacle.positionX- player.positionX)<5 &&
//                        Math.abs(row2.level_segments[i].obstacle.positionY- player.positionY)<5
//                        && Math.abs(row2.level_segments[i].obstacle.positionZ- player.positionZ)<5)
//                    player.shown=false;
//            }
        }

        for(int i = 0; i< row3.level_segments.length; i++){
            if(row3.level_segments[i].collectible !=null){

                if(Math.abs(row3.level_segments[i].collectible.positionX- player.positionX)<0.5 &&
                        Math.abs(row3.level_segments[i].collectible.positionY- player.positionY)<0.5
                        && Math.abs(row3.level_segments[i].collectible.positionZ- player.positionZ)<0.5)
                    row3.level_segments[i].collectible.shown=false;
                score++;
            }

//            if(row3.level_segments[i].obstacle !=null){
//
//                if(Math.abs(row3.level_segments[i].obstacle.positionX- player.positionX)<5 &&
//                        Math.abs(row3.level_segments[i].obstacle.positionY- player.positionY)<5
//                        && Math.abs(row3.level_segments[i].obstacle.positionZ- player.positionZ)<5)
//                    player.shown=false;
//            }
        }

        for(int i = 0; i< row4.level_segments.length; i++){
            if(row4.level_segments[i].collectible !=null){

                if(Math.abs(row4.level_segments[i].collectible.positionX- player.positionX)<0.5 &&
                        Math.abs(row4.level_segments[i].collectible.positionY- player.positionY)<0.5
                        && Math.abs(row4.level_segments[i].collectible.positionZ- player.positionZ)<0.5)
                    row4.level_segments[i].collectible.shown=false;
                score++;
            }

//            if(row4.level_segments[i].obstacle !=null){
//
//                if(Math.abs(row4.level_segments[i].obstacle.positionX- player.positionX)<5 &&
//                        Math.abs(row4.level_segments[i].obstacle.positionY- player.positionY)<5
//                        && Math.abs(row4.level_segments[i].obstacle.positionZ- player.positionZ)<5)
//                    player.shown=false;
//            }
        }

        for(int i = 0; i< row5.level_segments.length; i++){
            if(row5.level_segments[i].collectible !=null){

                if(Math.abs(row5.level_segments[i].collectible.positionX- player.positionX)<0.5 &&
                        Math.abs(row5.level_segments[i].collectible.positionY- player.positionY)<0.5
                        && Math.abs(row5.level_segments[i].collectible.positionZ- player.positionZ)<0.5)
                    row5.level_segments[i].collectible.shown=false;
                score++;
            }

//            if(row5.level_segments[i].obstacle !=null){
//
//                if(Math.abs(row5.level_segments[i].obstacle.positionX- player.positionX)<5 &&
//                        Math.abs(row5.level_segments[i].obstacle.positionY- player.positionY)<5
//                        && Math.abs(row5.level_segments[i].obstacle.positionZ- player.positionZ)<5)
//                    player.shown=false;
//            }
        }

    }

    @Override
    public void draw() {

        GLES30.glClear(GLES30.GL_COLOR_BUFFER_BIT| GLES30.GL_DEPTH_BUFFER_BIT);


        player.draw();

        row1.draw();
        row2.draw();
        row3.draw();
        row4.draw();
        row5.draw();

    }



    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {


        if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
            Log.d("Example","tap");
            player.speedX=1f;
        }



        return false;
    }
}
