package com.iplusplus.custopoly.model.gamemodel.element;
import java.util.ArrayList;

public class Board {
	
	private ArrayList<Land> lands;
	
	public Board() {
		this.lands = new ArrayList<Land>();
	}

	public int getSize() {
		return lands.size();
	}

	public ArrayList<Land> getLands() {
		return lands;
	}

	public void setLands(ArrayList<Land> lands) {
		this.lands = lands;
	}
	
	
}
