/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.S2020.project;

//Importations
import java.io.File;
import java.io.FileNotFoundException;
import static java.lang.String.valueOf;
import java.util.Scanner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * // OVERVIEW: The data types of this class only have the Scanner readTicketFile. The class operates to display the table view 
 * containing the ticketNumber, floor, price, vehicleType and hours information for all files. This class is mutable.
 *
 * Design Pattern, Builder: The ManagerFiles class is the representation of the more complex object of type TicketFiles which is responsible for the 
 * construction of several TicketFiles objects containing the String ticketNumber, floor, price, vehicleType, hours instance
 * variables obtained from all the files in the folder TicketFiles.
 * @author: Group 3
 */
public class ManagerFiles extends ManagerView{
    //Declarations
    public static TableView<TicketFiles> table;
    public static Scanner readTicketFile;
    private static Label labelFileError;
    
    //Methods
    /**
     * //Requires: This method requires the input parameter String title to be a String and not equal to null.
     * //Effects:  This method displays a table view with rows and columns of all file information, with the ticketNumber, floor, price,
     * vehicleType and hours obtained from class TicketFiles. The columns and rows of the table view control are modified to display
     * this information for each file which passes through a loop in the getTicket() method. The input Close button will close the Stage
     * filesView and display the Stage managerView.
     * @param title 
     * Uses the String title parameter
     */
    public static void showManagerFiles(String title) {
        //Control objects declarations and assignments
        
        Stage filesView = new Stage();
        
        filesView.initModality(Modality.APPLICATION_MODAL);
        filesView.setTitle(title);
        
        labelFileError = new Label("");
        
        Button buttonClose = new Button("Close Window");
        
        //TableView assignments for columns/rows
        //Ticket Number column
        TableColumn<TicketFiles, String> ticketNumberColumn = new TableColumn<>("Ticket Number");
        ticketNumberColumn.setMinWidth(200);
        ticketNumberColumn.setCellValueFactory(new PropertyValueFactory<>("ticketNumber"));
        
        //Floor column
        TableColumn<TicketFiles, Integer> floorColumn = new TableColumn<>("Floor");
        floorColumn.setMinWidth(200);
        floorColumn.setCellValueFactory(new PropertyValueFactory<>("floor"));
        
        //Price column
        TableColumn<TicketFiles, Double> priceColumn = new TableColumn<>("Price");
        priceColumn.setMinWidth(200);
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        //Vehicle Type column
        TableColumn<TicketFiles, String> vehicleTypeColumn = new TableColumn<>("Vehicle Type");
        vehicleTypeColumn.setMinWidth(200);
        vehicleTypeColumn.setCellValueFactory(new PropertyValueFactory<>("vehicleType"));
        
        //Hours column
        TableColumn<TicketFiles, Integer> hoursColumn = new TableColumn<>("Hours");
        hoursColumn.setMinWidth(200);
        hoursColumn.setCellValueFactory(new PropertyValueFactory<>("hours"));
        
        //Column and rows addition to TableView
        table = new TableView<>();
        table.setItems(getTicket());
        table.getColumns().addAll(ticketNumberColumn, floorColumn, priceColumn, vehicleTypeColumn, hoursColumn);
        
        //TableView, Button, Label addition to TableView
        VBox vboxTable = new VBox();
        vboxTable.getChildren().addAll(table, buttonClose, labelFileError);
        
        //Button click lambda event
        buttonClose.setOnAction(e -> {
            filesView.close();
        });
        
        //Scene assignment
        Scene scene = new Scene(vboxTable);
        filesView.setScene(scene);
        filesView.showAndWait();
    } 
    
    /**
     * //Effects: This method will declare the ObservableList of the table view, and loop through each file of the TicketFiles folder
     * then add values to the ticketFiles variable of type ObservableList. the values of the file are recorded in floor, totalPrice, 
     * vehiclePrice, and hoursUsed. Each File readTicketFile will then be closed and the method then catches any exceptions produced.
     * The method will then return the ticketFiles variable of type ObservableList.
     * @return
     * Returns ticketFiles
     */
    public static ObservableList<TicketFiles> getTicket() {
        ObservableList<TicketFiles> ticketFiles = FXCollections.observableArrayList();
        
        try {
            String floor = "";
            String totalPrice = "";
            String vehicleType = "";
            String hoursUsed = "";

            File file = new File("TicketFiles\\");
            File[] fileArray = file.listFiles();

            for (File f:fileArray) {
                readTicketFile = new Scanner(new File(valueOf(f)));

                while(readTicketFile.hasNext()) {
                    floor = readTicketFile.next();
                    totalPrice = readTicketFile.next();
                    vehicleType = readTicketFile.next();
                    hoursUsed = readTicketFile.next();
                }  

                ticketFiles.add(new TicketFiles(valueOf(f).replace("TicketFiles\\",""), floor, totalPrice, vehicleType, hoursUsed));

                readTicketFile.close();
            }
        } catch (FileNotFoundException | NullPointerException ex) {
            labelFileError.setText("Sorry one or more files were not found");
            readTicketFile.close();
        }
        
        return ticketFiles;
    }
}
