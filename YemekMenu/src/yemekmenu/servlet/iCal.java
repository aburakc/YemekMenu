package yemekmenu.servlet;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import yemekmenu.calendar.YemekMenuCalendar;
import yemekmenu.domain.GununYemegi;
import yemekmenu.parser.Parser;

/**
 * Servlet implementation class iCal
 */
@WebServlet("/iCal")
public class iCal extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static long lastUpdateTime = 0;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public iCal() {
		super();
		// TODO Auto-generated constructor stub
	}

	private void getMenu(){
		Parser p = new Parser();
		try {
			List<GununYemegi> ayinYemegi = p.aylikYemekleriCek();
			for(GununYemegi gy : ayinYemegi){
				YemekMenuCalendar.addMenu(gy.getDay(), gy.getMeal());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if (YemekMenuCalendar.getInstance().getComponents().size() == 0) {
			getMenu();
		}
		
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(YemekMenuCalendar.getInstance().toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
