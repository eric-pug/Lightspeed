package com.example.dig4634.Lightspeed_Eric;

import gl.Texture;

public class AngledWall {

    int segments=0;
    int row=0;
    int ypos=-2;
    int angle = 0;
    LevelSegment[] level_segments;

    public AngledWall(int s, int r, int y, int a, Texture texture){
        segments=s;
        row = r;
        ypos = y;
        angle = a;

        level_segments=new LevelSegment[s];
        for(int i=0;i<segments;i++){
            level_segments[i]=new LevelSegment();
            level_segments[i].setTexture(texture);

            level_segments[i].positionZ=-5-i*4;
            level_segments[i].positionY=y;
            level_segments[i].positionX=r*2;
            level_segments[i].angleY=a*30;

            level_segments[i].speedZ=4;
            level_segments[i].speedX=0.5f*a;
            level_segments[i].segments=s;
        }
    }

    public void simulate(float perSec){
        for(int i=0;i<segments;i++) {
            level_segments[i].simulate(perSec);
        }
    }

    public void draw(){
        for(int i=0;i<segments;i++) {
            level_segments[i].draw();
        }
    }

}
