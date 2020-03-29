package controller;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class AppointmentBoxController {
    @FXML
    private Text studentT, idT, dateT;

    public void setValues(String student, String id, String date) {
        studentT.setText(student);
        idT.setText(id);
        dateT.setText(date);
    }
}
