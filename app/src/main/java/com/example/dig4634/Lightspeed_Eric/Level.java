package com.example.dig4634.Lightspeed_Eric;

import gl.Texture;

public class Level {

    int segments=0;
    int row=0;
    LevelSegment[] level_segments;

    Texture texture_c;

    Texture texture_o;
    Texture texture_m;
    int s;
    int r;
    public Level(int s, int r, Texture texture, Texture texture_c, Texture texture_o){
        segments=s;
        row = r;

        this.texture_c = texture_c;
        this.texture_o = texture_o;
        this.texture_m = texture;
        this.s = s;
        this.r = r;
        level_segments=new LevelSegment[s];
        for(int i=0;i<segments;i++){
            level_segments[i]=new LevelSegment();
            level_segments[i].setTexture(texture);

            level_segments[i].positionZ=-5-i*4;
            level_segments[i].positionY=-2;
            level_segments[i].positionX=r*2;

            level_segments[i].speedZ=4;
            level_segments[i].segments=s;

            if(Math.random()<0.12f) {
                level_segments[i].collectible = new Collectible();
                level_segments[i].collectible.setTexture(texture_c);
                level_segments[i].collectible.positionZ = -5 - i * 4;
                level_segments[i].collectible.positionY = -1f;
                level_segments[i].collectible.positionX = (float) ((Math.random()-0.5f) * 5f);
                level_segments[i].collectible.speedZ = 8;
                level_segments[i].collectible.segments = s;
            }

            if(Math.random()<0.10f) {
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

    public void updateObstacles(double input)
    {
        for(int i=0;i<segments;i++){
            if(Math.random()<0.02f) {
                level_segments[i].obstacle = new Obstacle();
                level_segments[i].obstacle.setTexture(texture_o);
                level_segments[i].obstacle.positionZ = -5 - i * 4;
                level_segments[i].obstacle.positionY = -1f;
                level_segments[i].obstacle.positionX = (float) ((Math.random()-0.5f) * 5f);
                level_segments[i].obstacle.speedZ = (float) (8 + input);
                level_segments[i].obstacle.segments = s;
            }

            if (level_segments[i].collectible != null)
                level_segments[i].collectible.speedZ = (float) (8 + input);
            if (level_segments[i].obstacle != null)
                level_segments[i].obstacle.speedZ = (float) (8+input);
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
