package com.example.even.helicoptergame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import sheep.game.Game;
import android.app.Activity;


public class MainActivity extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Create the game.
        Game game = new Game(this, null);
        // Push the main state.
        game.pushState(new TitleScreen());
        // View the game.
        setContentView(game);

    }
}
