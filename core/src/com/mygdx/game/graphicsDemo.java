package com.mygdx.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
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
    private Pixmap pixmap;
    private Wall wall;
    private avatarDemo AD;

    @Override
    public void create() {

        batch = new SpriteBatch();

        // A Pixmap is basically a raw image in memory as repesented by pixels
        // We create one with the width of the screen and the height of the screen, using 8 bytes for Red, Green, Blue and Alpha channels
        pixmap = new Pixmap(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), Pixmap.Format.RGBA8888);
        wall = new Wall(pixmap);
        AD = new avatarDemo(pixmap, wall);

        texture = new Texture(pixmap);
        sprite = new Sprite(texture);

    }

    @Override
    public void dispose() {
        batch.dispose();
        texture.dispose();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // her kaldes rendermetoderne
        wall.myRender();
        texture.dispose();
        AD.render();
        AD.update();

        if (AD.isDead()) {
            texture = new Texture("access-denied.png");
            sprite = new Sprite(texture);
            sprite.rotate90(true);
            sprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            if (Gdx.input.justTouched()) {
               AD.reset();
            }
        } else if(AD.isWin()){
            texture = new Texture("access-granted.png");
            sprite = new Sprite(texture);
            sprite.rotate90(true);
            sprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        } else {
            texture = new Texture(pixmap);
            sprite = new Sprite(texture);
        }


        batch.begin();
        sprite.setPosition(0, 0);
        sprite.draw(batch);
        batch.end();


    }


    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {
//        currentFrame = 0;
    }
}