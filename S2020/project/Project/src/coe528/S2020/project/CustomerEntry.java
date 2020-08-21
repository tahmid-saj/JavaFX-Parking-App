/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.S2020.project;

//Importations
import java.io.File;
import static java.lang.String.valueOf;
import java.util.Random;
import java.util.Scanner;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * // OVERVIEW: This class holds data types for int floor, hours, boolean floorFull, String floorStr, vehicleType, ticketNumber,
 * String[] openCompactSpots, openElectricSpots, openHandicappedSpots, openMotorcycleSpots, openSpots, openTruckSpots, openVanSpots,
 * double price. This class provides the customer entry to show appropriate information about each floor details from the files. 
 * From this class, the customer can search and book a ticket. This classes role in this project regards the customers entry. 
 * The customer Entry refers to the input of the customers information in accordance to their parking requirements, based on the 
 * parking constraints presented. This class is mainly responsible for GUI of the customer screen. This includes the labeling of the
 * customer portion of the GUI in order to identify which each label represents and display the list of parking constraints such as 
 * the available parking spots for each vehicle on a specified floor, hourly prices, Entry panels, Exit panels and information 
 * portals. This also includes the choice boxes of each screen in order to focus user inputs. This class also is responsible for 
 * setting the parking price for customers, and the amount of choices for floors,types of vehicles by the application and the 
 * amount of available parking time. Additionally this class creates the buttons book parking spot and search parking spots, which 
 * are then also tested for functionality. Furthermore, the file the customers information goes to is both read and tested in this 
 * class. Finally a unique ticket identification number is created in this class. This class is mutable.
 * @author: Group 3
 */
public class CustomerEntry extends ManagerView{
    //Declarations
    private final static BorderPane canvasCustomer = new BorderPane();
    public static int floor;
    public static String floorStr;
    public static int hours;
    public static String vehicleType;
    public static String ticketNumber;
    public static double price;
    public static boolean floorFull = false;
    
    public static Random rand = new Random();
    
    public static Label labelParkingSpots, labelCompactSpots, labelTruckSpots, labelVanSpots, labelHandicappedSpots, labelMotorcycleSpots, labelElectricSpots;
    public static Label labelElectricCharging, labelEntryPanels, labelExitPanels, labelParkingAttendants, labelInfoPortals, labelHoursAllowed, labelVehiclesAccepted, labelTotal;
    public static final Text textWarning = new Text();
    
    public static String [] openSpots = new String[7];
    public static String [] openCompactSpots = new String[7];
    public static String [] openTruckSpots = new String[7];
    public static String [] openVanSpots = new String[7];
    public static String [] openHandicappedSpots = new String[7];
    public static String [] openMotorcycleSpots = new String[7];
    public static String [] openElectricSpots = new String[7];
    
    //Methods
    /**
     * //Requires: This method requires the input parameter String title to be a String and not equal to null.
     * //Modifies: This method will display the input parameter String title on to the Stage as its title.
     * //Effects:  This method will allow the customer to enter floor, hours and vehiclType values from the respective choice boxes and then
     * the method will generate the information of each floor, the open spots, entry, exit panels etc from the files. If the input is the 
     * Book Ticket button, the method will generate a random ticketNumber which is assigned to String ticketNumber.
     * @param title 
     * Uses the String title parameter
     */
    public static void display(String title) {
        //Control objects declarations and assignments
        
        Stage customerEntry = new Stage();
        
        customerEntry.initModality(Modality.APPLICATION_MODAL);
        customerEntry.setTitle(title);
        customerEntry.setMaximized(true);
        
        ChoiceBox<Integer> choiceFloor = new ChoiceBox<>();
        ChoiceBox<Integer> choiceHours = new ChoiceBox<>();
        ChoiceBox<String> choiceVehicleType = new ChoiceBox<>();
        
        choiceFloor.getItems().addAll(1, 2, 3, 4, 5, 6, 7);
        choiceHours.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8);
        choiceVehicleType.getItems().addAll("Compact", "Truck", "Van", "Handicapped", "Motorcycle", "Electric");
        
