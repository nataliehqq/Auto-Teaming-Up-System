package controllers;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
/**
 * Controller class for student_running.fxml
 */
public class StudentRunningController {
    private Stage stage;
	private Scene scene;
	private Parent root;

    /**
     * for storing the student itsc
     */
    public static String ipt_itsc = "NA";
    /**
     * for storing the student name to be display
     */
    public static String ipt_stuName = "NA";
     /**
     * for storing the student ID to be display
     */
    public static String ipt_stuID = "NA";
     /**
     * for storing the team ID to be display
     */
    public static String teamNo = "NA";
     /**
     * array for storing the teammembe name to be display
     */
    public static String[] teamMember = {"-","-","-"};
     /**
     * storing the average k1, k2 to be display
     */
    public static String[] k1k2 = {"NA", "NA"};

    @FXML
    private Label k1Avg;

    @FXML
    private Label k2Avg;

    @FXML
    private Label myStudentID;

    @FXML
    private Label myStudentName;

    @FXML
    private Label myTeamNo;

    @FXML
    private Button returnButton;

    @FXML
    private Label teamMember1;

    @FXML
    private Label teamMember2;

    @FXML
    private Label teamMember3;

    /**
     * Return to student inquire input scene
     * @param event
     * @throws IOException
     */
    @FXML
    void returnToMainMenu(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("/student_main_menu.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
    }

    /**
     * Used to initialize the GUI componment for displaying student inquiry result
     */
    @FXML
    public void initialize(){
        myStudentName.setText(ipt_stuName);
        myStudentID.setText(ipt_stuID);
        myTeamNo.setText(teamNo);
        teamMember1.setText(teamMember[0]);
        teamMember2.setText(teamMember[1]);
        teamMember3.setText(teamMember[2]);
        k1Avg.setText(k1k2[0]);
        k2Avg.setText(k1k2[1]);
    }

}
