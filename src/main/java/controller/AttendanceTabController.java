package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.print.Paper;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;
import model.Attendant;
import model.Course;
import model.TablePrinter;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;

import java.util.ArrayList;
import java.util.List;

public class AttendanceTabController {
    @FXML
    private TableView attendanceTV;
    @FXML
    private TableColumn studentCol, firstCol, surnameCol, attendedCol, causeCol;
    @FXML
    private DatePicker datePicker;
    @FXML
    private ComboBox<String> courseCB, moduleCB;

    public void populateCourseCB(String courseVal) {
        courseCB.getItems().add(courseVal);
        courseCB.setValue(courseVal);
    }

    public void populateModuleCB(String courseVal) throws SQLException {
        String dbURL = "jdbc:mysql://localhost:3306/rmsdb";
        String username = "root";
        String password = "root";
        Connection rmsConnection = DriverManager.getConnection(dbURL, username, password);
        Statement fetchStaff = rmsConnection.createStatement();
        ResultSet result = fetchStaff.executeQuery("SELECT module_code FROM modules WHERE course ='" + courseVal + "'");
        while (result.next()) {
            moduleCB.getItems().add(result.getString("module_code"));
        }
    }

    public List<Attendant> fetchFromFile(String fileConvention) throws IOException {
        File dir = Paths.get("attendance_records/").toFile();
        File[] fileListings = dir.listFiles();
        List<Attendant> attendantListInFile = null;
        List<Attendant> attendantList = new ArrayList<>();
        for (File file : fileListings) {
            if (file.getPath().equalsIgnoreCase("attendance_records/" + fileConvention)) {
                try (ObjectInput inputStream = new ObjectInputStream(new FileInputStream(file))) {
                    try {
                        while ((attendantListInFile = (List<Attendant>) inputStream.readObject()) != null) {
                            attendantList.addAll(attendantListInFile);
                            break;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return attendantList;
    }

    public void generate() {
        try {
            populate();
        } catch (IOException | SQLException ex) {
            ex.printStackTrace();
        }
    }


    public List<Attendant> fetchStudentsOnCourse(String courseVal) throws SQLException {
        String dbURL = "jdbc:mysql://localhost:3306/rmsdb";
        String username = "root";
        String password = "root";
        Connection rmsConnection = DriverManager.getConnection(dbURL, username, password);
        Statement fetchStaff = rmsConnection.createStatement();
        List<Attendant> attendants = new ArrayList<>();
        ResultSet result = fetchStaff.executeQuery("SELECT * FROM students WHERE current_course_code ='" + courseVal + "'");
        while (result.next()) {
            int id = Integer.parseInt(result.getString("student_id"));
            String firstName = result.getString("first_name");
            String surname = result.getString("surname");
            String attended = "N";
            String cause = "N/A";
            attendants.add(new Attendant(id, firstName, surname, attended, cause));
        }
        return attendants;
    }

    public void populateTable(List<Attendant> newAttendant) {
        studentCol.setCellValueFactory(new PropertyValueFactory<Attendant, Integer>("id"));
        firstCol.setCellValueFactory(new PropertyValueFactory<Attendant, String>("firstName"));
        surnameCol.setCellValueFactory(new PropertyValueFactory<Attendant, String>("surname"));
        attendedCol.setCellValueFactory(new PropertyValueFactory<Attendant, String>("attended"));
        causeCol.setCellValueFactory(new PropertyValueFactory<Attendant, String>("cause"));

        studentCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        firstCol.setCellFactory(TextFieldTableCell.forTableColumn());
        surnameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        attendedCol.setCellFactory(TextFieldTableCell.forTableColumn());
        causeCol.setCellFactory(TextFieldTableCell.forTableColumn());

        ObservableList<Attendant> rows = FXCollections.observableArrayList();
        for (Attendant attendant : newAttendant) {
            rows.add(attendant);
        }
        attendanceTV.setItems(rows);
        attendanceTV.setEditable(true);
        attendanceTV.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        editColumns();
    }

    public void populate() throws IOException, SQLException {
        String[] dateVal = datePicker.getValue().toString().split("-");
        String[] moduleVal = moduleCB.getValue().split("-");
        String day = dateVal[0];
        String month = dateVal[1];
        String year = dateVal[2];
        String code = moduleVal[0];
        String start = moduleVal[1];
        String end = moduleVal[2];
        List<Attendant> newAttendant = fetchFromFile(day + month + year + code + start + end);
        if (!newAttendant.isEmpty()) {
            System.out.println("File found");
            populateTable(newAttendant);
        }
        else {
            newAttendant = fetchStudentsOnCourse(courseCB.getValue());
            populateTable(newAttendant);
            System.out.println("No File found");
        }
    }

    public void editColumns() {
        studentCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Attendant, Integer>>) courseIntegerCellEditEvent -> ((Attendant) courseIntegerCellEditEvent.getTableView().getItems().get(courseIntegerCellEditEvent.getTablePosition().getRow())).setId(courseIntegerCellEditEvent.getNewValue()));
        firstCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Attendant, String>>) courseStringCellEditEvent -> ((Attendant) courseStringCellEditEvent.getTableView().getItems().get(courseStringCellEditEvent.getTablePosition().getRow())).setFirstName(courseStringCellEditEvent.getNewValue()));
        surnameCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Attendant, String>>) courseStringCellEditEvent -> ((Attendant) courseStringCellEditEvent.getTableView().getItems().get(courseStringCellEditEvent.getTablePosition().getRow())).setSurname(courseStringCellEditEvent.getNewValue()));
        attendedCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Attendant, String>>) courseIntegerCellEditEvent -> ((Attendant) courseIntegerCellEditEvent.getTableView().getItems().get(courseIntegerCellEditEvent.getTablePosition().getRow())).setAttended(courseIntegerCellEditEvent.getNewValue()));
        causeCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Attendant, String>>) courseStringCellEditEvent -> ((Attendant) courseStringCellEditEvent.getTableView().getItems().get(courseStringCellEditEvent.getTablePosition().getRow())).setCause(courseStringCellEditEvent.getNewValue()));
    }

    public void updateTable() throws SQLException {
        Path path = Paths.get("attendance_records");
        String[] dateVal = datePicker.getValue().toString().split("-");
        String[] moduleVal = moduleCB.getValue().split("-");
        String day = dateVal[0];
        String month = dateVal[1];
        String year = dateVal[2];
        String code = moduleVal[0];
        String start = moduleVal[1];
        String end = moduleVal[2];
        Attendant attendant;
        List<Attendant> attendanceList = new ArrayList<>();
        for (int i = 0; i < attendanceTV.getItems().size(); i++) {
            attendant = (Attendant) attendanceTV.getItems().get(i);
            attendant.setId((Integer) studentCol.getCellData(i));
            attendant.setFirstName((String) firstCol.getCellData(i));
            attendant.setSurname((String) surnameCol.getCellData(i));
            attendant.setCause((String) causeCol.getCellData(i));
            attendant.setAttended((String) attendedCol.getCellData(i));
            attendanceList.add(attendant);
            System.out.println(attendant.getAttended());
        }
        try (ObjectOutput oos = new ObjectOutputStream(new FileOutputStream(String.valueOf(path.resolve(day + month + year + code + start + end))))) {
            oos.writeObject(attendanceList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeAttendant() {
        Object selectedItems = attendanceTV.getSelectionModel().getSelectedItems().get(0);
        ObservableList<Course> allRows, singleRow;
        allRows = attendanceTV.getItems();
        singleRow = attendanceTV.getSelectionModel().getSelectedItems();
        singleRow.forEach(allRows::remove);
        System.out.println("Removed selected items!");
    }

    public void printTable() {
        TablePrinter tablePrinter = new TablePrinter();
        tablePrinter.print(Paper.A4, attendanceTV);
    }
}
