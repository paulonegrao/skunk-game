package servlet;

public class Player {

	private int skunkSseId;
	private String skunkId;
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
		
		String message = "";
		
		if ((SkunkGame.gameStatus.equals("settingUp") && (action.equals("choose")))) {
			// remove skunk from choose 
			message = message + dataHeader + skunkId + "_choose.classList.remove('on');" 				+ dataBreak;
			message = message + dataHeader + skunkId + "_choose.classList.add('off');" 					+ dataBreak;
			message = message + dataHeader + skunkId + "_choose.disabled = true;" 						+ dataBreak;
			// add skunk to play
			message = message + dataHeader + skunkId + "_play.classList.remove('off');" 				+ dataBreak;
			message = message + dataHeader + skunkId + "_play.classList.add('on', 'up');" 				+ dataBreak;
			message = message + dataHeader + skunkId + "_play.disabled = false;" 						+ dataBreak;
			
			if (skunkId.equals(SkunkGame.skunkBoss)) {
				// add play button to boss
				message = message + dataHeader + "play_finish.classList.remove('off');" 				+ dataBreak;
				message = message + dataHeader + "play_finish.classList.add('on', 'play');" 			+ dataBreak;
				message = message + dataHeader + 
						"play_finish.setAttribute('onclick', 'skunkMove(\"skunk4\", \"play\")');" 		+ dataBreak;
				message = message + dataHeader + "play_finish.disabled = false;"  						+ dataBreak;
			}else {
				// add zeroed score to skunk
				zeroScoreMove(skunkId);
			}
			// set client's game skunkId
			message = message + dataHeader + 	"if (getCookie('skunkSseId') == " +  this.skunkSseId +
												" ){setCookie('skunkId', '" + skunkId + "', 1)};"		+ dataTrailer;

		// gameStatus PLAYING
		}else if ((SkunkGame.gameStatus.equals("playing")) && (action.equals("play"))) {
			for (String skunkSet : SkunkGame.skunksToChoose) {
				// remove remaining no chosen skunks
				message = message + dataHeader + skunkSet  + "_choose.classList.remove('on');" 	        + dataBreak;
				message = message + dataHeader + skunkSet  + "_choose.classList.add('off');" 	        + dataBreak;
			}
			// add finish button to boss
			message = message + dataHeader + "play_finish.classList.remove('play');" 					+ dataBreak;
			message = message + dataHeader + "play_finish.classList.add('finish');" 					+ dataBreak;
			message = message + dataHeader + 
					"play_finish.setAttribute(\"onclick\", \"skunkMove('skunk4', 'finish')\");" 		+ dataBreak;
			
			// add dice to boss
			message = message + dataHeader + "overlay.style.display = 'block';" 						+ dataBreak;
			
			// add dice buttons with 2 dice option selected (default)
			setDiceButtonsMove(2);
			
		// move skunks UP & DOWN
		}else if ((SkunkGame.gameStatus.equals("playing")) && (action.equals("down"))) {
			message = message + dataHeader + skunkId + "_play.classList.remove('up');" 					+ dataBreak;
			message = message + dataHeader + skunkId + "_play.classList.add('down');" 					+ dataBreak;
			message = message + dataHeader + skunkId + "_play.disabled = false;" 						+ dataBreak;
			message = message + dataHeader + skunkId + 
					"_play.setAttribute(\"onclick\", \"skunkMove('" + skunkId + "', 'up')\");" 			+ dataTrailer;
			
		}else if ((SkunkGame.gameStatus.equals("playing")) && (action.equals("up"))) {
			message = message + dataHeader + skunkId + "_play.classList.remove('down');" 				+ dataBreak;
			message = message + dataHeader + skunkId + "_play.classList.add('up');" 					+ dataBreak;
			message = message + dataHeader + skunkId + "_play.disabled = false;" 						+ dataBreak;
			message = message + dataHeader + skunkId + 
					"_play.setAttribute(\"onclick\", \"skunkMove('" + skunkId + "', 'down')\");" 		+ dataTrailer;
			
		}
		
		// add message to bufferMsg
		SkunkGame.bufferMsg.add(message);
		