        Label labelFloor = new Label("Select Floor: ");
        Label labelHours = new Label("Select Hours: ");
        Label labelVehicleType = new Label("Select Vehicle Type: ");
        textWarning.setFill(Color.FIREBRICK);
        Label labelInstructions = new Label("Please select a floor, hour, vehicle type\nfrom the top and click search,\nthen book the ticket on the right");
        
        Label labelInformation = new Label("This floor supports a total of 60 parking spots.\nWe support 10 spots each for vehicle type: Compact, Truck, Van, Handicapped, Motorcycle, Electric");
        labelParkingSpots = new Label("Open Parking Spots: ");
        labelCompactSpots = new Label("Open Compact Spots: ");
        labelTruckSpots = new Label("Open Truck Spots: ");
        labelVanSpots = new Label("Open Van Spots: ");
        labelHandicappedSpots = new Label("Open Handicapped Spots: ");
        labelMotorcycleSpots = new Label("Open Motorcycle Spots: ");
        labelElectricSpots = new Label("Open Electric Spots: ");
        
        labelElectricCharging = new Label("Electric Charging Panels: ");
        labelEntryPanels = new Label("Entry Panels: ");
        labelExitPanels = new Label("Exit Panels: ");
        labelParkingAttendants = new Label("Parking Attendants: ");
        labelInfoPortals = new Label("Infomation Portals: ");
        
        labelHoursAllowed = new Label("Hours Allowed: ");
        labelVehiclesAccepted = new Label("Vehicles Accepted: ");
       
        Label labelTotalPrice = new Label("Total Price/Hour");
        Label labelFirstHour = new Label("1st Hour = $5.00 CAD");
        Label labelSecondHour = new Label("2nd Hour = $4.00 CAD");
        Label labelThirdHour = new Label("3rd Hour = $3.50 CAD");
        Label labelFourthHour = new Label("4th Hour = $3.00 CAD");
        Label labelFifthHour = new Label("5th-8th Hour = $2.50 CAD per hour");
        labelTotal = new Label();
        
        Image imageFour = new Image("file:backgroundcustomer.jpg");
        ImageView ivFour = new ImageView(imageFour);
        
        Button buttonSearch = new Button("Search Parking Spots");
        Button buttonBook = new Button("Book Parking");
        
        //Button click lambda events
        buttonSearch.setOnAction(e -> {
            try{
                floorFull = false;
                textWarning.setText("");
                floor = choiceFloor.getValue();
                hours = choiceHours.getValue();
                vehicleType = choiceVehicleType.getValue();
                
                Project.primaryStage.close();
                
                if (choiceFloor.getValue().equals(null) || choiceHours.getValue().equals(null) || choiceVehicleType.getValue().equals(null)) {
                    throw new NullPointerException();
                }
            
                searchFileInfo();
                readFileInfo();
                displayInformation(floor, hours, vehicleType);
                ManagerView.readFile.close();
            } catch (NullPointerException ex) {
                textWarning.setText("Please enter floor, hours, vehicle type");
            }
        });
        
        buttonBook.setOnAction(e -> {
            try {
                textWarning.setText("");
                floor = choiceFloor.getValue();
                hours = choiceHours.getValue();
                vehicleType = choiceVehicleType.getValue();
                
                if (choiceFloor.getValue().equals(null) || choiceHours.getValue().equals(null) || choiceVehicleType.getValue().equals(null) || (floorFull == true)) {
                    throw new NullPointerException();
                }
                
                if (price == 0.0D) {
                    throw new IllegalArgumentException();
                }
                
                ticketNumber = getRandomTicketNumber();
                customerEntry.close();
                CustomerTicket.display("ParKing - Customer's Ticket", ticketNumber, hours, vehicleType, price, floor);
                
                if (CustomerFiles.fileExistsChild() == false) {
                    CustomerFiles.createTicketFile();
                    CustomerFiles.fileWriteTicket(floor + " ", price + " ", vehicleType + " ", hours + "");
                }
            } catch (NullPointerException ex) {
                textWarning.setText("Please enter floor, hours, vehicle type\nand enter a floor that isn't full");
            } catch (IllegalArgumentException ex) {
                textWarning.setText("Please click the search button first");
            }
        });
        
