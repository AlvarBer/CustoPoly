package com.iplusplus.custopoly.model.gamemodel.element;

import java.io.Serializable;
import java.util.Random;

public class DicePair implements Serializable {
	
	private static int[] current;
	
	public static void roll(){
		Random rgen = new Random();
		current = new int[] {rgen.nextInt(6) + 1, rgen.nextInt(6) + 1};
	}

	public static int getDiceValue() {
		return current[0] + current[1];
	}
	
	public static int[] getCurrent() {
		return current;
	}
	
	
}
