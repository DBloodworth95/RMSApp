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
    private Student student;
    private TempMedicalStorage tempMedicalStorage;

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
}
