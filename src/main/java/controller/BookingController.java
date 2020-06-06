package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import model.Assessment;
import model.Booking;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class BookingController {
    @FXML
    private TableView bookingTV;
    @FXML
    private TableColumn roomCol, dateCol, timeCol, descCol;
    @FXML
    private ComboBox<String> roomCB, startCB, endCB;

    public void populateCB() {
        for (int i = 100; i < 300; i++) {
            roomCB.getItems().add("Room " + i);
        }
        for (int i = 0; i < 24; i++) {
            if (i < 10) {
                startCB.getItems().add("0" + i + ":00");
                endCB.getItems().add("0" + i + ":00");
            } else {
                startCB.getItems().add(i + ":00");
                endCB.getItems().add(i + ":00");
            }
        }
    }

    public List<Booking> fetchBookings() throws SQLException {
        String dbURL = "jdbc:mysql://localhost:3306/rmsdb";
        String username = "root";
        String password = "root";
        Connection rmsConnection = DriverManager.getConnection(dbURL, username, password);
        Statement fetchStaff = rmsConnection.createStatement();
        List<Booking> bookings = new ArrayList<>();
        ResultSet result = fetchStaff.executeQuery("SELECT * FROM bookings");
        while (result.next()) {
            int id = Integer.parseInt(result.getString("id"));
            String room = result.getString("room");
            String start = result.getString("start");
            String end = result.getString("end");
            String date = result.getString("date");
            String desc = result.getString("description");
            bookings.add(new Booking(id, room, date, start, end, desc, "0"));
        }
        return bookings;
    }

    public void populateTable(List<Booking> bookingList) {
        roomCol.setCellValueFactory(new PropertyValueFactory<Booking, String>("room"));
        dateCol.setCellValueFactory(new PropertyValueFactory<Booking, String>("date"));
        timeCol.setCellValueFactory(new PropertyValueFactory<Booking, String>("time"));
        descCol.setCellValueFactory(new PropertyValueFactory<Booking, String>("description"));

        roomCol.setCellFactory(TextFieldTableCell.forTableColumn());
        dateCol.setCellFactory(TextFieldTableCell.forTableColumn());
        timeCol.setCellFactory(TextFieldTableCell.forTableColumn());
        descCol.setCellFactory(TextFieldTableCell.forTableColumn());
        ObservableList<Booking> rows = FXCollections.observableArrayList();
        rows.addAll(bookingList);
        bookingTV.setItems(rows);
    }

    public void displayTable() throws SQLException {
        List<Booking> bookingList = fetchBookings();
        populateTable(bookingList);
    }
}