        //Creation of GridPane
        GridPane gridCustomerEntry = new GridPane();
        gridCustomerEntry.setAlignment(Pos.CENTER);
        gridCustomerEntry.setHgap(60);
        gridCustomerEntry.setVgap(10);
        gridCustomerEntry.setPadding(new Insets(20, 20, 20, 20));
        
        //Node additions to GridPane
        gridCustomerEntry.add(labelFloor, 0, 0);
        gridCustomerEntry.add(choiceFloor, 0, 1);
        gridCustomerEntry.add(labelHours, 0, 2);
        gridCustomerEntry.add(choiceHours, 0, 3);
        gridCustomerEntry.add(labelVehicleType, 0, 4);
        gridCustomerEntry.add(choiceVehicleType, 0, 5);
        gridCustomerEntry.add(buttonSearch, 0, 7);
        gridCustomerEntry.add(textWarning, 0, 8);
        gridCustomerEntry.add(labelInstructions, 0, 9);
        
        gridCustomerEntry.add(labelInformation, 1, 0);
        gridCustomerEntry.add(labelParkingSpots, 1, 1);
        gridCustomerEntry.add(labelCompactSpots, 1, 2);
        gridCustomerEntry.add(labelTruckSpots, 1, 3);
        gridCustomerEntry.add(labelVanSpots, 1, 4);
        gridCustomerEntry.add(labelHandicappedSpots, 1, 5);
        gridCustomerEntry.add(labelMotorcycleSpots, 1, 6);
        gridCustomerEntry.add(labelElectricSpots, 1, 7);
        
        gridCustomerEntry.add(labelElectricCharging, 1, 8);
        gridCustomerEntry.add(labelEntryPanels, 1, 9);
        gridCustomerEntry.add(labelExitPanels, 1, 10);
        gridCustomerEntry.add(labelParkingAttendants, 1, 11);
        gridCustomerEntry.add(labelInfoPortals, 1, 12);
        
        gridCustomerEntry.add(labelHoursAllowed, 1, 13);
        gridCustomerEntry.add(labelVehiclesAccepted, 1, 14);
        
        gridCustomerEntry.add(labelTotalPrice, 2, 0);
        gridCustomerEntry.add(labelFirstHour, 2, 1);
        gridCustomerEntry.add(labelSecondHour, 2, 2);
        gridCustomerEntry.add(labelThirdHour, 2, 3);
        gridCustomerEntry.add(labelFourthHour, 2, 4);
        gridCustomerEntry.add(labelFifthHour, 2, 5);
        
        gridCustomerEntry.add(labelTotal, 2, 7);
        gridCustomerEntry.add(buttonBook, 2, 8);
        
        //GridPane and Image addition to canvasCustomer
        canvasCustomer.getChildren().addAll(ivFour);
        canvasCustomer.setCenter(gridCustomerEntry);
        gridCustomerEntry.setGridLinesVisible(false);
        
