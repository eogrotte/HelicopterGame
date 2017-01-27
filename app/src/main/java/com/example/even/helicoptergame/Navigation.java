package com.example.even.helicoptergame;

/**
 * Created by Even on 26.01.2017.
 */

import sheep.game.Sprite;
import sheep.game.State;
import sheep.graphics.Image;
import sheep.game.Game;




public class Navigation extends Sprite {


    private boolean moveLeft;
    private boolean moveRight;
    private boolean moveUp;
    private boolean moveDown;
    private static final float velocity = 150.0f;

    public Navigation(Image image) {
        super(image);
        setPosition(100.0f, 900.0f);
        moveLeft = false;
        moveRight = false;
        moveUp = false;
        moveDown = false;
    }

    @Override
    public void update(float dt) {
        super.update(dt);
        if (moveLeft) {
            float x = getX();
            float y = getY();
            float dx = dt * velocity;
            setPosition(x - dx, y);
        }
        if (moveRight) {
            float x = getX();
            float y = getY();
            float dx = dt * velocity;
            setPosition(x + dx, y);
        }
        if (moveUp) {
            float x = getX();
            float y = getY();
            float dy = dt * velocity;
            setPosition(x, y - dy);
        }
        if (moveDown) {
            float x = getX();
            float y = getY();
            float dy = dt * velocity;
            setPosition(x, y + dy);
        }
    }

    public void startMovingLeft() {
        moveLeft = true;
    }

    public void startMovingRight() {
        moveRight = true;
    }

    public void startMovingUp() {
        moveUp = true;
    }

    public void startMovingDown() {
        moveDown = true;
    }

    public void stopMovingLeft() {
        moveLeft = false;
    }

    public void stopMovingRight() {
        moveRight = false;
    }

    public void stopMovingUp() {
        moveUp = false;
    }

    public void stopMovingDown() {
        moveDown = false;
    }
}
