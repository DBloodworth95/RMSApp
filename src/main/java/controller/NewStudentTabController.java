package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class NewStudentTabController {
    @FXML
    private TextField firstNameTF, midNameTF, surnameTF, passwordTF, termNTF, termSTF, termCountryTF, termHNTF,
            termTTF, termZTF, homeNTF, homeSTF, homeCountryTF, homeHNTF, homeTTF, homeZTF, currCourseTF, ptIDTF,
            emergPhoneTF, emergEmailTF, employerTF, addNoteTF, allergyTF, religionTF, medicalTF;
    @FXML
    private ComboBox genderCB, currYearCB, enrolYearCB, statusCB, entryQCB1, entryQCB2, entryQCB3, dormCB;
    @FXML
    private Button pictureBtn, saveBtn, mediSaveBtn;
}
