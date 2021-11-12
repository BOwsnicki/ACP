package logic;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import access.SongDAO;
import model.Song;

/**
 * Servlet implementation class Logic
 */
@WebServlet("/Logic")
public class Logic extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String prolog = "<!DOCTYPE html><html><head><title>Here they are!</title></head><body><ol>";
	private static final String epilog = "</ol><a href='index.html'>One more?</a></body></html>";
	private SongDAO dao;

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
		dao = new SongDAO();
	}

	private List<Song> constructQuery(HttpServletRequest request) {
		String artist = request.getParameter("artist");
        String mood = request.getParameter("mood");
        
        if (artist == null && mood == null) {
        	return dao.findAll();
        }
        if (artist != null && mood == null) {
        	return dao.findWhere("artist LIKE '" + artist + "'");
        }
        if (artist == null && mood != null) {
        	return dao.findWhere("mood LIKE '" + mood + "'");
        }
        String logOp = request.getParameter("logop").toUpperCase();
        return dao.findWhere("artist LIKE '" + artist + "' " 
        		+ logOp + " mood LIKE '" + mood + "'");
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		
        List<Song> resultFinal = constructQuery(request);

        switch (request.getHeader("Accept").toLowerCase()) {
		case "application/json":
			response.setContentType("application/json");
			out.println(resultFinal);
			break;
		default:
			response.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE); // 415
		}
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
