package com.iplusplus.custopoly.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.*;
import com.iplusplus.custopoly.model.MusicPlayer;
import com.iplusplus.custopoly.model.gamemodel.element.Player;
import com.iplusplus.custopoly.model.gamemodel.element.PropertyLand;

import java.util.ArrayList;


public class PropertiesViewActivity extends Activity {
    private PropertyLand selectedProperty = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //These will put the app on full screen
        //supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_properties_view);

        Intent intent = getIntent();

        //Get intent arguments
        final Player currentPlayer = (Player)intent.getSerializableExtra("currentPlayer");
        final ArrayList<PropertyLand> properties = (ArrayList<PropertyLand>)intent.getSerializableExtra("propertiesList");
        final ArrayList<Integer> imageIdsUnMortgaged = intent.getIntegerArrayListExtra("imageIdsListUnMortgaged");
        final ArrayList<PropertyLand> mortgages = (ArrayList<PropertyLand>)intent.getSerializableExtra("mortgageList");
        final ArrayList<Integer> imageIdsMortgaged = intent.getIntegerArrayListExtra("imageIdsListMortgaged");

        //Set the different tabs
        TabHost viewsTabs = (TabHost)findViewById(R.id.propertiesViewTabHost);
        viewsTabs.setup();

        TabHost.TabSpec unMortgageTab = viewsTabs.newTabSpec("UnMortgaged");
        unMortgageTab.setContent(R.id.UnMortgaged);
        unMortgageTab.setIndicator("Unmortgaged");
        viewsTabs.addTab(unMortgageTab);

        TabHost.TabSpec mortgageTab = viewsTabs.newTabSpec("Mortgaged");
        mortgageTab.setContent(R.id.Mortgaged);
        mortgageTab.setIndicator("Mortgaged");
        viewsTabs.addTab(mortgageTab);
        //Implement the tabChange listener
        viewsTabs.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                if(tabId.equals("UnMortgaged"))
                {
                    Button mortageButton = (Button)findViewById(R.id.mortgageButton);
                    mortageButton.setText("Mortgage");

                    buildPropertiesViewFromInformation(currentPlayer, properties, imageIdsUnMortgaged, "UnMortgaged");
                }
                else
                {
                    Button mortageButton = (Button)findViewById(R.id.mortgageButton);
                    mortageButton.setText("Unmortgage");

                    buildPropertiesViewFromInformation(currentPlayer, mortgages, imageIdsMortgaged, "Mortgaged");
                }
            }
        });

        //Process arguments
        buildPropertiesViewFromInformation(currentPlayer, properties, imageIdsUnMortgaged, "UnMortgaged");

        //Handle mrotage button
        final Button mortageButton = (Button)findViewById(R.id.mortgageButton);
        final PropertiesViewActivity me = this;
        if (properties.size() == 0) mortageButton.setEnabled(false);
        mortageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(me)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle(getString(R.string.ingame_mortagege_title))
                        .setMessage(String.format(getString(R.string.ingame_mortagege_message), selectedProperty.getName()))
                        .setPositiveButton(getString(R.string.ingame_buyyesbutton), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //Create a new intent and return OK from the activity
                                Intent returnIntent = new Intent();
                                returnIntent.putExtra("mortgageLand", selectedProperty.getName());
                                setResult(RESULT_OK, returnIntent);
                                finish();
                            }

                        })
                        .setNegativeButton(getString(R.string.ingame_buynobutton), null)
                        .show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        //Create a new intent and return OK from the activity
        Intent returnIntent = new Intent();
        setResult(RESULT_CANCELED, returnIntent);
        PropertiesViewActivity.this.finish();
    }

    private void buildPropertiesViewFromInformation(Player currentPlayer, ArrayList<PropertyLand> properties, ArrayList<Integer> imageIds, String tab)
    {
        //Select the appropiate container
        LinearLayout propertiesContainer;
        if(tab.equals("UnMortgaged")) {
            propertiesContainer = (LinearLayout) findViewById(R.id.unmortagedContainerLayout);
        }
        else
        {
            propertiesContainer = (LinearLayout) findViewById(R.id.mortgagedContainerLayout);
        }
        propertiesContainer.removeAllViews();

        //Add an image to the porpertiesContainerLayout (its an horizontal scrollview)
        int i = 0;
        for(PropertyLand prop: properties)
        {
            ImageView propertyImageView = new ImageView(this);
            //Set the image resource id
            propertyImageView.setImageResource(imageIds.get(i));
            //Add the new imageView
            propertiesContainer.addView(propertyImageView);

            //Configure the listener
            final PropertyLand attachedProp = prop;
            propertyImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    displayPropertyInformation(attachedProp);
                }
            });

            i++;
        }

        //Display Initial information
        if(!properties.isEmpty()) {
            displayPropertyInformation(properties.get(0));
        }
    }

    void displayPropertyInformation(PropertyLand property)
    {
        //Get the text views that will be modifed
        TextView propertyNameTextView = (TextView)findViewById(R.id.nameTextView);
        TextView propertyValueTextView = (TextView)findViewById(R.id.valueTextView);
        TextView propertyRentTextView = (TextView)findViewById(R.id.rentValueTextView);
        TextView propertyMortgageValueTextView = (TextView)findViewById(R.id.mortgageValueTextView);

        //Set values
        propertyNameTextView.setText(property.getName());
        propertyValueTextView.setText(Integer.toString(property.getPrice()));
        propertyRentTextView.setText(Integer.toString(property.getRentInfo().getBaseRent()));
        propertyMortgageValueTextView.setText(Integer.toString(property.getMortgage()));

        selectedProperty = property;
    }

    @Override
    protected void onPause() {
        super.onPause();
        MusicPlayer.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MusicPlayer.resume();
    }
}
