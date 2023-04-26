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
 * Controller class for student_invalid_menu
 */

public class StudentInvalidMenuController {

    private Stage stage;
	private Scene scene;
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
     * Used to handle button on click action
     * It will return to the role selecting scene
     * @param event button onlick
     * @throws IOException catch the exception
     */
    @FXML
    void buttonPressed(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/start.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
    }

}