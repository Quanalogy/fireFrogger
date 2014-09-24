package com.mygdx.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by munken on 9/24/14.
 */
public class graphicsDemo implements ApplicationListener {

    private SpriteBatch batch;
    private Texture texture;
    private Sprite sprite;

    @Override
    public void create(){

        batch = new SpriteBatch();
        texture = new Texture(Gdx.files.internal("jet.png"));
        sprite = new Sprite(texture);
    }

    @Override
    public void dispose(){
        batch.dispose();
        texture.dispose();
    }

    @Override
    public void render(){
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        sprite.draw(batch);
        batch.end();
    }

    @Override
    public void resize(int width, int height){

    }

    @Override
    public void pause(){

    }

    @Override
    public void resume(){

    }
}
