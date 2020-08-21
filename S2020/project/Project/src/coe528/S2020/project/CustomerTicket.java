/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.S2020.project;

//Importations
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * // OVERVIEW: The data types of this class are of boolean electricCharge, double totalPrice. This class displays the information
 * about the customer's ticket which is booked. The information includes (ticketNumber, floor, totalPrice, vehicleType, and hours).
 * The customer is also able to perform electric charge if the vehicleType is "Electric". This class is mutable.
 *
 * Design Pattern, Observer: The observer design pattern is present between CustomerTicket and CustomerPayment, where if the totalPrice
 * state of CustomerTicket changes due to an electric charge option, this is reflected in the CustomerPayment class as well.
 * @author: Group 3
 */
public class CustomerTicket extends CustomerEntry{
    //Declarations
    private final static BorderPane canvasTicket = new BorderPane();
    private static boolean electricCharge = false;
    public static double totalPrice = price;
    
    //Methods
    /**
     * //Requires: This method will require the input parameters String title, ticketNumber, vehicleType to be Strings and not equal to
     * null, and int hours to be an Integer, floor, also double price to be a Double value.
     * //Modifies: This method will modify the totalPrice variable using price if the Electric Charge button is clicked.
     * //Effects:  This method will display the Stage customerTicket title and information from the input parameters passed (ticketNumber,
     * hours, vehicleType, price, floor) in the respective labels. If the Pay Ticket Price button is clicked, the Stage will shift to the
     * Stage customerPayment of the CustomerPayment class.
     * @param title
     * @param ticketNumber
     * @param hours
     * @param vehicleType
     * @param price
     * @param floor 
     * Uses String title, ticketNumber, vehicleType, int hours, floor and double price parameters
     */
    public static void display(String title, String ticketNumber, int hours, String vehicleType, double price, int floor) {
        //Control objects declarations and assignments
        
        Stage customerTicket = new Stage();
        
        customerTicket.initModality(Modality.APPLICATION_MODAL);
        customerTicket.setTitle(title);
        customerTicket.setMaximized(true);
        
        Label labelTicketNumber = new Label("Your Ticket Number: " + ticketNumber);
        Label labelVehicleType = new Label("Your Vehicle Type: " + vehicleType);
        Label labelHoursBooked = new Label("Your Booked Hours: " + hours);
        Label labelTotalPrice = new Label("Your Total Price: $" + price);
        Label labelFloor = new Label("Your Floor: " + floor);
        Label labelPaymentMethod = new Label("Choose how you want to pay for the ticket: exit panel, attendant, information portal ");
        
        Image imageFive = new Image("file:backgroundticket.jpg");
        ImageView ivFive = new ImageView(imageFive);
        
        Button buttonElectricCharge = new Button("Charge Electric Car");
        Button buttonPay = new Button("Pay Ticket Price");
        
        if (!vehicleType.equals("Electric"))
            buttonElectricCharge.setDisable(true);
        
        //Button click lambda events
        buttonElectricCharge.setOnAction(e -> {
            if (electricCharge == false) {
                labelTotalPrice.setText("Your Total Price: $" + (price + 3.0D));
                electricCharge = true;
                totalPrice = price + 3.0D;
            }
        });
        
        buttonPay.setOnAction(e -> {
            customerTicket.close();
            CustomerPayment.receipt("ParKing - Customer Payment", ticketNumber, hours, totalPrice, vehicleType);
        });
        
        //Creation of GridPane
        GridPane gridTicket = new GridPane();
        gridTicket.setAlignment(Pos.TOP_CENTER);
        gridTicket.setHgap(60);
        gridTicket.setVgap(10);
        gridTicket.setPadding(new Insets(20, 20, 20, 20));
        
        //Node additions to GridPane
        gridTicket.add(labelTicketNumber, 0, 0);
        gridTicket.add(labelVehicleType, 1, 0);
        gridTicket.add(labelHoursBooked, 0, 1);
        gridTicket.add(labelFloor, 0, 2);
        gridTicket.add(labelTotalPrice, 1, 1);
        gridTicket.add(buttonElectricCharge, 2, 0);
        gridTicket.add(labelPaymentMethod, 2, 1);
        gridTicket.add(buttonPay, 2, 2);
        
        Image imageParkingLot = new Image("file:parkinglotimage.jpg");
        ImageView iv = new ImageView();
        iv.setImage(imageParkingLot);
        
        //GridPane and Image additions to canvasTicket
        canvasTicket.getChildren().addAll(ivFive);
        canvasTicket.setCenter(iv);
        
        canvasTicket.setTop(gridTicket);
        gridTicket.setGridLinesVisible(false);
        
        //Scene assignment
        Scene customerTicketScene = new Scene(canvasTicket);
        customerTicket.setScene(customerTicketScene);
        customerTicket.show();
    }
    
    /**
     * //Effects:  returns the String representation of the Customer Entry and returns the String representation of the abstraction 
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
