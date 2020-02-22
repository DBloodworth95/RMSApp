package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class NewStaffTabController {
    @FXML
    private TextField idTF, firstNameTF, middleNameTF, surnameTF, pwTF, addNumberTF, houseNTF, houseSTF, houseTTF, countyTF, countryTF, zipTF, phoneTF, emailTF,
            emergPTF, emergETF;
    @FXML
    private ComboBox genderCB, statusCB, dormCB, specialismCB;
    @FXML
    private TextArea allergyTA, religiousTA, addNoteTA, medicalHTA;
    @FXML
    private Button saveBtn;

    public void createStaff() throws SQLException {
        String dbURL = "jdbc:mysql://localhost:3306/rmsdb";
        String username = "root";
        String password = "root";
        String query = "INSERT INTO staff(status, dormancy_reason, first_name, middle_name, surname, gender, address_number, address_house_name, address_street, address_town, address_county, " +
                "address_country, zip_code, telephone, email_address, emergency_contact_phone, emergency_contact_email, specialist_subject, resume, additional_notes, medical_history, medical_allergies, medical_religious, image) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        Connection myConnection = DriverManager.getConnection(dbURL, username, password);
        PreparedStatement preparedStatement = myConnection.prepareStatement(query);
        preparedStatement.setString(1, statusCB.getValue().toString());
        preparedStatement.setString(2, dormCB.getValue().toString());
        preparedStatement.setString(3, firstNameTF.getText());
        preparedStatement.setString(4, middleNameTF.getText());
        preparedStatement.setString(5, surnameTF.getText());
        preparedStatement.setString(6, genderCB.getValue().toString());
        preparedStatement.setString(7, addNumberTF.getText());
        preparedStatement.setString(8, houseNTF.getText());
        preparedStatement.setString(9, houseSTF.getText());
        preparedStatement.setString(10, houseTTF.getText());
        preparedStatement.setString(11, countyTF.getText());
        preparedStatement.setString(12, countryTF.getText());
        preparedStatement.setString(13, zipTF.getText());
        preparedStatement.setString(14, phoneTF.getText());
        preparedStatement.setString(15, emailTF.getText());
        preparedStatement.setString(16, emergPTF.getText());
        preparedStatement.setString(17, emergETF.getText());
        preparedStatement.setString(18, specialismCB.getValue().toString());
        preparedStatement.setString(19, "resume");
        preparedStatement.setString(20, addNoteTA.getText());
        preparedStatement.setString(21, medicalHTA.getText());
        preparedStatement.setString(22, allergyTA.getText());
        preparedStatement.setString(23, religiousTA.getText());
        preparedStatement.setString(24, "image");
        preparedStatement.executeUpdate();
        System.out.println("Student Created!");
        Stage stage = (Stage) saveBtn.getScene().getWindow();
        stage.close();
    }

    public void populateCB() {
        genderCB.getItems().addAll("M", "F");
        statusCB.getItems().addAll("Provisional", "Live", "Dormant");
        dormCB.getItems().addAll("Graduated", "Withdrawn", "Terminated");
        specialismCB.getItems().addAll("Databases", "AI", "Software Development", "Web Development", "Systems");
    }
}
