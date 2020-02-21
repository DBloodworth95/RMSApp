package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.print.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import model.student.Student;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentTabController {
    @FXML
    private TableView studentTV;
    @FXML
    private TableColumn idCol, pwCol, statusCol, dormCol, currCourseCol, currYearCol, enrollYearCol, entQualCol, firstNCol, lastNCol, midNCol,
            genderCol, phoneCol, emailCol, tutorCol, emPhonCol, emEmailCol, employerCol, addNotesCol, medicalCol,
            allergyCol, religionCol, imgCol, termAddNCol, termAddHCol, termAddTCol, termAddCountCol, termAddCountryCol, termAddZipCol,
            homeAddNCol, homeAddHCol, homeAddTCol, homeAddCountCol, homeAddCountryCol, homeAddZipCol, termAddSCol, homeAddSCol;
    @FXML
    private Button printBtn;

    public List<Student> fetchTable() throws SQLException {
        String dbURL = "jdbc:mysql://localhost:3306/rmsdb";
        String username = "root";
        String password = "root";
        String fetchQuery = ("SELECT * FROM staff");
        Connection rmsConnection = DriverManager.getConnection(dbURL, username, password);
        Statement fetchStaff = rmsConnection.createStatement();
        ResultSet result = fetchStaff.executeQuery("SELECT * FROM students");
        List<Student> students = new ArrayList<>();
        while (result.next()) {
            int id = Integer.parseInt(result.getString("student_id"));
            String pw = result.getString("password");
            String status = result.getString("status");
            String dorm = result.getString("dormancy_reason");
            String firstName = result.getString("first_name");
            String midName = result.getString("middle_name");
            String surname = result.getString("surname");
            String image = result.getString("image");
            String gender = result.getString("gender");
            String telephone = result.getString("telephone");
            String currCourse = result.getString("current_course_code");
            int currYear = Integer.parseInt(result.getString("current_year"));
            int enrollYear = Integer.parseInt(result.getString("enrollment_year"));
            String entQual = result.getString("entry_qualifications");
            String emPhone = result.getString("emergency_contact_phone");
            String emEmail = result.getString("emergency_contact_email");
            String employer = result.getString("employer");
            String addNotes = result.getString("additional_notes");
            int tutor = Integer.parseInt(result.getString("personal_tutor"));
            String mediHistory = result.getString("medical_history");
            String allergies = result.getString("medical_allergies");
            String religious = result.getString("medical_religious");
            String email = result.getString("email");
            String termNumber = (result.getString("address_term_number"));
            String termHouseName = result.getString("address_term_house_name");
            String termHouseStreet = result.getString("address_term_street");
            String termHouseTown = result.getString("address_term_town");
            String termHouseCounty = result.getString("address_term_county");
            String termHouseCountry = result.getString("address_term_country");
            String termHouseZip = result.getString("address_term_zip_code");
            String homeNumber = (result.getString("noterm_address_number"));
            String homeHouseName = result.getString("noterm_address_house_name");
            String homeHouseStreet = result.getString("noterm_address_street");
            String homeHouseTown = result.getString("noterm_address_town");
            String homeHouseCounty = result.getString("noterm_address_county");
            String homeHouseCountry = result.getString("noterm_address_country");
            String homeHouseZip = result.getString("noterm_zip_code");
            students.add(new Student(id, tutor, pw, status, dorm, firstName, midName, surname, image, gender, telephone, currCourse, currYear, enrollYear, entQual, emPhone,
                    emEmail, employer, addNotes, mediHistory, allergies, religious, termNumber, termHouseName, termHouseStreet, termHouseTown,
                    termHouseCounty, termHouseCountry, termHouseZip, homeNumber, homeHouseName, homeHouseStreet, homeHouseTown, homeHouseCounty
                    , homeHouseCountry, homeHouseZip, email));
        }
        return students;
    }

    public void populateTable(List<Student> newStudents) {
        idCol.setCellValueFactory(new PropertyValueFactory<Student, Integer>("id"));
        pwCol.setCellValueFactory(new PropertyValueFactory<Student, String>("password"));
        statusCol.setCellValueFactory(new PropertyValueFactory<Student, String>("status"));
        dormCol.setCellValueFactory(new PropertyValueFactory<Student, String>("dormancyReason"));
        currCourseCol.setCellValueFactory(new PropertyValueFactory<Student, String>("currCourseCode"));
        currYearCol.setCellValueFactory(new PropertyValueFactory<Student, Integer>("currYear"));
        enrollYearCol.setCellValueFactory(new PropertyValueFactory<Student, Integer>("enrolYear"));
        entQualCol.setCellValueFactory(new PropertyValueFactory<Student, String>("entryQual"));
        firstNCol.setCellValueFactory(new PropertyValueFactory<Student, String>("firstName"));
        lastNCol.setCellValueFactory(new PropertyValueFactory<Student, String>("surname"));
        midNCol.setCellValueFactory(new PropertyValueFactory<Student, String>("middleName"));
        genderCol.setCellValueFactory(new PropertyValueFactory<Student, String>("gender"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<Student, String>("phoneNumber"));
        emailCol.setCellValueFactory(new PropertyValueFactory<Student, String>("email"));
        tutorCol.setCellValueFactory(new PropertyValueFactory<Student, Integer>("personalTutor"));
        emPhonCol.setCellValueFactory(new PropertyValueFactory<Student, String>("emergContactPhone"));
        emEmailCol.setCellValueFactory(new PropertyValueFactory<Student, String>("emergContactEmail"));
        employerCol.setCellValueFactory(new PropertyValueFactory<Student, String>("employer"));
        addNotesCol.setCellValueFactory(new PropertyValueFactory<Student, String>("addNotes"));
        medicalCol.setCellValueFactory(new PropertyValueFactory<Student, String>("medicalHistory"));
        allergyCol.setCellValueFactory(new PropertyValueFactory<Student, String>("medicalAllergies"));
        religionCol.setCellValueFactory(new PropertyValueFactory<Student, String>("medicalReligious"));
        imgCol.setCellValueFactory(new PropertyValueFactory<Student, String>("image"));
        termAddNCol.setCellValueFactory(new PropertyValueFactory<Student, String>("addTermNumber"));
        termAddHCol.setCellValueFactory(new PropertyValueFactory<Student, String>("addTermHouse"));
        termAddSCol.setCellValueFactory(new PropertyValueFactory<Student, String>("addTermStreet"));
        termAddTCol.setCellValueFactory(new PropertyValueFactory<Student, String>("addTermTown"));
        termAddCountCol.setCellValueFactory(new PropertyValueFactory<Student, String>("addTermCounty"));
        termAddCountryCol.setCellValueFactory(new PropertyValueFactory<Student, String>("addTermCountry"));
        termAddZipCol.setCellValueFactory(new PropertyValueFactory<Student, String>("addTermZip"));
        homeAddNCol.setCellValueFactory(new PropertyValueFactory<Student, String>("homeNumber"));
        homeAddHCol.setCellValueFactory(new PropertyValueFactory<Student, String>("homeHouse"));
        homeAddSCol.setCellValueFactory(new PropertyValueFactory<Student, String>("homeStreet"));
        homeAddTCol.setCellValueFactory(new PropertyValueFactory<Student, String>("homeTown"));
        homeAddCountCol.setCellValueFactory(new PropertyValueFactory<Student, String>("homeCounty"));
        homeAddCountryCol.setCellValueFactory(new PropertyValueFactory<Student, String>("homeCountry"));
        homeAddZipCol.setCellValueFactory(new PropertyValueFactory<Student, String>("homeZip"));

        idCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        pwCol.setCellFactory(TextFieldTableCell.forTableColumn());
        statusCol.setCellFactory(TextFieldTableCell.forTableColumn());
        dormCol.setCellFactory(TextFieldTableCell.forTableColumn());
        currCourseCol.setCellFactory(TextFieldTableCell.forTableColumn());
        currYearCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        enrollYearCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        entQualCol.setCellFactory(TextFieldTableCell.forTableColumn());
        firstNCol.setCellFactory(TextFieldTableCell.forTableColumn());
        lastNCol.setCellFactory(TextFieldTableCell.forTableColumn());
        midNCol.setCellFactory(TextFieldTableCell.forTableColumn());
        genderCol.setCellFactory(ComboBoxTableCell.forTableColumn("M", "F"));
        phoneCol.setCellFactory(TextFieldTableCell.forTableColumn());
        emailCol.setCellFactory(TextFieldTableCell.forTableColumn());
        tutorCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        emPhonCol.setCellFactory(TextFieldTableCell.forTableColumn());
        emEmailCol.setCellFactory(TextFieldTableCell.forTableColumn());
        employerCol.setCellFactory(TextFieldTableCell.forTableColumn());
        addNotesCol.setCellFactory(TextFieldTableCell.forTableColumn());
        medicalCol.setCellFactory(TextFieldTableCell.forTableColumn());
        allergyCol.setCellFactory(TextFieldTableCell.forTableColumn());
        religionCol.setCellFactory(TextFieldTableCell.forTableColumn());
        imgCol.setCellFactory(TextFieldTableCell.forTableColumn());
        termAddNCol.setCellFactory(TextFieldTableCell.forTableColumn());
        termAddHCol.setCellFactory(TextFieldTableCell.forTableColumn());
        termAddSCol.setCellFactory(TextFieldTableCell.forTableColumn());
        termAddTCol.setCellFactory(TextFieldTableCell.forTableColumn());
        termAddCountCol.setCellFactory(TextFieldTableCell.forTableColumn());
        termAddCountryCol.setCellFactory(TextFieldTableCell.forTableColumn());
        termAddZipCol.setCellFactory(TextFieldTableCell.forTableColumn());
        homeAddNCol.setCellFactory(TextFieldTableCell.forTableColumn());
        homeAddHCol.setCellFactory(TextFieldTableCell.forTableColumn());
        homeAddSCol.setCellFactory(TextFieldTableCell.forTableColumn());
        homeAddTCol.setCellFactory(TextFieldTableCell.forTableColumn());
        homeAddCountCol.setCellFactory(TextFieldTableCell.forTableColumn());
        homeAddCountryCol.setCellFactory(TextFieldTableCell.forTableColumn());
        homeAddZipCol.setCellFactory(TextFieldTableCell.forTableColumn());
        ObservableList<Student> rows = FXCollections.observableArrayList();
        for (Student student : newStudents) {
            rows.add(student);
        }
        studentTV.setItems(rows);
        studentTV.setEditable(true);
        studentTV.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        editColumns();
    }

    public void populate() throws SQLException {
        List<Student> newStudent = fetchTable();
        populateTable(newStudent);
    }

    public void editColumns() {
        idCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Student, Integer>>) studentIntegerCellEditEvent -> ((Student) studentIntegerCellEditEvent.getTableView().getItems().get(studentIntegerCellEditEvent.getTablePosition().getRow())).setId(studentIntegerCellEditEvent.getNewValue()));
        pwCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Student, String>>) studentStringCellEditEvent -> ((Student) studentStringCellEditEvent.getTableView().getItems().get(studentStringCellEditEvent.getTablePosition().getRow())).setPassword(studentStringCellEditEvent.getNewValue()));
        statusCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Student, String>>) studentStringCellEditEvent -> ((Student) studentStringCellEditEvent.getTableView().getItems().get(studentStringCellEditEvent.getTablePosition().getRow())).setStatus(studentStringCellEditEvent.getNewValue()));
        dormCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Student, String>>) studentStringCellEditEvent -> ((Student) studentStringCellEditEvent.getTableView().getItems().get(studentStringCellEditEvent.getTablePosition().getRow())).setDormancyReason(studentStringCellEditEvent.getNewValue()));
        currCourseCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Student, String>>) studentStringCellEditEvent -> ((Student) studentStringCellEditEvent.getTableView().getItems().get(studentStringCellEditEvent.getTablePosition().getRow())).setCurrCourseCode(studentStringCellEditEvent.getNewValue()));
        currYearCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Student, Integer>>) studentIntegerCellEditEvent -> ((Student) studentIntegerCellEditEvent.getTableView().getItems().get(studentIntegerCellEditEvent.getTablePosition().getRow())).setCurrYear(studentIntegerCellEditEvent.getNewValue()));
        enrollYearCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Student, Integer>>) studentIntegerCellEditEvent -> ((Student) studentIntegerCellEditEvent.getTableView().getItems().get(studentIntegerCellEditEvent.getTablePosition().getRow())).setEnrolYear(studentIntegerCellEditEvent.getNewValue()));
        entQualCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Student, String>>) studentStringCellEditEvent -> ((Student) studentStringCellEditEvent.getTableView().getItems().get(studentStringCellEditEvent.getTablePosition().getRow())).setEntryQual(studentStringCellEditEvent.getNewValue()));
        firstNCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Student, String>>) studentStringCellEditEvent -> ((Student) studentStringCellEditEvent.getTableView().getItems().get(studentStringCellEditEvent.getTablePosition().getRow())).setFirstName(studentStringCellEditEvent.getNewValue()));
        lastNCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Student, String>>) studentStringCellEditEvent -> ((Student) studentStringCellEditEvent.getTableView().getItems().get(studentStringCellEditEvent.getTablePosition().getRow())).setSurname(studentStringCellEditEvent.getNewValue()));
        midNCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Student, String>>) studentStringCellEditEvent -> ((Student) studentStringCellEditEvent.getTableView().getItems().get(studentStringCellEditEvent.getTablePosition().getRow())).setMiddleName(studentStringCellEditEvent.getNewValue()));
        genderCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Student, String>>) studentStringCellEditEvent -> ((Student) studentStringCellEditEvent.getTableView().getItems().get(studentStringCellEditEvent.getTablePosition().getRow())).setGender(studentStringCellEditEvent.getNewValue()));
        phoneCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Student, String>>) studentStringCellEditEvent -> ((Student) studentStringCellEditEvent.getTableView().getItems().get(studentStringCellEditEvent.getTablePosition().getRow())).setPhoneNumber(studentStringCellEditEvent.getNewValue()));
        emailCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Student, String>>) studentStringCellEditEvent -> ((Student) studentStringCellEditEvent.getTableView().getItems().get(studentStringCellEditEvent.getTablePosition().getRow())).setEmail(studentStringCellEditEvent.getNewValue()));
        tutorCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Student, Integer>>) studentIntegerCellEditEvent -> ((Student) studentIntegerCellEditEvent.getTableView().getItems().get(studentIntegerCellEditEvent.getTablePosition().getRow())).setPersonalTutor(studentIntegerCellEditEvent.getNewValue()));
        emPhonCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Student, String>>) studentStringCellEditEvent -> ((Student) studentStringCellEditEvent.getTableView().getItems().get(studentStringCellEditEvent.getTablePosition().getRow())).setEmergContactPhone(studentStringCellEditEvent.getNewValue()));
        emEmailCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Student, String>>) studentStringCellEditEvent -> ((Student) studentStringCellEditEvent.getTableView().getItems().get(studentStringCellEditEvent.getTablePosition().getRow())).setEmergContactEmail(studentStringCellEditEvent.getNewValue()));
        employerCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Student, String>>) studentStringCellEditEvent -> ((Student) studentStringCellEditEvent.getTableView().getItems().get(studentStringCellEditEvent.getTablePosition().getRow())).setEmployer(studentStringCellEditEvent.getNewValue()));
        addNotesCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Student, String>>) studentStringCellEditEvent -> ((Student) studentStringCellEditEvent.getTableView().getItems().get(studentStringCellEditEvent.getTablePosition().getRow())).setAddNotes(studentStringCellEditEvent.getNewValue()));
        medicalCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Student, String>>) studentStringCellEditEvent -> ((Student) studentStringCellEditEvent.getTableView().getItems().get(studentStringCellEditEvent.getTablePosition().getRow())).setMedicalHistory(studentStringCellEditEvent.getNewValue()));
        allergyCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Student, String>>) studentStringCellEditEvent -> ((Student) studentStringCellEditEvent.getTableView().getItems().get(studentStringCellEditEvent.getTablePosition().getRow())).setMedicalAllergies(studentStringCellEditEvent.getNewValue()));
        religionCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Student, String>>) studentStringCellEditEvent -> ((Student) studentStringCellEditEvent.getTableView().getItems().get(studentStringCellEditEvent.getTablePosition().getRow())).setMedicalReligious(studentStringCellEditEvent.getNewValue()));
        imgCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Student, String>>) studentStringCellEditEvent -> ((Student) studentStringCellEditEvent.getTableView().getItems().get(studentStringCellEditEvent.getTablePosition().getRow())).setImage(studentStringCellEditEvent.getNewValue()));
        termAddNCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Student, String>>) studentStringCellEditEvent -> ((Student) studentStringCellEditEvent.getTableView().getItems().get(studentStringCellEditEvent.getTablePosition().getRow())).setAddTermNumber(studentStringCellEditEvent.getNewValue()));
        termAddHCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Student, String>>) studentStringCellEditEvent -> ((Student) studentStringCellEditEvent.getTableView().getItems().get(studentStringCellEditEvent.getTablePosition().getRow())).setAddTermHouse(studentStringCellEditEvent.getNewValue()));
        termAddTCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Student, String>>) studentStringCellEditEvent -> ((Student) studentStringCellEditEvent.getTableView().getItems().get(studentStringCellEditEvent.getTablePosition().getRow())).setAddTermTown(studentStringCellEditEvent.getNewValue()));
        termAddCountCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Student, String>>) studentStringCellEditEvent -> ((Student) studentStringCellEditEvent.getTableView().getItems().get(studentStringCellEditEvent.getTablePosition().getRow())).setAddTermCounty(studentStringCellEditEvent.getNewValue()));
        termAddCountryCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Student, String>>) studentStringCellEditEvent -> ((Student) studentStringCellEditEvent.getTableView().getItems().get(studentStringCellEditEvent.getTablePosition().getRow())).setAddTermCountry(studentStringCellEditEvent.getNewValue()));
        termAddZipCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Student, String>>) studentStringCellEditEvent -> ((Student) studentStringCellEditEvent.getTableView().getItems().get(studentStringCellEditEvent.getTablePosition().getRow())).setAddTermZip(studentStringCellEditEvent.getNewValue()));
        homeAddNCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Student, String>>) studentStringCellEditEvent -> ((Student) studentStringCellEditEvent.getTableView().getItems().get(studentStringCellEditEvent.getTablePosition().getRow())).setHomeNumber(studentStringCellEditEvent.getNewValue()));
        homeAddHCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Student, String>>) studentStringCellEditEvent -> ((Student) studentStringCellEditEvent.getTableView().getItems().get(studentStringCellEditEvent.getTablePosition().getRow())).setHomeHouse(studentStringCellEditEvent.getNewValue()));
        homeAddTCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Student, String>>) studentStringCellEditEvent -> ((Student) studentStringCellEditEvent.getTableView().getItems().get(studentStringCellEditEvent.getTablePosition().getRow())).setHomeTown(studentStringCellEditEvent.getNewValue()));
        homeAddCountCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Student, String>>) studentStringCellEditEvent -> ((Student) studentStringCellEditEvent.getTableView().getItems().get(studentStringCellEditEvent.getTablePosition().getRow())).setHomeCounty(studentStringCellEditEvent.getNewValue()));
        homeAddCountryCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Student, String>>) studentStringCellEditEvent -> ((Student) studentStringCellEditEvent.getTableView().getItems().get(studentStringCellEditEvent.getTablePosition().getRow())).setHomeCountry(studentStringCellEditEvent.getNewValue()));
        homeAddZipCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Student, String>>) studentStringCellEditEvent -> ((Student) studentStringCellEditEvent.getTableView().getItems().get(studentStringCellEditEvent.getTablePosition().getRow())).setHomeZip(studentStringCellEditEvent.getNewValue()));
        termAddSCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Student, String>>) studentStringCellEditEvent -> ((Student) studentStringCellEditEvent.getTableView().getItems().get(studentStringCellEditEvent.getTablePosition().getRow())).setAddTermStreet(studentStringCellEditEvent.getNewValue()));
        homeAddSCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Student, String>>) studentStringCellEditEvent -> ((Student) studentStringCellEditEvent.getTableView().getItems().get(studentStringCellEditEvent.getTablePosition().getRow())).setHomeStreet(studentStringCellEditEvent.getNewValue()));
    }

    public String[] getYears() {
        String[] years = new String[89];
        for (int x = 0; x<89; x++) {
            for (int i = 2019; i < 2099; i++) {
                years[x] = Integer.toString(i);
            }
        }
        return years;
    }

    public void updateTableEntries() throws SQLException {
        Student student = new Student();
        List <List<String>> sList = new ArrayList<>();
        for (int i = 0; i < studentTV.getItems().size(); i++) {
            student = (Student) studentTV.getItems().get(i);
            sList.add(new ArrayList<>());
            sList.get(i).add(Integer.toString(student.getId()));
            sList.get(i).add(Integer.toString(student.getPersonalTutor()));
            sList.get(i).add(student.getPassword());
            sList.get(i).add(student.getStatus());
            sList.get(i).add(student.getDormancyReason());
            sList.get(i).add(student.getFirstName());
            sList.get(i).add(student.getMiddleName());
            sList.get(i).add(student.getSurname());
            sList.get(i).add(student.getImage());
            sList.get(i).add(student.getGender());
            sList.get(i).add(student.getPhoneNumber());
            sList.get(i).add(student.getCurrCourseCode());
            sList.get(i).add(Integer.toString(student.getCurrYear()));
            sList.get(i).add(Integer.toString(student.getEnrolYear()));
            sList.get(i).add(student.getEntryQual());
            sList.get(i).add(student.getEmergContactPhone());
            sList.get(i).add(student.getEmergContactEmail());
            sList.get(i).add(student.getEmployer());
            sList.get(i).add(student.getAddNotes());
            sList.get(i).add(student.getMedicalHistory());
            sList.get(i).add(student.getMedicalAllergies());
            sList.get(i).add(student.getMedicalReligious());
            sList.get(i).add(student.getAddTermNumber());
            sList.get(i).add(student.getAddTermHouse());
            sList.get(i).add(student.getAddTermTown());
            sList.get(i).add(student.getAddTermCounty());
            sList.get(i).add(student.getAddTermCountry());
            sList.get(i).add(student.getAddTermZip());
            sList.get(i).add(student.getHomeNumber());
            sList.get(i).add(student.getHomeHouse());
            sList.get(i).add(student.getHomeTown());
            sList.get(i).add(student.getHomeCounty());
            sList.get(i).add(student.getHomeCountry());
            sList.get(i).add(student.getHomeZip());
            sList.get(i).add(student.getEmail());

            int getStudentID = student.getId();
            int getPT = student.getPersonalTutor();
            String getPass = student.getPassword();
            String getStatus = student.getStatus();
            String getDorm = student.getDormancyReason();
            String getFirstName = student.getFirstName();
            String getMidName = student.getMiddleName();
            String getSurname = student.getSurname();
            String getImg = student.getImage();
            String getGender = student.getGender();
            String getTelephone = student.getPhoneNumber();
            String getCurrCourseCode = student.getCurrCourseCode();
            int getCurrYear = student.getCurrYear();
            int getEnrollYear = student.getEnrolYear();
            String getQuals = student.getEntryQual();
            String getEmergPhone = student.getEmergContactPhone();
            String getEmergEmail = student.getEmergContactEmail();
            String getEmployer = student.getEmployer();
            String getAddNotes = student.getAddNotes();
            String getMedHistory = student.getMedicalHistory();
            String getAllergy = student.getMedicalAllergies();
            String getReligion = student.getMedicalReligious();
            String getAddN = student.getAddTermNumber();
            String getAddH = student.getAddTermHouse();
            String getAddT = student.getAddTermTown();
            String getAddCounty = student.getAddTermCounty();
            String getAddCountry = student.getAddTermCountry();
            String getAddZ = student.getAddTermZip();
            String getHomeN = student.getHomeNumber();
            String getHomeH = student.getHomeHouse();
            String getHomeT = student.getHomeTown();
            String getHomeCounty = student.getHomeCounty();
            String getHomeCountry = student.getHomeCountry();
            String getHomeZip = student.getHomeZip();
            String getEmail = student.getEmail();
            String getAddS = student.getAddTermStreet();
            String getHomeS = student.getHomeStreet();

            String dbURL = "jdbc:mysql://localhost:3306/rmsdb";
            String username = "root";
            String password = "root";
            Connection rmsConnection = DriverManager.getConnection(dbURL, username, password);
            Statement fetchStaff = rmsConnection.createStatement();
            String query = ("UPDATE students SET personal_tutor='" + getPT + "', password='" + getPass + "', status='" + getStatus + "', dormancy_reason='" + getDorm +
                    "', first_name='" + getFirstName + "', middle_name='" + getMidName + "', surname='" + getSurname + "', image='" + getImg + "', gender='" + getGender +
                    "', telephone='" + getTelephone + "', current_course_code='" + getCurrCourseCode + "', current_year='" + getCurrYear + "', enrollment_year='" + getEnrollYear +
                    "', entry_qualifications='" + getQuals + "', emergency_contact_phone='" + getEmergPhone + "', emergency_contact_email='" + getEmergEmail +
                    "', employer='" + getEmployer + "', additional_notes='" + getAddNotes + "', personal_tutor='" + getPT + "', medical_history='" + getMedHistory +
                    "', medical_allergies='" + getAllergy + "', medical_religious='" + getReligion + "', address_term_number='" + getAddN + "', address_term_house_name='" + getAddH +
                    "', address_term_street='" + getAddS + "', address_term_town='" + getAddT + "', address_term_county='" + getAddCounty + "', address_term_country='" + getAddCountry +
                    "', address_term_zip_code='" + getAddZ + "', noterm_address_number='" + getHomeN + "', noterm_address_house_name='" + getHomeH + "', noterm_address_street='" + getHomeS +
                    "', noterm_address_town='" + getHomeT + "', noterm_address_county='" + getHomeCounty + "', noterm_address_country='" + getHomeCountry + "', noterm_zip_code='" + getHomeZip +
                    "', email='" + getEmail + "' WHERE student_id='" + getStudentID + "'");
            Connection myConnection = DriverManager.getConnection(dbURL, username, password);
            PreparedStatement preparedStatement = myConnection.prepareStatement(query);
            preparedStatement.execute();
            System.out.println("Saved!");
        }
    }

    public void createStudent() throws IOException {
        FXMLLoader createStudentLoader = new FXMLLoader();
        createStudentLoader.setLocation(getClass().getResource("/FXMLview/NewStudentTab.fxml"));
        Scene scene = new Scene(createStudentLoader.load(), 1440,580);
        Stage stage = new Stage();
        stage.setTitle("Add a Student");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        Object temp = createStudentLoader.getController();
        NewStudentTabController controller = (NewStudentTabController) temp;
        controller.populateComboBoxes();
    }

    public void removeStudent() throws SQLException {
        Object selectedItems = studentTV.getSelectionModel().getSelectedItems().get(0);
        String dbUrl = "jdbc:mysql://localhost:3306/rmsdb";
        String username = "root";
        String password = "root";
        String query = ("DELETE FROM students WHERE student_id ='" + idCol.getCellData(selectedItems) + "'");
        Connection myConnection = DriverManager.getConnection(dbUrl, username, password);
        PreparedStatement preparedStatement = myConnection.prepareStatement(query);
        preparedStatement.execute();
        ObservableList<Student> allRows, singleRow;
        allRows = studentTV.getItems();
        singleRow = studentTV.getSelectionModel().getSelectedItems();
        singleRow.forEach(allRows::remove);
    }

    public void printTable() {
        Printer printer = Printer.getDefaultPrinter();
        PrinterJob printerJob = PrinterJob.createPrinterJob(printer);
        System.out.println("Printing.");
        if (printerJob != null) {
            System.out.println("Printing..");
            PageLayout pageLayout = printerJob.getPrinter().createPageLayout(Paper.A4, PageOrientation.LANDSCAPE, 0, 0, 0, 0);
            System.out.println("Printing...");
            boolean success = printerJob.printPage(pageLayout, studentTV);
            System.out.println("Printing");
            if (success) {
                printerJob.endJob();
            }
        }
    }
}

