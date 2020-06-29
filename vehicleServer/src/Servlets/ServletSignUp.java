package Servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Models.User;
import Models.VehicleDAO;

public class ServletSignUp extends HttpServlet
{
	
	private static final long serialVersionUID = 1L;
	
	protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException,IOException
	{
		RequestDispatcher view = request.getRequestDispatcher("signUp.jsp");
		view.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
		{
			VehicleDAO dao = new VehicleDAO();
			
			String firstname = request.getParameter("insertFirstname");
			String surname = request.getParameter("insertSurname");
			String username = request.getParameter("insertUsername");
			String password = request.getParameter("insertPassword");
			String userType = request.getParameter("insertUser_type");
			String api =  dao.apiGenerator(16);
			
			User user = new User(firstname, surname, username, password, userType, api);
			try {
				boolean done = dao.insertUser(user);
				System.out.println(done);
			}
				catch(SQLException e)
				{
					e.printStackTrace();
				}
		}

}
