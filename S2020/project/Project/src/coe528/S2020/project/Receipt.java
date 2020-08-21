/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.S2020.project;

//Importations
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * // OVERVIEW: This class holds no data types for instance variables. This class displays the relevant information or the receipt for
 * the ticket. This class is also mutable.
 *
 * Design Pattern, Singleton: The receipt class has a singleton design pattern where it holds only one instance, double totalPrice, 
 * also provides a global point of access to it. Only one instance is needed for the receipt class, the totalPrice, which may 
 * change as the customer may select the option to electric charge their electric vehicle.
 * @author: Group 3
 */
public class Receipt extends CustomerPayment{
    //Declarations
    private final static Pane canvasReceipt = new Pane();
    public Label labelAllInfo = new Label(toString());
    private static double totalPrice;
    
    //Methods
    /**
     * //Requires: This method will require the input parameters String title, ticketNumber and vehicleType to be Strings and not equal to
     * null. The parameter int hours and double price will also need to be an Integer and Double respectively.
     * //Effects: This method will display the Stage title. Also, this method will display the information (ticketNumber, hours, price, 
     * vehicleType) of the Ticket paid. The input from the Exit button will close the application.
     * @param title
     * @param ticketNumber
     * @param hours
     * @param price
     * @param vehicleType 
     * Uses the String title, ticketNumber, vehicleType, int hours, and double price parameters
     */
    public static void display(String title, String ticketNumber, int hours, double price, String vehicleType) {
        //Control objects declarations and assignments
        
        Stage customerReceipt = new Stage();
        
        customerReceipt.initModality(Modality.APPLICATION_MODAL);
        customerReceipt.setTitle(title);
        customerReceipt.setMinWidth(500);
        customerReceipt.setMinHeight(500);
        totalPrice = price;
        
        Label labelTicketPurchased = new Label("Receipt:");
        Label labelTicketNumber = new Label("Ticket Number: " + ticketNumber);
        Label labelVehicleType = new Label("Vehicle Type: " + vehicleType);
        Label labelTotalHours = new Label("Total Hours: " + hours);
        Label labelTotalPrice = new Label("Total Price: $" + price);
        Label labelPaidPrice = new Label("Paid Price: $" + totalPrice);
        Label labelPrintTicket = new Label("Please take picture \nif asked by attendant");
        
        Button buttonExit = new Button("Exit");
        canvasReceipt.setStyle("-fx-background-color: white");
        
        //Button click lambda events
        buttonExit.setOnAction(e -> {
            customerReceipt.close();
        });
        
        //VBox creation and node additions
        VBox vboxReceiptInfo = new VBox(10);
        vboxReceiptInfo.setPadding(new Insets(10, 10, 10, 10));
        
        vboxReceiptInfo.getChildren().addAll(labelTicketPurchased, labelTicketNumber, labelVehicleType, labelTotalHours, labelTotalPrice, labelPaidPrice, buttonExit, labelPrintTicket);
        
        //VBox addition to canvasReceipt
        canvasReceipt.getChildren().addAll(vboxReceiptInfo);
        
        //Scene assignment
        Scene customerReceiptScene = new Scene(canvasReceipt);
        customerReceipt.setScene(customerReceiptScene);
        customerReceipt.show();
    }
    
    /**
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
