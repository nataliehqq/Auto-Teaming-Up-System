package controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * Controller class for instructor_invalid_menu.fxml
 */

public class InstructorInvalidMenuController {
    /**
     * Stage for javafx
     */
    private Stage stage;
    /**
     * Scene for javafx
     */
	private Scene scene;
    /**
     * Parent for javafx
     */
	private Parent root;

    @FXML
    private Label DescriptionButton;

    @FXML
    private Label DescriptionButton1;

    @FXML
    private Label Title;

    @FXML
    private Button button1;

    /**
     * Return to main menu when button is clicked
     * @param event onclick
     * @throws IOException Catch IO exception
     */
    @FXML
    void buttonPressed(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/instructor_main_menu.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
    }

}