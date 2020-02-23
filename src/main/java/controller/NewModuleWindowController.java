package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.*;

public class NewModuleWindowController {
    @FXML
    private TextField titleTF, creditTF, leaderTF, moduleTF;
    @FXML
    private ComboBox levelCB, startCB, endCB, courseCB;
    @FXML
    private TextArea descTA, aimTA;
    @FXML
    private Button saveBtn;

    public void createModule() throws SQLException {
        String dbURL = "jdbc:mysql://localhost:3306/rmsdb";
        String username = "root";
        String password = "root";
        String query = "INSERT INTO modules(module_code, start_year, end_year, level, credits, title, leader, description, aims_and_objectives, course) VALUES (?,?,?,?,?,?,?,?,?,?)";
        Connection myConnection = DriverManager.getConnection(dbURL, username, password);
        PreparedStatement preparedStatement = myConnection.prepareStatement(query);
        preparedStatement.setString(1, moduleTF.getText());
        preparedStatement.setString(2, startCB.getValue().toString());
        preparedStatement.setString(3, endCB.getValue().toString());
        preparedStatement.setString(4, levelCB.getValue().toString());
        preparedStatement.setString(5, creditTF.getText());
        preparedStatement.setString(6, titleTF.getText());
        preparedStatement.setString(7, leaderTF.getText());
        preparedStatement.setString(8, descTA.getText());
        preparedStatement.setString(9, aimTA.getText());
        preparedStatement.setString(10, courseCB.getValue().toString());
        preparedStatement.executeUpdate();
        System.out.println("Module created!");
        Stage stage = (Stage) saveBtn.getScene().getWindow();
        stage.close();
    }

    public void populateCB() throws SQLException {
        levelCB.getItems().addAll("4", "5", "6");
        populateCourse();
        populateYearCB();
    }

    private void populateYearCB() {
        for (int i = 2018; i<3000; i++) {
            startCB.getItems().addAll(String.valueOf(i));
            endCB.getItems().addAll(String.valueOf(i));
        }
    }

    public void generateCode() throws SQLException {
        String dbURL = "jdbc:mysql://localhost:3306/rmsdb";
        String username = "root";
        String password = "root";
        Connection rmsConnection = DriverManager.getConnection(dbURL, username, password);
        Statement fetchStaff = rmsConnection.createStatement();
        ResultSet result = fetchStaff.executeQuery("SELECT module_code FROM modules");
        char Ci = courseCB.getValue().toString().charAt(0);
        char Mi = titleTF.getText().charAt(0);
        String initials = (""+Ci + Mi);
        String year = startCB.getValue().toString();
        String genCode = ("" + initials + year);
        while (result.next()) {
            int code = (int) (Math.random() * 999);
            if (!result.getString("module_code").equals("" + genCode+code)) {
                moduleTF.setText(""+genCode+code);
            } else {
                generateCode();
            }
        }
    }

    private void populateCourse() throws SQLException {
        String dbURL = "jdbc:mysql://localhost:3306/rmsdb";
        String username = "root";
        String password = "root";
        Connection rmsConnection = DriverManager.getConnection(dbURL, username, password);
        Statement fetchStaff = rmsConnection.createStatement();
        ResultSet result = fetchStaff.executeQuery("SELECT course_code FROM courses");
        while (result.next()) {
            courseCB.getItems().addAll(result.getString("course_code"));
        }
    }
}
