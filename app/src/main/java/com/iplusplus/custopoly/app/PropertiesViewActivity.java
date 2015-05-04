package com.iplusplus.custopoly.app;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.iplusplus.custopoly.model.gamemodel.element.Player;
import com.iplusplus.custopoly.model.gamemodel.element.PropertyLand;

import java.util.ArrayList;


public class PropertiesViewActivity extends ActionBarActivity {
    private PropertyLand selectedProperty = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_properties_view);

        Intent intent = getIntent();

        //Get intent arguments
        Player currentPlayer = (Player)intent.getSerializableExtra("currentPlayer");
        ArrayList<PropertyLand> properties = (ArrayList<PropertyLand>)intent.getSerializableExtra("propertiesList");
        ArrayList<Integer> imageIds = intent.getIntegerArrayListExtra("imageIdsList");

        //Process arguments
        buildPropertiesViewFromInformation(currentPlayer, properties, imageIds);

        //Handle mrotage button
        Button mortageButton = (Button)findViewById(R.id.mortgageButton);
        final PropertiesViewActivity me = this;
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
        super.onBackPressed();
    }

    private void buildPropertiesViewFromInformation(Player currentPlayer, ArrayList<PropertyLand> properties, ArrayList<Integer> imageIds)
    {
        //Get the container layout (where the images are placed)
        LinearLayout propertiesContainer = (LinearLayout)findViewById(R.id.propertiesContainerLayout);

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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_property_view, menu);
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
