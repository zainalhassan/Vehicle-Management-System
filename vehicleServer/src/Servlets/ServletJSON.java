package Servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Models.Vehicle;
import Models.VehicleDAO;

/** The purpose of the ServletHome class is to provide a homepage for the web-front version of the Vehicle crud database.
 * 
 * @author Zain Al-Hassan
 *
 */
public class ServletJSON extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	//performs the get methods found in index.jsp -> table
	protected void doGet( HttpServletRequest request, HttpServletResponse response )
			throws ServletException,IOException
	{
		VehicleDAO dao = new VehicleDAO();
		
		ArrayList<Vehicle> allVehicles= new ArrayList<>();
		allVehicles = dao.getAllVehicles();
		request.setAttribute("allVehicles", allVehicles);
		RequestDispatcher view = request.getRequestDispatcher("JSON.jsp");
		view.forward(request, response);
		
	}
			
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException 
	{
			
	}
}
