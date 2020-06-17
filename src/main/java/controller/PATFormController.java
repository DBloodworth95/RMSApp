package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.PATForm;

public class PATFormController {
    @FXML
    private Button saveBtn;
    @FXML
    private TextField studentIDTF, studentNameTF, tutorIDTF, tutorNameTF, tutorSigTF, studentSigTF;
    @FXML
    private TextArea summaryTA, actionPointsTA;
    @FXML
    private ComboBox<String> statusCB, meetingCB, startCB, endCB;
    @FXML
    private DatePicker nextMeetingDP;

    public void setSaveBtnListener(AppointmentBoxController appointmentBoxController, int appointmentID) {
        saveBtn.setOnAction(e -> {
            PATForm patForm = new PATForm(studentIDTF.getText(), studentNameTF.getText(), tutorIDTF.getText(), tutorNameTF.getText(), statusCB.getValue(),
                    meetingCB.getValue(), nextMeetingDP.getValue().toString(), startCB.getValue(), endCB.getValue(), summaryTA.getText(), actionPointsTA.getText(),
                    tutorSigTF.getText(), studentSigTF.getText());
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
