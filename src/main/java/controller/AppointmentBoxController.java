package controller;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class AppointmentBoxController {
    @FXML
    private Text studentT, idT, dateT;
    @FXML
    private HBox hBox;

    public void setValues(String student, String id, String date) {
        hBox.setMargin(hBox, new Insets(10,0,10,0));
        studentT.setText(student);
        idT.setText(id);
        dateT.setText(date);
    }
}
