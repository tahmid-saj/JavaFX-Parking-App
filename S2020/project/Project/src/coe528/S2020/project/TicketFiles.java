/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.S2020.project;

/**
 *
 * @author: Group 3
 */
import static coe528.S2020.project.CustomerEntry.hours;
import static coe528.S2020.project.CustomerEntry.price;
import static coe528.S2020.project.ManagerView.floor;
import static coe528.S2020.project.ManagerView.vehicleType;

/**
 * // OVERVIEW: The data types of this class are all of String, for ticketNumber, floor, price, vehicleType, hours. This class
 * holds the constructors, getters and setters needed to provide the values to create the table view of the class ManagerFiles
 * which displays relevant information for all files. This class is immutable.
 *
 * Design Pattern, Builder: The TicketFiles class is responsible for the construction of a more complex object using the information
 * from the text files in the TicketFiles folder. Therefore the construction is separated from it's representation.
 * @author: Group 3
 */
public final class TicketFiles extends ManagerFiles{
    //Declarations of instance variables
    private String ticketNumber;
    private String floor;
    private String price;
    private String vehicleType;
    private String hours;
    
    //Constructors
    /**
     * //Effects: This constructor assigns "" values to the ticketNumber, floor, price, vehicleType, hours instance variables of the
     * TicketFiles object that called this constructor.
     */
    public TicketFiles() {
        this.ticketNumber = "";
        this.floor = "";
        this.price = "";
        this.vehicleType = "";
        this.hours = "";
    }
    
    /**
     * //Requires: This constructor requires the input parameters String, ticketNumber, floor, price, vehicleType and hours to be 
     * Strings and not equal to null.
     * //Modifies: This constructor sets the respective instance variables of the TicketFiles called using the parameters String 
     * ticketNumber, floor, price, vehicleType, and hours.
     * //Effects: This constructor assigns the instance variables of the TicketFiles object called with the input parameters String
     * ticketNumber, floor, price, vehicleType, and hours. Also prints all the file information using the toString() abstraction method.
     * @param ticketNumber
     * @param floor
     * @param price
     * @param vehicleType
     * @param hours 
     * Uses the parameters String ticketNumber, floor, price, vehicleType and hours
     */
    public TicketFiles(String ticketNumber, String floor, String price, String vehicleType, String hours) {
        this.ticketNumber = ticketNumber;
        this.floor = floor;
        this.price = price;
        this.vehicleType = vehicleType;
        this.hours = hours;
        System.out.println(this.toString());
    }

    //Methods
    /**
     * //Effects: This getter returns the ticketNumber instance variable of the TicketFiles object that it is called from
     * @return 
     * ticketNumber
     */
    public String getTicketNumber() {
        return ticketNumber;
    }

    /**
     * //Requires: This setter requires the input parameter String ticketNumber to be a String and not equal to null.
     * //Modifies: The setter modifies the ticketNumber instance variable using the input parameter String ticketNumber.
     * //Effects: This setter will equate the input parameter to the TicketFiles object instance variable ticketNumber.
     * @param ticketNumber 
     * Uses the parameter String ticketNumber
     */
    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    /**
     * //Effects: This getter returns the floor instance variable of the TicketFiles object that it is called from
     * @return 
     * floor
     */
    public String getFloor() {
        return floor;
    }

    /**
     * //Requires: This setter requires the input parameter String floor to be a String and not equal to null.
     * //Modifies: The setter modifies the floor instance variable using the input parameter String floor.
     * //Effects:  This setter will equate the input parameter to the TicketFiles object instance variable floor. 
     * @param floor 
     * Uses the String floor parameter
     */
    public void setFloor(String floor) {
        this.floor = floor;
    }

    /**
     * //Effects: This getter returns the price instance variable of the TicketFiles object that it is called from
     * @return 
     * price
     */
    public String getPrice() {
        return price;
    }

    /**
     * //Requires: This setter requires the input parameter String price to be a String and not equal to null.
     * //Modifies: The setter modifies the price instance variable using the input parameter String price.
     * //Effects: This setter will equate the input parameter to the TicketFiles object instance variable price. 
     * @param price 
     * Uses the String price parameter
     */
    public void setPrice(String price) {
        this.price = price;
    }

    /**
     * //Effects: This getter returns the vehicleType instance variable of the TicketFiles object that it is called from
     * @return 
     * vehicleType
     */
    public String getVehicleType() {
        return vehicleType;
    }

    /**
     * //Requires: This setter requires the input parameter String vehicleType to be a String and not equal to null.
     * //Modifies: The setter modifies the vehicleType instance variable using the input parameter String vehicleType.
     * //Effects:  This setter will equate the input parameter to the TicketFiles object instance variable vehicleType.
     * @param vehicleType 
     * Uses the String vehicleType parameter
     */
    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    /**
     * //Effects: This getter returns the hours instance variable of the TicketFiles object that it is called from
     * @return 
     * hours
     */
    public String getHours() {
        return hours;
    }

    /**
     * //Requires: This setter requires the input parameter String hours to be a String and not equal to null.
     * //Modifies: The setter modifies the hours instance variable using the input parameter String hours.
     * //Effects: This setter will equate the input parameter to the TicketFiles object instance variable hours.
     * @param hours 
     * Uses the String hours parameter
     */
    public void setHours(String hours) {
        this.hours = hours;
    }
    
    /**
     * //Effects:  This method returns a string representation of the ticket, the information on Floor, Total Price, Vehicle Type, and
     * Hours.
     * @return 
     * "Floor: " + floor + " Total Price: " + price + " Vehicle Type: " + vehicleType + " Hours: " + hours
     */
    @Override
    public String toString() {
        return "Ticket Number: " + ticketNumber + "Floor: " + floor + " Total Price: " + price + " Vehicle Type: " + vehicleType + " Hours: " + hours; 
    }
}
