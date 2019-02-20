package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
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

	private HashMap<String, String> skunks;
	private String gameId;
	private HashMap<Integer, Player> players = new HashMap<Integer, Player>();
	private String skunkId;
	private String action;
	private int skunkRide;
	private int scoreRide;
	private String message;
	private int skunkSseId = 0;
	static Set<String> skunksToChoose 	= new HashSet<String>();
	private Set skunksChosen 	= new HashSet();
	private Set skunksUp 		= new HashSet();
	private Set skunksDown 		= new HashSet();
	
	static int numDice;
	static Dice d1				= new Dice(1);
	static Dice d2				= new Dice(2);
	static Dice d3				= new Dice(3);
	static Dice[] dice 			= new Dice[] {d1, d2, d3};
	
	static String validskunks 	= "skunk1 skunk2 skunk3 skunk4 skunk5 skunk6 skunk7";
	static String gameStatus;
	static String skunkBoss 			= "skunk4";
	static String clientInitialSkunkId 	= "skunk0";
	
	static final int HEARTBEAT_BEAT = 2000;
	
	private Vector<PrintWriter> listeners = new Vector<>();
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SkunkGame() {
		super();
		setGameId();

		gameStatus = "setup";
		skunkRide  = 0;
		
		skunksToChoose.add("skunk1");
		skunksToChoose.add("skunk2");
		skunksToChoose.add("skunk3");
		skunksToChoose.add("skunk4");
		skunksToChoose.add("skunk5");
		skunksToChoose.add("skunk6");
		skunksToChoose.add("skunk7");
		
		skunksChosen.clear();
		
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException
	{
		// TODO Auto-generated method stub
		
		 //response.getWriter().append("Served at: ").append(request.getContextPath());
		 
		System.out.println("entrando sse......");

		 String clientSkunkSseId 	= request.getParameter("skunkSseId");
		 String clientSkunkId 		= request.getParameter("skunkId");
		 String clientSkunkGameId	= request.getParameter("skunkGameId");
		 String action 				= request.getParameter("action");

		 /*request.setAttribute("result", gameStatus);
		 * request.getRequestDispatcher("/t1.jsp").forward(request, response);
		 * System.out.println("passo1...." + skunkId);
		 */
		 
		System.out.println("passo1....cookies Sse=" + clientSkunkSseId +" Game=" + clientSkunkGameId + " Skunk=" + clientSkunkId + " action=" + action);

		newFace = getRandom(randSeed, randRange);

		String skunkAction = request.getParameter("action");
		oldFace = newFace;

	    try {
	        response.setContentType("text/html;charset=UTF-8");
	
	        String ze = request.getContentType();
	        System.out.println("contentype= " + ze);
	
	        // Is this a message subsciption request?

	        if (((clientSkunkSseId 	!= null && clientSkunkSseId.length() 	> 0))  && 
	        	((clientSkunkGameId != null && clientSkunkGameId.length() 	> 0))  &&
	        	((clientSkunkId 	!= null && clientSkunkId.length() 		> 0))){
	        	
	        	System.out.println("passo2....");
	        	System.out.println("if ( clientSkunkId != null && clientSkunkId.length() > 0 ");
	        	
	        	response.setContentType("text/event-stream");
	        	response.setHeader("Connection", "keep-alive");
	        	response.setCharacterEncoding("UTF-8");
	        	// CORS stuff
	        	response.setHeader("Access-Control-Allow-Origin", "https://skunkgame.herokuapp.com/");
	        	response.setHeader("Access-Control-Expose-Headers", "*");
	        	response.setHeader("Access-Control-Allow-Credentials", "true");
	        	
	        	PrintWriter out = response.getWriter();
	        	// Store until a message needs to be sent
	        	synchronized ( listeners ) {
	        		listeners.add(out);
	        	}
	        	
	        	// processing first connection: clientSkunkId = skunk0
	        	if (clientSkunkId.equals(clientInitialSkunkId)) {
	        		
	        		skunkSseId++;
	        		
	        		players.put(skunkSseId, new Player(skunkSseId));
	        		
	        		out.write("event: skunkSseId\n");
	        		out.write("data: " + skunkSseId + "\n\n");
	        		
	        		out.write("event: skunkGameId\n");
	        		out.write("data: " + gameId + "\n\n");
	        		
	        		out.write("event: skunkId\n");
	        		out.write("data: skunk0\n");
	        		
	        		out.write("retry: 300000\n\n");
	        		while ( true ) {
	                    //System.out.println("SSE Sending heartbeat");
	                    out.write(": \n\n");
	                    if ( out.checkError() ) { 
	                    	System.out.println("Subscriber error......");
	                        break;
	                    }
	                    Thread.sleep(HEARTBEAT_BEAT);
	                }
	        		removeListener(out);
	        	}else {
	        		if (clientSkunkGameId.equals(gameId)) {
	        			System.out.println("ta no mesmo gameid=" + gameId);
		        		System.out.println("Setting up new event listener com clientSkunkId = " + clientSkunkId + " action=" + action);
		        		//////////////  gameStatus = SETUP  /////////////////
		        		if (gameStatus.equals("setup")) {
		        			if (action.equals("choose")) {
		        				//message = Player.makeMove(clientSkunkId, action);
		        				int wSkunkSseId = Integer.parseInt(clientSkunkSseId);
		        				Player wPlayer = players.get(wSkunkSseId);
		        				System.out.println("wPlayer=" + wPlayer);
		        				message = wPlayer.makeMove(clientSkunkId, action);
		        				
		        				skunksToChoose.remove(clientSkunkId);
		        				skunksChosen.add(clientSkunkId);
		        				
		        			}else if ((action.equals("play")) && (clientSkunkId.equals(skunkBoss))) {
		        				gameStatus = "play";
		        				skunkRide++;
		        				scoreRide = 0;
		        				int wSkunkSseId = Integer.parseInt(clientSkunkSseId);
		        				Player wPlayer = players.get(wSkunkSseId);
		        				System.out.println("wPlayer=" + wPlayer);
		        				message = wPlayer.makeMove(clientSkunkId, action);
		        				goPlay();
		        				
		        				skunksUp = skunksChosen;
		        				skunksDown.clear();
		        			}
			        	//////////////  gameStatus = PLAY  /////////////////
		        		    //////////  skunk4(BOSS) hits the play button
		        		}else if (gameStatus.equals("play")) {
		        			if ((action.equals("down")) || (action.equals("up"))) {
		        				// starting game
		        				int wSkunkSseId = Integer.parseInt(clientSkunkSseId);
		        				message = players.get(wSkunkSseId).makeMove(clientSkunkId, action);
		        				
		        				skunksUp.remove(clientSkunkId);
		        				skunksDown.add(clientSkunkId);
		        				
		        			///////////  skunksUp/Down hit the down/up button
		        			}else if ((action.equals("roll1")) || (action.equals("roll2")) || (action.equals("roll2"))) {
			        				
			        				numDice = Integer.parseInt(action.substring(4));
			        				for (int i = 0; i < numDice; i++) {
			        					// rolling the dice
			        					dice[i].setDiceValue(dice[i].rollDice(6));
			        					System.out.println("dice.Value= " + dice[i].getDiceValue());
			        					
			        					int wSkunkSseId = Integer.parseInt(clientSkunkSseId);
			        					message = players.get(wSkunkSseId).diceMove(clientSkunkId, action, dice[i]);
;			        				}
			        				if (numDice == 1) {
			        					if (d1.value == 1) {
			        						scoreRide = 0;
			        					}else {
			        						scoreRide = d1.value;
			        					}
		        					}else if (numDice == 2) {
		        						if (d1.value == 1) {
		        							scoreRide = 0;
		        							if (d2.value == 1) {
			        						   scoreRide = -1;
		        							}
		        						}else if (d2.value == 1) {
			        						scoreRide = 0;
		        						}else {
		        							scoreRide = d1.value + d2.value;
		        						}
		        					// 3 equal figures	
		        					}else if ((d1.value == d2.value) && (d1.value == d3.value)) {
		        						scoreRide = 100;
		        					}else if (d1.value == 1) {
			        						scoreRide = 0;   								
			        						if (d2.value == 1) {
			        							scoreRide = -1;
			        						}else if (d3.value == 1) {
			        							scoreRide = -1;
			        						}
	        						}else if (d2.value == 1) {
		        							scoreRide = 0;
		        							if (d3.value == 1) {
			        							scoreRide = -1;
			        						}
	        						}else if (d3.value == 1) {
	        								scoreRide = 0;
	        						}else {
	        							scoreRide = d1.value + d2.value + d3.value;
	        						}
			        				
			        				
		        			}
		        		}
			            System.out.println("Setting up new event listener com message = " + message);
			            
			            sendUpdate(message);
			            System.out.println("voltou do sendup...........");
	        		}else {
	        			// cai fora mostra pagina game running
	        			System.out.println("client showing expired gameId. client=" + clientSkunkGameId + " gameId=" + gameId);
	        		}
	        	}
	        }
	    }
	    catch ( Exception e ) {
	        e.printStackTrace();
	    }
	}

	private void removeListener(PrintWriter out) {
	      synchronized ( listeners ) {
	          listeners.removeElement(out);
	          if ( listeners.size() == 0 ) {
	              System.out.println("No more subscribers");
	          }
	      }
	}

	public int getRandom(int randSeed, int randRange) {
		return r.nextInt(randRange) + randSeed;
	}

    private void sendUpdate(String message) {
      try {
          if ( listeners == null || listeners.isEmpty() )
              return;

          // Copy the list of listeners to safely iterate
          //
          Vector<PrintWriter> toSend;
          synchronized ( listeners ) {
              toSend = (Vector<PrintWriter>)listeners.clone();
          }

          // Send update to all listeners
          //
          Iterator<PrintWriter> iter = toSend.iterator();
          while ( iter.hasNext() ) {
              PrintWriter out = iter.next();
              if ( out == null ) {
                  continue;
              }

              try {
                  // Send SSE update
                  //
                      System.out.println("SSE Sending data: " + message);
                      out.write("event: message\n");
                      out.write(message + "\n");
                      out.flush();
                      System.out.println("no loop sending");
              }
              catch( Exception e ) {
                  // Bad listener. Remove from original list and move on
                  System.out.println("Listener error: " + e.toString() );
                  e.printStackTrace();

                  try {
                      removeListener(out);
                  }
                  catch ( Exception e1 ) { }
              }
          }
      }
      catch ( Exception e ) {
          e.printStackTrace();
      }
  }
    public void goPlay() {
    	
    }
}
