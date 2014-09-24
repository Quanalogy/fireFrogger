package com.mygdx.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


/**
 * Created by munken on 9/24/14.
 */
public class helloWorld implements ApplicationListener{

    private SpriteBatch batch;
    private BitmapFont font;


    @Override
    /** Called when the {@link com.badlogic.gdx.Application} is first created. */
    public void create (){
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(com.badlogic.gdx.graphics.Color.RED);
    }

    @Override
    /** Called when the {@link com.badlogic.gdx.Application} is destroyed. Preceded by a call to {@link #pause()}. */
    public void dispose (){
        batch.dispose();
        font.dispose();
    }

    @Override
    /** Called when the {@link com.badlogic.gdx.Application} should render itself. */
    public void render (){
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        font.draw(batch, "Hello World", 200, 200);
        batch.end();
    }


    @Override
    /** Called when the {@link com.badlogic.gdx.Application} is resized. This can happen at any point during a non-paused state but will never happen
     * before a call to {@link #create()}.
     *
     * @param width the new width in pixels
     * @param height the new height in pixels */
    public void resize (int width, int height){

    }


    @Override
    /** Called when the {@link com.badlogic.gdx.Application} is paused. An Application is paused before it is destroyed, when a user pressed the Home
     * button on Android or an incoming call happened. On the desktop this will only be called immediately before {@link #dispose()}
     * is called. */
    public void pause (){

    }

    @Override
    /** Called when the {@link com.badlogic.gdx.Application} is resumed from a paused state. On Android this happens when the activity gets focus
     * again. On the desktop this method will never be called. */
    public void resume (){

    }


}
