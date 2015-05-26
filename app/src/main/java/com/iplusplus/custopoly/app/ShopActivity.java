package com.iplusplus.custopoly.app;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.iplusplus.custopoly.Custopoly;
import com.iplusplus.custopoly.model.GameTheme;
import com.iplusplus.custopoly.model.ShopKeeper;
import com.iplusplus.custopoly.model.ThemeHandler;

import java.util.HashMap;

/**
 * Corresponds with the shop_activity in the mockup.
 *
 * Allows to buy themes, to see the total points, etc.
 *
 * Access to the model is doe via ModelFacade.getInstance().[methodname]
 *  EXAMPLE:
 *          ModelFacade.getInstance().switchThemeTo(Themes.THEME1);
 */

public class ShopActivity extends Activity implements  View.OnClickListener {

    private LinearLayout themesLayout;
    private TextView playerName;
    private ImageView selectedView;
    private TextView playerMoney;
    private ShopKeeper shopKeeper = ThemeHandler.getInstance().getShopKeeperInstance();
    private GameTheme selectedTheme = null;
    private Handler handler;
    private HashMap<ImageView, GameTheme> themeDict = new HashMap<ImageView, GameTheme>();

    private boolean buy = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        //TODO: THIS IS DEBUG CODE
        ThemeHandler.getInstance().getShopKeeperInstance().addPoints(10000);

        setupViews();
        fillWithContent();

    }
    private void setupViews()
    {
            this.themesLayout = (LinearLayout) findViewById(R.id.activity_shop_ll_themes);
            //this.playerName = (TextView) findViewById(R.id.activity_shop_tv_player);
            this.playerMoney = (TextView) findViewById(R.id.activity_shop_tv_money);
            this.playerMoney.setText(this.playerMoney.getText() + "i$$ "
                    + ThemeHandler.getInstance().getShopKeeperInstance().getPoints());
    }

    private void fillWithContent(){
        this.themesLayout.removeAllViews();
        for (GameTheme theme : this.shopKeeper.getThemesInShopList()) {
            GridLayout layout;
            layout = createThemeView(theme);

            this.themesLayout.addView(layout);
        }
    }

    private GridLayout createThemeView(GameTheme theme) {
        GridLayout layout = new GridLayout(this);
        layout.setColumnCount(1);
        layout.setRowCount(3);

        //Set up image
        ImageView themeImage = new ImageView(this);
        themeImage.setImageResource(getResources().getIdentifier(theme.getBackgroundPathResource(), "drawable", getPackageName()));
        this.themeDict.put(themeImage, theme);
        if (!this.shopKeeper.isThemePurchased(theme)) {
                themeImage.setOnClickListener(this);
        } else {
                themeImage.setEnabled(false);
                themeImage.setAlpha(0.5f);
        }

        themeImage.setLayoutParams(this.createGridLayoutParams(0, 0));

        //Set up name
        TextView themeName = new TextView(this);
        themeName.setText(theme.getName());
        themeName.setTextSize(30.0f);
        themeName.setLayoutParams(this.createGridLayoutParams(0, 1));

        //Set up cost
        TextView themeCost = new TextView(this);
        themeCost.setText("i$$ " + Double.toString(theme.getPrice()));
        themeCost.setTextSize(30.0F);
        themeCost.setLayoutParams(this.createGridLayoutParams(0, 2));

        //Add everything to the layout
        layout.addView(themeImage);
        layout.addView(themeName);
        layout.addView(themeCost);

        //layout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
        //                                                 ViewGroup.LayoutParams.MATCH_PARENT));

        return layout;
    }

    private ViewGroup.LayoutParams createGridLayoutParams(int col, int row) {
        GridLayout.LayoutParams params = new GridLayout.LayoutParams();
        params.columnSpec = GridLayout.spec(col);
        params.rowSpec = GridLayout.spec(row);
        return params;
    }

    @Override
    //TODO: THIS WILL BREAK IF WE ARE LISTENING TO MORE THAN THE THEME IMAGES
    public void onClick(View view) {
        if (view instanceof ImageView) {
            this.selectedTheme = this.themeDict.get(view);
            this.selectedView = (ImageView) view;
            if (this.selectedTheme != null) {
                if (this.shopKeeper.getPoints() >= this.selectedTheme.getPrice()) {
                //Show wanna buy dialog
                    showWantToBuyDialog();
                } else {
                //Show not enough points dialog
                    showNotEnoughPointsDialog();
                }
            }
        }
    }

    private void showWantToBuyDialog() {
        new AlertDialog.Builder(this)
        .setMessage(Custopoly.getAppContext().getString(R.string.shop_wanttobuy_message))
        .setTitle(Custopoly.getAppContext().getString(R.string.shop_wanttobuy_title))
        .setPositiveButton(Custopoly.getAppContext().getString(R.string.shop_yes),  new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                    buyProperty(); } })
        .setNegativeButton(Custopoly.getAppContext().getString(R.string.shop_no), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            } })
                .show();

    }

    private void buyProperty() {
        this.shopKeeper.buyTheme(this.selectedTheme);
        new AlertDialog.Builder(this)
        .setIcon(android.R.drawable.ic_dialog_alert)
        .setMessage(String.format(Custopoly.getAppContext().getString(R.string.shop_themebought_message), this.selectedTheme.getName()))
        .setTitle(Custopoly.getAppContext().getString(R.string.shop_themebought_title))
        .show();
        this.playerMoney.setText("MONEY: i$$" + ThemeHandler.getInstance().getShopKeeperInstance().getPoints());
        this.selectedView.setEnabled(false);
        this.selectedView.setAlpha(0.5f);
        this.selectedView = null;
        this.selectedTheme = null;
    }

    private void showNotEnoughPointsDialog() {
        new AlertDialog.Builder(this)
        .setIcon(android.R.drawable.ic_dialog_alert)
        .setMessage(Custopoly.getAppContext().getString(R.string.shop_notenoughfunds_message))
        .setTitle(Custopoly.getAppContext().getString(R.string.shop_notenoughfunds_title))
        .show();
    }

    @Override
    public void onBackPressed() {
        Intent play = new Intent(ShopActivity.this, MainActivity.class);
        ShopActivity.this.finish();
        startActivity(play);
    }

}
