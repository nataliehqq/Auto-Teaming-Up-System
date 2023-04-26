package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import teaming.Statistics;
import teaming.Student;

import java.io.IOException;

import teaming.Team;
import helpers.Process;
import java.io.FileWriter;

/** 
 * Controller class for instructor_running.fxml
 */
public class InstructorRunningController {
	
	/*
	 * Attributes area:
	 * Objects we may need to use for the this scene
	 * Feel free to add new objects
	 */
    private Stage stage;
	private Scene scene;
	private Parent root;
	
	
     /**
      * Button for allocating student into teams
      * When onlick, Process.ATUengine will be executed
      * @param event button onclick
      */
    @FXML
    /*
     * TODO: 
     * 1. Allocate students to team using some algorithm
     * 2. Update the team column of the table shown on the screen 
     * 3. Create a file of the teaming result, storing it in src/main/resources
     */
    void allocateStudentsToTeams(ActionEvent event) {
    	Process.ATUengine(InstructorMainMenuController.teams);
    	
    	//create teaming result file and store to src/main/resources
     	StringBuilder stringBuilder =new StringBuilder();
    	stringBuilder.append("Team Id").append(",").append("Student_id").append("\n");
    	for(Team tm: InstructorMainMenuController.teams) {
    		for(int i=0; i<tm.getEachTeamSize(); i++) {
    			stringBuilder.append(tm.getTeamID()).append(",").append(tm.getMember().get(i).getStudentid()).append("\n");
    		}
    	}
    	
    	try(FileWriter writeFile =new FileWriter("src/main/resources/teamingResult.csv")){
    		writeFile.write(stringBuilder.toString());
    	}catch(Exception e) {
    		System.out.println(e);
    	}
    	
    	if(InstructorMainMenuController.teams.size() >=1) {
    		// Prompt information alert to show teaming successful
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setContentText("Automatic teaming up successful!");
    		alert.show();
			
    	}else {
    	 	// Prompt error alert to show teaming up failed
    	 	Alert alert = new Alert(AlertType.ERROR);
    	 	alert.setContentText("Faild to automatic teaming up!");
    	 	alert.show();
    	 }
    	
    	// Refresh the table to see the team allocated to each student
    	studentTable.refresh();
    }
    
    @FXML
    // TODO: Handle event of returning back to main menu
    void returnToMainMenu(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("/instructor_main_menu.fxml"));
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
    private TableView<Student> studentTable;
    
    @FXML
    private TableColumn<Statistics, String> stuRowIndexColumn;
    
    @FXML
    private TableColumn<Statistics, String> teamColumn;
    
    @FXML
    private TableColumn<Statistics, String> stuidColumn;
    
    @FXML
    private TableColumn<Statistics, String> nameColumn;

    @FXML
    private TableColumn<Statistics, String> emailColumn;

    @FXML
    private TableColumn<Statistics, String> k1eneColumn;

    @FXML
    private TableColumn<Statistics, String> k2eneColumn;

    @FXML
    private TableColumn<Statistics, String> tick1Column;

    @FXML
    private TableColumn<Statistics, String> tick2Column;
    
    @FXML
    private TableColumn<Statistics, String> prefColumn;
    
    @FXML
    private TableColumn<Statistics, String> concColumn;
    
    @FXML
    private TableView<Statistics> statTable;
    
    @FXML
    private TableColumn<Statistics, String> staRowIndexColumn;
    
    @FXML
    private TableColumn<Statistics, String> entryColumn;
    
    @FXML
    private TableColumn<Statistics, String> valueColumn;

    @FXML
    private Button allocateButton;

    @FXML
    private Button returnButton;
    
    /**
     * Used to initialize the gui elements in this scene
     */
    @FXML
    public void initialize() {
    	// Show the table of information of each student
    	stuRowIndexColumn.setCellValueFactory(new PropertyValueFactory<Statistics, String>("rowindex"));
    	teamColumn.setCellValueFactory(new PropertyValueFactory<Statistics, String>("team"));
    	stuidColumn.setCellValueFactory(new PropertyValueFactory<Statistics, String>("studentid"));
    	nameColumn.setCellValueFactory(new PropertyValueFactory<Statistics, String>("studentname"));
    	emailColumn.setCellValueFactory(new PropertyValueFactory<Statistics, String>("email"));
    	k1eneColumn.setCellValueFactory(new PropertyValueFactory<Statistics, String>("k1energy"));
    	k2eneColumn.setCellValueFactory(new PropertyValueFactory<Statistics, String>("k2energy"));
    	tick1Column.setCellValueFactory(new PropertyValueFactory<Statistics, String>("k3tick1"));
    	tick2Column.setCellValueFactory(new PropertyValueFactory<Statistics, String>("k3tick2"));
    	prefColumn.setCellValueFactory(new PropertyValueFactory<Statistics, String>("mypreference"));
    	concColumn.setCellValueFactory(new PropertyValueFactory<Statistics, String>("concerns"));
        studentTable.setItems(InstructorMainMenuController.students);
        
        // Show the table of overall statistics
        staRowIndexColumn.setCellValueFactory(new PropertyValueFactory<Statistics, String>("rowindex"));
        entryColumn.setCellValueFactory(new PropertyValueFactory<Statistics, String>("entry"));
        valueColumn.setCellValueFactory(new PropertyValueFactory<Statistics, String>("value"));
        statTable.setItems(InstructorMainMenuController.stats);
    }
}
