package com.iplusplus.custopoly.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
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
private HashMap<ImageView, GameTheme> themeDict = new HashMap<ImageView, GameTheme>();

private boolean buy = false;


@Override
protected void onCreate(Bundle savedInstanceState) {
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

        private void fillWithContent()
                {
                for (GameTheme theme : this.shopKeeper.getThemesInShopList()) {
                        LinearLayout layout;
                        layout = createThemeView(theme);

                        this.themesLayout.addView(layout);
                }
                }

        private LinearLayout createThemeView(GameTheme theme) {
                LinearLayout layout = new LinearLayout(Custopoly.getAppContext());
                layout.setOrientation(LinearLayout.VERTICAL);

                //Set up image
                ImageView themeImage = new ImageView(Custopoly.getAppContext());
                themeImage.setBackgroundColor(Color.BLUE);
                themeImage.setMinimumHeight(50);
                themeImage.setMinimumWidth(50);
                //themeImage.setImageResource(getResources().getIdentifier(theme.getBackgroundPathResource(), "drawable", getPackageName()));
                this.themeDict.put(themeImage, theme);
                if (!this.shopKeeper.isThemePurchased(theme)) {
                themeImage.setOnClickListener(this);
                } else {
                themeImage.setEnabled(false);
                }

                //Set up name
                TextView themeName = new TextView(Custopoly.getAppContext());
                themeName.setText(theme.getName());
                themeName.setTextSize(30.0f);

                //Set up cost
                TextView themeCost = new TextView(Custopoly.getAppContext());
                themeCost.setText("i$$ " + Double.toString(theme.getPrice()));
                themeCost.setTextSize(30.0F);

                //Add everything to the layout
                layout.addView(themeImage);
                layout.addView(themeName);
                layout.addView(themeCost);

                return layout;
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
                .setPositiveButton(Custopoly.getAppContext().getString(R.string.shop_yes),
                new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
                buyProperty();
                }

                })
                .setNegativeButton(Custopoly.getAppContext().getString(R.string.shop_no), new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
                }
                })
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
}
