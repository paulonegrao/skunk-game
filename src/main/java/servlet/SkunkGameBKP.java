package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SkunkGame
 */
@WebServlet("/SkunkGameBKP")
public class SkunkGameBKP extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String gameStatus;
	private static int randSeed = 0;
	private static int randRange = 6;

	private int oldFace = 10;
	private int newFace = 10;

	private HashMap<String, String> skins;
	private Random r = new Random();

	private String skin;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SkunkGameBKP() {
		super();
		// TODO Auto-generated constructor stub

		gameStatus = "New";

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		// TODO Auto-generated method stub
		/*
		 * response.getWriter().append("Served at: ").append(request.getContextPath());
		 */

		
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
		
		// CORS stuff
		//response.setHeader("Access-Control-Allow-Origin", "https://skunkgame.herokuapp.com/");
		//response.setHeader("Access-Control-Expose-Headers", "*");
		//response.setHeader("Access-Control-Allow-Credentials", "true");
		
		response.setCharacterEncoding("UTF-8");
		PrintWriter printWriter = null;

		newFace = getRandom(randSeed, randRange);

		String skinAction = request.getParameter("action");
		System.out.println("passo2...." + skinAction);
		oldFace = newFace;

		try {

			printWriter = response.getWriter();
			printWriter.write("event: message\n");
			/* printWriter.write("data: " + skin + "testando essa encrenca\r\n"); */
			printWriter.write("retry: 300000\n");
			printWriter.write("data: ” + “start” + “\n\n");
			System.out.println("printei...." + skinAction);
			//printWriter.flush();
			/*
			 * printWriter.print("data: " + skin + ".." + skinAction + ".." + skin +
			 * " Oldinho was " + oldFace + " .You have trhew " + newFace);
			 */
			while (true) {
				double randomNumber = Math.random() * 3000;
				printWriter.write(": \n\n");
				if (printWriter.checkError()) {
					System.out.println("erro breakando...." + skinAction);
					// Subscriber error, break out of loop
					break;
				}
				/* response.flushBuffer(); */
				Thread.sleep((long) randomNumber);
			}
			return;
		} catch (IOException e) {
			printWriter.close();
			System.out.println("erro no catch...." + skinAction);
		} catch (InterruptedException e) {
			printWriter.close();
			System.out.println("erro no catch...." + skinAction);
		}
		/* } */
	}

	public int getRandom(int randSeed, int randRange) {
		return r.nextInt(randRange) + randSeed;
	}

}