		return message;
	}
		
		
	public String diceMove(String skunkId, String action, Dice dice) {
			System.out.println("no diceMove do player c/ dice=" + dice);
		
		String message = "";
		
		// roll 1, 2 or 3 dice
		if ((SkunkGame.gameStatus.equals("playing")) && (action.substring(0, 4).equals("roll"))) {
	
			message = message + dataHeader + "cube" + dice.diceId + ".style.transform = 'translateZ(-100px) "
										   + dice.getDiceRotation() + "';" 										+ dataBreak;
			
		}
		
		// add message to bufferMsg
		SkunkGame.bufferMsg.add(message);
		
		return message;
	}	
	
	
	public String scoreMove(String skunkId, int rideScore, int totalScore, int skunkRide) {
			System.out.println("no diceMove do player");
			
		String message = "";
		
		// add values to skunk score
		int children = skunkRide - 1;

		message = message + dataHeader + skunkId + 
					"_score_ride.children[" + children + "].innerHTML = '" + rideScore + "';" 	 		+ dataBreak;
		message = message + dataHeader + skunkId + 
				    "_score_total.innerHTML = '" + totalScore + "';" 									+ dataBreak;
			
		
		// add message to bufferMsg
		SkunkGame.bufferMsg.add(message);
		
		return message;
	}
	
	public String zeroScoreMove(String skunkId) {
		System.out.println("no zeroScoreMove do player");
		
		String message = "";
		
		// add zeroed score to skunk
		for (int i = 0; i <= 4; i++) {	
			message = message + dataHeader + skunkId + 
					"_score_ride.children[" + i + "].innerHTML = '0';" 						    		+ dataBreak;
		}
		message = message + dataHeader + skunkId + "_score_total.innerHTML = '0';" 						+ dataBreak;
		
		// add message to bufferMsg
		SkunkGame.bufferMsg.add(message);
		
		return message;
	}
	
	public String winnerScoreMove(String skunkId) {
		System.out.println("no winnerScoreMove do player");
		
		String message = "";
		
		// set winner score to skunk
		message = message + dataHeader + skunkId + "_score.classList.add('blink');"  					+ dataBreak;
		
		// add message to bufferMsg
		SkunkGame.bufferMsg.add(message);
		
		return message;
	}
	
	public String skunkTitleMove(int skunkRide) {
		System.out.println("no winnerScoreMove do player");
		
		String message = "";
		
		// set color to skunk letter in the title
		int children = skunkRide - 1;
		message = message + dataHeader + "skunk_title.children[" + children + "].classList.add('ride_done');"  + dataBreak;
		
		// add message to bufferMsg
		SkunkGame.bufferMsg.add(message);
		
		return message;
	}
	
	public String finishMove(String skunkId, String action) {
		System.out.println("no finishMove do player");
		String message = "";
		
		// boss finishing the game
		if ((skunkId.equals("skunk4")) && (action.equals("finish"))) {
			message = message + dataHeader + "removeCookies(); location.reload();" 						+ dataBreak;
		}
		// add message to bufferMsg
		SkunkGame.bufferMsg.add(message);
		
		return message;
	}
	
	public String setDiceButtonsMove(int selectedNunDice) {
		System.out.println("no setDiceButtonsMove do player");
		
		String message = "";
		
		// set dice images 
		if (selectedNunDice == 1) {
			message = message + dataHeader + "cube1.style.display = 'block';" 									+ dataBreak;
			message = message + dataHeader + "cube2.style.display = 'none';" 									+ dataBreak;
			message = message + dataHeader + "cube3.style.display = 'none';" 									+ dataBreak;
		} else if (selectedNunDice == 2) {
			message = message + dataHeader + "cube1.style.display = 'block';" 									+ dataBreak;
			message = message + dataHeader + "cube2.style.display = 'block';" 									+ dataBreak;
			message = message + dataHeader + "cube3.style.display = 'none';" 									+ dataBreak;
		} else {
			message = message + dataHeader + "cube1.style.display = 'block';" 									+ dataBreak;
			message = message + dataHeader + "cube2.style.display = 'block';" 									+ dataBreak;
			message = message + dataHeader + "cube3.style.display = 'block';" 									+ dataBreak;
		}
		
		// set dice buttons 
		for (int i = 1; i < 4; i++) {
			if (i == selectedNunDice ) {		
				message = message + dataHeader + "dice" + i + ".classList.remove('off', 'not_selected');" 		+ dataBreak;
				message = message + dataHeader + "dice" + i + ".classList.add('on', 'selected');"				+ dataBreak;
			} else {
				message = message + dataHeader + "dice" + i + ".classList.remove('off', 'selected');" 			+ dataBreak;
				message = message + dataHeader + "dice" + i + ".classList.add('on', 'not_selected');"			+ dataBreak;
			}
		}
		
		// add message to bufferMsg
			SkunkGame.bufferMsg.add(message);
			
			return message;
		}
	
}