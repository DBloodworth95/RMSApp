package view;

import controller.NewStaffTabController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class NewStaffWindow {

    public NewStaffWindow() throws IOException {
        FXMLLoader createStaffLoader = new FXMLLoader();
        createStaffLoader.setLocation(getClass().getResource("/FXMLview/NewStaffTab.fxml"));
        Scene scene = new Scene(createStaffLoader.load(), 1440,580);
        Stage stage = new Stage();
        stage.setTitle("Add a Student");
        stage.setScene(scene);
        stage.setResizable(true);
        stage.show();
        Object temp = createStaffLoader.getController();
        NewStaffTabController controller = (NewStaffTabController) temp;
        controller.populateCB();
    }
}
