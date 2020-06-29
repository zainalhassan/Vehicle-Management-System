package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import Models.Vehicle;
import Models.VehicleDAO;

public class ServletApi extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	//calls the dao
	VehicleDAO dao = new VehicleDAO();
	//initialise GSON
	Gson gson = new Gson();
	PrintWriter writer;
	//used to store the api key of the user attempting to use the crud features
	String api_key;
	
	//When user attempts to retrieve all vehicles
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException
	{
		ArrayList<Vehicle> allVehicles = null;
		
		//store api retrieved from session into variable
		api_key = req.getParameter("api_key");
		
		try
		{
			//check if api is valid by checking if it is in the users table
			if(dao.checkApi(api_key))
			{
				//retrieve all vehicles
				allVehicles = dao.getAllVehicles();
			 
				resp.setContentType("application/json");
				writer = resp.getWriter();
				//JSON format
				String vehicleJSON = gson.toJson(allVehicles);
				writer.write(vehicleJSON);
				writer.close();
			}
			else
			{
				resp.setContentType("application/json");
				writer = resp.getWriter();
				//display error message
				String error = "Please verify your api key";
				writer.write(error);
				writer.close();
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	//when user attempts to insert a vehicle
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		Gson gson = new Gson();
		
		writer = resp.getWriter();
		resp.setContentType("text/html;charset=UTF-8");
		//store user input as a vehicle object
		Vehicle vehicle = gson.fromJson(req.getParameter("json"),  Vehicle.class);
		System.out.println(vehicle);
		//retrieve their api key
		api_key = req.getParameter("api_key");
		System.out.println(api_key);
		
		try
		{
			//check if api is valid by checking if it is in the users table
			if(dao.checkApi(api_key))
			{
				try
				{
					//attempt vehicle insertion
					boolean updated = dao.insertVehicle(vehicle);
					if (updated)
					{
						writer.write("Vehicle has been inserted");
					}
					else
					{
						writer.write("vehicle failed to be inserted");
					}
					writer.close();
				}
				catch (SQLException e) 
	 			{
	 				// TODO Auto-generated catch block
	 				e.printStackTrace();
	 			}
			}
			else
			{
				resp.setContentType("application/json");
 				writer = resp.getWriter();
 				//display error message
 				String error = "Please verify your api key";
 				writer.write(error);
 				writer.close();
			}
		}
		catch (SQLException e) 
		{
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
	}
	
	//When user attempts to delete a vehicle from the database
	public void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		writer = resp.getWriter();
		resp.setContentType("text/html;charset=UFT-8");
		
		//stores the vehicle wished to be deleted in a variable
		int vehicleToDelete = Integer.parseInt(req.getParameter("vehicle_id"));
		
		
		api_key = req.getParameter("api_key");
		try
		{
			if(dao.checkApi(api_key))
			{
				try
				{
					//attempt to delete the vehicle
					boolean deleted = dao.deleteVehicle(vehicleToDelete);
					
					if(deleted)
					{
						writer.write("Vehicle successfully deleted");
					}
					else 
					{
						writer.write("Vehicle unsuccessflly deleted");
					}
					writer.close();
				}
				catch(SQLException e)
		        {
		              e.printStackTrace();
		        }
			}
			else 
			{
				resp.setContentType("application/json");
				writer = resp.getWriter();
				String error = "Please verify your api key";
				writer.write(error);
				writer.close();
				
			}
		}
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//When the user attempts to update an existing vehicle
	public void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		Gson gson = new Gson();
		
		writer = resp.getWriter();
		resp.setContentType("text/html;charset=UTF-8");
		
		//retrieve the vehicle wished to be updated, and its values, in json format
		String json = req.getParameter("json");
		
		System.out.println("json = " + json);
		
		//store json as an object
		Vehicle vehicle = gson.fromJson(json,  Vehicle.class);
		System.out.println(vehicle);
		
		api_key = req.getParameter("api_key");
		
		try
		{
			if(dao.checkApi(api_key))
			{
				try
				{
					//attempt to update the vehicle
					Boolean updated = dao.updateVehicle(vehicle, vehicle.getVehicle_id());
					
					if (updated)
					{
						writer.write("Vehicle has been updated");
					}
					else
					{
						writer.write("vehicle failed to be updated");
					}
					writer.close();
				}
				catch (SQLException e) 
	   			{
	   				// TODO Auto-generated catch block
	   				e.printStackTrace();
	   			}
			}
			else
			{
				resp.setContentType("application/json");
   				writer = resp.getWriter();
   				String error = "Please verify your api key";
   				writer.write(error);
   				writer.close();
			}
		}
			catch (SQLException e) 
   			{
   				// TODO Auto-generated catch block
   				e.printStackTrace();
   			}
	}
}
