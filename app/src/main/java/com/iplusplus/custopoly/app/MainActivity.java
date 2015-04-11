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
import com.iplusplus.custopoly.Custopoly;
import com.iplusplus.custopoly.model.GameTheme;
import com.iplusplus.custopoly.model.PlayerSkin;
import com.iplusplus.custopoly.model.ThemeHandler;

import java.io.Serializable;
import java.util.HashSet;


public class MainActivity extends ActionBarActivity implements View.OnClickListener{

    //Attributes
    private Button play;
    private Button shop;
    private Button theme;
    private RelativeLayout layout;
    private TextView title;

    //Methods
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       //Associate the components of the XML file to the class attributes
        this.title = (TextView) findViewById(R.id.activity_main_tv_title);
        this.play = (Button) findViewById(R.id.activity_main_btn_play);
        this.shop = (Button) findViewById(R.id.activity_main_btn_shop);
        this.theme = (Button) findViewById(R.id.activity_main_btn_theme);
        this.layout = (RelativeLayout) findViewById(R.id.activity_main_rl);

        this.play.setOnClickListener(this);
        this.shop.setOnClickListener(this);
        this.theme.setOnClickListener(this);
        //Define behaviour of Play Button when is pressed

        //Disable the other buttons until their activities were implemented
        shop.setEnabled(false);


    }

    @Override
    public void onClick(View v) {
        Intent myIntent;
        switch (v.getId()) {
            case R.id.activity_main_btn_play:
                myIntent = new Intent(MainActivity.this, GameMenuActivity.class);
                MainActivity.this.startActivity(myIntent);
                break;
            case R.id.activity_main_btn_shop:
                myIntent = new Intent(MainActivity.this, ShopActivity.class);
                MainActivity.this.startActivity(myIntent);
                break;
            case R.id.activity_main_btn_theme:
                myIntent = new Intent(MainActivity.this, ThemeSelectionActivity.class);
                MainActivity.this.startActivity(myIntent);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
// Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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