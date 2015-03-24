package com.iplusplus.custopoly.model.gamemodel;

import com.iplusplus.custopoly.model.PlayerSkins;
import com.iplusplus.custopoly.model.gamemodel.cellelements.PropertyLand;

import java.util.List;

/**
 * Holds all information regarding a particular player.
 */
public class Player {

    private PlayerSkins skin;
    private String name;
    private List<PropertyLand> properties;
    private List<Card> cards;


}
