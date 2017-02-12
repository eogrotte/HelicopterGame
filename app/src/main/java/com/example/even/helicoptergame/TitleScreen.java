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

//TitleScreen er 'viewet'

public final class TitleScreen extends State  {

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


    Controller controller = new Controller();
    HelicopterModel model = new HelicopterModel();


    public TitleScreen() {

        model.HeliCopterModel();
        this.addTouchListener(new TouchListener() {
            @Override
            public boolean onTouchDown(MotionEvent motionEvent) {
                float trykkX=motionEvent.getX();
                float trykkY=motionEvent.getY();

                float helicopterX=heliSprite.getX();
                float helicopterY=heliSprite.getY();

                double[] speed=controller.movement(trykkX, trykkY, helicopterX, helicopterY);
                heliSprite.setSpeed((float)speed[0], (float)speed[1]);

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

        controller.update(5);

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



}
