package com.iplusplus.custopoly.model.gamemodel.element;

import android.media.Image;

/**
 * Class that holds a single player's token information.
 */

public class Token {
	private int landIndex;
	String imagePath;

	public Token(int tokenID) {
		this.imagePath = String.format("assets/tokens/monopoly_token_%d.png", tokenID);
	}

	public void setLocation(int landIndex) {
		this.landIndex = landIndex;
	}

	public int getLandIndex() {
		return landIndex;
	}

    //TODO: have a way to retrieve the image of the player.
	public Image getImage() {
        /*
		Image tokenImage = new ImageIcon(imagePath).getImage();
		return tokenImage.getScaledInstance(30, 22, Image.SCALE_SMOOTH);
		*/
        return null;
	}
}
