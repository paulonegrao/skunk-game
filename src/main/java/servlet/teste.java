package servlet;

import java.util.HashMap;

public class teste{
	private DiceRotation dd = new DiceRotation();
	private HashMap<Integer, Player> players = new HashMap<Integer, Player>();
	HashMap<Integer,  String[][]> rotations = new HashMap<Integer,  String[][]>();
	
	public teste() {
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Player pl = new Player(1);
		

		
		System.out.println("..." + pl);
		
		DiceRotation dr = new DiceRotation();

		System.out.println("..." + dr.get);

	}
	public void zx() {

	}
}
