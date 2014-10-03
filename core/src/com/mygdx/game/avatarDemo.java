package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;


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
    private Wall wall;

    public boolean isDead() {
        return dead;
    }

    public void reset() {
        this.dead = false;
        makeAvatar();
    }

    private boolean dead = false;


    public avatarDemo (Pixmap pixmap, Wall wall){

        this.pixmap = pixmap;
        this.wall = wall;
        makeAvatar();
    }

    //sæt koordinater til bold
    private void makeAvatar(){

        xPos = pixmap.getWidth()/10;
        yPos = pixmap.getHeight()/2;

    }


    //Lav cirkel
    private void avatarRender(){

        pixmap.setColor(Color.GREEN);
        pixmap.fillCircle(xPos, yPos, radius);


    }



    public void move(){
        // tjek på om der er et accelerometer

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

    public boolean isTouchingWall(){
        boolean res = false;
        // vi kører alle 4 rækker igennem
        for(int row = 0; row < 4; row ++){
            //dernæst længden af vores array af walls
            for(int n = 0; n < wall.getYLength().get(row).size(); n+= 2){
                // her tjekker vi om der er collision mellem vores bold og væggen (tænkes som er vores x2 større end objektes x1 og er vores x2 større end objektes x1
                // og er vores y1 mindre end objektes y2 og er vores y2 større end objektes y1.
                // dette kan bruges til alle collision checks. Hvis nogle af disse tjeks er false er der collision, ellers er der ikke :)
                if(this.xPos-radius <= wall.getX2(row, n) &&
                        this.xPos+radius >= wall.getX1(row, n) &&
                        this.yPos-radius <= wall.getY1(row, n) &&
                        this.yPos+radius >= wall.getY2(row, n)){
                        res = true;
                        System.out.println("true");
                }
            }
        }
        return res;

    }

    //Update sørger for alt der skal opdateres.
    public void update(){
        move();
        if(isTouchingWall()){
            this.dead = true;
        }
    }

    public boolean isWin(){
        if(this.getX()-radius > wall.getX2(3, 0)){
            return true;
        }
        return false;
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
