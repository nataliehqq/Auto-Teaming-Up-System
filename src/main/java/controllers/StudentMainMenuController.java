package controllers;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;

import helpers.Output;

/**
 * Controller class for student_main_menu.fxml
 */

public class StudentMainMenuController {
    private Stage stage;
	private Scene scene;
	private Parent root;

    @FXML
    private Label Description1;

    @FXML
    private Label Description11;

    @FXML
    private Label Title;

    @FXML
    private Button returnButton1;

    @FXML
    private Button searchButton;

    @FXML
    private Label warningMessage;

    @FXML
    private TextField studentID;

    @FXML
    private TextField studentName;

    /**
     * Back to role selecting scene
     * @param event button pressed
     * @throws IOException catch IO exception
     */
    @FXML
    void switchToStartScene(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("/start.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
    }
    /**
     * Switch to student inquire scene
     * @param event button on click
     * @throws IOException capture IO exception
     */
    @FXML
    void switchToTeamResultScene(ActionEvent event) throws IOException{
        String s_itsc = studentName.getText();
        String s_id = studentID.getText();
        
        //If wrong format
        if (!Output.formatValidation(s_itsc, s_id)){
            warningMessage.setVisible(true);
            return;
        }
        //If student does not exist
        String[] tm = Output.studentExist(s_itsc, s_id, InstructorMainMenuController.students);
        if (tm[0] == "-1"){
            warningMessage.setVisible(true);
            System.out.println("Does NOT EXIST");
            return;
        }
        //Validated all the information
        //Now retrieve information from teaming result
        //jump to searching screen
        Output.initInfo(s_itsc, s_id, tm[0], tm[1], InstructorMainMenuController.teams);
		root = FXMLLoader.load(getClass().getResource("/student_running.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
    }
}
