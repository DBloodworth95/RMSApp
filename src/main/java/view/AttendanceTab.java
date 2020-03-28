package view;

import controller.AttendanceTabController;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import java.sql.SQLException;

public class AttendanceTab extends Tab {

    public AttendanceTab(TabPane tabPane, FXMLLoader loader) throws IOException, SQLException {
        AttendanceTab attendanceTab = new AttendanceTab();
        loader = new FXMLLoader(getClass().getResource("/FXMLview/AttendanceTabRecordsStaff.fxml"));
        AnchorPane attendanceTabContent = loader.load();
        tabPane.getTabs().add(attendanceTab);
        attendanceTab.setText("Attendance");
        attendanceTab.setContent(attendanceTabContent);
        Object temp = loader.getController();
        AttendanceTabController controller = (AttendanceTabController) temp;
        controller.populate();
        tabPane.getSelectionModel().select(attendanceTab);
    }

    public AttendanceTab() {

    }
}
