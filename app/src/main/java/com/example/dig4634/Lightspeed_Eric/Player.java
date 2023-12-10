package com.example.dig4634.Lightspeed_Eric;

import gl.ObjectMaker;
import gl.modeltypes.ShadedTexturedModel;

public class Player extends ShadedTexturedModel {

    public boolean shown = true;

    public float positionX=-0.20f;
    public float positionY=0;
    public float positionZ=0;

    public float speedX=0;
    public float speedY=0;
    public float speedZ=0;

    public static float accX =0;
    public float accY =0;
    public float accZ =0;

    public float angleX=-60;
    public float angleY=0;
    public float angleZ=0;

    Player(){
        ObjectMaker om=new ObjectMaker();
        om.identity();

        om.color(0.5f, 0.95f, 0.95f);
        om.pyramid(1f, 1.5f, 1f);
        om.identity();

        om.color(0.5f, 0.5f, 0.9f);
        om.translate(0f, -0.5f, 0f);
        om.box(1f,0.6f,1f);
        om.identity();

        //FORMER TRAPEZOIDS
//        om.translate(0.4f, -0.6f, 0f);
//        om.pyramid(0.8f, 0.8f, 0.8f);
//        om.identity();
//
//        om.translate(-0.4f, -0.6f, 0f);
//        om.pyramid(0.8f, 0.8f, 0.8f);
//        om.identity();

//        om.translate(0f, -0.6f, 0.4f);
//        om.pyramid(0.8f, 0.8f, 0.8f);
//        om.identity();

//        om.translate(0f, -0.6f, -0.4f);
//        om.pyramid(0.8f, 0.8f, 0.8f);
//        om.identity();

        //NEW TRAPEZOIDS
        om.translate(0.8f, -0.6f, 0f);
        om.trapezoid(0.4f, 0.8f, 0.8f, 0.2f, 0.4f);
        om.identity();

        om.translate(-0.8f, -0.6f, 0f);
        om.trapezoid(0.4f, 0.8f, 0.8f, 0.2f, 0.4f);
        om.identity();

        //WINDOWS
//        om.translate(0.4f, 0.3f, 0f);
//        om.sphere(0.4f, 0.4f, 0.4f, 1);
//        om.identity();
//
//        om.translate(-0.4f, 0.3f, 0f);
//        om.sphere(0.4f, 0.4f, 0.4f, 1);
//        om.identity();
//
//        om.translate(0f, 0.3f, 0.4f);
//        om.sphere(0.4f, 0.4f, 0.4f, 1);
//        om.identity();
//
//        om.translate(0f, 0.3f, -0.4f);
//        om.sphere(0.4f, 0.4f, 0.4f, 1);
//        om.identity();

        //JETS
        om.color(0.4f, 0.4f, 0.8f);

        om.translate(0.25f, -0.81f, 0.15f);
        om.cylinder(0.4f, 0.4f, 0.4f, 7);
        om.identity();

        om.translate(-0.25f, -0.81f, 0.15f);
        om.cylinder(0.4f, 0.4f, 0.4f, 7);
        om.identity();

//        om.translate(0f, -0.81f, 0.4f);
//        om.cylinder(0.5f, 0.4f, 0.5f, 7);
//        om.identity();

        om.translate(0f, -0.81f, -0.2f);
        om.cylinder(0.4f, 0.4f, 0.4f, 7);
        om.identity();

        om.flush(this);

        //PYRAMID SHAPE
//        super();
//
//        setXYZ(new float[]{
//                //BASE VERTICES
//                0, 0, 0, //lower left corner    id:0
//                1, 0, 0, //lower right corner   id:1
//                0, 1, 0, //upper left corner    id:2
//                1, 1, 0, // upper right corner   id:3
//
//                //APEX
//                0.5f, 0.5f, 0.8f //apex              id:4
//        });
//
//        // Define the indices for the triangles that make up each face of the cube.
//        setTriangles(new short[]{
//                //BASE TRIANGLES
//                0, 1, 2,
//                1, 2, 3,
//
//                //SIDE TRIANGLES
//                0, 1, 4,
//                0, 2, 4,
//                1, 3, 4,
//                2, 3, 4,
//        });
//
//        // Set texture coordinates for each vertex to map the texture image onto the cube's surfaces.
//        setUV(new float[]{
//                0, 0, 1, 0, 0, 1, 1, 1,
//                0.5f, 0.5f
//        });
//        setNormals(new float[]{0,0,1,0,0,1,0,0,1,0,0,1,0,0,-1,0,0,-1,0,0,-1,0,0,-1,1,0,0,1,0,0,1,0,0,1,0,0,-1,0,0,-1,0,0,-1,0,0,-1,0,0,0,1,0,0,1,0,0,1,0,0,1,0,0,-1,0,0,-1,0,0,-1,0,0,-1,0});


    }

    public void simulate(float perSec){
        if (shown)
        {
            accY = -6;//gravity

            //accX = -speedX/2;

            speedX += accX *perSec;
            speedY += accY *perSec;
            speedZ += accZ *perSec;

            positionX += speedX*perSec;
            positionY += speedY*perSec;
            positionZ += speedZ*perSec;


            if(positionY<-1){
                positionY=-1;
                speedY=0;
            }

            if(positionX<-0.8f){
                positionX=-0.8f;
                speedX=0;
            }

            if(positionX>0.6f){
                positionX=0.6f;
                speedX=0;
            }

            localTransform.reset();
            localTransform.translate(positionX,positionY,positionZ);
            localTransform.rotateX(angleX);
            localTransform.rotateY(angleY);
            localTransform.rotateZ(angleZ);
            localTransform.scale(0.2f,0.2f,0.2f);
            localTransform.updateShader();
        }
    }
}
