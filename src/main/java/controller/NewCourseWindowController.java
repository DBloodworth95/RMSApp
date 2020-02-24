package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewCourseWindowController {
    @FXML
    private TextField courseTF, titleTF, leaderTF;
    @FXML
    private ComboBox startCB, endCB, module1CB, module2CB, module3CB, module4CB, module5CB, module6CB;
    @FXML
    private TextArea descTA, aimTA;
    @FXML
    private Button saveBtn;

    public void createCourse() throws SQLException {
        String dbURL = "jdbc:mysql://localhost:3306/rmsdb";
        String username = "root";
        String password = "root";
        String query = "INSERT INTO courses(course_code, course_title, start_year, end_year, description, aims_and_objectives, course_leader) VALUES (?,?,?,?,?,?,?)";
        Connection myConnection = DriverManager.getConnection(dbURL, username, password);
        PreparedStatement preparedStatement = myConnection.prepareStatement(query);
        preparedStatement.setString(1, generateCode());
        preparedStatement.setString(2, titleTF.getText());
        preparedStatement.setString(3, startCB.getValue().toString());
        preparedStatement.setString(4, endCB.getValue().toString());
        preparedStatement.setString(5, descTA.getText());
        preparedStatement.setString(6, aimTA.getText());
        preparedStatement.setString(7, leaderTF.getText());
        preparedStatement.executeUpdate();
        System.out.println("Course created!");
        Stage stage = (Stage) saveBtn.getScene().getWindow();
        stage.close();
    }

    public void populateCB() throws SQLException {
        populateYearCB();
        populateModuleCB();
    }

    private void populateYearCB() {
        for (int i = 2018; i<3000; i++) {
            startCB.getItems().addAll(String.valueOf(i));
            endCB.getItems().addAll(String.valueOf(i));
        }
    }

    private void populateModuleCB() throws SQLException {
        String dbURL = "jdbc:mysql://localhost:3306/rmsdb";
        String username = "root";
        String password = "root";
        Connection rmsConnection = DriverManager.getConnection(dbURL, username, password);
        Statement fetchStaff = rmsConnection.createStatement();
        ResultSet result = fetchStaff.executeQuery("SELECT module_code FROM modules");
        while (result.next()) {
            module1CB.getItems().addAll(result.getString("module_code"));
            module2CB.getItems().addAll(result.getString("module_code"));
            module3CB.getItems().addAll(result.getString("module_code"));
            module4CB.getItems().addAll(result.getString("module_code"));
            module5CB.getItems().addAll(result.getString("module_code"));
            module6CB.getItems().addAll(result.getString("module_code"));
        }
    }

    private String generateCode() {
        Pattern pattern = Pattern.compile("((^| )[A-Za-z])");
        Matcher matcher = pattern.matcher(titleTF.getText());
        String initials = "";
        while(matcher.find()) {
            initials += matcher.group().trim();
        }
        String code = initials.toUpperCase() + "Y" + "-" + startCB.getValue().toString().charAt(2) + startCB.getValue().toString().charAt(3) +
                endCB.getValue().toString().charAt(2) + endCB.getValue().toString().charAt(3);
        return code;
    }




}
