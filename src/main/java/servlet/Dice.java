package servlet;

import java.util.HashMap;
import java.util.Random;

public class Dice {
	private DiceRotation rotations = new DiceRotation();
	
	int diceId;
	int value;
	int pairRotation;
	
	private Random r = new Random();
	
	public Dice(int diceId) {
		if (diceId > 0) {
			this.diceId = diceId;
		}
		if (value > 0) {
			this.value = value;
		}
		pairRotation = 0;

	}
	
	public int rollDice(int faces) {
		if (pairRotation == 0) {
			pairRotation = 1;
		}else {
			pairRotation = 0;
		}
		if (faces > 0) {
			return r.nextInt(faces) + 1;
		}
		// return 1 when finishing SKUNK rides
		return 1;
	}
	
	public void setDiceValue(int value) {
		/*if (pairRotation == 0) {
			pairRotation = 1;
		}else {
			pairRotation = 0;
		}*/
		if (value > 0) {
			this.value = value;
		}
	}
	public int getDiceValue() {
		return this.value;
	}
	public String getDiceRotation() {
		System.out.println("getDiceRotaion value = " + value);
		
		String[] wR = rotations.getRotation(value,  pairRotation);
		System.out.println("getDiceRotaion rotateX = " + wR[0]);
		System.out.println("getDiceRotaion rotateY = " + wR[1]);
		return "rotateX(" + wR[0] + ") rotateY(" + wR[1] + ")";

	}

}
