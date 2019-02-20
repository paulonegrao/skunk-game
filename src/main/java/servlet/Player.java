package servlet;

public class Player {

	private int skunkSseId;
	private String skunkId;
	private String message 		= "";
	private String dataHeader	= "data: ";
	private String dataBreak	= "\n";
	private String dataTrailer	= "\n\n";
	
	public Player(int skunkSseId) {
		if (skunkSseId > 0 ){
			this.skunkSseId = skunkSseId;
			skunkId = "skunk0";
			System.out.println("criou Player=" + this.skunkSseId + "skunkId=" + this.skunkId);
		}
	}
	
	public Player(String skunkId) {
		if ((skunkId != null) && (SkunkGame.validskunks.contains(skunkId))){
			this.skunkId = skunkId;
		}
	}
	
	public String makeMove(String skunkId, String action) {
		System.out.println("no makeMove do player");
		
		if ((SkunkGame.gameStatus.equals("setup") && (action.equals("choose")))) {
			String newAction = "play";
			message = message + dataHeader + skunkId + "_choose.classList.remove('on');" 				+ dataBreak;
			message = message + dataHeader + skunkId + "_choose.classList.add('off');" 					+ dataBreak;
			message = message + dataHeader + skunkId + "_choose.disabled = true;" 						+ dataBreak;
			
			message = message + dataHeader + skunkId + "_play.classList.remove('off');" 				+ dataBreak;
			message = message + dataHeader + skunkId + "_play.classList.add('on', 'up');" 				+ dataBreak;
			message = message + dataHeader + skunkId + "_play.disabled = false;" 						+ dataBreak;
			
			if (skunkId.equals(SkunkGame.skunkBoss)) {
				message = message + dataHeader + "play_pause.classList.remove('off');" 					+ dataBreak;
				message = message + dataHeader + "play_pause.classList.add('on');" 						+ dataBreak;
				message = message + dataHeader + "play_pause.disabled = false;"  						+ dataBreak;
				message = message + dataHeader + "finish.classList.remove('off');" 						+ dataBreak;
				message = message + dataHeader + "finish.classList.add('on');" 							+ dataBreak;
				message = message + dataHeader + "finish.disabled = false;"  							+ dataBreak;
			}
			// set client's new skunkId
			message = message + dataHeader + 	"if (getCookie('skunkSseId') == " +  this.skunkSseId +
												" ){setCookie('skunkId', '" + skunkId + "', 1)};"		+ dataTrailer;
			

		// remove no chosen skunks
		}else if ((SkunkGame.gameStatus.equals("play")) && (action.equals("play"))) {
			for (String skunkSet : SkunkGame.skunksToChoose) {
				message = message + dataHeader + skunkSet  + "_choose.classList.remove('on');" 	        + dataBreak;
				message = message + dataHeader + skunkSet  + "_choose.classList.add('off');" 	        + dataBreak;
				message = message + dataHeader + "overlay.style.display = 'block';" 					+ dataBreak;
				message = message + dataHeader + "cube1.style.display = 'block';" 						+ dataBreak;
				message = message + dataHeader + "cube2.style.display = 'block';" 						+ dataBreak;
				message = message + dataHeader + "cube3.style.display = 'none';" 						+ dataBreak;
			}
		// move skunks UP & DOWN
		}else if ((SkunkGame.gameStatus.equals("play")) && (action.equals("down"))) {
			message = message + dataHeader + skunkId + "_play.classList.remove('up');" 					+ dataBreak;
			message = message + dataHeader + skunkId + "_play.classList.add('down');" 					+ dataBreak;
			message = message + dataHeader + skunkId + "_play.disabled = true;" 						+ dataTrailer;
			
		}else if ((SkunkGame.gameStatus.equals("play")) && (action.equals("up"))) {
			message = message + dataHeader + skunkId + "_play.classList.remove('down');" 				+ dataBreak;
			message = message + dataHeader + skunkId + "_play.classList.add('up');" 					+ dataBreak;
			message = message + dataHeader + skunkId + "_play.disabled = true;" 						+ dataTrailer;
			
		}
		System.out.println("message=" + message);
		return message;
	}
		
		
	public String diceMove(String skunkId, String action, Dice dice) {
			System.out.println("no diceMove do player");
		
		// roll 1, 2 or 3 dice
		if ((SkunkGame.gameStatus.equals("play")) && (action.substring(0, 4).equals("roll"))) {
	
			message = message + dataHeader + "cube" + dice.diceId + ".style.transform = 'translateZ(-100px) "
										   + dice.getDiceRotation(dice.getDiceValue()) + "';" 					+ dataBreak;
			
		}
		System.out.println("message=" + message);
		return message;
	}	
}