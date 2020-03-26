package view;

import controller.StudentTabController;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.SQLException;

public class StudentTab extends Tab {

    public StudentTab(TabPane tabPane, FXMLLoader loader) throws IOException, SQLException {
        StudentTab studentTab = new StudentTab();
        loader = new FXMLLoader(getClass().getResource("/FXMLview/StudentTab.fxml"));
        AnchorPane studentTabContent = loader.load();
        tabPane.getTabs().add(studentTab);
        studentTab.setText("Students");
        studentTab.setContent(studentTabContent);
        Object temp = loader.getController();
        StudentTabController controller = (StudentTabController) temp;
        controller.populate();
        tabPane.getSelectionModel().select(studentTab);
    }

    public StudentTab() {

    }
}
