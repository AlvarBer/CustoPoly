package com.iplusplus.custopoly.app;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.*;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ViewFlipper;
import com.iplusplus.custopoly.model.GameTheme;
import com.iplusplus.custopoly.model.ShopKeeper;
import com.iplusplus.custopoly.model.ThemeHandler;

import java.util.ArrayList;
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


public class ThemeSelectionActivity extends ActionBarActivity implements View.OnClickListener, View.OnLongClickListener {

    private Button bBack;
    private ViewFlipper themeFlipper;
    private TextView themeNameText;
    private ArrayList<GameTheme> purchasedThemes;

    private final ShopKeeper shopKeeperInstance = ThemeHandler.getInstance().getShopKeeperInstance();

    // I have put all the RadioButtons but the first disable, so it can be handled by the shopKeeper
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //These will put the app on full screen
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme_selection);

        purchasedThemes = ThemeHandler.getInstance().getThemes();

        this.bBack = (Button)findViewById(R.id.bBack);
        this.themeFlipper = (ViewFlipper)findViewById(R.id.themeFlipper);
        this.themeNameText = (TextView)findViewById(R.id.themeNameTextView);

        setupThemeFlipper();

        //Quitar el hack y hacerlo para la lista de themes en la shopkeeper
        this.themeNameText.setText(purchasedThemes.get(this.themeFlipper.getCurrentView().getId()).getName());

        this.bBack.setOnClickListener(this);
        this.themeFlipper.setOnClickListener(this);
        this.themeFlipper.setOnLongClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bBack:
                Intent back = new Intent(ThemeSelectionActivity.this, MainActivity.class);
                startActivity(back);
                break;

            case R.id.themeFlipper:

                this.themeFlipper.showNext();
                // version buena, pero usamos el pequeño hack
                // we access to the name of the current view
                this.themeNameText.setText(purchasedThemes.get(this.themeFlipper.getCurrentView().getId()).getName());
                //this.themeNameText.setText(ThemeHandler.getInstance().getCurrentTheme().getName());
                break;
        }
    }

    @Override
    public boolean onLongClick(View v) {
        switch(v.getId()) {
            case R.id.themeFlipper:
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Select Theme")
                    .setMessage("Do you want to select this theme?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //esto esta bien cambiar por el hack
                           ThemeHandler.getInstance().setCurrentTheme(themeFlipper.getCurrentView().getId());
                            //ThemeHandler.getInstance().switchThemeTo(ThemeHandler.getInstance().getCurrentTheme());

                            Intent main = new Intent(ThemeSelectionActivity.this, MainActivity.class);
                            startActivity(main);
                        }

                    })
                    .setNegativeButton("No", null)
                    .show();
                break;
        }

        return false;
    }

    /**
     * Defines the behaviour of the back button. (Of the phone)
     */
    @Override
    public void onBackPressed() {
        Intent play = new Intent(ThemeSelectionActivity.this, MainActivity.class);
        startActivity(play);
    }

    private void setupThemeFlipper() { // here I add the images in the arrayList so I can put them in the flippers;
        // ESTE ES EL CÓDIGO CORRECTO, PERO VOY A USAR EL HACK DE ABAJO PARA DEBUGGEAR
       Iterator it = this.purchasedThemes.iterator();
        int count = 0;
        ImageView skinImage;

        while (it.hasNext()) {
            GameTheme theme = (GameTheme)it.next();

            skinImage = createImage(count, getResources().getIdentifier(theme.getBackgroundPathResource(), "drawable", getPackageName()));
            this.themeFlipper.addView(skinImage, count);

            count++;
        }

        //ImageView skinImage = createImage(0, getResources().getIdentifier(ThemeHandler.getInstance().getCurrentTheme().getBackgroundPathResource(), "drawable", getPackageName()));
        //this.themeFlipper.addView(skinImage, 0);
    }

    private ImageView createImage(int count, int imageResId) {
        ImageView image = new ImageView(this);
        //  image.setLayoutParams(new LinearLayout.LayoutParams( LayoutParams.WRAP_CONTENT,          LayoutParams.WRAP_CONTENT));//I set wrap_content to the layout params
        image.setId(count);//I set the id from 0 to numImages - 1, I don't know how to put a string
        image.setImageResource(imageResId);
        return image;
    }


}
