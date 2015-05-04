package com.iplusplus.custopoly.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.*;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.iplusplus.custopoly.model.gamemodel.element.Player;
import com.iplusplus.custopoly.model.gamemodel.element.PropertyLand;

import java.util.ArrayList;


public class PropertiesViewActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //These will put the app on full screen
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_properties_view);

        Intent intent = getIntent();

        Player currentPlayer = (Player)intent.getSerializableExtra("currentPlayer");
        ArrayList<PropertyLand> properties = (ArrayList<PropertyLand>)intent.getSerializableExtra("propertiesList");
        ArrayList<Integer> imageIds = intent.getIntegerArrayListExtra("imageIdsList");
        
        buildPropertiesViewFromInformation(currentPlayer, properties, imageIds);
    }

    private void buildPropertiesViewFromInformation(Player currentPlayer, ArrayList<PropertyLand> properties, ArrayList<Integer> imageIds)
    {
        LinearLayout propertiesContainer = (LinearLayout)findViewById(R.id.propertiesContainerLayout);

        //Add an image to the porpertiesContainerLayout (its an horizontal scrollview)
        int i = 0;
        for(PropertyLand prop: properties)
        {
            ImageView propertyImageView = new ImageView(this);
            //Set the image resource id
            propertyImageView.setImageResource(imageIds.get(i));
           // propertyImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

            propertiesContainer.addView(propertyImageView);

            final PropertyLand attachedProp = prop;
            propertyImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    displayPropertyInformation(attachedProp);
                }
            });

            i++;
        }

        //Initial information
        if(!properties.isEmpty()) {
            displayPropertyInformation(properties.get(0));
        }
    }

    void displayPropertyInformation(PropertyLand property)
    {
        //Get the text views that will be modifed
        TextView propertyNameView = (TextView)findViewById(R.id.nameTextView);
        TextView propertyValueView = (TextView)findViewById(R.id.valueTextView);
        TextView propertyRentView = (TextView)findViewById(R.id.rentValueTextView);

        //Set values
        propertyNameView.setText(property.getName());
        propertyValueView.setText(Integer.toString(property.getPrice()));
        propertyRentView.setText(Integer.toString(property.getRentInfo().getBaseRent()));
    }
}
