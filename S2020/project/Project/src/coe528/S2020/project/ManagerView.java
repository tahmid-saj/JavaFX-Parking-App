/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.S2020.project;

//Importations
import static coe528.S2020.project.CustomerEntry.floor;
import static coe528.S2020.project.CustomerEntry.hours;
import static coe528.S2020.project.CustomerEntry.price;
import static coe528.S2020.project.CustomerEntry.vehicleType;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.util.*;
import java.lang.*;
import java.io.*;
import static java.lang.String.valueOf;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

/**
 * // OVERVIEW: The data types in this class are of boolean fileCreated, modifyClicked String floor, hoursUsed, totalPrice, vehicleType.
 * The data types File file, fileDelete, fileModifyDelete, fileRename, and Scanner readFile, Formatter writeFile are also associated
 * with this class. This class operates to perform the manager tasks from searching for information about a specific file, adding a 
 * file, updating, modifying, and deleting a file, as well as showing all the files in an ObservableList. This class is mutable.
 *
 * Design Pattern, Facade: The facade design pattern of the ManagerView class allows a combined interface which is then separated into
 * distinct interfaces, very much different but still performs methods required for the ManagerView class. For example, the CustomerEntry
 * class must create the ticket files in the folder through its customer input for the ManagerFiles class to display all the files in the 
 * folder. This allows the ManagerView class to perform efficiently with two classes handling different tasks.
 * @author: Group 3
 */
public class ManagerView{
    //Declarations
    private final static BorderPane canvas = new BorderPane();
    
    public static Formatter writeFile; 
    public static Scanner readFile;
    public static File file, fileRename, fileDelete, fileModifyDelete;
    
    public static boolean modifyClicked;
    public static boolean fileCreated = false;
    
    public final static Text textError = new Text();
    public static TextField textSearch;
    public static TextField textTicketNumber;
    public static TextField textNewPrice;
    public static ChoiceBox<String> choiceVehicleType;
    public static ChoiceBox<String> choiceTicketHours;
    public static ChoiceBox<String> choiceFloor;
    
    public static Label labelTicketNumber;
    public static Label labelTicketPrice;
    public static Label labelVehicleType;
    public static Label labelTicketHours;
    public static Label labelFloor;
    
    public static String floor;
    public static String totalPrice;
    public static String vehicleType;
    public static String hoursUsed;
    
