package servlet;

import java.util.HashMap;

public class DiceRotation extends HashMap{

		HashMap<Integer,  String[][]> rotations = new HashMap<Integer,  String[][]>();
		
		public DiceRotation() {

			rotations.put(1, new String[][]{{"180deg", "0deg"}, {"1980deg", "360deg"}});
			rotations.put(2, new String[][]{{"0deg", "90deg"}, {"360deg", "1890deg"}});
			rotations.put(3, new String[][]{{"-90deg", "0deg"}, {"-1890deg", "360deg"}});
			rotations.put(4, new String[][]{{"90deg", "0deg"}, {"1890deg", "360deg"}});
			rotations.put(5, new String[][]{{"0deg", "-90deg"}, {"360deg", "-1890deg"}});
			rotations.put(6, new String[][]{{"0deg", "0deg"}, {"1800deg", "360deg"}});
			
		}
		
		public String[] getRotation(int value, int pairRotation){
			String[][] wR = rotations.get(value);
			return wR[pairRotation];
		}

}
