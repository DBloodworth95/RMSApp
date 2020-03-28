package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.print.Paper;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;
import model.Staff;
import model.TablePrinter;
import view.NewStaffWindow;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StaffTabController {
    @FXML
    private TableView staffTV;
    @FXML
    private TableColumn staffIDCol, statusCol, dormCol, firstNameCol, lastNameCol, middleNameCol, genderCol, houseNumCol, houseNameCol, streetCol, townCol, countyCol,
            countryCol, zipCol, phoneCol, emailCol, specialistCol, emergPCol, emergECol, medicalHCol, allergyCol, medicalRCol, resumeCol, imgCol, addNoteCol;
    @FXML
    private Button createBtn, archiveBtn;

    public List<Staff> fetchTable(Boolean isArchive) throws SQLException {
        String dbURL = "jdbc:mysql://localhost:3306/rmsdb";
        String username = "root";
        String password = "root";
        Connection rmsConnection = DriverManager.getConnection(dbURL, username, password);
        Statement fetchStaff = rmsConnection.createStatement();
        List<Staff> staff = new ArrayList<>();
        if(isArchive){
            ResultSet result = fetchStaff.executeQuery("SELECT * FROM staff WHERE archived = 1");
            while (result.next()) {
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
        } else {
            ResultSet result = fetchStaff.executeQuery("SELECT * FROM staff WHERE archived = 0");
            while (result.next()) {
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
        List<Staff> newStaff = fetchTable(false);
        populateTable(newStaff);
    }
    public void populateArchive() throws SQLException {
        List<Staff> newStaff = fetchTable(true);
        populateTable(newStaff);
        createBtn.setVisible(false);
        archiveBtn.setVisible(false);
    }

    public void editColumns() {
        staffIDCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Staff, Integer>>) staffIntegerCellEditEvent -> ((Staff) staffIntegerCellEditEvent.getTableView().getItems().get(staffIntegerCellEditEvent.getTablePosition().getRow())).setStaffID(staffIntegerCellEditEvent.getNewValue()));
        statusCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Staff, String>>) staffStringCellEditEvent -> ((Staff) staffStringCellEditEvent.getTableView().getItems().get(staffStringCellEditEvent.getTablePosition().getRow())).setStatus(staffStringCellEditEvent.getNewValue()));
        dormCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Staff, String>>) staffStringCellEditEvent -> ((Staff) staffStringCellEditEvent.getTableView().getItems().get(staffStringCellEditEvent.getTablePosition().getRow())).setDorm(staffStringCellEditEvent.getNewValue()));
        firstNameCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Staff, String>>) staffStringCellEditEvent -> ((Staff) staffStringCellEditEvent.getTableView().getItems().get(staffStringCellEditEvent.getTablePosition().getRow())).setFirstName(staffStringCellEditEvent.getNewValue()));
        lastNameCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Staff, String>>) staffStringCellEditEvent -> ((Staff) staffStringCellEditEvent.getTableView().getItems().get(staffStringCellEditEvent.getTablePosition().getRow())).setSurname(staffStringCellEditEvent.getNewValue()));
        middleNameCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Staff, String>>) staffStringCellEditEvent -> ((Staff) staffStringCellEditEvent.getTableView().getItems().get(staffStringCellEditEvent.getTablePosition().getRow())).setMiddleName(staffStringCellEditEvent.getNewValue()));
        genderCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Staff, String>>) staffStringCellEditEvent -> ((Staff) staffStringCellEditEvent.getTableView().getItems().get(staffStringCellEditEvent.getTablePosition().getRow())).setGender(staffStringCellEditEvent.getNewValue()));
        houseNumCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Staff, String>>) staffStringCellEditEvent -> ((Staff) staffStringCellEditEvent.getTableView().getItems().get(staffStringCellEditEvent.getTablePosition().getRow())).setAddressNumber(staffStringCellEditEvent.getNewValue()));
        houseNameCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Staff, String>>) staffStringCellEditEvent -> ((Staff) staffStringCellEditEvent.getTableView().getItems().get(staffStringCellEditEvent.getTablePosition().getRow())).setAddressName(staffStringCellEditEvent.getNewValue()));
        streetCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Staff, String>>) staffStringCellEditEvent -> ((Staff) staffStringCellEditEvent.getTableView().getItems().get(staffStringCellEditEvent.getTablePosition().getRow())).setAddressStreet(staffStringCellEditEvent.getNewValue()));
        townCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Staff, String>>) staffStringCellEditEvent -> ((Staff) staffStringCellEditEvent.getTableView().getItems().get(staffStringCellEditEvent.getTablePosition().getRow())).setAddressTown(staffStringCellEditEvent.getNewValue()));
        countyCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Staff, String>>) staffStringCellEditEvent -> ((Staff) staffStringCellEditEvent.getTableView().getItems().get(staffStringCellEditEvent.getTablePosition().getRow())).setAddressCounty(staffStringCellEditEvent.getNewValue()));
        countryCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Staff, String>>) staffStringCellEditEvent -> ((Staff) staffStringCellEditEvent.getTableView().getItems().get(staffStringCellEditEvent.getTablePosition().getRow())).setAddressCountry(staffStringCellEditEvent.getNewValue()));
        zipCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Staff, String>>) staffStringCellEditEvent -> ((Staff) staffStringCellEditEvent.getTableView().getItems().get(staffStringCellEditEvent.getTablePosition().getRow())).setZipCode(staffStringCellEditEvent.getNewValue()));
        phoneCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Staff, String>>) staffStringCellEditEvent -> ((Staff) staffStringCellEditEvent.getTableView().getItems().get(staffStringCellEditEvent.getTablePosition().getRow())).setTelephone(staffStringCellEditEvent.getNewValue()));
        emailCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Staff, String>>) staffStringCellEditEvent -> ((Staff) staffStringCellEditEvent.getTableView().getItems().get(staffStringCellEditEvent.getTablePosition().getRow())).setEmail(staffStringCellEditEvent.getNewValue()));
        specialistCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Staff, String>>) staffStringCellEditEvent -> ((Staff) staffStringCellEditEvent.getTableView().getItems().get(staffStringCellEditEvent.getTablePosition().getRow())).setSpecialism(staffStringCellEditEvent.getNewValue()));
        emergPCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Staff, String>>) staffStringCellEditEvent -> ((Staff) staffStringCellEditEvent.getTableView().getItems().get(staffStringCellEditEvent.getTablePosition().getRow())).setEmergPhone(staffStringCellEditEvent.getNewValue()));
        emergECol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Staff, String>>) staffStringCellEditEvent -> ((Staff) staffStringCellEditEvent.getTableView().getItems().get(staffStringCellEditEvent.getTablePosition().getRow())).setEmergEmail(staffStringCellEditEvent.getNewValue()));
        medicalHCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Staff, String>>) staffStringCellEditEvent -> ((Staff) staffStringCellEditEvent.getTableView().getItems().get(staffStringCellEditEvent.getTablePosition().getRow())).setMedicalHistory(staffStringCellEditEvent.getNewValue()));
        medicalRCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Staff, String>>) staffStringCellEditEvent -> ((Staff) staffStringCellEditEvent.getTableView().getItems().get(staffStringCellEditEvent.getTablePosition().getRow())).setMedicalReligious(staffStringCellEditEvent.getNewValue()));
        resumeCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Staff, String>>) staffStringCellEditEvent -> ((Staff) staffStringCellEditEvent.getTableView().getItems().get(staffStringCellEditEvent.getTablePosition().getRow())).setResume(staffStringCellEditEvent.getNewValue()));
        imgCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Staff, String>>) staffStringCellEditEvent -> ((Staff) staffStringCellEditEvent.getTableView().getItems().get(staffStringCellEditEvent.getTablePosition().getRow())).setImage(staffStringCellEditEvent.getNewValue()));
        addNoteCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Staff, String>>) staffStringCellEditEvent -> ((Staff) staffStringCellEditEvent.getTableView().getItems().get(staffStringCellEditEvent.getTablePosition().getRow())).setAddNotes(staffStringCellEditEvent.getNewValue()));
    }

    public void updateTable() throws SQLException {
        Staff staff;
        List <List<String>> staffList = new ArrayList<>();
        for (int i = 0; i < staffTV.getItems().size(); i++) {
            staff = (Staff) staffTV.getItems().get(i);
            staffList.add(new ArrayList<>());
            staffList.get(i).add(Integer.toString(staff.getStaffID()));
            staffList.get(i).add(staff.getStatus());
            staffList.get(i).add(staff.getDorm());
            staffList.get(i).add(staff.getFirstName());
            staffList.get(i).add(staff.getMiddleName());
            staffList.get(i).add(staff.getSurname());
            staffList.get(i).add(staff.getGender());
            staffList.get(i).add(staff.getAddressNumber());
            staffList.get(i).add(staff.getAddressName());
            staffList.get(i).add(staff.getAddressStreet());
            staffList.get(i).add(staff.getAddressTown());
            staffList.get(i).add(staff.getAddressCounty());
            staffList.get(i).add(staff.getAddressCountry());
            staffList.get(i).add(staff.getZipCode());
            staffList.get(i).add(staff.getTelephone());
            staffList.get(i).add(staff.getEmail());
            staffList.get(i).add(staff.getEmergPhone());
            staffList.get(i).add(staff.getEmergEmail());
            staffList.get(i).add(staff.getSpecialism());
            staffList.get(i).add(staff.getResume());
            staffList.get(i).add(staff.getAddNotes());
            staffList.get(i).add(staff.getMedicalHistory());
            staffList.get(i).add(staff.getMedicalAllergy());
            staffList.get(i).add(staff.getMedicalReligious());
            staffList.get(i).add(staff.getImage());

            String dbURL = "jdbc:mysql://localhost:3306/rmsdb";
            String username = "root";
            String password = "root";
            String query = ("UPDATE staff SET status='" + staff.getStatus() + "', dormancy_reason='" + staff.getDorm() + "', first_name='" + staff.getFirstName() + "', middle_name='" +
                    staff.getMiddleName() + "', surname='" + staff.getSurname() + "', gender='" + staff.getGender() + "', address_number='" + staff.getAddressNumber() + "', address_house_name='" +
                    staff.getAddressName() + "', address_street='" + staff.getAddressStreet() + "', address_town='" + staff.getAddressTown() + "', address_county='" + staff.getAddressCounty() +
                    "', address_country='" + staff.getAddressCountry() + "', zip_code='" + staff.getZipCode() + "', telephone='" + staff.getTelephone() + "', email_address='" + staff.getEmail() +
                    "', emergency_contact_phone='" + staff.getEmergPhone() + "', emergency_contact_email='" + staff.getEmergEmail() + "', specialist_subject='" + staff.getSpecialism() +
                    "', resume='" + staff.getResume() + "', additional_notes='" + staff.getAddNotes() + "', medical_history='" + staff.getMedicalHistory() + "', medical_allergies='" +
                    staff.getMedicalAllergy() + "', medical_religious='" + staff.getMedicalReligious() + "', image='" + staff.getImage() + "' WHERE staff_id='" + staff.getStaffID() + "'");
            Connection myCon = DriverManager.getConnection(dbURL, username, password);
            PreparedStatement preparedStatement = myCon.prepareStatement(query);
            preparedStatement.execute();
        }
        System.out.println("Saved!");
    }

    public void removeStaff() throws SQLException {
        Object selectedItems = staffTV.getSelectionModel().getSelectedItems().get(0);
        String dbUrl = "jdbc:mysql://localhost:3306/rmsdb";
        String username = "root";
        String password = "root";
        String query = ("DELETE FROM staff WHERE staff_id ='" + staffIDCol.getCellData(selectedItems) + "'");
        Connection myConnection = DriverManager.getConnection(dbUrl, username, password);
        PreparedStatement preparedStatement = myConnection.prepareStatement(query);
        preparedStatement.execute();
        ObservableList<Staff> allRows, singleRow;
        allRows = staffTV.getItems();
        singleRow = staffTV.getSelectionModel().getSelectedItems();
        singleRow.forEach(allRows::remove);
        System.out.println("Removed selected items!");
    }

    public void openNewStaffTab() throws IOException {
        new NewStaffWindow(this);
    }

    public void archiveStaff() throws SQLException {
        Object selectedItems = staffTV.getSelectionModel().getSelectedItems().get(0);
        String dbUrl = "jdbc:mysql://localhost:3306/rmsdb";
        String username = "root";
        String password = "root";
        String query = ("UPDATE staff SET archived= 1 WHERE staff_id ='" + staffIDCol.getCellData(selectedItems) + "'");
        Connection myConnection = DriverManager.getConnection(dbUrl, username, password);
        PreparedStatement preparedStatement = myConnection.prepareStatement(query);
        preparedStatement.execute();
        populate();
    }

    public void printTable() {
        TablePrinter tablePrinter = new TablePrinter();
        tablePrinter.print(Paper.A4, staffTV);
    }
}
