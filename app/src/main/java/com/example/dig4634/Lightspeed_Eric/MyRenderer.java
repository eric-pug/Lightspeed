package com.example.dig4634.Lightspeed_Eric;

import android.app.Activity;
import android.content.Intent;
import android.opengl.GLES30;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import gl.Texture;
import gl.renderers.ThirdEyeRenderer;

public class MyRenderer extends ThirdEyeRenderer implements View.OnTouchListener {

    Player player;

    //BOTTOM ROWS
    Level botrow1;
    Level botrow2;
    Level botrow3;
    Level botrow4;
    Level botrow5;

    //TOP ROWS
    Level toprow1;
    Level toprow2;
    Level toprow3;
    Level toprow4;
    Level toprow5;

    //LEFT ROWS
    AngledWall leftrow1;
    AngledWall leftrow2;
    AngledWall leftrow3;
    AngledWall leftrow4;
    AngledWall leftrow5;

    //RIGHT ROWS
    AngledWall rightrow1;
    AngledWall rightrow2;
    AngledWall rightrow3;
    AngledWall rightrow4;
    AngledWall rightrow5;


    //Texture wooden_texture;
    Texture asteroid_texture;
    Texture collect_texture;
    Texture ship_texture;

    Texture space_texture;
    private DataListener dataListener;
    public MyRenderer(Activity activity){
        super(activity);
        this.dataListener = (DataListener) activity;
    }




    @Override
    public void setup() {

        asteroid_texture =new Texture(getContext(),"asteroid.jpg");
        collect_texture =new Texture(getContext(),"yellow.jpg");
        ship_texture =new Texture(getContext(), "purple.jpg");
        space_texture =new Texture(getContext(), "space.png");


        player =new Player();
        player.setTexture(ship_texture);
        player.positionZ=-4;


        botrow1 =new Level(20, -2, -2, space_texture, collect_texture, asteroid_texture);
        botrow2 =new Level(20, -1, -2, space_texture, collect_texture, asteroid_texture);
        botrow3 =new Level(20, 0, -2, space_texture, collect_texture, asteroid_texture);
        botrow4 =new Level(20, 1, -2, space_texture, collect_texture, asteroid_texture);
        botrow5 =new Level(20, 2, -2, space_texture, collect_texture, asteroid_texture);

        toprow1 =new Level(20, -2, 2, space_texture, collect_texture, asteroid_texture);
        toprow2 =new Level(20, -1, 2, space_texture, collect_texture, asteroid_texture);
        toprow3 =new Level(20, 0, 2, space_texture, collect_texture, asteroid_texture);
        toprow4 =new Level(20, 1, 2, space_texture, collect_texture, asteroid_texture);
        toprow5 =new Level(20, 2, 2, space_texture, collect_texture, asteroid_texture);

        leftrow1 =new AngledWall(20, -2, 2, -1, space_texture);
        leftrow2 =new AngledWall(20, -2, 1, -1, space_texture);
        leftrow3 =new AngledWall(20, -2, 0, -1, space_texture);
        leftrow4 =new AngledWall(20, -2, -1, -1, space_texture);
        leftrow5 =new AngledWall(20, -2, -2, -1, space_texture);

        rightrow1 =new AngledWall(20, 2, 2, 1, space_texture);
        rightrow2 =new AngledWall(20, 2, 1, 1, space_texture);
        rightrow3 =new AngledWall(20, 2, 0, 1, space_texture);
        rightrow4 =new AngledWall(20, 2, -1, 1, space_texture);
        rightrow5 =new AngledWall(20, 2, -2, 1, space_texture);


        background(5/255f,	5/255f,	5/255f);
        setLightDir(0,-1,-1);

    }



    double lastTime=0;

    double gameTime = 0;
    double levelTime = 0;
    int levelCounter = 0;

