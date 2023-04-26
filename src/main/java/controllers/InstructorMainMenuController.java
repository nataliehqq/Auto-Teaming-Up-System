package controllers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import teaming.Student;
import teaming.Team;
import teaming.Statistics;
import helpers.Input;
import helpers.InputH;
import helpers.Process;

/**
 * Controller class for instructor_main_menu.fxml
 * 
 */

public class InstructorMainMenuController {

	/*
	 * Attributes area:
	 * Objects we may need to use for the this scene
	 * Feel free to add new objects
	 */
	
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

	/**
	 * File choose provided by javafx
	 */
	final FileChooser fileChooser = new FileChooser();
	
	// Objects that we may pass to other scenes
	/**
	 * Observable list of student that may pass to other scenes
	 */
	public static ObservableList<Student> students = FXCollections.observableArrayList();
	/**
	 * Observable list of statistic that may pass to other scenes
	 */
	public static ObservableList<Statistics> stats = FXCollections.observableArrayList();
	/**
	 * List of team that may pass to other scenes
	 */
	public static List<Team> teams = new ArrayList<>();
	
	/*
     * TODO Area:
     * For INPUT and OUTPUT
     * All the things we need to do are shown here
     */
    
	// TODO: Load the student data file to the system (INPUT)
	@FXML
    void loadFile(ActionEvent event) throws IOException {
    	
    	// Show the open file dialog for the user to choose a file
    	Input.configureFileChooser(fileChooser);
        File file = fileChooser.showOpenDialog(stage);
        
        // If file is not null, then scan through the whole file to get all students
        
        if (file != null) 
        {
        	// Scan the file to retrieve data and store it to the student ObservableList
        	ObservableList<Student> stu_temp = Input.getAllStudents(Input.loadRawStudentData(file));
        	if (stu_temp != null)
        	{
        		// Clear existing student team and statistics
        		if (stats != null)
            		stats.clear();
        		if (!teams.isEmpty())
        			teams.clear();
        		// Construct the table for the overall statistics
        		students = stu_temp;
        		Input.constructOverallStats(students, stats);
        		
        		// Change scene to instructor running
        		root = FXMLLoader.load(getClass().getResource("/instructor_running.fxml"));
        		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        		scene = new Scene(root);
        		stage.setScene(scene);
        		stage.show();
        	}
        	else
        	{
        		// Prompt error alert for loading problematic file 
        		Alert alert = new Alert(AlertType.ERROR);
        		alert.setContentText("Missing or unmatching data in the file!");
        		alert.show();
        	}
        }
        else
        {
        	// Prompt information alert for not selecting any file
        	Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setContentText("You haven't select any file.");
    		alert.show();
        }
    }
    
	// TODO: Generate a random data set of students (INPUT H)
	@FXML
	void generateRandomData(ActionEvent event) throws IOException {
		// Prompt a TextInputDialog for the user to enter the number of students
		TextInputDialog td = new TextInputDialog("");
		td.setHeaderText("Enter the number of students (200-500)");
		td.showAndWait();
						
		// Get the input from the user
		String input = td.getResult();
		
		int numStudent = InputH.getNumStudent(input);
		// Check for any erroneous inputs
		if (numStudent == -1)
		{
			// Prompt an error message for erroneous inputs
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("Please enter an integer between 200 to 500!");
			alert.show();
		}
		else if (numStudent == 0)
		{
			// Do nothing as user enters nothing
		}
		else
		{
			// Clear existing student data, statistics and teams
        	if (students != null)
        		students.clear();
        	else
        		students = FXCollections.observableArrayList();
        	if (stats != null)
        		stats.clear();
        	if (!teams.isEmpty())
    			teams.clear();
        	
			// Generate the data set and construct the table for the overall statistics
			InputH.generateStudents(numStudent, students);
			Input.constructOverallStats(students, stats);
			
			// Change scene to instructor running
    		root = FXMLLoader.load(getClass().getResource("/instructor_running.fxml"));
    		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    		scene = new Scene(root);
    		stage.setScene(scene);
    		stage.show();
		}
    }
	
    
    // TODO: Show the statistical graph of how well student are teamed (OUTPUT)
    // You may need to create a scene for instructor and switch to it
	// The new scene need a new controller class
    @FXML
    void graphView(ActionEvent event) throws IOException{
		if (Process.existTeam(InstructorMainMenuController.teams)){
			root = FXMLLoader.load(getClass().getResource("/instructor_graph_menu.fxml"));
		}else{
			root = FXMLLoader.load(getClass().getResource("/instructor_invalid_menu.fxml"));
		}
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

    }

    // TODO: Handle instructor query of student teaming result (OUTPUT)
    @FXML
    void listView(ActionEvent event) throws IOException{
		if (Process.existTeam(InstructorMainMenuController.teams)){
			root = FXMLLoader.load(getClass().getResource("/instructor_list_menu.fxml"));
		}else{
			root = FXMLLoader.load(getClass().getResource("/instructor_invalid_menu.fxml"));
		}
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

    }

    /*
     * Do not touch anything below
     * These are functions that are implemented already and some components you can see on the screen
     */

    @FXML
    private Label DescriptionButton;

    @FXML
    private Button GenerateRandomDataButton;

    @FXML
    private Button GraphViewButton;

    @FXML
    private Button ListViewButton;

    @FXML
    private Button LoadFileButton;

    @FXML
    private Label Title;

    @FXML
    private Button returnButton;
    
    // Handle event of switching to start menu
    @FXML
    void switchToStartScene(ActionEvent event) throws IOException {
    	root = FXMLLoader.load(getClass().getResource("/start.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
    }

}
