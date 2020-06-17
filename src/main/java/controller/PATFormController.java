package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PATFormController {
    @FXML
    private Button saveBtn;
    @FXML
    private TextField studentIDTF, studentNameTF, tutorIDTF, tutorNameTF;

    public void setSaveBtnListener(AppointmentBoxController appointmentBoxController) {
        saveBtn.setOnAction(e -> {
            appointmentBoxController.setUploadBtnToUploaded();
            Stage stage = (Stage) saveBtn.getScene().getWindow();
            stage.close();
        });
    }

    public void populateFields(String studentID, String studentName, String tutorID, String[] tutorName) {
        studentIDTF.setText(studentID);
        studentNameTF.setText(studentName);
        tutorIDTF.setText(tutorID);
        tutorNameTF.setText(tutorName[0] + " " + tutorName[1]);
    }
}
