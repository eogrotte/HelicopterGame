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
    private Sprite secondSprite;
    private Sprite westWall;
    private Sprite backSprite;
    private Sprite heliSprite;



    public TitleScreen() {
        backSprite = new Sprite(backgroundImage);
        heliSprite = new Sprite(heliImage);
        aSprite = new Sprite(aImage);
        secondSprite = new Sprite (aImage);
        westWall = new Sprite(wallVerImage);
        westWall.setPosition(4, 215);

        aSprite.setPosition(60, 60);
        aSprite.setSpeed((float)Math.random()*(-100), (float)Math.random()*(-100));

        secondSprite.setPosition(200, 200);
        secondSprite.setSpeed(40, 0);

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
        aSprite.draw(canvas);
        secondSprite.draw(canvas);
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

        if(heliSprite.getX()>=254)
        {
            System.out.println("crash east border!");
            heliSprite.setSpeed(-40, 0);
        }

        else if(heliSprite.collides(westWall)) // collides is true first time, and change the object direction.
        {
            System.out.println("crash west border!");
            heliSprite.setSpeed(40, 0);
        }

        if (heliSprite.getY()<25){
            System.out.println("Crash north border!");
            heliSprite.setSpeed(0, 40);
        }

        if (heliSprite.getY()> 400){
            System.out.println("Crash southern border! Must build wall!");
            heliSprite.setSpeed(0, -40);
        }

        //"Borders" for aSprite/AI

        if(aSprite.getX()>=285)
        {
            System.out.println("crash east border!");
            aSprite.setSpeed(-(float)Math.random()*(100), aSprite.getSpeed().getY());
        }

        else if(aSprite.collides(westWall)) // collides is true first time, and change the object direction.
        {
            System.out.println("crash west border!");
            aSprite.setSpeed((float)Math.random()*(100), aSprite.getSpeed().getY());
        }

        if (aSprite.getY()<20){
            System.out.println("Crash north border!");
            aSprite.setSpeed(aSprite.getSpeed().getX(), (float)Math.random()*(100));
        }

        if (aSprite.getY()> 400){
            System.out.println("Crash southern border! Must build wall!");
            aSprite.setSpeed(aSprite.getSpeed().getX(), (float)Math.random()*(-100));
        }

         if(heliSprite.collides(aSprite)) {
            System.out.println("crash each other!");
            if (heliSprite.getSpeed().getX()>1){
                aSprite.setSpeed((float)Math.random()*(-120), aSprite.getSpeed().getY());

            }
            else{
                aSprite.setSpeed(aSprite.getSpeed().getX(), (float)Math.random()*(-100));

            }
            heliSprite.setSpeed(heliSprite.getSpeed().getX(), heliSprite.getSpeed().getY());
        }

        else if(aSprite.collides(heliSprite)) {
            System.out.println("crash each other!");
            if (heliSprite.getSpeed().getX()>1){
                aSprite.setSpeed((float)Math.random()*(-120), aSprite.getSpeed().getY());

            }
            else{
                aSprite.setSpeed(aSprite.getSpeed().getX(), (float)Math.random()*(-100));

            }
            heliSprite.setSpeed(heliSprite.getSpeed().getX(), heliSprite.getSpeed().getY());
        }

        //borders for seondSprite

        if(secondSprite.getX()>=290)
        {
            System.out.println("crash east border!");
            secondSprite.setSpeed(-(float)Math.random()*(100), secondSprite.getSpeed().getY());
        }

        else if(secondSprite.collides(westWall)) // collides is true first time, and change the object direction.
        {
            System.out.println("crash west border!");
            secondSprite.setSpeed((float)Math.random()*(100), secondSprite.getSpeed().getY());
        }

        if (secondSprite.getY()<20){
            System.out.println("Crash north border!");
            secondSprite.setSpeed(secondSprite.getSpeed().getX(), (float)Math.random()*(100));
        }

        if (secondSprite.getY()> 400){
            System.out.println("Crash southern border! Must build wall!");
            secondSprite.setSpeed(aSprite.getSpeed().getX(), (float)Math.random()*(-100));
        }

        if(secondSprite.collides(aSprite)) {
            System.out.println("crash each other!");
            if (aSprite.getSpeed().getX()>aSprite.getSpeed().getY()){
                secondSprite.setSpeed((float)Math.random()*(-120), secondSprite.getSpeed().getY());
                aSprite.setSpeed((float)Math.random()*(-120), secondSprite.getSpeed().getY());

            }
            else{
                aSprite.setSpeed(aSprite.getSpeed().getX(), (float)Math.random()*(-100));
                secondSprite.setSpeed( secondSprite.getSpeed().getY(), (float)Math.random()*(-100));

            }
            heliSprite.setSpeed(heliSprite.getSpeed().getX(), heliSprite.getSpeed().getY());
        }

        else if(secondSprite.collides(heliSprite)) // This collides is judged since the above collides.
        // First execution collides will be true at the first time without any judge.
        {
            System.out.println("crash each other!");
            if (heliSprite.getSpeed().getX()>1){
                secondSprite.setSpeed((float)Math.random()*(-120), secondSprite.getSpeed().getY());

            }
            else{
                secondSprite.setSpeed(secondSprite.getSpeed().getX(), (float)Math.random()*(-100));

            }
            heliSprite.setSpeed(heliSprite.getSpeed().getX(), heliSprite.getSpeed().getY());
        }


        westWall.update(dt);
        aSprite.update(dt);
        heliSprite.update(dt);

    }


}
