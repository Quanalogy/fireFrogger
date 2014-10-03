package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;


/**
 * Created by munke on 9/30/14.
 */
public class avatarDemo{

    private Pixmap pixmap;
    private float accelX = Gdx.input.getAccelerometerX();
    private float accelY = Gdx.input.getAccelerometerY();
    private int xPos;
    private int yPos;
    private static final int velY = 6;
    private static final int velX = 3;
    private static final int radius = 15;


    public avatarDemo (Pixmap pixmap){

        this.pixmap = pixmap;
        makeAvatar();
    }


    private void makeAvatar(){

        xPos = pixmap.getWidth()/10;
        yPos = pixmap.getHeight()/2;



    }

    private void avatarRender(){

        pixmap.setColor(Color.GREEN);
        pixmap.fillCircle(xPos, yPos, radius);


    }



    public void move(){

        if(Gdx.input.isPeripheralAvailable(Input.Peripheral.Accelerometer)){
            // do something


            accelX = Gdx.input.getAccelerometerX();
            accelY = Gdx.input.getAccelerometerY();
            xPos = getX() - velX*((int) Math.round(accelX));
            yPos = getY() + velY*((int) Math.round(accelY));

            if(xPos < 0+radius){
                xPos = 0 + radius;
            }

            if(xPos > pixmap.getWidth()-radius){
                xPos = pixmap.getWidth() - radius;
            }

            if(yPos > pixmap.getHeight()-radius){
                yPos = pixmap.getHeight() - radius;
            }

            if(yPos < 0+radius){
                yPos = 0 + radius;
            }


        } else {
            System.out.println("Your device does not have an Accelerometer");
        }
    }

    //Update sørger for alt der skal opdateres.
    public void update(){
        move();
    }

    //Render sørger for alt der skal tegnes
    public void render() {
        avatarRender();
    }

    private int getX(){
        return xPos;
    }

    private int getY(){
        return yPos;
    }
}
