package com.iplusplus.custopoly.app;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
