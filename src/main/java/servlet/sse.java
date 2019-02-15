import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Paulo Negrao
 */
@WebServlet(urlPatterns={"/sse"}, asyncSupported=true)
    public class sse extends HttpServlet implements DataEvents {

    static final int HEARTBEAT_INTERVAL = 2000;

    public boolean sending = true;
    public Object lock = new Object();
    Vector<PrintWriter> listeners = new Vector<>();
    PrintWriter chatListener = null;

    /////////////////////////////////////////////////////////////////////////

    public void init() throws ServletException {
        System.out.println("SSE Servlet created...");

    }

    public void destroy() {
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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

                // Store until a message needs to be sent
                PrintWriter out = response.getWriter();
                synchronized ( listeners ) {
                    listeners.add(out);
                }
                out.write("event: message\n");
                out.write("retry: 300000\n");
                out.write("data: " + "start" + "\n\n");

                // Send heartbeats continuously
                while ( true ) {
                    System.out.println("SSE Sending heartbeat");
                    out.write(": \n\n");
                    if ( out.checkError() ) {
                        System.out.println("Subscriber error");
                        break;
                    }

                    Utility.delay(HEARTBEAT_INTERVAL);
                }

                removeListener(out);
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
                Utility.log("No more subscribers");
                sse.demoDriver.stopSimulation();
            }
        }
    }

    private void removeListener(PrintWriter out) {
        synchronized ( listeners ) {
            listeners.removeElement(out);
            if ( listeners.size() == 0 ) {
                Utility.log("No more subscribers");
                sse.demoDriver.stopSimulation();
            }
        }
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

    public void sendEvent(String type, String msg) {
        try {
            if ( chatListener == null ) {
                return;
            }

            synchronized ( chatListener ) {
                PrintWriter out = chatListener;
                try {
                    // Send SSE update
                    //
                    Utility.log("SSE Sending " + type + " event with data: " + msg);
                    if ( type.length() > 0 ) {
                        out.write("event: " + type + "\n");
                    }
                    out.write("data: " + msg + "\n\n");
                    out.flush();
                }
                catch( Exception e ) {
                    // Bad listener
                    Utility.log("Listener error: " + e.toString() );
                    e.printStackTrace();
                    chatListener = null;
                }
            }
        }
        catch ( Exception e ) {
            e.printStackTrace();
        }
    }







}