    //Methods
    /**
     * //Requires: The input parameter, String title will need to be a String value, and not equal to null.
     * //Effects: If the input is the Back To Start Window button, the Stage managerView will change to the Stage primaryStage of 
     * the Project class, and close the Stage managerView. If the input is the Logout button, the Stage managerView will close.
     * If the input is the Search button, the method will search and display the file information from textSearch, the ticket number
     * following the appropriate exceptions. If the input is the Add Ticket button, the method will add a file with its name as the
     * ticket number entered in textTicketNumber, and information entered in textNewPrice, choiceFloor, choiceVehicleType, and
     * choiceHours following the appropriate exceptions. If the input is the Update Ticket button, the method will update the file
     * name in textSearch with the file name entered in textTicketNumber following the appropriate exceptions. If the input is the 
     * Modify button, the method will modify contents in the textSearch file with the information entered in textNewPrice, choiceFloor,
     * choiceVehicleType, and choiceHours following the appropriate exceptions. If the input is the Delete button, the method will
     * delete the file in textSearch following the appropriate exceptions. If the input is the Show All Files button, the method will 
     * open Stage filesView of class ManagerFiles and display a table view controller of all the files and relevant information.
     * @param title 
     * Uses the String title parameter
     */
    public static void display(String title) {
        //Control object declarations and assignments
        
        Stage managerView = new Stage();
        
        managerView.initModality(Modality.APPLICATION_MODAL);
        managerView.setTitle(title);
        managerView.setMaximized(true);
        
        labelTicketNumber = new Label("Ticket Number: ");
        labelTicketPrice = new Label("Ticket Price: ");
        labelVehicleType  = new Label("Vehicle Type: ");
        labelTicketHours = new Label("Hours Used: ");
        labelFloor = new Label("Floor: ");
        textError.setFill(Color.FIREBRICK);
        
        Label labelNewTicket = new Label("New Ticket: ");
        Label labelSearch = new Label("Search: ");
        Label labelNewPrice = new Label("New Price:");
        Label labelModifyVehicle = new Label("Vehicle Type:");
        Label labelEnterHours = new Label("Enter Hours:");
        Label labelEnterFloor = new Label("Enter Floor");
        Label labelInstructions = new Label("Welcome Manager! Please see below for the instructions on each button."
                + "\n-Show All Files button: Displays a window showing all the files and relevant information."
                + "\n-Search button: The search button searches for a ticket file with the ticket number entered in the search and shows the information."
                + "\n-Add Ticket button: The add ticket button adds a new ticket with the ticket number, price, vehicle type, hours, floor entered on the right."
                + "\n-Update Ticket button: This button updates the existing searched ticket file name with a new entered ticket number on the right."
                + "\n-Modify Ticket button: This button modifies/creates the searched ticket file with the entered price, vehicle type, hours, floor on the right."
                + "\n-Delete Ticket button: This button deletes the searched ticket file");
        
        Image imageThree = new Image("file:backgroundwidth.jpg");
        ImageView ivThree = new ImageView(imageThree);
        
        textTicketNumber = new TextField("Ticket Number");
        textSearch = new TextField("Search");
        textNewPrice = new TextField("New Price");
        
        choiceVehicleType = new ChoiceBox<>();
        choiceVehicleType.getItems().addAll("Compact", "Truck", "Van", "Handicapped", "Motorcycle", "Electric");
        choiceTicketHours = new ChoiceBox<>();
        choiceTicketHours.getItems().addAll("1","2", "3", "4", "5", "6", "7", "8");
        choiceFloor = new ChoiceBox<>();
        choiceFloor.getItems().addAll("1","2", "3", "4", "5", "6", "7");
        
        Button buttonSearch = new Button("Search Ticket");
        Button buttonAddTicket = new Button("Add Ticket");
        Button buttonUpdateTicket = new Button("Update Ticket");
        Button buttonModifyTicket = new Button("Modify Ticket");
        Button buttonDeleteTicket = new Button("Delete Ticket");
        Button buttonLogout = new Button("Logout");
        Button buttonBack = new Button("Back To Start Window");
        Button buttonShowAllFiles = new Button("Show All Files");
        
        //Button click lambda events
        buttonBack.setOnAction(e -> {
            managerView.close();
            Project.primaryStage.setScene(Project.introScene);
            Project.primaryStage.show();
            Project.primaryStage.setTitle("ParKing");
        });
        
        buttonLogout.setOnAction(e -> {
            managerView.close();
        });
        
        buttonSearch.setOnAction(e -> {
            fileSearching();
            fileReading();
            fileClosing();
        });
        
        buttonAddTicket.setOnAction(e -> {
            fileCreated = false;
            if (fileExists() == false) {
                fileCreate();
                if (fileCreated)
                    fileWrite(choiceFloor.getValue(), textNewPrice.getText(), choiceVehicleType.getValue(), choiceTicketHours.getValue());
            }
        });
        
        buttonUpdateTicket.setOnAction(e -> {
            textError.setText("");
            
            try {
                file = new File("TicketFiles\\" + textSearch.getText()+".txt");
                fileRename = new File("TicketFiles\\" + textTicketNumber.getText()+".txt");
                
                if (textTicketNumber.getText().length() != 10) {
                    throw new IllegalArgumentException();
                } else {
                    for (char digit: textTicketNumber.getText().toCharArray()) {
                        int textNumberDigits = Integer.parseInt(valueOf(digit));
                    }
                } 
                
                if (file.renameTo(fileRename)) {
                    System.out.println("File " + textSearch.getText()+".txt is renamed to " + textTicketNumber.getText()+".txt");
                }
            } catch (NullPointerException ex) {
                textError.setText("Please enter searched and updating ticket name");
            } catch (IllegalArgumentException ex) {
                textError.setText("Please enter a 10 digit ticket number");
            }
        });
        
        buttonModifyTicket.setOnAction(e -> {
            fileSearching();
            try {
                fileRemoveInfo();
            } catch (IOException ex) {
                System.out.println("Unable to remove contents in file");
            }
            
            try {
                writeFile = new Formatter("TicketFiles\\" + textSearch.getText()+".txt");
            } catch (FileNotFoundException ex) {
                System.out.println("Unable to modify contents in file");
            }
            modifyClicked = true;
            fileWrite(choiceFloor.getValue(), textNewPrice.getText(), choiceVehicleType.getValue(), choiceTicketHours.getValue());
            
            try {
                readFile.close();
                writeFile.close(); 
            } catch (NullPointerException ex) {
                textError.setText("Please search a ticket number");
            }
            modifyClicked = false;
        });
        
        buttonDeleteTicket.setOnAction(e -> {
            textError.setText("");
            file = new File("TicketFiles\\" + textSearch.getText()+".txt");
            
            if (file.delete()) {
                System.out.println("File " + textSearch.getText()+".txt" + " is deleted, deletion successful");
            } else {
                textError.setText("Sorry, couldn't find file to delete");
            }
        });
        
        buttonShowAllFiles.setOnAction(e -> {
            try {
                ManagerFiles.showManagerFiles("ParKing - Manager Files");
            } catch (IllegalArgumentException ex) {
                System.out.println("Already viewed all files");
            } 
        });
        
        //Creation of GridPane
        GridPane gridManagerView = new GridPane();
        gridManagerView.setAlignment(Pos.CENTER);
        gridManagerView.setHgap(35);
        gridManagerView.setVgap(35);
        gridManagerView.setPadding(new Insets(25, 25, 25, 25));
        
        //Node additions to GridPane
        gridManagerView.add(labelSearch, 0, 0);
        gridManagerView.add(textSearch, 1, 0);
        gridManagerView.add(buttonSearch, 1, 1);
        
        gridManagerView.add(labelTicketNumber, 0, 1);
        gridManagerView.add(labelTicketPrice, 0, 2);
        gridManagerView.add(labelVehicleType, 0, 3);
        gridManagerView.add(labelTicketHours, 0, 4);
        gridManagerView.add(labelFloor, 0, 5);
        gridManagerView.add(textError, 0, 6, 2, 1);
        gridManagerView.add(labelInstructions, 0, 7, 6, 1);
        
        gridManagerView.add(labelNewTicket, 4, 0);
        gridManagerView.add(labelNewPrice, 4, 1);
        gridManagerView.add(labelModifyVehicle, 4, 2);
        gridManagerView.add(labelEnterHours, 4, 3);
        gridManagerView.add(labelEnterFloor, 4, 4);
        gridManagerView.add(textTicketNumber, 5, 0);
        gridManagerView.add(textNewPrice, 5, 1);
        gridManagerView.add(choiceVehicleType, 5, 2);
        gridManagerView.add(choiceTicketHours, 5, 3);
        gridManagerView.add(choiceFloor, 5, 4);
        
        gridManagerView.add(buttonAddTicket, 6, 0);
        gridManagerView.add(buttonUpdateTicket, 6, 1);
        gridManagerView.add(buttonModifyTicket, 6, 2);
        gridManagerView.add(buttonDeleteTicket, 6, 3);
        gridManagerView.add(buttonShowAllFiles, 6, 4);
        
        gridManagerView.setHalignment(buttonSearch, HPos.CENTER);
        gridManagerView.setHalignment(buttonAddTicket, HPos.CENTER);
        gridManagerView.setHalignment(buttonUpdateTicket, HPos.CENTER);
        gridManagerView.setHalignment(buttonModifyTicket, HPos.CENTER);
        gridManagerView.setHalignment(buttonDeleteTicket, HPos.CENTER);
        
        //GridPane, Buttons and Image additions to canvas
        canvas.getChildren().add(ivThree);
        canvas.setLeft(buttonBack);
        canvas.setRight(buttonLogout);
        canvas.setCenter(gridManagerView);
        gridManagerView.setGridLinesVisible(false);
        
        //Scene assignment
        Scene managerViewScene = new Scene(canvas);
        managerView.setScene(managerViewScene);
        managerView.show();
    }
    