    @Override
    public void simulate(double elapsedDisplayTime) {

        float perSec = (float) (elapsedDisplayTime - lastTime);
        lastTime = elapsedDisplayTime;
        gameTime+=perSec;
        levelTime+=perSec;
        MainActivity.updateTime((int) gameTime);
        if (levelTime > 5)
        {
            levelCounter++;
            levelTime = 0;
            botrow1.updateGame(levelCounter * 0.5);
            botrow2.updateGame(levelCounter * 0.5);
            botrow3.updateGame(levelCounter * 0.5);
            botrow4.updateGame(levelCounter * 0.5);
            botrow5.updateGame(levelCounter * 0.5);
        }

        player.simulate(perSec);
        botrow1.simulate(perSec);
        botrow2.simulate(perSec);
        botrow3.simulate(perSec);
        botrow4.simulate(perSec);
        botrow5.simulate(perSec);

        toprow1.simulate(perSec);
        toprow2.simulate(perSec);
        toprow3.simulate(perSec);
        toprow4.simulate(perSec);
        toprow5.simulate(perSec);

        leftrow1.simulate(perSec);
        leftrow2.simulate(perSec);
        leftrow3.simulate(perSec);
        leftrow4.simulate(perSec);
        leftrow5.simulate(perSec);

        rightrow1.simulate(perSec);
        rightrow2.simulate(perSec);
        rightrow3.simulate(perSec);
        rightrow4.simulate(perSec);
        rightrow5.simulate(perSec);

        //collision detection logic
        Level[] rows = {botrow1, botrow2, botrow3, botrow4, botrow5};
        for (Level row : rows) {
            for (int i = 0; i < row.level_segments.length; i++) {
                if (row.level_segments[i].collectible != null) {
                    if (Math.abs(row.level_segments[i].collectible.positionX - player.positionX) < 0.5 &&
                            Math.abs(row.level_segments[i].collectible.positionY - player.positionY) < 0.5 &&
                            Math.abs(row.level_segments[i].collectible.positionZ - player.positionZ) < 0.5) {
                        if (row.level_segments[i].collectible.shown) {
                            row.level_segments[i].collectible.shown = false;
                            MainActivity.updateScore((int)gameTime);
                        }
                    }
                }
                if (row.level_segments[i].obstacle != null) {
                    if (Math.abs(row.level_segments[i].obstacle.positionX - player.positionX) < 0.5 &&
                            Math.abs(row.level_segments[i].obstacle.positionY - player.positionY) < 0.5 &&
                            Math.abs(row.level_segments[i].obstacle.positionZ - player.positionZ) < 0.5) {
                        row.level_segments[i].obstacle.shown = false;
                        dataListener.onDataReceived((int)gameTime);
                    }
                }
            }
        }
    }

    public interface DataListener {
        void onDataReceived(int data); // Define the method signature to send data
    }


    @Override
    public void draw() {

        GLES30.glClear(GLES30.GL_COLOR_BUFFER_BIT| GLES30.GL_DEPTH_BUFFER_BIT);


        player.draw();

        botrow1.draw();
        botrow2.draw();
        botrow3.draw();
        botrow4.draw();
        botrow5.draw();

        toprow1.draw();
        toprow2.draw();
        toprow3.draw();
        toprow4.draw();
        toprow5.draw();

        leftrow1.draw();
        leftrow2.draw();
        leftrow3.draw();
        leftrow4.draw();
        leftrow5.draw();

        rightrow1.draw();
        rightrow2.draw();
        rightrow3.draw();
        rightrow4.draw();
        rightrow5.draw();
    }



    @Override
    public boolean onTouch(View view, MotionEvent event) {


        if(event.getAction()==MotionEvent.ACTION_DOWN){
            Log.d("Example","tap");

            float tapX = event.getX();

            DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
            //float height = displayMetrics.heightPixels / displayMetrics.density;
            float width = displayMetrics.widthPixels / displayMetrics.density;


            switch(event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    // The user just touched the screen
                    if (tapX >= (width/2)+400)
                    {
                        player.speedX+=1f;
                    }
                    else
                    {
                        player.speedX+=-1f;
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    // The touch just ended
                    player.accX = -player.speedX/2;
                    break;
            }

            return false;
        }



        return false;
    }
}
