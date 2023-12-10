package com.example.dig4634.Lightspeed_Eric;

import gl.Texture;

public class Level {

    int segments=0;
    int row=0;

    int ypos=-2;
    LevelSegment[] level_segments;
    Texture texture_o;
    int s;

    public Level(int s, int r, int y, Texture texture, Texture texture_c, Texture texture_o){
        segments=s;
        row = r;
        ypos = y;
        this.texture_o = texture_o;
        this.s = s;

        level_segments=new LevelSegment[s];
        for(int i=0;i<segments;i++){
            level_segments[i]=new LevelSegment();
            level_segments[i].setTexture(texture);

            level_segments[i].positionZ=-5-i*4;
            level_segments[i].positionY=y;
            level_segments[i].positionX=r*2;

            level_segments[i].speedZ=4;
            level_segments[i].segments=s;

            if(Math.random()<0.08f) {
                level_segments[i].collectible = new Collectible();
                level_segments[i].collectible.setTexture(texture_c);
                level_segments[i].collectible.positionZ = -5 - i * 4;
                level_segments[i].collectible.positionY = -1f;
                level_segments[i].collectible.positionX = (float) ((Math.random()-0.5f) * 5f);
                level_segments[i].collectible.speedZ = 8;
                level_segments[i].collectible.segments = s;
            }

            if(Math.random()<0.05f) {
                level_segments[i].obstacle = new Obstacle();
                level_segments[i].obstacle.setTexture(texture_o);
                level_segments[i].obstacle.positionZ = -5 - i * 4;
                level_segments[i].obstacle.positionY = -1f;
                level_segments[i].obstacle.positionX = (float) ((Math.random()-0.5f) * 5f);
                level_segments[i].obstacle.speedZ = 8;
                level_segments[i].obstacle.segments = s;
            }
        }
    }

    public void updateGame(double input)
    {
        for(int i=0;i<segments;i++) {
            if(Math.random()<0.01f) {
                level_segments[i].obstacle = new Obstacle();
                level_segments[i].obstacle.setTexture(texture_o);
                level_segments[i].obstacle.positionZ = -5 - i * 4;
                level_segments[i].obstacle.positionY = -1f;
                level_segments[i].obstacle.positionX = (float) (2.5f);
                level_segments[i].obstacle.speedZ = (float) (8 + input);
                level_segments[i].obstacle.segments = s;
            }

            if(level_segments[i].collectible !=null)
                level_segments[i].collectible.speedZ = (float) (8 + input);
            else if(level_segments[i].obstacle !=null)
                level_segments[i].obstacle.speedZ = (float) (8 + input);
        }
    }

    public void simulate(float perSec){
        for(int i=0;i<segments;i++) {
            level_segments[i].simulate(perSec);
            if(level_segments[i].collectible !=null)
                    level_segments[i].collectible.simulate(perSec);
            else if(level_segments[i].obstacle !=null)
                level_segments[i].obstacle.simulate(perSec);
        }
    }

    public void draw(){
        for(int i=0;i<segments;i++) {
            level_segments[i].draw();
            if(level_segments[i].collectible !=null && level_segments[i].collectible.shown)
                level_segments[i].collectible.draw();
            else if(level_segments[i].obstacle !=null && level_segments[i].obstacle.shown)
                level_segments[i].obstacle.draw();
        }
    }

}
