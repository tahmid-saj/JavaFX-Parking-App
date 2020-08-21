/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.S2020.project;

//Importations
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.Scanner;

/**
 * // OVERVIEW: This class contains no data types, The class contains methods to check if the file exists within the TicketFiles
 * folder and also creates and writes a file in the TicketFiles folder. This class is mutable.
 *
 * Design Pattern, Adapter: The adapter design pattern is present when the CustomerTicket class is able to use the instance variables of the 
 * ManagerView class through the CustomerEntry class. This pattern is shown as both classes ManagerView and CustomerFiles could 
 * communicate together, when otherwise wouldn't be able to if CustomerEntry did not exist, and 
 * values were not being entered into the files which both ManagerView and CustomerFiles classes use.
 * @author: Group 3
 */
public class CustomerFiles extends CustomerEntry{
    //Methods
    /**
     * //Effects: This method declares the variable readFile of type Scanner using the class ManagerView. This readFile variable is then
     * used to read any files from the folder TicketFiles. If any exceptions are caught, they will return that there exists a file. If
     * no exceptions are caught, the method will return false and the CustomerEntry can then create the ticket file.
     * @return 
     * boolean
     */
    public static boolean fileExistsChild() {
        try {
            ManagerView.readFile = new Scanner(new File("TicketFiles\\" + CustomerEntry.ticketNumber+".txt"));
        } catch (FileNotFoundException e) {
            return false;
        } catch (NullPointerException e) {
            return true;
        }
        return true;
    }
    
    /**
     * //Effects: This method creates a writeFile variable of type Formatter using the ManagerView class. This writeFile variable is then
     * used to create a file using the ticketNumber instance variable of CustomerEntry. If any exceptions are caught, the details will 
     * be displayed showing that the file was not created.
     */
    public static void createTicketFile() {
        try {
            ManagerView.writeFile = new Formatter("TicketFiles\\" + CustomerEntry.ticketNumber+".txt");
        } catch(Exception e) {
            System.out.println("Sorry, file was not created");
        }
    }
    
    /**
     * //Requires: This method requires the input parameters String floor, price, vehicleType, hours to be Strings and not equal to null.
     * //Effects: This method uses the writeFile variable of class ManagerView to format the file using the input parameters String floor,
     * price, vehicleType, hours. If any successful deletion did not occur, an exception is thrown which will delete the writeFile variable.
     * @param floor
     * @param price
     * @param vehicleType
     * @param hours 
     * Uses String floor, price, vehicleType, hours parameters
     */
    public static void fileWriteTicket(String floor, String price, String vehicleType, String hours) {
        try {
            ManagerView.writeFile.format("%s%s%s%s", floor, price, vehicleType, hours);
            ManagerView.writeFile.close();
        } catch (NullPointerException e) {
            ManagerView.fileDelete = new File("TicketFiles\\" + CustomerEntry.ticketNumber+".txt");
            ManagerView.writeFile.close();
            
            if (ManagerView.fileDelete.delete()) {
                System.out.println("File " + CustomerEntry.ticketNumber+".txt" + " is deleted, deletion successful");
            }    
        }
    }
}
