package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;

import java.util.ArrayList;

/**
 * Created by munke on 10/2/14.
 */
public class Wall {
    private Pixmap pixmap;
    private int currentFrame = 0;
    private int currentNegativeFrame = Gdx.graphics.getHeight();
    private ArrayList<ArrayList<Integer>> yLength = new ArrayList<ArrayList<Integer>>();
    private static int MIN = 75;
    private static int MAX = 150;
    private static int LINEWIDTH234 = 20;
    private static int SPACEHEIGHT = 80;

    public Wall(Pixmap pixmap){
        this.pixmap = pixmap;
        makeArrayRows();

    }
    public void myRender(){

        //making sure that the previous has been disposed (otherwise you'll get overload)
//        texture.dispose();


        //set background color
        pixmap.setColor(Color.BLACK);
        pixmap.fill();


        // Make walls positive

        for (int i = 0; i<4; i++){

            if (i == 0 || i == 2) {
            for (int j = 0; j<yLength.get(i).size(); j += 2){

                    int min = yLength.get(i).get(j) + currentFrame;
                    int max = yLength.get(i).get(j + 1) + currentFrame;

                    pixmap.setColor(Color.RED);
                    pixmap.drawRectangle((pixmap.getWidth() / 5) * (1 + i), min, LINEWIDTH234, max - min);

            }
                // make walls negative
            } else {
                for (int j = 0; j<yLength.get(i).size(); j += 2) {
                    int min = yLength.get(i).get(j) + currentNegativeFrame;
                    int max = yLength.get(i).get(j + 1) + currentNegativeFrame;

                    pixmap.setColor(Color.RED);
                    pixmap.drawRectangle((pixmap.getWidth() / 5) * (1 + i), min, LINEWIDTH234, max - min);
//                    pixmap.fillRectangle((pixmap.getWidth() / 5) * (1 + i), min, LINEWIDTH234, max - min);

                }
            }


        }


        currentFrame = currentFrame+3;
        currentNegativeFrame = currentNegativeFrame -3 ;

        if(currentFrame == pixmap.getHeight()){
            currentFrame = 0;
        }

        if (currentNegativeFrame == 0){
            currentNegativeFrame = pixmap.getHeight();
        }



    }



    private void makeArrayRows(){

        int totalHeight = pixmap.getHeight();

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
            }
        }

    }

    public int getX1(int row, int n){

//        return 0;
       return (pixmap.getWidth() / 5) * (1 + row);
    }

    public int getX2(int row, int n){

        return ((pixmap.getWidth() / 5) * (1 + row)) + LINEWIDTH234;
    }

    public int getY1(int row, int n){

        int tmp = yLength.get(row).get(n);
        if(row % 2 == 0){
            tmp = tmp + currentFrame;
        } else {
            tmp = tmp + currentNegativeFrame;
        }

        return tmp;

    }

    public int getY2(int row, int n){

        int tmp = yLength.get(row).get(n+1);

        if(row % 2 == 0){
            tmp = tmp + currentFrame;
        } else {
            tmp = tmp + currentNegativeFrame;
        }

        return tmp;
    }

    public ArrayList<ArrayList<Integer>> getYLength(){
        return yLength;
    }


    public void resume(){
        currentFrame = 0;
    }

}
