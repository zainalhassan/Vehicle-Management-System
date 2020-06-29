package Models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/** The purpose of the VehicleDAO class is to implement database functionality for vehicles. Vehicle information and users will be stored and queries are executed here for the CRUD methods. 
 * 
 * @author Zain Al-Hassan
 *
 */
public class VehicleDAO 
{
	//Connect to the vehicles database
	private static Connection getDBConnection()
	{
		Connection conn = null;
		
		try 
		{
			Class.forName("org.sqlite.JDBC");
		}
		catch(ClassNotFoundException e)
		{
			System.out.println(e.getMessage());
		}
		
		try
		{
			String url = "jdbc:sqlite:vehicles.sqlite";
			conn = DriverManager.getConnection(url);
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
		
		return conn;
	}
	
	
	public void closeConnection() {
	
	}
	
	//get all vehicles from vehicles table
	public ArrayList<Vehicle> getAllVehicles()
	{
		System.out.println("Retrieving all Vehicles ...");
		Connection dbConnection = null;
		Statement statement = null;
		ResultSet result = null;
		String query = "SELECT * FROM vehicles;";
		ArrayList<Vehicle> vehicleList = new ArrayList<>();
		
		try
		{
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("DBQuery = " + query);
			result = statement.executeQuery(query);
			while(result.next())
			{
				int vehicle_id = result.getInt("Vehicle_id");
				String make = result.getString("make");
				String model = result.getString("model");
				int year = result.getInt("year");
				int price = result.getInt("price");
				String license_number = result.getString("license_number");
				String colour = result.getString("colour");
				int number_doors = result.getInt("number_doors");
				String transmission = result.getString("transmission");
				int mileage = result.getInt("mileage");
				String fuel_type = result.getString("fuel_type");
				int engine_size = result.getInt("engine_size");
				String body_style = result.getString("body_style");
				String condition = result.getString("condition");
				String notes = result.getString("notes");
				
				vehicleList.add(new Vehicle(vehicle_id,make, model, year, price, license_number, colour, number_doors, transmission, mileage, fuel_type, engine_size, body_style, condition, notes));
			}
		}
		
		catch (SQLException s) 
		{
			System.out.println(s);
		}
		finally
		{
			if (result != null) 
			{
				try {
					result.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
			
			if (statement != null) 
			{
				try {
					statement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
			
			if (dbConnection != null) 
			{
				try {
					dbConnection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
		}
		
		
		return vehicleList;
		
		
		
		
	}
	
	//get specific vehicle using vehicle ID
	public Vehicle getVehicle(int vehicle_id) throws SQLException
	{
		System.out.println("Retrieving vehicle ...");
		Vehicle temp = null;
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		String query = "SELECT * FROM vehicles WHERE vehicle_id =" + vehicle_id + ";";
		
		try 
		{
			connection = getDBConnection();
			statement = connection.createStatement();
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("DBQuery: " + query);
			// execute SQL query
			result = statement.executeQuery(query);
			
			while(result.next())
			{
				int vehicle_ID = result.getInt("vehicle_id");
				String make = result.getString("make");
				String model = result.getString("model");
				int year = result.getInt("year");
				int price = result.getInt("price");
				String license_number = result.getString("license_number");
				String colour = result.getString("colour");
				int number_doors = result.getInt("number_doors");
				String transmission = result.getString("transmission");
				int mileage = result.getInt("mileage");
				String fuel_type = result.getString("fuel_type");
				int engine_size = result.getInt("engine_size");
				String body_style = result.getString("body_style");
				String condition = result.getString("condition");
				String Notes = result.getString("Notes");
				
				temp = new Vehicle(vehicle_ID, make, model, year, price, license_number, colour, number_doors, transmission, mileage, fuel_type, engine_size, body_style, condition, Notes);

			}
		}
		
		finally
		{
			if (result != null)
			{
				result.close();
			}
			
			if (statement != null)
			{
				statement.close();
			}
			
			if (connection != null)
			{
				connection.close();
			}
		}
		
		return temp;
	}
	
	//Delete a vehicle using the vehicle ID
	public Boolean deleteVehicle(int vehicle_id) throws SQLException
	{
		System.out.println("Deleting Vehicle");
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		int deleteResult = 0;
		
		String query = "DELETE FROM vehicles WHERE vehicle_id = " + vehicle_id + ";";
		
		try
		{
			connection = getDBConnection();
			statement = connection.createStatement();
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("DBQuery: " + query);
			// execute SQL query
			deleteResult = statement.executeUpdate(query);
		}
		
		finally
		{
			if (statement != null)
			{
				statement.close();
			}
			
			if (connection != null)
			{
				connection.close();
			}
		}
		
		if (deleteResult == 1)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	//insert new vehicle into the database
	public boolean insertVehicle(Vehicle in) throws SQLException
	{
		Connection connection = null;
		Statement statement = null;
		
		//no need to insert vehicle id as it is auto incrementing, the vehicle will automatically be assigned one.
		String insert = "INSERT INTO vehicles (make, model, year, price, license_number, colour, number_doors, transmission, mileage, fuel_type, engine_size, body_style, condition, Notes) VALUES ('" + in.getMake() + "','" + in.getModel() + "'," + in.getYear() + "," + in.getPrice() + ",'" + in.getLicense_number() + "','" + in.getColour() + "'," + in.getNumber_doors() + ",'" + in.getTransmission() + "'," + in.getMileage() + ",'" +in.getFuel_type() + "'," + in.getEngine_size() + ",'" + in.getBody_style() + "','" + in.getCondition() + "','" + in.getNotes() +"');";
		boolean ok = false;
		try
		{
			connection = getDBConnection();
			statement = connection.createStatement();
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println(insert);
			
			//execute SQL query
			statement.executeUpdate(insert);
			ok = true;
		}
		
		catch (SQLException e)
		{
			System.out.println(e.getMessage());
			System.out.println();
		}
		
		finally
		{
			if (statement != null)
			{
				statement.close();
			}
			
			if (connection != null)
			{
				connection.close();
			}
		}
		return ok;
	}
	
	//update a vehicle using vehicle ID
	public Boolean updateVehicle(Vehicle vehicle, int updateVehicle) throws SQLException
	{
		Connection connection = null;
		Statement statement = null;
		
		String query = "UPDATE vehicles SET " + "make = '" + vehicle.getMake() + "'," + "model = '" + vehicle.getModel() + "'," + "year = " + vehicle.getYear() + "," + "price = " + vehicle.getPrice() + "," + "license_number = '"+ vehicle.getLicense_number() + "'," + "colour = '" + vehicle.getColour() + "'," + "number_doors = " + vehicle.getNumber_doors() + "," + "transmission = '" + vehicle.getTransmission() + "'," + "mileage = " + vehicle.getMileage() + "," + "fuel_type = '" + vehicle.getFuel_type() + "'," + "engine_size = " + vehicle.getEngine_size() + "," + "body_style = '" + vehicle.getBody_style() + "'," + "condition = '" + vehicle.getCondition() + "'," +  "Notes = '" + vehicle.getNotes() + "' WHERE Vehicle_id = " + vehicle.getVehicle_id() + ";";
		
		try
		{
			connection = getDBConnection();
			statement = connection.createStatement();
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println(query);
			
			//execute SQL Query
			statement.executeUpdate(query);
		}
		
		catch (SQLException e)
		{
			System.out.println(e.getMessage());
			return false;
		}
		
		finally
		{
			if (statement != null)
			{
				statement.close();
			}
			
			if (connection != null)
			{
				connection.close();
			}
		}
		return true;
	}
	
	// generate a random string for api generation
    public String apiGenerator(int n) 
    {
        String apiGenerator = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                                    + "0123456789"
                                    + "abcdefghijklmnopqrstuvxyz"; 
  
        // create StringBuilder
        StringBuilder sb = new StringBuilder(n); 
  
        for (int i = 0; i < n; i++) { 
  
            // generate a random number between 0 and n
            int index 
                = (int)(apiGenerator.length() 
                        * Math.random()); 
  
            // add Character one by one in end of sb 
            sb.append(apiGenerator 
                          .charAt(index)); 
        } 
  
        return sb.toString(); 
    }
    
    public Boolean checkApi(String api) throws SQLException
    {
    	Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		
		//no need to insert vehicle id as it is auto incrementing, the vehicle will automatically be assigned one.
		String checkAPI = "SELECT 1 from users WHERE api_key = '"+api+"'";
		boolean ok = false;
		try
		{
			connection = getDBConnection();
			statement = connection.createStatement();
			
			//execute SQL query
			result = statement.executeQuery(checkAPI);
			if(result.next()) {
				ok = true;
			}else {
				ok = false;
			}
		}
		catch (SQLException e)
		{
			System.out.println(e.getMessage());
			System.out.println();
		}
		finally
		{
			
			if (statement != null)
			{
				statement.close();
			}
			
			if (connection != null)
			{
				connection.close();
			}
		}
		
		return ok;
    	
    }
    
  //insert new vehicle into the database
	public boolean insertUser(User in) throws SQLException
	{
		Connection connection = null;
		Statement statement = null;
		
		System.out.println(in.getFirstname());
		
		//no need to insert user id as it is auto incrementing, the vehicle will automatically be assigned one.
		String insert = "INSERT INTO users (firstname, surname, username, password, user_type, api_key) VALUES ('" + in.getFirstname() + "','" + in.getSurname() + "','" + in.getUsername() + "','" + in.getPassword() + "','" + in.getUser_type() + "','" + in.getApi_key() +  "');";
		System.out.println(insert);
		boolean ok = false;
		try
		{
			connection = getDBConnection();
			statement = connection.createStatement();
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println(insert);
			
			//execute SQL query
			statement.executeUpdate(insert);
			ok = true;
		}
		
		catch (SQLException e)
		{
			System.out.println(e.getMessage());
			System.out.println();
		}
		
		finally
		{
			
			if (statement != null)
			{
				statement.close();
			}
			
			if (connection != null)
			{
				connection.close();
			}
		}
		return ok;
	}
    
    
}
