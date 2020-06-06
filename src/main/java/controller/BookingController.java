package controller;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


public class BookingController {
    @FXML
    private TableView bookingTV;
    @FXML
    private TableColumn roomCol, dateCol, timeCol, descCol;
    @FXML
    private ComboBox<String> roomCB, startCB, endCB;

    public void populateCB() {
        for(int i = 100; i < 300; i++) {
            roomCB.getItems().add("Room " + i);
        }
        for(int i = 0; i < 24; i++) {
            if(i < 10) {
                startCB.getItems().add("0" + i + ":00");
                endCB.getItems().add("0" + i + ":00");
            } else {
                startCB.getItems().add(i + ":00");
                endCB.getItems().add(i + ":00");
            }
        }
    }
}
