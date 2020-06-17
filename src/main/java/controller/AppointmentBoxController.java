package controller;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import view.PATWindow;

import java.io.IOException;

public class AppointmentBoxController {
    @FXML
    private Text studentT, idT, dateT;
    @FXML
    private HBox hBox;
    @FXML
    private Button uploadBtn;

    private String tutorID;
    private String[] tutorName;

    public void setValues(String[] student, String id, String date, String idOfTutor, String[] nameOfTutor) {
        hBox.setMargin(hBox, new Insets(10,0,10,0));
        studentT.setText(student[0] + " " + student[1]);
        idT.setText(id);
        dateT.setText(date);
        tutorID = idOfTutor;
        tutorName = nameOfTutor;
    }

    public void openPATWindow() throws IOException {
        new PATWindow(this);
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
}
