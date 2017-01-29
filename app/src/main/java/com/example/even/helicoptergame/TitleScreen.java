package com.example.even.helicoptergame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.icu.text.DecimalFormat;
import android.view.MotionEvent;

import sheep.game.Sprite;
import sheep.game.State;
import sheep.graphics.Image;
import sheep.game.Game;
import sheep.input.TouchListener;

/**
 * Created by Even on 26.01.2017.
 */

public class TitleScreen extends State  {

    private Image aImage = new Image(R.drawable.icon);
    private Image heliImage = new Image(R.drawable.heli1_east);
    private Image heliWest = new Image(R.drawable.heli1);
    private Image wallVerImage = new Image(R.drawable.wall_vertical);
    private Image backgroundImage = new Image(R.drawable.background);
    private Sprite aSprite;
    private Sprite westWall;
    private Sprite backSprite;
    private Sprite heliSprite;



    public TitleScreen() {
        backSprite = new Sprite(backgroundImage);
        heliSprite = new Sprite(heliImage);
        aSprite = new Sprite(aImage);
        westWall = new Sprite(wallVerImage);
        westWall.setPosition(4, 215);
       // aSprite.setPosition(200, 120);
       // aSprite.setSpeed(40, 0); // it should move right direction, but since collides bug, it will move (-40,0),
                                // If we input (-40,0), it move (40,0), after collides, helicopter is disappeared. bug?
        heliSprite.setPosition(200, 120);
        heliSprite.setSpeed(40, 0);
        this.addTouchListener(new TouchListener() {
            @Override
            public boolean onTouchDown(MotionEvent motionEvent) {
                float trykkX=motionEvent.getX();
                float trykkY=motionEvent.getY();

                float helicopterX=heliSprite.getX();
                float helicopterY=heliSprite.getY();

                float deltaX=helicopterX-trykkX;
                float deltaY=helicopterY-trykkY;



                //28.01.17: Switched to heliSprite instead of aSprite.
                //This if shall change the direction of the heli to go in the direction of the touchEvent
                if (deltaX<0 && Math.abs(deltaX)>Math.abs(deltaY)){
//                    heliSprite.setScale(-1,1);
//                    heliSprite.setPosition(helicopterX+130, helicopterY);
                    heliSprite.setSpeed(40, 0);
                }
                if (deltaX>0 && Math.abs(deltaX)>Math.abs(deltaY)){
//                    heliSprite.setScale(-1,1);
//                    heliSprite.setPosition(helicopterX-130, helicopterY);
                    heliSprite.setSpeed(-40, 0);

                }
                if (deltaY>0 && Math.abs(deltaY)>Math.abs(deltaX)){
                    heliSprite.setSpeed(0, -40);
                }
                if (deltaY<0 && Math.abs(deltaY)>Math.abs(deltaX)){
                    heliSprite.setSpeed(0, 40);
                }


                return true;
            }

            @Override
            public boolean onTouchUp(MotionEvent motionEvent) {

             return false;
            }

            @Override
            public boolean onTouchMove(MotionEvent motionEvent) {
                return false;
            }
        });

    }

    public void draw(android.graphics.Canvas canvas){
        backSprite.draw(canvas);

        westWall.draw(canvas);
        //aSprite.draw(canvas);
        heliSprite.draw(canvas);
        //Setter inn informasjon om helikopterets koordinater
        //Må først finne x og y-koordinatene:

        float heliXCoord=heliSprite.getX();
        float heliYCoord=heliSprite.getY();


        String CoordString = Float.toString(heliXCoord) + " " + Float.toString(heliYCoord);

        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setTextSize(15);
        canvas.drawText(CoordString, 20, 20, paint);

    }


    //Har nå skiftet ut alle "asprite" med heliSprite, for å fjerne aSPrite-dritten.
    //Denne utskiftingen gjelder de først 2 if-ene
    public void update(float dt) {

        if(heliSprite.getX()>=255)
        {
            System.out.println("crash east border!");
            heliSprite.setSpeed(-heliSprite.getSpeed().getX(), heliSprite.getSpeed().getY());
        }

        else if(heliSprite.collides(westWall)) // collides is true first time, and change the object direction.
        {
            System.out.println("crash west border!");
            heliSprite.setSpeed(40, 0);
        }

        if(heliSprite.getX()>=300)
        {
            System.out.println("crash east border!");
            heliSprite.setSpeed(-heliSprite.getSpeed().getX(), heliSprite.getSpeed().getY());
        }

        if (heliSprite.getY()<20){
            System.out.println("Crash north border!");
            heliSprite.setSpeed(0, 40);
        }

        if (heliSprite.getY()> 400){
            System.out.println("Crash southern border! Must build wall!");
            heliSprite.setSpeed(0, -40);
        }



        westWall.update(dt);
        aSprite.update(dt);
        heliSprite.update(dt);

    }


}
