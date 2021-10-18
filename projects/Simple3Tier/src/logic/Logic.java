package logic;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbserver.DBServer;
import model.SetOp;
import model.Song;

/**
 * Servlet implementation class Logic
 */
@WebServlet("/Logic")
public class Logic extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DBServer dbs;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Logic() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
	public void init() throws ServletException {
		super.init(); // important
		dbs = new DBServer();
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
        response.setContentType("text/plain");
 
        String paramValue = request.getParameter("artist");
        List<Song> result1 = dbs.queryArtist(paramValue);
        paramValue = request.getParameter("mood");
        List<Song> result2 = dbs.queryMood(paramValue);
        String logOp = request.getParameter("logop");
        List<Song> resultFinal = null;
        if (logOp.equals("and")) {
        	resultFinal = SetOp.intersection(result1,result2);
        } else {
        	resultFinal = SetOp.union(result1,result2);
        }
        out.write(resultFinal.toString());
        out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
