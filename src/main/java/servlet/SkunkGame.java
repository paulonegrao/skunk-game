package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SkunkGame
 */
@WebServlet("/SkunkGame")
public class SkunkGame extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static int randSeed = 0;
	private static int randRange = 6;

	private int oldFace = 10;
	private int newFace = 10;

	private Random r = new Random();

	static Vector<String> bufferMsg = new Vector<String>();
	private int listenerIndex;

	private HashMap<String, String> skunks;
	private String gameId;
	private HashMap<Integer, Player> players = new HashMap<Integer, Player>();
	//private String skunkId;
	//private String clientSkunkAction;
	private int skunkRide;
	private int scoreRide;
	private String message;
	private int skunkSseId = 0;
	static Set<String> skunksToChoose = new HashSet<String>();
	private Set<String> skunksChosen = new HashSet<String>();
	private Set<String> skunksUp = new HashSet<String>();
	private Set<String> skunksDown = new HashSet<String>();

	static int numDice;
	private int selectedNumDice;
	static Dice d1 = new Dice(1);
	static Dice d2 = new Dice(2);
	static Dice d3 = new Dice(3);
	static Dice[] dice = new Dice[] { d1, d2, d3 };

	static String validskunks = "skunk1 skunk2 skunk3 skunk4 skunk5 skunk6 skunk7";
	static String gameStatus;
	static String skunkBoss = "skunk4";
	static String clientInitialSkunkId = "skunk0";
	
	private int winnerScore;
	private Set<String> winners = new HashSet<String>();

	private HashMap<String, Score> skunkScore = new HashMap<String, Score>();

	static final int HEARTBEAT_BEAT = 2000;

	private Vector<PrintWriter> listeners = new Vector<>();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SkunkGame() {
		super();
		
		setUp();
	}
	
	public void setUp() {
		
		setGameId();

		gameStatus = "settingUp";
		skunkRide = 0;

		skunksToChoose.add("skunk1");
		skunksToChoose.add("skunk2");
		skunksToChoose.add("skunk3");
		skunksToChoose.add("skunk4");
		skunksToChoose.add("skunk5");
		skunksToChoose.add("skunk6");
		skunksToChoose.add("skunk7");

		skunkScore.put("skunk1", new Score());
		skunkScore.put("skunk2", new Score());
		skunkScore.put("skunk3", new Score());
		skunkScore.put("skunk5", new Score());
		skunkScore.put("skunk6", new Score());
		skunkScore.put("skunk7", new Score());

		skunksChosen.clear();
		bufferMsg.clear();
		message = "";

		System.out.println("####### init gameId = " + gameId + " #######");
	}

	private void setGameId() {
		LocalTime time = LocalTime.now();
		gameId = time.toString();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		// TODO Auto-generated method stub

		// response.getWriter().append("Served at: ").append(request.getContextPath());

		System.out.println("entrando sse......");

		String clientSkunkSseId = request.getParameter("skunkSseId");
		String clientSkunkId = request.getParameter("skunkId");
		String clientSkunkGameId = request.getParameter("skunkGameId");
		String clientSkunkAction = request.getParameter("skunkAction");
		System.out.println("passo0....cookies Sse=" + clientSkunkSseId + " Game=" + clientSkunkGameId + " Skunk="
				+ clientSkunkId + " action=" + clientSkunkAction);
		/*
		 * request.setAttribute("result", gameStatus);
		 * request.getRequestDispatcher("/t1.jsp").forward(request, response);
		 * System.out.println("passo1...." + skunkId);
		 */


		try {
			response.setContentType("text/html;charset=UTF-8");

			// Is this a message subscription request?

			if (((clientSkunkSseId != null && clientSkunkSseId.length() 	> 0))  && 
			   ((clientSkunkGameId != null && clientSkunkGameId.length() 	> 0))  &&
			   ((clientSkunkId 	   != null && clientSkunkId.length() 		> 0))) {
				
				int intClientSkunkSseId = Integer.parseInt(clientSkunkSseId);
				System.out.println("passo1....cookies Sse=" + clientSkunkSseId + " Game=" + clientSkunkGameId + " Skunk="
						+ clientSkunkId + " action=" + clientSkunkAction);
				

				response.setContentType("text/event-stream");
				response.setHeader("Connection", "keep-alive");
				response.setCharacterEncoding("UTF-8");
				// CORS stuff
				response.setHeader("Access-Control-Allow-Origin", "https://skunkgame.herokuapp.com/");
				response.setHeader("Access-Control-Expose-Headers", "*");
				response.setHeader("Access-Control-Allow-Credentials", "true");
				
				// boss reinitializing the game
				if 	((clientSkunkId.equals(skunkBoss)) 		&&
					 (clientSkunkAction != null)			&&
					 (clientSkunkAction.equals("finish"))) 	{
							message = players.get(intClientSkunkSseId).finishMove(clientSkunkId, clientSkunkAction);
							sendUpdateToAll(message);
							listeners.clear();
							setUp();
				}else if ((clientSkunkId.equals(clientInitialSkunkId)) || (clientSkunkAction == null)) {
						
					System.out.println("passo2.... frist connection");
					System.out.println("if ( clientSkunkId == skunk0 && clientSkunkAction == null" + clientSkunkId + "  "   + clientSkunkAction);
					
					PrintWriter out = response.getWriter();
					// Store until a message needs to be sent
					synchronized (listeners) {
						listeners.add(out);
						listenerIndex = listeners.size() - 1;
					}
					
					skunkSseId++;
					
					players.put(skunkSseId, new Player(skunkSseId));
					
					out.write("event: skunkSseId\n");
					out.write("data: " + skunkSseId + "\n\n");
					
					out.write("event: skunkGameId\n");
					out.write("data: " + gameId + "\n\n");
					
					out.write("event: skunkId\n");
					out.write("data: skunk0\n");
					
					out.write("retry: 300000\n\n");
					
					// send all updates to new user
					if (bufferMsg.size() > 0) {
						sendUpdateToOne(listenerIndex, bufferMsg);
					}
					
					while (true) {
						// System.out.println("SSE Sending heartbeat");
						out.write(": \n\n");
						if (out.checkError()) {
							System.out.println("Subscriber error......");
							break;
						}
						Thread.sleep(HEARTBEAT_BEAT);
					}
					removeListener(out);
				} else {
					goGame(intClientSkunkSseId, clientSkunkSseId, clientSkunkId, clientSkunkGameId, clientSkunkAction);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void goGame(int intClientSkunkSseId, String clientSkunkSseId, String clientSkunkId, String clientSkunkGameId, String clientSkunkAction) {
		
		if (clientSkunkGameId.equals(gameId)) {
			
			System.out.println("ta no mesmo gameid=" + gameId);
			System.out.println("....passo3....Setting up new event listener com clientSkunkId = " + clientSkunkId
					+ " clientSkunkAction=" + clientSkunkAction);
			////////////// gameStatus = SETTINGUP /////////////////
			if (gameStatus.equals("settingUp")) {
				if (clientSkunkAction.equals("choose")) {
					// message = Player.makeMove(clientSkunkId, clientSkunkAction);
					Player wPlayer = players.get(intClientSkunkSseId);
					
					System.out.println("....passo4....Setting up new event listener com clientSkunkId = " + clientSkunkId
							+ " clientSkunkAction=" + clientSkunkAction);
					
					message = wPlayer.makeMove(clientSkunkId, clientSkunkAction);

					skunksToChoose.remove(clientSkunkId);
					
					if (!clientSkunkId.equals(skunkBoss)) {
						skunksChosen.add(clientSkunkId);
					}

				} else if ((clientSkunkAction.equals("play")) && (clientSkunkId.equals(skunkBoss))) {
					gameStatus = "playing";
					skunkRide++;
					scoreRide = 0;
					selectedNumDice = 2;
					
					// add dice buttons with 2 dice option selected (default)
					message = players.get(intClientSkunkSseId).setDiceButtonsMove(selectedNumDice);

					System.out.println("....passo5....clientSkunkAction.equals(\"play\")) && (clientSkunkId.equals(skunkBoss) com clientSkunkId = " + clientSkunkId
							+ " clientSkunkAction=" + clientSkunkAction);
					
					message = message + players.get(intClientSkunkSseId).makeMove(clientSkunkId, clientSkunkAction);

					skunksUp = skunksChosen;
					skunksDown.clear();
				}
				////////////// gameStatus = PLAYING /////////////////
				////////////// skunk4(BOSS) hits the play button
			} else if (gameStatus.equals("playing")) {
				/////////// skunksUp/Down hit the down/up button
				if ((clientSkunkAction.equals("down")) || (clientSkunkAction.equals("up"))) {
					// starting game

					System.out.println("....passo6....(clientSkunkAction.equals(\"down\")) || (clientSkunkAction.equals(\"up\") com clientSkunkId = " + clientSkunkId
							+ " clientSkunkAction=" + clientSkunkAction);
					message = players.get(intClientSkunkSseId).makeMove(clientSkunkId, clientSkunkAction);

					if (clientSkunkAction.equals("down")) {
						skunksUp.remove(clientSkunkId);
						skunksDown.add(clientSkunkId);
					} else {
						skunksDown.remove(clientSkunkId);
						skunksUp.add(clientSkunkId);
					}
					
					// dice button clicked
				} else if ((clientSkunkAction.equals("roll1")) || (clientSkunkAction.equals("roll2"))
						|| (clientSkunkAction.equals("roll3"))) {
					
					goRolling(intClientSkunkSseId, clientSkunkId, clientSkunkAction);
				}
			}
			
			System.out.println(".....MESSAGE=" + message);
			sendUpdateToAll(message);
			System.out.println("voltou do sendup...........");
			message = "";
			System.out.println("limpou>>>>>.....MESSAGE=" + message);
		} else {
			// cai fora mostra pagina game running
			System.out.println(
					"client showing expired gameId. client=" + clientSkunkGameId + " gameId=" + gameId);
		}
		
	}
	
	public void goRolling(int intClientSkunkSseId, String clientSkunkId, String clientSkunkAction) {
		
		System.out.println("....passo7....(clientSkunkAction.equals(\"roll1\")) || (clientSkunkAction.equals(\"roll2\"))\n" + 
				"									|| (clientSkunkAction.equals(\"roll3\")) com clientSkunkId = " + clientSkunkId
				+ " clientSkunkAction=" + clientSkunkAction);
		
		Iterator<String> it1 = skunksChosen.iterator();
		while(it1.hasNext()){
			System.out.println(".......chosen===="+ it1.next());
		}
		Iterator<String> it2 = skunksDown.iterator();
		while(it2.hasNext()){
			System.out.println(".......DOWN=" + it2.next());
		}
		Iterator<String> it3 = skunksUp.iterator();
		while(it3.hasNext()){
			System.out.println(".......UP=======" + it3.next());
		}
		System.out.println("... skunksUp.size=" + skunksUp.size());
		
		numDice = Integer.parseInt(clientSkunkAction.substring(4));
		
		// check & reset selected dice button
		if (numDice != selectedNumDice) {
			message = message + players.get(intClientSkunkSseId).setDiceButtonsMove(numDice);
			selectedNumDice = numDice;
		}
		
		System.out.println("numDice=" + numDice);
		for (int i = 0; i < numDice; i++) {
			// rolling the dice
			dice[i].setDiceValue(dice[i].rollDice(6));
			System.out.println("dice.Value= " + dice[i].getDiceValue());

			message = message + players.get(intClientSkunkSseId).diceMove(clientSkunkId, clientSkunkAction, dice[i]);
		}
	
		// Score only if there are skunks standing up....
		if (skunksUp.size() > 0) {
			if (numDice == 1) {
				if (d1.value == 1) {
					scoreRide = 0;
				} else {
					scoreRide = d1.value;
				}
			} else if (numDice == 2) {
				if (d1.value == 1) {
					scoreRide = 0;
					if (d2.value == 1) {
						scoreRide = -1;
					}
				} else if (d2.value == 1) {
					scoreRide = 0;
				} else {
					scoreRide = d1.value + d2.value;
				}
				// 3 dice equal figures
			} else if ((d1.value == d2.value) && (d1.value == d3.value)) {
				scoreRide = 100;
			} else if (d1.value == 1) {
				scoreRide = 0;
				if (d2.value == 1) {
					scoreRide = -1;
				} else if (d3.value == 1) {
					scoreRide = -1;
				}
			} else if (d2.value == 1) {
				scoreRide = 0;
				if (d3.value == 1) {
					scoreRide = -1;
				}
			} else if (d3.value == 1) {
				scoreRide = 0;
			} else {
				scoreRide = d1.value + d2.value + d3.value;
			}
			
			System.out.println(" #######..scoreRide=" + scoreRide);
			
			for (String skunkUp : skunksUp) {
				if (scoreRide == -1) {
					skunkScore.get(skunkUp).zeroScore();
					message = message + players.get(intClientSkunkSseId).zeroScoreMove(skunkUp); 
				}else if (scoreRide == 0) {
					int wScoreRide = skunkScore.get(skunkUp).getRideScore(skunkRide);
					if (wScoreRide > 0) {
						skunkScore.get(skunkUp).addScore(skunkRide, - wScoreRide);
					}
					message = message + players.get(intClientSkunkSseId).scoreMove(skunkUp, 
							skunkScore.get(skunkUp).getRideScore(skunkRide), 
							skunkScore.get(skunkUp).getTotalScore(), skunkRide);
				} else {
					skunkScore.get(skunkUp).addScore(skunkRide, scoreRide);
					message = message + players.get(intClientSkunkSseId).scoreMove(skunkUp, 
										skunkScore.get(skunkUp).getRideScore(skunkRide), 
										skunkScore.get(skunkUp).getTotalScore(), skunkRide);	
				}
			}
			message = message + players.get(intClientSkunkSseId).skunkTitleMove(skunkRide); 
			skunkRide = skunkRide + 1;
			if (skunkRide > 5) {
				goSkunk(intClientSkunkSseId);
			}
		}
	}
	
	public void goSkunk(int intClientSkunkSseId) {
		System.out.println(" ####### S K U N K #######" + scoreRide);
		findWinners(skunksChosen);
		
		for (String winner : winners) {
			if (skunkScore.get(winner).getTotalScore() == this.winnerScore) {
				message = message + players.get(intClientSkunkSseId).winnerScoreMove(winner); 
			}
		}
	}
	
	public void findWinners(Set<String> skunksChosen) {

		winnerScore = 0;
		int playerScore = 0;
		for (String skunkChosen : skunksChosen) {
			playerScore = skunkScore.get(skunkChosen).getTotalScore();
			if (playerScore >= winnerScore) {
			   if (playerScore > winnerScore) {
				   this.winnerScore = playerScore;
				   this.winners.clear();
			   }
			   this.winners.add(skunkChosen);
			}
		}
	}

	private void removeListener(PrintWriter out) {
		synchronized (listeners) {
			listeners.removeElement(out);
			if (listeners.size() == 0) {
				System.out.println("No more subscribers");
			}
		}
	}

	public int getRandom(int randSeed, int randRange) {
		return r.nextInt(randRange) + randSeed;
	}

	private void sendUpdateToAll(String message) {
		try {
			if (listeners == null || listeners.isEmpty())
				return;

			// Copy the list of listeners to safely iterate
			//
			Vector<PrintWriter> toSend;
			synchronized (listeners) {
				toSend = (Vector<PrintWriter>) listeners.clone();
			}
			System.out.println("...........tosend vector=" + toSend);
			// Send update to all listeners
			//
			Iterator<PrintWriter> iter = toSend.iterator();
			while (iter.hasNext()) {
				PrintWriter out = iter.next();
				if (out == null) {
					continue;
				}

				try {
					// Send SSE update
					//
					System.out.println("SSE Sending data: ");
					out.write("event: message\n");
					out.write(message + "\n");
					out.flush();
					System.out.println("no loop sending");
				} catch (Exception e) {
					// Bad listener. Remove from original list and move on
					System.out.println("Listener error: " + e.toString());
					e.printStackTrace();

					try {
						removeListener(out);
					} catch (Exception e1) {
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void sendUpdateToOne(int listenerIndex, Vector<String> bufferMsg) {
		try {
			if (listenerIndex < 0)
				return;

			// Copy the list of listeners to safely process
			//
			Vector<PrintWriter> toSend;
			synchronized (listeners) {
				toSend = (Vector<PrintWriter>) listeners.clone();
			}
			// Send update to the listenerIndex listener

			Iterator<String> iter = bufferMsg.iterator();
			while (iter.hasNext()) {
				String message = iter.next();
				if (message == null) {
					continue;
				}
				PrintWriter out = toSend.get(listenerIndex);

				try {
					// Send SSE update
					//

					System.out.println("SSE Sending TO ONE....data: " + message);
					out.write("event: message\n");
					out.write(message + "\n");
					out.flush();
					System.out.println("no loop sending");
				} catch (Exception e) {
					// Bad listener. Remove from original list and move on
					System.out.println("Listener error: " + e.toString());
					e.printStackTrace();

					try {
						removeListener(out);
					} catch (Exception e1) {
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

