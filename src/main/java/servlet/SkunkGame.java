package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
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

	private String gameStatus;
	private static int randSeed = 0;
	private static int randRange = 6;

	private int oldFace = 10;
	private int newFace = 10;

	private HashMap<String, String> skins;
	private Random r = new Random();

	private String skin;
	
	private Vector<PrintWriter> listeners = new Vector<>();
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SkunkGame() {
		super();
		// TODO Auto-generated constructor stub

		gameStatus = "New";

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException
	{
		// TODO Auto-generated method stub
		
		 //response.getWriter().append("Served at: ").append(request.getContextPath());
		 


		 skin 		= request.getParameter("skin");
		 //skinAction = request.getParameter("action");


		 /*request.setAttribute("result", gameStatus);
		 * request.getRequestDispatcher("/t1.jsp").forward(request, response);
		 * System.out.println("passo1...." + skin);
		 *
		 * }else {
		 */
		System.out.println("passo1...." + skin);
		response.setContentType("text/event-stream");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Connection", "keep-alive");


		response.setCharacterEncoding("UTF-8");
		PrintWriter printWriter = null;

		newFace = getRandom(randSeed, randRange);

		String skinAction = request.getParameter("action");
		System.out.println("passo2...." + skinAction);
		oldFace = newFace;

	    try {
	        response.setContentType("text/html;charset=UTF-8");
	        String param = "";
	
	
	
	        // Is this a message subsciption request?
	        param = request.getParameter("msg");
	        if ( param != null && param.length() > 0 ) {
	            System.out.println("Setting up new event listener");
	
	            // set content type and header
	            response.setContentType("text/event-stream");
	            response.setCharacterEncoding("UTF-8");
	            response.setHeader("Connection", "keep-alive");
	         // CORS stuff
	    		response.setHeader("Access-Control-Allow-Origin", "https://skunkgame.herokuapp.com/");
	    		response.setHeader("Access-Control-Expose-Headers", "*");
	    		response.setHeader("Access-Control-Allow-Credentials", "true");
	
	            // Store until a message needs to be sent
	            PrintWriter out = response.getWriter();
	            synchronized ( listeners ) {
	                listeners.add(out);
	            }
	            sendUpdate("teste", "...........caraca..........");
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

    private void sendUpdate(String type, String msg) {
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
                  if ( type.length() > 0 ) {
                      System.out.println("SSE Sending data: " + msg);
                      out.write("event: message\n");
                      out.write("data: " + msg + "\n\n");
                      out.flush();
                  }
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
}