    /**
     * //Effects:  This method will set the text of textError to "" and create a new File, readFile of Scanner, with the name entered in
     * textSearch, and search it in the TicketFiles folder. If the appropriate exceptions are produced, details are provided to the
     * textError's text property.
     */
    public static void fileSearching() {
        try {
            textError.setText("");
            readFile = new Scanner(new File("TicketFiles\\" + textSearch.getText()+".txt"));
        } catch (FileNotFoundException | NullPointerException e) {
            textError.setText("Sorry, could not find file " + textSearch.getText()+".txt");
        }
    }
    
    /**
     * //Effects:  This method loops through the readFile declared in fileSearching() and assigns the contents of the file to int floor,
     * double totalPrice, String vehicleType, int hoursUsed. This information is then displayed in the corresponding label controls.
     * If a NullPointerException is produced, the details are provided in textError.
     */
    public static void fileReading() {
        try {
            while(readFile.hasNext()) {
                floor = readFile.next();
                totalPrice = readFile.next();
                vehicleType = readFile.next();
                hoursUsed = readFile.next();

                labelTicketNumber.setText("Ticket Number: " + textSearch.getText());
                labelTicketPrice.setText("Ticket Price: " + totalPrice);
                labelVehicleType.setText("Vehicle Type : " + vehicleType);
                labelTicketHours.setText("Hours Used: " + hoursUsed);
                labelFloor.setText("Floor: " + floor);
            }    
        } catch (NullPointerException | IllegalStateException e) {
            textError.setText("Sorry, could not find file " + textSearch.getText()+".txt");
        }
    }
    
