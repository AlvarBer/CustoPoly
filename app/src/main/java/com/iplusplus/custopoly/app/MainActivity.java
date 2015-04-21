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
        //These will put the app on full screen
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

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
}