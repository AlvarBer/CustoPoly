package com.iplusplus.custopoly.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Corresponds with the game_menu_activity in the mockup.
 *
 * Allows to create a new game (and to resume one if time allows).
 *
 * Access to the model is doe via ModelFacade.getInstance().[methodname]
 *  EXAMPLE:
 *          ModelFacade.getInstance().switchThemeTo(Themes.THEME1);
 */


public class GameMenuActivity extends ActionBarActivity {

    //Atttributes
    private RelativeLayout layout;
    private TextView title;
    private Button newGame;
    private Button loadGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //These will put the app on full screen
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_menu);

        //Associate the components of the XML file to the class attributes
        this.layout = (RelativeLayout) findViewById(R.id.activity_game_menu_rl);
        this.title = (TextView) findViewById(R.id.activity_game_menu_tv_title);
        this.newGame = (Button) findViewById(R.id.activity_game_menu_btn_newGame);
        this.loadGame = (Button) findViewById(R.id.activity_game_menu_btn_loadGame);


        //Define behaviour of New Button when is pressed
        newGame.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //Starts the Pre-Game Activity
                Intent myIntent = new Intent(GameMenuActivity.this, PreGameActivity.class);
                startActivity(myIntent);

            }
        });

        //Define behaviour of Load Button when is pressed
        loadGame.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //Starts the Pre-Game Activity
                Intent myIntent = new Intent(GameMenuActivity.this, GameActivity.class);
                startActivity(myIntent);

            }
        });
    }

    public void onBackPressed() {
        Intent back = new Intent(GameMenuActivity.this, MainActivity.class);
        startActivity(back);
    }

}
