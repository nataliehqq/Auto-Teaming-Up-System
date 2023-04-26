package main;

import java.io.IOException;

import controllers.InstructorMainMenuController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;

import helpers.Process;

/**
 * Controller for the first starting scene
 */
public class StartSceneController {
	private Stage stage;
	private Scene scene;
	private Parent root;

	
	/**
	 * Switch to the main menu of instructor mode
	 * @param event onclick event
	 * @throws IOException catch IO exception
	 */
	public void switchToInstructorScene(ActionEvent event) throws IOException
	{
		root = FXMLLoader.load(getClass().getResource("/instructor_main_menu.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	// TODO: Handle student query for teaming result (OUTPUT)
	// You may need to create a scene for student (like student_running.fmxl) and switch to it
	// The new scene need a new controller class like this class
	/**
	 * Switch to student main menu scene
	 * @param event onclick event
	 * @throws IOException catch IO exception
	 */
	public void runAsStudent(ActionEvent event) throws IOException
	{
		if (Process.existTeam(InstructorMainMenuController.teams)){
			root = FXMLLoader.load(getClass().getResource("/student_main_menu.fxml"));
		}else{
			root = FXMLLoader.load(getClass().getResource("/student_invalid_menu.fxml"));
		}
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
	}
}