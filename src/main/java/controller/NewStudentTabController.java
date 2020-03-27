package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.sql.*;

public class NewStudentTabController {
    @FXML
    private TextField firstNameTF, midNameTF, surnameTF, passwordTF, termNTF, termSTF, termCountryTF, termHNTF,
            termTTF, termZTF, homeNTF, homeSTF, homeCountryTF, homeHNTF, homeTTF, homeZTF, currCourseTF, ptIDTF,
            emergPhoneTF, emergEmailTF, employerTF, allergyTF, homeCountyTF, termCountyTF;
    @FXML
    private ComboBox genderCB, currYearCB, enrolYearCB, statusCB, entryQCB1, entryQCB2, entryQCB3, dormCB;
    @FXML
    private Button pictureBtn, saveBtn, mediSaveBtn;
    @FXML
    private TextArea addNoteTF, religionTF, medicalTF;
    private Alert errorAlert = new Alert(Alert.AlertType.INFORMATION);

    public void createStudent() throws SQLException {
        if(!verifyFilledInputFields()) {
            String dbURL = "jdbc:mysql://localhost:3306/rmsdb";
            String username = "root";
            String password = "root";
            String query = "INSERT INTO students(password, status, dormancy_reason, first_name, middle_name, surname, image, gender, telephone, current_course_code, current_year, enrollment_year, entry_qualifications, emergency_contact_phone, emergency_contact_email, employer, additional_notes, personal_tutor, medical_history, medical_allergies, medical_religious, address_term_number, address_term_house_name, address_term_street, address_term_town, address_term_county, address_term_country, " +
                    "address_term_zip_code, noterm_address_number, noterm_address_house_name, noterm_address_street, noterm_address_town, noterm_address_county, noterm_address_country, noterm_zip_code, email) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            Connection myConnection = DriverManager.getConnection(dbURL, username, password);
            PreparedStatement preparedStatement = myConnection.prepareStatement(query);
            preparedStatement.setString(1, passwordTF.getText());
            preparedStatement.setString(2, statusCB.getValue().toString());
            if(!statusCB.getValue().toString().equals("Dormant")) {
                preparedStatement.setString(3, "Inactive");
            } else {
                preparedStatement.setString(3, dormCB.getValue().toString());
            }
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
            preparedStatement.setString(19, medicalTF.getText());
            preparedStatement.setString(20, allergyTF.getText());
            preparedStatement.setString(21, religionTF.getText());
            preparedStatement.setInt(22, Integer.parseInt(termNTF.getText()));
            preparedStatement.setString(23, termHNTF.getText());
            preparedStatement.setString(24, termSTF.getText());
            preparedStatement.setString(25, termTTF.getText());
            preparedStatement.setString(26, termCountyTF.getText());
            preparedStatement.setString(27, termCountryTF.getText());
            preparedStatement.setString(28, termZTF.getText());
            preparedStatement.setInt(29, Integer.parseInt(homeNTF.getText()));
            preparedStatement.setString(30, homeHNTF.getText());
            preparedStatement.setString(31, homeSTF.getText());
            preparedStatement.setString(32, homeTTF.getText());
            preparedStatement.setString(33, homeCountyTF.getText());
            preparedStatement.setString(34, homeCountryTF.getText());
            preparedStatement.setString(35, homeZTF.getText());
            preparedStatement.setString(36, emergEmailTF.getText());
            preparedStatement.executeUpdate();
            System.out.println("Student Created!");
            Stage stage = (Stage) saveBtn.getScene().getWindow();
            stage.close();
        }
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

    private boolean verifyFilledInputFields() {
        TextField[] inputForms = new TextField[] {
                firstNameTF, midNameTF, surnameTF, passwordTF, termNTF, termSTF, termCountryTF, termHNTF,
                termTTF, termZTF, homeNTF, homeSTF, homeCountryTF, homeHNTF, homeTTF, homeZTF, currCourseTF, ptIDTF,
                emergPhoneTF, emergEmailTF, employerTF, allergyTF, homeCountyTF, termCountyTF
        };
        ComboBox[] comboForms = new ComboBox[] {
                genderCB, currYearCB, enrolYearCB, statusCB, entryQCB1, entryQCB2, entryQCB3, dormCB
        };
        TextArea[] inputFormsTA = new TextArea[] {
                addNoteTF, religionTF, medicalTF
        };
        for (TextField inputForm : inputForms) {
            if (inputForm.getText() == null || inputForm.getText().trim().isEmpty()) {
                errorAlert.setTitle("Record entry failed.");
                errorAlert.setHeaderText(null);
                errorAlert.setContentText("Some fields were left empty!");
                errorAlert.showAndWait();
                return true;
            }
        }
        for (ComboBox comboBox : comboForms) {
            if (comboBox.getValue() == null) {
                errorAlert.setTitle("Record entry failed.");
                errorAlert.setHeaderText(null);
                errorAlert.setContentText("Some fields were left empty!");
                errorAlert.showAndWait();
                return true;
            }
        }
        for (TextArea textArea : inputFormsTA) {
            if (textArea.getText().isEmpty()) {
                errorAlert.setTitle("Record entry failed.");
                errorAlert.setHeaderText(null);
                errorAlert.setContentText("Some fields were left empty!");
                errorAlert.showAndWait();
                return true;
            }
        }
        if(statusCB.getValue().toString().equals("Dormant") && dormCB.getValue().toString().equals("Inactive")) {
            errorAlert.setTitle("Record entry failed.");
            errorAlert.setHeaderText(null);
            errorAlert.setContentText("Entry cannot be Dormant and Inactive at the same time!");
            errorAlert.showAndWait();
            return true;
        }
        return false;
    }

    public void checkForSave(StudentTabController studentTabController) {
        saveBtn.setOnAction((e)-> {
            try {
                createStudent();
                studentTabController.populate();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
    }
}
