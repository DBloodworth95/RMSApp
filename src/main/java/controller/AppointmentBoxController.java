package controller;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import view.PATWindow;

import java.io.IOException;
import java.sql.SQLException;

public class AppointmentBoxController {
    @FXML
    private Text studentT, idT, dateT;
    @FXML
    private HBox hBox;
    @FXML
    private Button uploadBtn;
    private int appointmentID;
    private String tutorID;
    private String[] tutorName;

    public void setValues(String[] student, String id, String date, String idOfTutor, String[] nameOfTutor, int idOfAppointment) {
        hBox.setMargin(hBox, new Insets(10,0,10,0));
        studentT.setText(student[0] + " " + student[1]);
        idT.setText(id);
        dateT.setText(date);
        tutorID = idOfTutor;
        tutorName = nameOfTutor;
        appointmentID = idOfAppointment;
    }

    public void openPATWindow() throws IOException, SQLException {
        new PATWindow(this, appointmentID);
    }

    public void setUploadBtnToUploaded() {
        uploadBtn.setText("PAT Uploaded");
        uploadBtn.setDisable(true);
    }

    public String getStudentName() {
        return studentT.getText();
    }

    public String getStudentID() {
        return idT.getText();
    }

    public String getTutorID() {
        return tutorID;
    }

    public String[] getTutorName() {
        return tutorName;
    }

    public int getAppointmentID() {
        return appointmentID;
    }
}
