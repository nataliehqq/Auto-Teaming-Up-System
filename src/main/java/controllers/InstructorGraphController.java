package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Collection;

import javafx.scene.chart.NumberAxis; 
import javafx.scene.chart.CategoryAxis;
import helpers.Output;

/**
 * Controller Class for instructor_graph_menu.fxml
 */
public class InstructorGraphController{
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
    private LineChart<String, Double> lineGraph;

    @FXML
    private Button returnButton;

    @FXML
    private NumberAxis xAxis;

    @FXML
    private CategoryAxis yAxis;

    /**
     * Return to main menu when button is clicked
     * @param event on click event
     * @throws IOException catch exception and do nothing
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
     * Used to initialize all the displaying text in the this page
     * This function will first search for existing teaming result and sort teams according to K2 in descending order then K1
     */
    @FXML
    void initialize(){
        lineGraph.getData().clear();
        XYChart.Series<String, Double>  k1Series = new XYChart.Series<String, Double> ();
        XYChart.Series<String, Double>  k2Series = new XYChart.Series<String, Double> ();  
        XYChart.Series<String, Double>  k3Series = new XYChart.Series<String, Double> ();  

        k1Series.setName("K1");
        k2Series.setName("K2");
        k3Series.setName("K1+K2");

        List<List<Double>> teamEnergy = new ArrayList<List<Double>>();
        for(int i = 0; i < InstructorMainMenuController.teams.size(); i++){
            double[] energyResult = Output.getTeamEnergyDouble(i, InstructorMainMenuController.teams);
            teamEnergy.add(new ArrayList<Double>());
            teamEnergy.get(i).add(energyResult[0]);
            teamEnergy.get(i).add(energyResult[1]);
            teamEnergy.get(i).add(energyResult[2]);
        }
        
        Output.sortK2EnergyinDescending(teamEnergy);
        Output.sortK1EnergyinDescending(teamEnergy);

        for (int i = 0; i < InstructorMainMenuController.teams.size(); i ++){
            String temp = Integer.toString(i+1);
            
            k1Series.getData().add(new XYChart.Data<String, Double> (temp,teamEnergy.get(i).get(0)));
            k2Series.getData().add(new XYChart.Data<String, Double>(temp,teamEnergy.get(i).get(1)));
            k3Series.getData().add(new XYChart.Data<String, Double>(temp,teamEnergy.get(i).get(2)));
        }

        // for (int i = 0; i < InstructorMainMenuController.teams.size(); i ++){
        //     String temp = Integer.toString(i+1);
        //     double[] energyResult = Output.getTeamEnergyDouble(i);
        //     k1Series.getData().add(new XYChart.Data<String, Double> (temp,energyResult[0]));
        //     k2Series.getData().add(new XYChart.Data<String, Double>(temp,energyResult[1]));
        //     k3Series.getData().add(new XYChart.Data<String, Double>(temp,energyResult[2]));
        // }

        lineGraph.getData().add(k1Series);
        lineGraph.getData().add(k2Series);
        lineGraph.getData().add(k3Series);

        teamEnergy = null;
    }
}
