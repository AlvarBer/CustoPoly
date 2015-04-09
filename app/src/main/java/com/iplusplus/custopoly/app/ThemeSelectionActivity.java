package com.iplusplus.custopoly.app;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

import com.iplusplus.custopoly.model.*;
import com.iplusplus.custopoly.model.gamemodel.element.Bank;
import com.iplusplus.custopoly.model.gamemodel.element.Board;
import com.iplusplus.custopoly.model.gamemodel.element.Game;
import com.iplusplus.custopoly.model.gamemodel.element.Player;
import com.iplusplus.custopoly.model.gamemodel.util.BoardFactory;
import com.iplusplus.custopoly.model.gamemodel.util.CardFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Corresponds with the theme_activity in the mockup.
 *
 * Allows to buy themes and/or skins.
 * Maybe this can be done through settings?
 *
 * Access to the model is doe via ModelFacade.getInstance().[methodname]
 *  EXAMPLE:
 *          ModelFacade.getInstance().switchThemeTo(Themes.THEME1);
 */


public class ThemeSelectionActivity extends ActionBarActivity implements View.OnClickListener{

    private Button bBack;
    private RadioButton t1, t2, t3, t4, t5, t6, t7, t8;
    //TODO I have put all the RadioButtons but the first disable, so it can be handled by the shopKeeper
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme_selection);

        this.bBack = (Button)findViewById(R.id.bBack);
        this.t1 = (RadioButton)findViewById(R.id.bTheme1);
        this.t2 = (RadioButton)findViewById(R.id.bTheme2);
        this.t3 = (RadioButton)findViewById(R.id.bTheme3);
        this.t4 = (RadioButton)findViewById(R.id.bTheme4);
        this.t5 = (RadioButton)findViewById(R.id.bTheme5);
        this.t6 = (RadioButton)findViewById(R.id.bTheme6);
        this.t7 = (RadioButton)findViewById(R.id.bTheme7);
        this.t8 = (RadioButton)findViewById(R.id.bTheme8);

        this.bBack.setOnClickListener(this);
        this.t1.setOnClickListener(this);
        this.t2.setOnClickListener(this);
        this.t3.setOnClickListener(this);
        this.t4.setOnClickListener(this);
        this.t5.setOnClickListener(this);
        this.t6.setOnClickListener(this);
        this.t7.setOnClickListener(this);
        this.t8.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bBack:
                if(this.t1.isChecked()) {
                    //TODO call the function to choose theme1
                }
                else if(this.t2.isChecked()) {
                    //TODO call the function to choose theme2
                }
                else if(this.t3.isChecked()) {
                    //TODO call the function to choose theme3
                }
                else if(this.t4.isChecked()) {
                    //TODO call the function to choose theme4
                }
                else if(this.t5.isChecked()) {
                    //TODO call the function to choose theme5
                }
                else if(this.t6.isChecked()) {
                    //TODO call the function to choose theme6
                }
                else if(this.t7.isChecked()) {
                    //TODO call the function to choose theme7
                }
                else if(this.t8.isChecked()) {
                    //TODO call the function to choose theme8
                }
                Intent back = new Intent(ThemeSelectionActivity.this, MainActivity.class);
                startActivity(back);
                break;
            case R.id.bTheme1:
                if(this.t1.isChecked()) {
                    this.t2.setChecked(false);
                    this.t3.setChecked(false);
                    this.t4.setChecked(false);
                    this.t5.setChecked(false);
                    this.t6.setChecked(false);
                    this.t7.setChecked(false);
                    this.t8.setChecked(false);
                    //if you click a button yo choose that theme and quit all others
                }
                break;
            case R.id.bTheme2:
                if(this.t2.isChecked()) {
                    this.t1.setChecked(false);
                    this.t3.setChecked(false);
                    this.t4.setChecked(false);
                    this.t5.setChecked(false);
                    this.t6.setChecked(false);
                    this.t7.setChecked(false);
                    this.t8.setChecked(false);
                    //if you click a button yo choose that theme and quit all others
                }
                break;
            case R.id.bTheme3:
                if(this.t3.isChecked()) {
                    this.t1.setChecked(false);
                    this.t2.setChecked(false);
                    this.t4.setChecked(false);
                    this.t5.setChecked(false);
                    this.t6.setChecked(false);
                    this.t7.setChecked(false);
                    this.t8.setChecked(false);
                    //if you click a button yo choose that theme and quit all others
                }
                break;
            case R.id.bTheme4:
                if(this.t4.isChecked()) {
                    this.t1.setChecked(false);
                    this.t2.setChecked(false);
                    this.t3.setChecked(false);
                    this.t5.setChecked(false);
                    this.t6.setChecked(false);
                    this.t7.setChecked(false);
                    this.t8.setChecked(false);
                    //if you click a button yo choose that theme and quit all others
                }
                break;
            case R.id.bTheme5:
                if(this.t5.isChecked()) {
                    this.t1.setChecked(false);
                    this.t2.setChecked(false);
                    this.t3.setChecked(false);
                    this.t4.setChecked(false);
                    this.t6.setChecked(false);
                    this.t7.setChecked(false);
                    this.t8.setChecked(false);
                    //if you click a button yo choose that theme and quit all others
                }
                break;
            case R.id.bTheme6:
                if(this.t6.isChecked()) {
                    this.t1.setChecked(false);
                    this.t2.setChecked(false);
                    this.t3.setChecked(false);
                    this.t4.setChecked(false);
                    this.t5.setChecked(false);
                    this.t7.setChecked(false);
                    this.t8.setChecked(false);
                    //if you click a button yo choose that theme and quit all others
                }
                break;
            case R.id.bTheme7:
                if(this.t7.isChecked()) {
                    this.t1.setChecked(false);
                    this.t2.setChecked(false);
                    this.t3.setChecked(false);
                    this.t4.setChecked(false);
                    this.t5.setChecked(false);
                    this.t6.setChecked(false);
                    this.t8.setChecked(false);
                    //if you click a button yo choose that theme and quit all others
                }
                break;
            case R.id.bTheme8:
                if(this.t8.isChecked()) {
                    this.t1.setChecked(false);
                    this.t2.setChecked(false);
                    this.t3.setChecked(false);
                    this.t4.setChecked(false);
                    this.t5.setChecked(false);
                    this.t6.setChecked(false);
                    this.t7.setChecked(false);
                    //if you click a button yo choose that theme and quit all others
                }
                break;

        }
    }

    //TODO the following functions may be erased
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_theme_selection, menu);
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
