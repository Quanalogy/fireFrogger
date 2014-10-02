package com.mygdx.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Window;

import java.util.ArrayList;


/**
 * Created by munken on 9/24/14.
 */
public class graphicsDemo implements ApplicationListener {

    private SpriteBatch batch;
    private Texture texture;
    private Sprite sprite;
    private Pixmap pixmap;
    private int currentFrame = 0;
    private ArrayList<ArrayList<Integer>> yLength = new ArrayList<ArrayList<Integer>>();
    private static int MIN = 75;
    private static int MAX = 150;
    private static int LINEWIDTH234 = 20;
    private static int SPACEHEIGHT = 50;
    private avatarDemo AD;

    @Override
    public void create(){

        batch = new SpriteBatch();

        // A Pixmap is basically a raw image in memory as repesented by pixels
        // We create one with the width of the screen and the height of the screen, using 8 bytes for Red, Green, Blue and Alpha channels
        pixmap = new Pixmap(Gdx.graphics.getWidth(),Gdx.graphics.getHeight(), Pixmap.Format.RGBA8888);

        makeArrayRows();

        AD = new avatarDemo(pixmap);

        texture = new Texture(pixmap);
        sprite = new Sprite(texture);

    }

    @Override
    public void dispose(){
        batch.dispose();
        texture.dispose();
    }

    @Override
    public void render(){
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // her kaldes rendermetoderne
        myRender();
        AD.render();
        AD.update();


        texture = new Texture(pixmap);
        sprite = new Sprite(texture);

        batch.begin();
        sprite.setPosition(0,0);
        sprite.draw(batch);
        batch.end();
    }

    private void myRender(){

        //making sure that the previous has been disposed (otherwise you'll get overload)
        texture.dispose();


        //set background color
        pixmap.setColor(Color.BLACK);
        pixmap.fill();



        // Make walls

        for (int i = 0; i<4; i++){
            for (int j = 0; j<yLength.get(i).size(); j += 2){
                int min = yLength.get(i).get(j) + currentFrame;
                int max = yLength.get(i).get(j+1) + currentFrame;

                pixmap.setColor(Color.RED);
                pixmap.drawRectangle((pixmap.getWidth()/5)*(1+i), min, LINEWIDTH234, max-min);

            }
        }

        currentFrame++;

        if(currentFrame == pixmap.getHeight()){
            currentFrame = 0;
        }



    }



    private void makeArrayRows(){
/*
        int currentMax = pixmap.getHeight();
*/      int totalHeight = pixmap.getHeight();

        for (int i = 0; i < 4; i++){
            int currentMax = totalHeight;
            int j = 0;
            yLength.add(new ArrayList<Integer>());

            while (currentMax > 0){
                int min = (int) (Math.random()*(MAX-MIN)+MIN);
                int max = currentMax;
                min = max - min;

                if (min < 0){
                    min = 0;
                }
                yLength.get(i).add(max);
                yLength.get(i).add(min);
                yLength.get(i).add(-(totalHeight - max));
                yLength.get(i).add(-(totalHeight - min));


                currentMax = min - SPACEHEIGHT;
                j += 4;
             //   System.out.println(currentMax);
            }
        }
    }


    @Override
    public void resize(int width, int height){

    }

    @Override
    public void pause(){

    }

    @Override
    public void resume(){
        currentFrame = 0;
    }
}