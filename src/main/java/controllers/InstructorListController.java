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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import teaming.Statistics;
import teaming.Student;

/**
 * Controller class for instructor_list_menu_controller.fxml
 */
public class InstructorListController {

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
    private Button returnButton;
    
    /**
     * Return to main menu when button is clicked
     * @param event onclick event
     * @throws IOException catch IO exception
     */
    @FXML
    void returnToMainMenu(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("/instructor_main_menu.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
    }
    
    /**
     * Used to initialize the list GUI.
     * It will read the teaming result itself
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
