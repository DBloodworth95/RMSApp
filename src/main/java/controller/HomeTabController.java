package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeTabController {

    public void showNewStudentWindow() throws IOException {
        System.out.println("lol");
        FXMLLoader createStudentLoader = new FXMLLoader();
        createStudentLoader.setLocation(getClass().getResource("/FXMLview/NewStudentTab.fxml"));
        Scene scene = new Scene(createStudentLoader.load(), 1440,580);
        Stage stage = new Stage();
        stage.setTitle("Add a Student");
        stage.setScene(scene);
        stage.setResizable(true);
        stage.setMaximized(true);
        stage.show();
        Object temp = createStudentLoader.getController();
        NewStudentTabController controller = (NewStudentTabController) temp;
        controller.populateComboBoxes();
        controller.checkForSave();
    }

}
