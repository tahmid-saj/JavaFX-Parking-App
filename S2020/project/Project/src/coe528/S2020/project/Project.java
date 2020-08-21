/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * Course: COE 528 Project
 * Group Number: 3
 * Names: Tahmid Sajin, Vincent To, Sameh Ahmed, Jaspreet Sahota
 */
package coe528.S2020.project;

//Importations
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * // OVERVIEW: There are no data types found in this class. Only controls of type Button, Text, Label, BorderPane, GridPane, Scene, 
 * Stage hold. The class will function to display the starting scene, introScene and the managerLoginScene. It will also open the 
 * ManagerView and CustomerFile classes respective Stages. This class is mutable.
 * @author: Group 3
 */
public class Project extends Application {
    //Declarations
    Button buttonCustomer;
    Button buttonManager;
    Button buttonLogin;
    public static Scene managerLoginScene, introScene;
    public static Stage primaryStage = new Stage();
    private BorderPane canvas = new BorderPane();
    private BorderPane canvasManager = new BorderPane();
    
    //Methods
    /**
     * //Requires: The Stage primaryStage will require the import, import javafx.stage.Stage.
     * //Modifies: This method will change the title to ParKing, alter minWidth, alter minHeight, set resizable property to false
     * and show the corresponding scene of Stage primaryStage, either introScene or managerLoginScene. 
     * //Effects:  This method displays the necessary windows depending on if the user clicks they're a manager or customer.
     * If the input is manager, it will display the managerLogin scene where the method accepts inputs of textUsername, textPassword 
     * and textRole to allow the manager to login using the buttonLogin, where the Stage primaryStage will be modified to Stage 
     * managerView of class ManagerView and pass the Stage title parameter, ParKing - Manager View. If the input is customer, the 
     * method will modify Stage primaryStage to Stage customerEntry of class CustomerEntry, while passing the Stage title parameter, 
     * ParKing - Customer Entrance Panel.
     * @param primaryStage 
     * Uses the parameter Stage primaryStage from main method
     */
    @Override
    public void start(Stage primaryStage) {
        //Starting Screen Scene:
        
        //Declarations and assignments of control objects
        Label labelQuestion = new Label("Are you a: ");
        Label labelOr1 = new Label("or");
        Label labelOr2 = new Label("?");
        Label labelSocialDistance = new Label("Please social distance");
        
        Image imageOne = new Image("file:backgroundlength.jpg");
        ImageView ivOne = new ImageView(imageOne);
        Image imageSocialDistance = new Image("file:ParKing.jpg");
        ImageView ivSocialDistance = new ImageView(imageSocialDistance);
        
        labelQuestion.setFont(Font.font("Tahoma", FontWeight.SEMI_BOLD, 20));
        labelOr1.setFont(Font.font("Tahoma", FontWeight.SEMI_BOLD, 20));
        labelOr2.setFont(Font.font("Tahoma", FontWeight.SEMI_BOLD, 20));
        
        buttonCustomer = new Button("Customer");
        buttonManager = new Button("Manager");
        
        //Creation of a GridPane
        GridPane gridIntro = new GridPane();
        gridIntro.setAlignment(Pos.CENTER);
        gridIntro.setHgap(50);
        gridIntro.setVgap(20);
        gridIntro.setPadding(new Insets(25, 25, 25, 25));
        
        //Node addition into GridPane
        gridIntro.add(labelQuestion, 0, 0, 3, 1);
        gridIntro.add(buttonCustomer, 0, 2, 2, 1);
        gridIntro.add(labelOr1, 2, 2);
        gridIntro.add(buttonManager, 3, 2, 2, 1);
        gridIntro.add(labelOr2, 5, 2);
        gridIntro.add(labelSocialDistance, 0, 3, 3, 1);
        
        gridIntro.setGridLinesVisible(false);
        
        //GridPane and image addition into canvas
        canvas.getChildren().add(ivOne);
        canvas.setCenter(ivSocialDistance);
        canvas.setTop(gridIntro);
        
        //Scene assignment
        introScene = new Scene(canvas);
        
        //Button click lambda events
        buttonCustomer.setOnAction(e -> {
            primaryStage.close();
            CustomerEntry.display("ParKing - Customer Entrance Panel");
        });
        
        buttonManager.setOnAction(e -> {
            primaryStage.setScene(managerLoginScene);
        });
        
        //Manager Login Scene:
        
        //Declarations of control objects
        Label labelSceneTitle = new Label("Manager Login");
        Label labelUsername = new Label("Username: ");
        Label labelPassword = new Label("Password: ");
        Label labelRole = new Label("Role: ");
        final Text textIncorrect = new Text();
        
        Image imageTwo = new Image("file:backgroundwidth.jpg");
        ImageView ivTwo = new ImageView(imageTwo);
        
        labelSceneTitle.setFont(Font.font("Tahoma", FontWeight.SEMI_BOLD, 20));
        labelUsername.setFont(Font.font("Tahoma", FontWeight.SEMI_BOLD, 20));
        labelPassword.setFont(Font.font("Tahoma", FontWeight.SEMI_BOLD, 20));
        labelRole.setFont(Font.font("Tahoma", FontWeight.SEMI_BOLD, 20));
        textIncorrect.setFill(Color.FIREBRICK);
        
        TextField textUsername = new TextField("Username");
        PasswordField textPassword = new PasswordField();
        TextField textRole = new TextField("Role");
        
        buttonLogin = new Button("Login");
        
        //Button click lambda events
        buttonLogin.setOnAction(e -> {
            if (textUsername.getText().equals("admin") && textPassword.getText().equals("admin") && textRole.getText().equals("manager")) {
                primaryStage.close();
                ManagerView.display("ParKing - Manager View");
            } else {
                textIncorrect.setText("Please enter the correct information");
            }
        });
        
        //Creation of GridPane
        GridPane gridManagerLogin = new GridPane();
        gridManagerLogin.setAlignment(Pos.CENTER);
        gridManagerLogin.setHgap(50);
        gridManagerLogin.setVgap(50);
        gridManagerLogin.setPadding(new Insets(25, 25, 25, 25));
        
        //Node addition into GridPane
        gridManagerLogin.add(labelSceneTitle, 0, 0, 3, 1);
        gridManagerLogin.add(labelUsername, 0, 1, 2, 1);
        gridManagerLogin.add(textUsername, 2, 1);
        gridManagerLogin.add(labelPassword, 0, 2, 2, 1);
        gridManagerLogin.add(textPassword, 2, 2);
        gridManagerLogin.add(labelRole, 0, 3, 2, 1);
        gridManagerLogin.add(textRole, 2, 3);
        gridManagerLogin.add(buttonLogin, 2, 4);
        gridManagerLogin.add(textIncorrect, 0, 5, 3, 1);
        
        gridManagerLogin.setGridLinesVisible(false);
        
        //GridPane and Image addition into canvasManager
        canvasManager.getChildren().addAll(ivTwo);
        canvasManager.setCenter(gridManagerLogin);
        
        //Scene assignment
        managerLoginScene = new Scene(canvasManager, 600, 600);
        
        //Setting the primaryStage
        primaryStage.setTitle("ParKing");
        primaryStage.setScene(introScene);
        primaryStage.setMinWidth(600);
        primaryStage.setMinHeight(600);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * //Effects: The main method will launch the JavaFX application using the input String[] args
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
