package Servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Models.Vehicle;
import Models.VehicleDAO;

public class ServletLogin extends HttpServlet
{
	/** The purpose of the ServletLogin class is to allow an interface for the user to log in and see additional features if the user who logged in is the admin. the table is displayed and CRUD methods too
	 * 
	 * @author Zain Al-Hassan
	 *
	 */
	private static final long serialVersionUID = 1L;
	
	protected void doGet( HttpServletRequest request, HttpServletResponse response )
			throws ServletException,IOException
	{
		RequestDispatcher view = request.getRequestDispatcher("login.jsp");
		view.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
		{
			String username = request.getParameter("usernameInput");
			String password = request.getParameter("passwordInput");
			String api = "";
			
			try
			{
				Class.forName("org.sqlite.JDBC");
				Connection conn = DriverManager.getConnection("jdbc:sqlite:vehicles.sqlite");
				PreparedStatement prepStatement = conn.prepareStatement("SELECT * FROM users WHERE username =? AND password =?");
				prepStatement.setString(1,username);
				prepStatement.setString(2, password);
				
				ResultSet result = prepStatement.executeQuery();
				
				VehicleDAO dao = new VehicleDAO();
				
				if (result.next())
				{
					HttpSession session = request.getSession();
					session.setAttribute("loggedIn", true);
					api = result.getString("api_key");
					session.setAttribute("api", api);
					ArrayList <Vehicle> allVehicles = null;
					
					allVehicles = dao.getAllVehicles();
			
					RequestDispatcher view = request.getRequestDispatcher("index.jsp");
					request.setAttribute("allVehicles", allVehicles);
					view.forward(request, response);
				}
				else
				{
					doGet(request, response);
				}
			}
		
	catch(SQLException e)
			{
			System.out.println(e.getMessage());
			}
			catch(ClassNotFoundException e)
			{
				e.printStackTrace();
			}
		}
	}