        //Scene assignment
        Scene customerEntryScene = new Scene(canvasCustomer);
        customerEntry.setScene(customerEntryScene);
        customerEntry.show();
    }
    
    /**
     * //Effects: This method searches the File of the Customer Entry and attempts to identify whether this file is accessible by the system
     * This method will search through the files in the ParkingInformation folder and assign the Scanner readFile, the File in the
     * folder so that it is able to read the file. An exception may be produced if it is caught.
     */
    private static void searchFileInfo() {
        try {
            ManagerView.readFile = new Scanner(new File("ParkingInformation\\ParkingInformation.txt"));
        } catch (Exception e) {
            System.out.println("Unable to retrieve parking information");
        }
    }
    
    /**
     * //Effects: This method is used to assign values to the file created by the CustomeEntry which are used for ManagerView and Receipt
     * This method reads the file info of the ParkingInformation folder. It will iterate through every content in the readFile
     * variable of type Scanner and assign the content values from the files to the respective arrays using the index i.
     */
    private static void readFileInfo() {
        int i = 0;
        
        while(ManagerView.readFile.hasNext()) {
            floorStr = ManagerView.readFile.next();
            openSpots[i] = ManagerView.readFile.next();
            openCompactSpots[i] = ManagerView.readFile.next();
            openTruckSpots[i] = ManagerView.readFile.next();
            openVanSpots[i] = ManagerView.readFile.next();
            openHandicappedSpots[i] = ManagerView.readFile.next();
            openMotorcycleSpots[i] = ManagerView.readFile.next();
            openElectricSpots[i] = ManagerView.readFile.next();
            i++;
        }
    }
    
    /**
     * //Requires: This method requires that the input parameters int floor, hours be of Integer values and String vehicleType be of a
     * String and not equal to null. Specifically, floor must be an integer value that is less than 0 and less than or equal to 7,hours must be an integer value 
     * that is  greater than 0 and less than or equal to 8, vehicleType must be a non empty String value that is either "Compact", "Truck", "Van", "Handicapped", 
     * "Motorcycle", or "Electric".
     * //Effects: This method displays the relevant information of each file from the input parameter floor, (ioenSpots, 
     * openCompactSpots, openTruckSpots, openVanSpots, openHandicappedSpots,. etc) in the respective labels and displays a 
     * total price in the Label labelTotal from the input parameter hours.
     * @param floor
     * @param hours
     * @param vehicleType 
     */
    private static void displayInformation(int floor, int hours, String vehicleType) {
        if (openSpots[floor-1].equals("0")) {
            textWarning.setText("Warning: Floor is full, \nplease select another floor.");
            floorFull = true;
        }
        
        labelParkingSpots.setText("Open Parking Spots: " +  openSpots[floor-1]);
        labelCompactSpots.setText("Open Compact Spots: " +  openCompactSpots[floor-1]);
        labelTruckSpots.setText("Open Truck Spots: " +  openTruckSpots[floor-1]);
        labelVanSpots.setText("Open Van Spots: " +  openVanSpots[floor-1]);
        labelHandicappedSpots.setText("Open Handicapped Spots: " +  openHandicappedSpots[floor-1]);
        labelMotorcycleSpots.setText("Open Motorcycle Spots: " +  openMotorcycleSpots[floor-1]);
        labelElectricSpots.setText("Open Electric Spots: " +  openElectricSpots[floor-1]);
        
        labelElectricCharging.setText("Electric Charging Panels: 4");
        labelEntryPanels.setText("Entry Panels: 4");
        labelExitPanels.setText("Exit Panels: 4");
        labelParkingAttendants.setText("Parking Attendants: 2");
        labelInfoPortals.setText("Information Portals: 6");
        labelHoursAllowed.setText("Hours Allowed: 8");
        labelVehiclesAccepted.setText("Vehicles Accepted: Compact, Truck, Van, Handicapped, Motorcycle, Electric");
        
        labelTotal.setText("Total Price: $" + getPrice(hours));
    }
    
    /**
     * //Requires: This method requires the input parameter int hours to be of an Integer. Specifically, hours is a type integer 
     * and is greater then 0
     * //Effects:  This method returns a price of the booked ticket using the input parameter int hours, which is entered from the
     * choice box choiceHours. Specifically, Returns variable double price which contains the price of parking depending on the 
     * inputted integer hours
     * @param hours
     * @return 
     * double price
     */
    private static double getPrice(int hours) {
        price = 0.0;
        for (int h = 1; h <= hours; h++) {
            if (h == 1)
                price += 5.00D;
            if (h == 2)
                price += 4.00D;
            if (h == 3)
                price += 3.50D;
            if (h == 4)
                price += 3.00D;
            if (h >= 5)
                price += 2.50D;
        }
        return price;
    }
    
    /**
     * //Effects:  This method returns a random ticket number of type String using the rand.next() method. It will return a random 
     * integer of String from the range of 0000000000-9999999999. Returns the sum of two integer ticket IDs with a random number, 
     * that being integer randTicketNumberOne and randTicketNumberTwo
     * @return 
     */
    private static String getRandomTicketNumber() {
        int upperbound = 99999;
        //generate random values from 0-999999999
        int randTicketNumberOne = rand.nextInt(upperbound); 
        int randTicketNumberTwo = rand.nextInt(upperbound);
      
        return (valueOf(randTicketNumberOne) + valueOf(randTicketNumberTwo));
    }
    
    /**
     * //Representation Invariants:
     * int hours must assert to true for (hours less than or equal to 8 and hours greater than 0)
     * int floor must assert to true for (floor less than or equal to 7 and floor greater than 0)
     * double price must assert to true for (price >= 0.0)
     * String vehicleType must be of type String and only the following String values: ("Compact", "Truck", "Van", "Handicapped", "Motorcycle", "Electric")
     * The following is required for the GirdPane arguments: GridPane gridCustomerEntry.add((floor less than or equal to 7 and floor greater than 0), (hours less than or equal to 8 and hours greater than 0), String);
     * 
     * //The repOk() method checks the representation invariant is true:
     * Note: This method will not execute unless assertion checking is turned on by passing -enableassertions to Java
     */
    private void repOk() {
        assert (hours <=8 && hours > 0);
        assert (floor <= 7 && floor > 0);
        assert (price >=0.0);
        assert (vehicleType.equals("Compact") || vehicleType.equals("Van") || vehicleType.equals("Truck") || vehicleType.equals("Handicapped") || vehicleType.equals("Motorcycle") || vehicleType.equals("Electric"));
    }
    
    /**
     * //Abstraction Function(s):
     * 
     * getPrice(int hours):
     * 
     * For hours, let parking time hours represent n. Let parking price be the price to park without charge stations.
     * Integer floor represents the parking floor number which spans the set, floor = {a∈Z:  0 less than a and a less than or equal to 7}
     * Integer hours represents the parking time which spans the set, parking time = {n∈Z:  0 less than n and n less than or equal to 8}
     * String vehicleType represents the type of vehicle parked and spans the set, vehicleType = {"Compact", "Truck", "Van", "Handicapped", "Motorcycle", "Electric"}
     * 
     * If the hours is 1 or 2 hours: price = { Set={1,2},n∈S :(parking price =5.0 - (n-1) }
     * If the hours time is 3 or 4 hours: price = { St={3,4},n∈S :(parking price =4.0 - (0.5*n) }
     * If the hours is greater than or equal to 5 hours: price = { n greater than or equal to 5: n∈Z (parking price =2.5*n} }
     * 
     * displayInformation(int floor, int hours, String vehicleType): 
     * Takes the users request of a floor number, desired number of hours parked and their type of vehicle. With this, the method 
     * forms and displays a new set of information. The information displayed is as followed with Vehicles Accepted not having any 
     * new value, Total price having a double value and the rest having a corresponding integer values. The corresponding integer 
     * value refers to the number amount of its respective label.
     * 
     *  "Open Parking Spots: " 
     *  "Open Compact Spots:
     *  "Open Truck Spots: " 
     *  "Open Van Spots: " 
     *  "Open Handicapped Spots: " 
     *  "Open Motorcycle Spots: "  
     *  "Open Electric Spots: " 									
     *  "Total Price: $"
     *  "Electric Charging Panels: "
     *  "Entry Panels: "
     *  "Exit Panels: "
     *  "Parking Attendants: "
     *  "Information Portals: "
     *  "Hours Allowed: "
     *  "Vehicles Accepted: Compact, Truck, Van, Handicapped, Motorcycle, Electric"
     * 
     * toString():
     * 
     * //Effects: returns the String representation of the Customer Entry and returns the String representation of the abstraction 
     * function(s). This method returns a string representation of the ticket, the information on Floor, Total Price, Vehicle Type, 
     * and hours.
     * @return 
     * "Floor: " + floor + " Total Price: " + price + " Vehicle Type: " + vehicleType + " Hours: " + hours
     */
    @Override
    public String toString() {
        return "Floor: " + floor + " Total Price: " + price + " Vehicle Type: " + vehicleType + " Hours: " + hours; 
    }
}
