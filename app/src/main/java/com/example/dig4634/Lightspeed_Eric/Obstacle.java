package com.example.dig4634.Lightspeed_Eric;

import gl.ObjectMaker;
import gl.modeltypes.ShadedTexturedModel;
public class Obstacle extends ShadedTexturedModel {

    public boolean shown=true;

    public float segments=0;

    public float positionX=0;
    public float positionY=0;
    public float positionZ=0;

    public float speedX=0;
    public float speedY=0;
    public float speedZ=0;

    public float accelerationX =0;
    public float accelerationY =0;
    public float accelerationZ =0;

    public float angleX=0;
    public float angleY=0;
    public float angleZ=0;



    Obstacle(){
        ObjectMaker o = new ObjectMaker();

        o.sphere(1.1f, 1.1f, 1.2f, 16);
        o.identity();

        o.translate(0, 0.08f, 0);
        o.sphere(1, 1.2f, 1, 16);
        o.identity();

        o.translate(0.1f, 0, 0);
        o.sphere(1.1f, 1, 1.15f, 16);
        o.identity();

        o.flush(this);


        //CUBE SHAPE
//        super();
//
//        setXYZ(new float[]{-0.5f,0.5f,0.5f,
//                0.5f,0.5f,0.5f,
//                -0.5f,-0.5f,0.5f,
//                0.5f,-0.5f,0.5f,
//                -0.5f,0.5f,-0.5f,
//                0.5f,0.5f,-0.5f,
//                -0.5f,-0.5f,-0.5f,
//                0.5f,-0.5f,-0.5f,
//                0.5f,0.5f,0.5f,
//                0.5f,-0.5f,0.5f,
//                0.5f,0.5f,-0.5f,
//                0.5f,-0.5f,-0.5f,
//                -0.5f,0.5f,0.5f,
//                -0.5f,-0.5f,0.5f,
//                -0.5f,0.5f,-0.5f,
//                -0.5f,-0.5f,-0.5f,
//                -0.5f,0.5f,0.5f,
//                0.5f,0.5f,0.5f,
//                -0.5f,0.5f,-0.5f,
//                0.5f,0.5f,-0.5f,
//                -0.5f,-0.5f,0.5f,
//                0.5f,-0.5f,0.5f,
//                -0.5f,-0.5f,-0.5f,
//                0.5f,-0.5f,-0.5f});
//
//
//        setTriangles(new short[]{0,2,1,1,2,3,4,5,6,6,5,7,9,11,8,8,11,10,13,12,15,15,12,14,16,17,18,18,17,19,21,20,22,21,22,23});
//
//        setUV(new float[]{0,1,1,1,0,0,1,0,1,1,0,1,1,0,0,0,0,1,0,0,1,1,1,0,1,1,1,0,0,1,0,0,0,0,1,0,0,1,1,1,1,0,0,0,1,1,0,1});
//
//        setNormals(new float[]{0,0,1,0,0,1,0,0,1,0,0,1,0,0,-1,0,0,-1,0,0,-1,0,0,-1,1,0,0,1,0,0,1,0,0,1,0,0,-1,0,0,-1,0,0,-1,0,0,-1,0,0,0,1,0,0,1,0,0,1,0,0,1,0,0,-1,0,0,-1,0,0,-1,0,0,-1,0});


    }

    public void simulate(float perSec){

        angleX=-72*perSec;
        angleY=63*perSec;
        angleZ+=180*perSec;

        speedX+= accelerationX *perSec;
        speedY+= accelerationY *perSec;
        speedZ+= accelerationZ *perSec;

        positionX+=speedX*perSec;
        positionY+=speedY*perSec;
        positionZ+=speedZ*perSec;

        if(positionZ>-2){
            positionZ= (float) ((-2-segments*4)-((Math.random()-0.5f)*10));
            shown=true;
            positionX = (float) ((Math.random()-0.5f)*5);
        }


        localTransform.reset();
        localTransform.translate(positionX,positionY,positionZ);
        localTransform.rotateX(angleX);
        localTransform.rotateY(angleY);
        localTransform.rotateZ(angleZ);
        localTransform.scale(0.3f,0.3f,0.3f);
        localTransform.updateShader();
    }
}
