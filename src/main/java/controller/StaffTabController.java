package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;
import model.Staff;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StaffTabController {
    @FXML
    private TableView staffTV;
    @FXML
    private TableColumn staffIDCol, statusCol, dormCol, firstNameCol, lastNameCol, middleNameCol, genderCol, houseNumCol, houseNameCol, streetCol, townCol, countyCol,
            countryCol, zipCol, phoneCol, emailCol, specialistCol, emergPCol, emergECol, medicalHCol, allergyCol, medicalRCol, resumeCol, imgCol, addNoteCol;

    public List<Staff> fetchTable() throws SQLException {
        String dbURL = "jdbc:mysql://localhost:3306/rmsdb";
        String username = "root";
        String password = "root";
        Connection rmsConnection = DriverManager.getConnection(dbURL, username, password);
        Statement fetchStaff = rmsConnection.createStatement();
        ResultSet result = fetchStaff.executeQuery("SELECT * FROM staff");
        List<Staff> staff = new ArrayList<>();
        while(result.next()) {
            int id = Integer.parseInt(result.getString("staff_id"));
            String status = result.getString("status");
            String dormReason = result.getString("dormancy_reason");
            String firstName = result.getString("first_name");
            String middleName = result.getString("middle_name");
            String surname = result.getString("surname");
            String gender = result.getString("gender");
            String addNumber = result.getString("address_number");
            String houseName = result.getString("address_house_name");
            String street = result.getString("address_street");
            String town = result.getString("address_town");
            String county = result.getString("address_county");
            String country = result.getString("address_country");
            String zip = result.getString("zip_code");
            String telephone = result.getString("telephone");
            String email = result.getString("email_address");
            String emergP = result.getString("emergency_contact_phone");
            String emergE = result.getString("emergency_contact_email");
            String specialism = result.getString("specialist_subject");
            String resume = result.getString("resume");
            String notes = result.getString("additional_notes");
            String medicalH = result.getString("medical_history");
            String medicalA = result.getString("medical_allergies");
            String medicalR = result.getString("medical_religious");
            String image = result.getString("image");
            staff.add(new Staff(id, status, dormReason, firstName, middleName, surname,
                    gender, addNumber, houseName, street, town, county, country,
                    zip, telephone, email, emergP, emergE, specialism, resume, notes, medicalH,
                    medicalA, medicalR, image));
        }
        return staff;
    }

    public void populateTable(List<Staff> newStaff) {
        staffIDCol.setCellValueFactory(new PropertyValueFactory<Staff, Integer>("staffID"));
        statusCol.setCellValueFactory(new PropertyValueFactory<Staff, String>("status"));
        dormCol.setCellValueFactory(new PropertyValueFactory<Staff, String>("dorm"));
        firstNameCol.setCellValueFactory(new PropertyValueFactory<Staff, String>("firstName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<Staff, String>("surname"));
        middleNameCol.setCellValueFactory(new PropertyValueFactory<Staff, String>("middleName"));
        genderCol.setCellValueFactory(new PropertyValueFactory<Staff, String>("gender"));
        houseNumCol.setCellValueFactory(new PropertyValueFactory<Staff, String>("addressNumber"));
        houseNameCol.setCellValueFactory(new PropertyValueFactory<Staff, String>("addressName"));
        streetCol.setCellValueFactory(new PropertyValueFactory<Staff, String>("addressStreet"));
        townCol.setCellValueFactory(new PropertyValueFactory<Staff, String>("addressTown"));
        countyCol.setCellValueFactory(new PropertyValueFactory<Staff, String>("addressCounty"));
        countryCol.setCellValueFactory(new PropertyValueFactory<Staff, String>("addressCountry"));
        zipCol.setCellValueFactory(new PropertyValueFactory<Staff, String>("zipCode"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<Staff, String>("telephone"));
        emailCol.setCellValueFactory(new PropertyValueFactory<Staff, String>("email"));
        specialistCol.setCellValueFactory(new PropertyValueFactory<Staff, String>("specialism"));
        emergPCol.setCellValueFactory(new PropertyValueFactory<Staff, String>("emergPhone"));
        emergECol.setCellValueFactory(new PropertyValueFactory<Staff, String>("emergEmail"));
        medicalHCol.setCellValueFactory(new PropertyValueFactory<Staff, String>("medicalHistory"));
        allergyCol.setCellValueFactory(new PropertyValueFactory<Staff, String>("medicalAllergy"));
        medicalRCol.setCellValueFactory(new PropertyValueFactory<Staff, String>("medicalReligious"));
        resumeCol.setCellValueFactory(new PropertyValueFactory<Staff, String>("resume"));
        imgCol.setCellValueFactory(new PropertyValueFactory<Staff, String>("image"));
        addNoteCol.setCellValueFactory(new PropertyValueFactory<Staff, String>("addNotes"));

        staffIDCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        statusCol.setCellFactory(TextFieldTableCell.forTableColumn());
        dormCol.setCellFactory(TextFieldTableCell.forTableColumn());
        firstNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        lastNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        middleNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        genderCol.setCellFactory(ComboBoxTableCell.forTableColumn("M", "F"));
        houseNumCol.setCellFactory(TextFieldTableCell.forTableColumn());
        houseNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        streetCol.setCellFactory(TextFieldTableCell.forTableColumn());
        townCol.setCellFactory(TextFieldTableCell.forTableColumn());
        countyCol.setCellFactory(TextFieldTableCell.forTableColumn());
        countryCol.setCellFactory(TextFieldTableCell.forTableColumn());
        zipCol.setCellFactory(TextFieldTableCell.forTableColumn());
        phoneCol.setCellFactory(TextFieldTableCell.forTableColumn());
        emailCol.setCellFactory(TextFieldTableCell.forTableColumn());
        specialistCol.setCellFactory(TextFieldTableCell.forTableColumn());
        emergPCol.setCellFactory(TextFieldTableCell.forTableColumn());
        emergECol.setCellFactory(TextFieldTableCell.forTableColumn());
        medicalHCol.setCellFactory(TextFieldTableCell.forTableColumn());
        allergyCol.setCellFactory(TextFieldTableCell.forTableColumn());
        medicalRCol.setCellFactory(TextFieldTableCell.forTableColumn());
        resumeCol.setCellFactory(TextFieldTableCell.forTableColumn());
        imgCol.setCellFactory(TextFieldTableCell.forTableColumn());
        addNoteCol.setCellFactory(TextFieldTableCell.forTableColumn());
        ObservableList<Staff> rows = FXCollections.observableArrayList();
        for (Staff staff : newStaff) {
            rows.add(staff);
        }
        staffTV.setItems(rows);
        staffTV.setEditable(true);
        staffTV.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        editColumns();
    }

    public void populate() throws SQLException {
        List<Staff> newStaff = fetchTable();
        populateTable(newStaff);
    }

    public void editColumns() {

    }
}
