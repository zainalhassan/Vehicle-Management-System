package Models;

/** The purpose of the Vehicle class is initialise the variables which will be stored in the database in the VehicleDAO, as well as this, there are getters and setters, used to get and set specific values of a vehicle. a toString has been used to print out the vehicle in a consistent and easy to read format
 * 
 * @author Zain Al-Hassan
 *
 */
public class Vehicle 
{
	//
	private int vehicle_id;
	private String make;
	private String model;
	private int year;
	private int price;
	private String license_number;
	private String colour;
	private int number_doors;
	private String transmission;
	private int mileage;
	private String fuel_type;
	private int engine_size;
	private String body_style;
	private String condition;
	private String notes;
	
	//Constructor used to initialise a vehicle class
	public Vehicle(int vehicle_id, String make, String model, int year, int price, String license_number, String colour,
			int number_doors, String transmission, int mileage, String fuel_type, int engine_size, String body_style,
			String condition, String notes) 
	{
		//initialisation of variables
		this.vehicle_id = vehicle_id;
		this.make = make;
		this.model = model;
		this.year = year;
		this.price = price;
		this.license_number = license_number;
		this.colour = colour;
		this.number_doors = number_doors;
		this.transmission = transmission;
		this.mileage = mileage;
		this.fuel_type = fuel_type;
		this.engine_size = engine_size;
		this.body_style = body_style;
		this.condition = condition;
		this.notes = notes;
	}
	
	//Getters & Setters for each of the variables declared above
	public int getVehicle_id() 
	{
		return vehicle_id;
	}

	public void setVehicle_id(int vehicle_id) 
	{
		this.vehicle_id = vehicle_id;
	}

	public String getMake() 
	{
		return make;
	}

	public void setMake(String make) 
	{
		this.make = make;
	}

	public String getModel() 
	{
		return model;
	}

	public void setModel(String model) 
	{
		this.model = model;
	}

	public int getYear() 
	{
		return year;
	}

	public void setYear(int year) 
	{
		this.year = year;
	}

	public int getPrice() 
	{
		return price;
	}

	public void setPrice(int price) 
	{
		this.price = price;
	}

	public String getLicense_number() 
	{
		return license_number;
	}

	public void setLicense_number(String license_number) 
	{
		this.license_number = license_number;
	}

	public String getColour() 
	{
		return colour;
	}

	public void setColour(String colour) 
	{
		this.colour = colour;
	}

	public int getNumber_doors() 
	{
		return number_doors;
	}

	public void setNumber_doors(int number_doors) 
	{
		this.number_doors = number_doors;
	}

	public String getTransmission() 
	{
		return transmission;
	}

	public void setTransmission(String transmission) 
	{
		this.transmission = transmission;
	}

	public int getMileage() 
	{
		return mileage;
	}

	public void setMileage(int mileage) 
	{
		this.mileage = mileage;
	}

	public String getFuel_type() 
	{
		return fuel_type;
	}

	public void setFuel_type(String fuel_type) 
	{
		this.fuel_type = fuel_type;
	}

	public int getEngine_size() 
	{
		return engine_size;
	}

	public void setEngine_size(int engine_size) 
	{
		this.engine_size = engine_size;
	}

	public String getBody_style() 
	{
		return body_style;
	}

	public void setBody_style(String body_style) 
	{
		this.body_style = body_style;
	}

	public String getCondition() 
	{
		return condition;
	}

	public void setCondition(String condition) 
	{
		this.condition = condition;
	}

	public String getNotes() 
	{
		return notes;
	}

	public void setNotes(String notes) 
	{
		this.notes = notes;
	}
	
	//toString is what will be printed in the console in the format displayed below.
	@Override
	public String toString() 
	{
		System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

		return "Vehicle [Vehicle ID = " + vehicle_id + ", Make = " + make + ", Model = " + model + ", Year = " + year
				+ ", Price = " + price + ", License Number = " + license_number + ", Colour = " + colour + ", Number doors = "
				+ number_doors + ", Transmission = " + transmission + ", Mileage = " + mileage + ", Fuel Type = " + fuel_type
				+ ", Engine Size = " + engine_size + ", Body Style = " + body_style + ", Condition = " + condition
				+ ", Notes = " + notes + "]";

	}
}
