package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.student.Student;
import model.student.TempMedicalStorage;

import java.io.IOException;
import java.sql.*;

public class NewStudentTabController {
    @FXML
    private TextField firstNameTF, midNameTF, surnameTF, passwordTF, termNTF, termSTF, termCountryTF, termHNTF,
            termTTF, termZTF, homeNTF, homeSTF, homeCountryTF, homeHNTF, homeTTF, homeZTF, currCourseTF, ptIDTF,
            emergPhoneTF, emergEmailTF, employerTF, allergyTF;
    @FXML
    private ComboBox genderCB, currYearCB, enrolYearCB, statusCB, entryQCB1, entryQCB2, entryQCB3, dormCB;
    @FXML
    private Button pictureBtn, saveBtn, mediSaveBtn;
    @FXML
    private TextArea addNoteTF, religionTF, medicalTF;
    public TempMedicalStorage tempMedicalStorage = new TempMedicalStorage();

    public void openMedical() throws IOException {
        FXMLLoader medicalLoader = new FXMLLoader();
        medicalLoader.setLocation(getClass().getResource("/FXMLview/MedicalFileTab.fxml"));
        Scene scene = new Scene(medicalLoader.load(), 1440,580);
        Stage stage = new Stage();
        stage.setTitle("Add a Student");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void createStudent() throws SQLException {
        String dbURL = "jdbc:mysql://localhost:3306/rmsdb";
        String username = "root";
        String password = "root";
        String query = "INSERT INTO students(password, status, dormancy_reason, first_name, middle_name, surname, image, gender, telephone, current_course_code, current_year, enrollment_year, entry_qualifications, emergency_contact_phone, emergency_contact_email, employer, additional_notes, personal_tutor, medical_history, medical_allergies, medical_religious, address_term_number, address_term_house_name, address_term_street, address_term_town, address_term_county, address_term_country, " +
               "address_term_zip_code, noterm_address_number, noterm_address_house_name, noterm_address_street, noterm_address_town, noterm_address_county, noterm_address_country, noterm_zip_code, email) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        Connection myConnection = DriverManager.getConnection(dbURL, username, password);
        PreparedStatement preparedStatement = myConnection.prepareStatement(query);
        System.out.println(tempMedicalStorage.getAllergies());
        preparedStatement.setString(1, passwordTF.getText());
        preparedStatement.setString(2, statusCB.getValue().toString());
        preparedStatement.setString(3, dormCB.getValue().toString());
        preparedStatement.setString(4, firstNameTF.getText());
        preparedStatement.setString(5, midNameTF.getText());
        preparedStatement.setString(6, surnameTF.getText());
        preparedStatement.setString(7, "image");
        preparedStatement.setString(8, genderCB.getValue().toString());
        preparedStatement.setString(9, termHNTF.getText());
        preparedStatement.setInt(10, Integer.parseInt(currCourseTF.getText()));
        preparedStatement.setInt(11, Integer.parseInt(currYearCB.getValue().toString()));
        preparedStatement.setInt(12, Integer.parseInt(enrolYearCB.getValue().toString()));
        preparedStatement.setString(13, entryQCB1.getValue().toString() + ", " + entryQCB2.getValue().toString() + ", " + entryQCB3.getValue().toString());
        preparedStatement.setString(14, emergPhoneTF.getText());
        preparedStatement.setString(15, emergEmailTF.getText());
        preparedStatement.setString(16, employerTF.getText());
        preparedStatement.setString(17, addNoteTF.getText());
        preparedStatement.setInt(18, Integer.parseInt(ptIDTF.getText()));
        preparedStatement.setString(19, tempMedicalStorage.getMedicalHistory());
        preparedStatement.setString(20, tempMedicalStorage.getAllergies());
        preparedStatement.setString(21, tempMedicalStorage.getReligious());
        preparedStatement.setInt(22, Integer.parseInt(termNTF.getText()));
        preparedStatement.setString(23, termHNTF.getText());
        preparedStatement.setString(24, termSTF.getText());
        preparedStatement.setString(25, termTTF.getText());
        preparedStatement.setString(26, "county");
        preparedStatement.setString(27, termCountryTF.getText());
        preparedStatement.setString(28, termZTF.getText());
        preparedStatement.setInt(29, Integer.parseInt(homeNTF.getText()));
        preparedStatement.setString(30, homeHNTF.getText());
        preparedStatement.setString(31, homeSTF.getText());
        preparedStatement.setString(32, homeTTF.getText());
        preparedStatement.setString(33, "county");
        preparedStatement.setString(34, homeCountryTF.getText());
        preparedStatement.setString(35, homeZTF.getText());
        preparedStatement.setString(36, emergEmailTF.getText());
        preparedStatement.executeUpdate();
        System.out.println("Student Created!");
    }

    public void createMedicalFile() {
        tempMedicalStorage.setAllergies(allergyTF.getText());
        tempMedicalStorage.setMedicalHistory(medicalTF.getText());
        tempMedicalStorage.setReligious(religionTF.getText());
        System.out.println("Medical file Created!");
        System.out.println(allergyTF.getText());
        System.out.println(tempMedicalStorage.getAllergies());
    }

    private void populateYearCB() {
        for (int i = 2018; i<3000; i++) {
            enrolYearCB.getItems().addAll(String.valueOf(i));
        }
    }

    public void populateComboBoxes() {
        genderCB.getItems().addAll("M", "F");
        currYearCB.getItems().addAll("1", "2", "3");
        statusCB.getItems().addAll("Provisional", "Live", "Dormant");
        dormCB.getItems().addAll("Graduated", "Withdrawn", "Terminated");
        entryQCB1.getItems().addAll("A+", "A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D+", "D", "D-", "E", "F", "G");
        entryQCB2.getItems().addAll("A+", "A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D+", "D", "D-", "E", "F", "G");
        entryQCB3.getItems().addAll("A+", "A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D+", "D", "D-", "E", "F", "G");
        populateYearCB();
    }
}
