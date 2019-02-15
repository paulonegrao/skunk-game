package launch;

public class Player {

	private int numId;
	private static String cmd0	= "sk_player_";
	private static String cmd1	= ".classList.remove('up');";
	
	public Player(int numId) {
		if ((numId >=1) && (numId <=7)){
			this.numId = numId;
		} else {
			throw new IllegalArgumentException("invalid Player ID number");
		}
	}
	
	public String movePlayer(int action) {
		switch (action) {
		case 2: return	cmd0 + numId + cmd1;
		default: return "";
		}
	}
			
}