    /**
     * //Effects:  This method will set the text of textError to "" and create a new File, readFile of Scanner, with the name entered in
     * textSearch, and search it in the TicketFiles folder. If the file, readFile exists the method will return true, otherwise false 
     * and an exception will be thrown and show the details in textError.
     * @return
     * boolean
     */
    public static boolean fileExists() {
        try {
            textError.setText("");
            readFile = new Scanner(new File("TicketFiles\\" + textTicketNumber.getText()+".txt"));
        } catch (FileNotFoundException e) {
            return false;
        } catch (NullPointerException e) {
            textError.setText("Please enter a file name");
            return true;
        }
        textError.setText("Sorry, file " + textTicketNumber.getText()+".txt exists.");
        return true;
    }
    
    /**
     * //Effects:  This method will prompt the user for a 10 digit ticket number in textTicketNumber and declare the Formatter, writeFile
     * in the TicketFiles folder, then fileCreated will be set to true. If any exception is thrown, the details are shown in textError.
     */
    public static void fileCreate() {
        try {
            if (textTicketNumber.getText().length() != 10) {
                throw new Exception();
            } else {
                for (char digit: textTicketNumber.getText().toCharArray()) {
                    int textNumberDigits = Integer.parseInt(valueOf(digit));
                }
            } 
            
            writeFile = new Formatter("TicketFiles\\" + textTicketNumber.getText()+".txt");
            fileCreated = true;
            System.out.println("New file created: " + textTicketNumber.getText()+".txt");
        } catch(Exception e) {
            textError.setText("Sorry, enter all floor, price, vehicle, hours details\nand a 10 digit ticket number");
        }
    }
    
    /**
     * //Requires: The input parameters, String floor, String price, String vehicle, String hours will all need to be String value types
     * and not null, otherwise an exception will be thrown.
     * //Effects: This method will write the String, floor, price, vehicle, hours to the file using the formatter writeFile created 
     * earlier, then the method closes writeFile and catches any NullPointerExceptions.
     * @param floor
     * @param price
     * @param vehicle
     * @param hours 
     * Uses the String floor, price, vehicle, hours parameters
     */
    public static void fileWrite(String floor, String price, String vehicle, String hours) {
        try {
            if (floor.equals(null) || price.equals(null) || vehicle.equals(null) || hours.equals(null)) {
                throw new NullPointerException();
            }
            writeFile.format("%s%s%s%s", floor + " ", price + " ", vehicle + " ", hours);
            writeFile.close();
        } catch (NullPointerException e) {
            textError.setText("Please enter all floor, price, vehicle, hours details");
            fileDelete = new File("TicketFiles\\" + textTicketNumber.getText()+".txt");
            writeFile.close();
            
            if (modifyClicked == true) {
                fileModifyDelete = new File("TicketFiles\\" + textSearch.getText()+".txt");
                if (fileModifyDelete.delete()) {
                    System.out.println("File " + textSearch.getText()+".txt" + " is deleted, deletion successful");
                } 
            }
            
            if (fileDelete.delete()) {
                System.out.println("File " + textTicketNumber.getText()+".txt" + " is deleted, deletion successful");
            }    
        }
    }
    
    /**
     * //Effects:  This method will create a new FileWriter in the TicketFiles folder with the ticket number entered intextSearch. This
     * FileWriter is then used to remove all contents in the file and close it. This is used to modify the file contents. The method 
     * also throws an IOException if the file's information cannot be removed.
     */
    public static void fileRemoveInfo() throws IOException {
        new FileWriter("TicketFiles\\" + textSearch.getText()+".txt", false).close();
    }
    
    /**
     * //Effects:  This method will close the readFile declared and catch any NullPointerException produced from deleting the file.
     */
    public static void fileClosing() {
        try {
            readFile.close();
        } catch (NullPointerException e) {
            textError.setText("Sorry, could not find file " + textSearch.getText()+".txt");
        }
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
