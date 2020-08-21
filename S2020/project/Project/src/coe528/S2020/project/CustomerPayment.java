/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.S2020.project;

//Importations
import static java.lang.String.valueOf;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * // OVERVIEW: This class holds no data types or instance variables. The class displays the payment scene where the customer is 
 * able to select payment types, credit/cash and payment methods, pay at info portal/pay at exit panel/pay at attendant. This 
 * class is also mutable.
 * @author: Group 3
 */
public class CustomerPayment extends CustomerTicket{
    //Declarations
    private final static BorderPane canvasPayment = new BorderPane();
    
    //Methods
    /**
     * //Requires: This method requires the input parameters String title, ticketNumber, vehicleType to be Strings and not equal to null.
     * The parameter int hours and double price will also need to be an Integer and Double respectively.
     * //Effects: This method will display the Stage title. Also, this method will allow the options of choosing Cash/Credit Payment 
     * Types and Payment Methods by Info Portal, Attendants, and Exit Panel. The Pay Ticket button will accept input from 
     * textPaymentType and proceed to display the receipt. The stage will then be changed to Stage customerReceipt of class Receipt 
     * from Stage customerPayment.
     * @param title
     * @param ticketNumber
     * @param hours
     * @param price
     * @param vehicleType
     * Uses String title, ticketNumber, vehicleType, int hours, and double price parameters
     */
    public static void receipt(String title, String ticketNumber, int hours, double price, String vehicleType) {
        //Control objects declarations and assignments
        
        Stage customerPaymentMethod = new Stage();
        
        customerPaymentMethod.initModality(Modality.APPLICATION_MODAL);
        customerPaymentMethod.setTitle(title);
        customerPaymentMethod.setMinWidth(600);
        customerPaymentMethod.setMinHeight(600);
        
        Label labelTicketNumber = new Label("Your Ticket Number: " + ticketNumber);
        Label labelTotalHours = new Label("Your Total Hours: " + hours);
        Label labelTotalPrice = new Label("Your Total Price: $" + price);
        Label labelPaymentType = new Label("Choose Payment Type: ");
        Label labelPaymentMethod = new Label("Choose Payment Method: ");
        Label labelEnterAmount = new Label("Enter Amount: $");
        final Text textWrongAmount = new Text();
        textWrongAmount.setFill(Color.FIREBRICK);
        
        TextField textPaymentType = new TextField();
        
        Button buttonPayTicket = new Button("Pay Ticket");
        
        ChoiceBox<String> choicePaymentType = new ChoiceBox<>();
        ChoiceBox<String> choicePaymentMethod = new ChoiceBox<>();
        
        choicePaymentType.getItems().addAll("Cash", "Credit");
        choicePaymentMethod.getItems().addAll("Pay Attendant", "Pay Info Portal", "Pay Exit Panel");
        
        Image imageSix = new Image("file:backgroundpayment.jpg");
        ImageView ivSix = new ImageView(imageSix);
        Image imageSeven = new Image("file:thankyouimage.jpg");
        ImageView ivSeven = new ImageView(imageSeven);

        //Event handlers for choicePaymentType
        choicePaymentType.getSelectionModel().selectedItemProperty().addListener( (v, oldValue, newValue) -> {
            if (choicePaymentType.getValue().equals("Cash"))
                textPaymentType.setText("Enter Cash Amount");
            if (choicePaymentType.getValue().equals("Credit"))
                textPaymentType.setText("Enter Credit Amount");
        });
        
        //Button click lambda event
        buttonPayTicket.setOnAction(e -> {
            try {
                textWrongAmount.setText("");
                if (choicePaymentMethod.getValue().equals("Pay Attendant") || choicePaymentMethod.getValue().equals("Pay Info Portal") || choicePaymentMethod.getValue().equals("Pay Exit Panel")) {
                    if (choicePaymentType.getValue().equals("Cash") || choicePaymentType.getValue().equals("Credit")) {
                        if (textPaymentType.getText().equals(valueOf(price))) {
                            customerPaymentMethod.close();
                            Receipt.display("ParKing - Customer's Receipt", ticketNumber, hours, price, vehicleType); 
                        } else {
                        textWrongAmount.setText("Please enter right amount.");
                        }
                    }
                }
                textWrongAmount.setText("Please enter right amount.");
            } catch (NullPointerException ex) {
                textWrongAmount.setText("Please choose payment type, method and enter amount");
            }
            
        });
        
        //Creation of GridPane
        GridPane gridPayment = new GridPane();
        gridPayment.setAlignment(Pos.TOP_CENTER);
        gridPayment.setHgap(30);
        gridPayment.setVgap(10);
        gridPayment.setPadding(new Insets(20, 20, 20, 20));
        
        //Node additions to GridPane
        gridPayment.add(labelTicketNumber, 0, 0);
        gridPayment.add(labelTotalHours, 0, 1);
        gridPayment.add(labelTotalPrice, 0, 2);
        gridPayment.add(textWrongAmount, 0, 4, 2, 1);
        
        gridPayment.add(labelPaymentType, 1, 0);
        gridPayment.add(choicePaymentType, 1, 1);
        gridPayment.add(labelPaymentMethod, 1, 2);
        gridPayment.add(choicePaymentMethod, 1, 3);
        
        gridPayment.add(labelEnterAmount, 2, 0);
        gridPayment.add(textPaymentType, 2, 1);
        gridPayment.add(buttonPayTicket, 2, 4);
        
        //GridPane and Image additions to canvasPayment
        canvasPayment.getChildren().addAll(ivSix);
        canvasPayment.setCenter(ivSeven);
        canvasPayment.setTop(gridPayment);
        gridPayment.setGridLinesVisible(false);
        
        //Scene assignment
        Scene customerPaymentScene = new Scene(canvasPayment);
        customerPaymentMethod.setScene(customerPaymentScene);
        customerPaymentMethod.show();
    }
}
