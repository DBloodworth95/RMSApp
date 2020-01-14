package rms;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class StudentTabController {
    @FXML
    private TableView studentTable;
    @FXML
    private TableColumn firstNameCol, lastNameCol;

    public List<Staff> fetchTable() throws SQLException {
        String dbURL = "jdbc:mysql://localhost:3306/rmsdb";
        String username = "root";
        String password = "root";
        String fetchQuery = ("SELECT * FROM staff");
        Connection rmsConnection = DriverManager.getConnection(dbURL, username, password);
        Statement fetchStaff = rmsConnection.createStatement();
        ResultSet result = fetchStaff.executeQuery("SELECT * FROM staff");
        List<Staff> newStaff = new ArrayList<>();
        while (result.next()) {
            int id = Integer.parseInt(result.getString("staff_id"));
            String status = result.getString("status");
            String dormReason = result.getString("dormancy_reason");
            String firstName = result.getString("first_name");
            String middleName = result.getString("middle_name");
            String surname = result.getString("surname");
            String gender = result.getString("gender");
            String address = result.getString("address");
            String telephone = result.getString("telephone");
            String emailAddress = result.getString("email_address");
            String emergencyPhone = result.getString("emergency_contact_phone");
            String emergencyEmail = result.getString("emergency_contact_email");
            String specialism = result.getString("specialism");
            int noteID = Integer.parseInt(result.getString("note_id"));
            int medicalID = Integer.parseInt(result.getString("medical_file_id"));
            newStaff.add(new Staff(id,noteID, medicalID, status,dormReason,firstName,middleName,surname,gender,address,telephone,emailAddress,emergencyPhone,emergencyEmail,specialism));
        }
        return newStaff;
    }

    public TableView populateTable(List<Staff> newStaff, TableColumn firstNameCol, TableColumn lastNameCol, TableView tableView) {
        firstNameCol.setCellValueFactory(new PropertyValueFactory<Staff, String>("firstName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<Staff, String>("surname"));
        firstNameCol.setEditable(true);
        lastNameCol.setEditable(true);
        tableView.setEditable(true);
        ObservableList<Staff> rows = FXCollections.observableArrayList();
        for (Staff staff: newStaff) {
            rows.add(staff);
        }
        tableView.setItems(rows);
        tableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        firstNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        lastNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        return tableView;
    }

    public void populate() throws SQLException {
        List<Staff> newStaff = fetchTable();
        populateTable(newStaff, firstNameCol, lastNameCol, studentTable);
    }



}
