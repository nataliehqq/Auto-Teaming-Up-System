package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Class for start scene
 */
public class UIApplication extends Application {
	
	@Override
	/**
	 * initialise the scene
	 */
	public void start(Stage stage) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/start.fxml"));
		VBox root = (VBox) loader.load();
		
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("ATU System");
		stage.show();
	}
	/**
	 * Launch the scene
	 * @param arg arguments for run
	 */
	public static void run(String arg[]) {
		Application.launch(arg);
	}
}
