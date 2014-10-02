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
    private int orientation = Gdx.input.getRotation();
    private Input.Orientation nativeOrientation = Gdx.input.getNativeOrientation();
    private float accelX = Gdx.input.getAccelerometerX();
    private float accelY = Gdx.input.getAccelerometerY();
    private float accelZ = Gdx.input.getAccelerometerZ();
    private int xPos;
    private int yPos;


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
        pixmap.fillCircle(xPos, yPos, 15);


    }



    public void move(){

        if(Gdx.input.isPeripheralAvailable(Input.Peripheral.Accelerometer)){
            // do something

            accelX = Gdx.input.getAccelerometerX();
            accelY = Gdx.input.getAccelerometerY();
            xPos = getX() - (int) Math.round(accelX);
            yPos = getY() + (int) Math.round(accelY);

            System.out.println("lalalalalal x koordinat:" + accelX + "\n" + "y koordinat:" + accelY);


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
