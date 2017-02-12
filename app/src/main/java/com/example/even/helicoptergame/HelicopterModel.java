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
 * Created by Even on 12.02.2017.
 */


//HelicopterModel er modellen
public class HelicopterModel {

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

    public void HeliCopterModel() {
        backSprite = new Sprite(backgroundImage);
        heliSprite = new Sprite(heliImage);
        aSprite = new Sprite(aImage);
        secondSprite = new Sprite (aImage);
        westWall = new Sprite(wallVerImage);
        westWall.setPosition(4, 215);

        aSprite.setPosition(60, 60);
        aSprite.setSpeed((float)Math.random()*(-100), (float)Math.random()*(-100));

        secondSprite.setPosition(60, 300);
        secondSprite.setSpeed((float)Math.random()*(-100), (float)Math.random()*(-100));

        heliSprite.setPosition(200, 120);
        heliSprite.setSpeed(40, 0);

    }

}
