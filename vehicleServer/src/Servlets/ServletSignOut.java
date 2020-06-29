package Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ServletSignOut extends HttpServlet
{
	public void doPost(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
	{
		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(-60*20);
		session.invalidate();
		response.sendRedirect("http://localhost:8005/VehiclesDB/home");
	}
}
