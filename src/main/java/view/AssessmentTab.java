package view;

import controller.AssessmentTabController;
import controller.CourseTabController;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.SQLException;

public class AssessmentTab extends Tab {

    public AssessmentTab(TabPane tabPane, FXMLLoader loader) throws IOException, SQLException {
        AssessmentTab assessmentTab = new AssessmentTab();
        loader = new FXMLLoader(getClass().getResource("/FXMLview/AssessmentsTabRecordsStaff.fxml"));
        AnchorPane assessmentTabContent = loader.load();
        tabPane.getTabs().add(assessmentTab);
        assessmentTab.setText("Assessments");
        assessmentTab.setContent(assessmentTabContent);
        Object temp = loader.getController();
        AssessmentTabController controller = (AssessmentTabController) temp;
        controller.populate();
    }

    public AssessmentTab() {

    }
}

