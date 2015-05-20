package yemekmenu.servlet;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import yemekmenu.calendar.YemekMenuCalendar;

/**
 * Servlet implementation class iCal
 */
@WebServlet("/iCal")
public class iCal extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public iCal() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if (YemekMenuCalendar.getInstance().getComponents().size() == 0) {
			Calendar cal = Calendar.getInstance();
			cal.set(2015, 4, 19);

			YemekMenuCalendar.addMenu(cal.getTime(), new String[] { "PATLICAN MUSAKKA / LAZANYA", 
				"MANTAR ÇORBA / BADEMLİ PİRİNÇ PİLAVI",
					"SALATABAR" , "KALBURABASTI / MEYVE", "YOGURT / MEYVE SUYU"});
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
