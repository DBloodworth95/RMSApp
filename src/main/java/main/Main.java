package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent loginPage = FXMLLoader.load(getClass().getResource("/FXMLview/Login.fxml"));
        Scene scene = new Scene(loginPage);
        scene.getStylesheets().add("main.css");
        primaryStage.setScene(scene);
        primaryStage.setTitle("Woodlands RMS");
        primaryStage.show();
    }
}
