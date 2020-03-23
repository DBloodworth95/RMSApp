package view;

import controller.NewGradeWindowController;
import controller.NewModuleWindowController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class NewGradeWindow {

    public NewGradeWindow() throws IOException, SQLException {
        FXMLLoader createGradeLoader = new FXMLLoader();
        createGradeLoader.setLocation(getClass().getResource("/FXMLview/NewGradeTab.fxml"));
        Scene scene = new Scene(createGradeLoader.load(), 1440,580);
        Stage stage = new Stage();
        stage.setTitle("Add a Grade");
        stage.setScene(scene);
        stage.setResizable(true);
        stage.show();
        Object temp = createGradeLoader.getController();
        NewGradeWindowController controller = (NewGradeWindowController) temp;
        //controller.populateCB();
    }
}
