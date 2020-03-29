package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.print.Paper;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;
import model.Attendant;
import model.Course;
import model.TablePrinter;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

public class AttendanceTabController {
    @FXML
    private TableView attendanceTV;
    @FXML
    private TableColumn studentCol, firstCol, surnameCol, attendedCol, causeCol, courseCol, moduleCol;
    @FXML
    private Button archiveBtn;

    public List<Attendant> fetchTable(boolean isArchive) throws SQLException {
        String dbURL = "jdbc:mysql://localhost:3306/rmsdb";
        String username = "root";
        String password = "root";
        Connection rmsConnection = DriverManager.getConnection(dbURL, username, password);
        Statement fetchStaff = rmsConnection.createStatement();
        List<Attendant> attendants = new ArrayList<>();
        if(isArchive) {
            ResultSet result = fetchStaff.executeQuery("SELECT * FROM attendance WHERE archived = 1");
            while (result.next()) {
                int id = Integer.parseInt(result.getString("student_id"));
                String firstName = result.getString("first_name");
                String surname = result.getString("surname");
                String attended = result.getString("attended");
                String cause = result.getString("cause_for_concern");
                String course = result.getString("course");
                String module = result.getString("module");
                attendants.add(new Attendant(id, firstName, surname, attended, cause, course, module));
            }
        } else {
            ResultSet result = fetchStaff.executeQuery("SELECT * FROM attendance WHERE archived = 0");
            while (result.next()) {
                int id = Integer.parseInt(result.getString("student_id"));
                String firstName = result.getString("first_name");
                String surname = result.getString("surname");
                String attended = result.getString("attended");
                String cause = result.getString("cause_for_concern");
                String course = result.getString("course");
                String module = result.getString("module");
                attendants.add(new Attendant(id, firstName, surname, attended, cause, course, module));
            }
        }
        return attendants;
    }

    public List<Attendant> fetchTableByCourse(boolean isArchive, String course) throws SQLException {
        String dbURL = "jdbc:mysql://localhost:3306/rmsdb";
        String username = "root";
        String password = "root";
        Connection rmsConnection = DriverManager.getConnection(dbURL, username, password);
        Statement fetchStaff = rmsConnection.createStatement();
        List<Attendant> attendants = new ArrayList<>();
        if (!isArchive) {
            ResultSet result = fetchStaff.executeQuery("SELECT * FROM attendance WHERE archived = 0 AND course ='" + course + "'");
            while (result.next()) {
                int id = Integer.parseInt(result.getString("student_id"));
                String firstName = result.getString("first_name");
                String surname = result.getString("surname");
                String attended = result.getString("attended");
                String cause = result.getString("cause_for_concern");
                String courseResult = result.getString("course");
                String module = result.getString("module");
                attendants.add(new Attendant(id, firstName, surname, attended, cause, courseResult, module));
            }
        }
        return attendants;
    }

    public void populateTable(List<Attendant> newAttendant) {
        studentCol.setCellValueFactory(new PropertyValueFactory<Attendant, Integer>("id"));
        firstCol.setCellValueFactory(new PropertyValueFactory<Attendant, String>("firstName"));
        surnameCol.setCellValueFactory(new PropertyValueFactory<Attendant, String>("surname"));
        attendedCol.setCellValueFactory(new PropertyValueFactory<Attendant, String>("attended"));
        causeCol.setCellValueFactory(new PropertyValueFactory<Attendant, String>("cause"));
        moduleCol.setCellValueFactory(new PropertyValueFactory<Attendant, String>("module"));
        courseCol.setCellValueFactory(new PropertyValueFactory<Attendant, String>("course"));


        studentCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        firstCol.setCellFactory(TextFieldTableCell.forTableColumn());
        surnameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        attendedCol.setCellFactory(TextFieldTableCell.forTableColumn());
        causeCol.setCellFactory(TextFieldTableCell.forTableColumn());
        moduleCol.setCellFactory(TextFieldTableCell.forTableColumn());
        courseCol.setCellFactory(TextFieldTableCell.forTableColumn());

        ObservableList<Attendant> rows = FXCollections.observableArrayList();
        for (Attendant attendant : newAttendant) {
            rows.add(attendant);
        }
        attendanceTV.setItems(rows);
        attendanceTV.setEditable(true);
        attendanceTV.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        editColumns();
    }

    public void populate() throws SQLException {
        List<Attendant> newAttendant = fetchTable(false);
        populateTable(newAttendant);
    }
    public void populateByCourse(String course) throws SQLException {
        List<Attendant> newAttendant = fetchTableByCourse(false, course);
        populateTable(newAttendant);
    }
    public void populateArchive() throws SQLException {
        List<Attendant> newAttendant = fetchTable(true);
        populateTable(newAttendant);
        archiveBtn.setVisible(false);
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
        moduleCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Attendant, String>>) courseStringCellEditEvent -> ((Attendant) courseStringCellEditEvent.getTableView().getItems().get(courseStringCellEditEvent.getTablePosition().getRow())).setModule(courseStringCellEditEvent.getNewValue()));
        courseCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Attendant, String>>) courseStringCellEditEvent -> ((Attendant) courseStringCellEditEvent.getTableView().getItems().get(courseStringCellEditEvent.getTablePosition().getRow())).setCourse(courseStringCellEditEvent.getNewValue()));
    }

    public void updateTable() throws SQLException {
        Attendant attendant;
        List <List<String>> attendanceList = new ArrayList<>();
        for (int i = 0; i < attendanceTV.getItems().size(); i++) {
            attendant = (Attendant) attendanceTV.getItems().get(i);
            attendanceList.add(new ArrayList<>());
            attendanceList.get(i).add(Integer.toString(attendant.getId()));
            attendanceList.get(i).add(attendant.getFirstName());
            attendanceList.get(i).add(attendant.getSurname());
            attendanceList.get(i).add(attendant.getAttended());
            attendanceList.get(i).add(attendant.getCause());
            attendanceList.get(i).add(attendant.getModule());
            attendanceList.get(i).add(attendant.getCourse());

            String dbURL = "jdbc:mysql://localhost:3306/rmsdb";
            String username = "root";
            String password = "root";
            String query = ("UPDATE attendance SET student_id='" + attendant.getId() + "', first_name='" + attendant.getFirstName() + "', surname='" + attendant.getSurname() +
                    "', attended='" + attendant.getAttended() + "', cause_for_concern='" + attendant.getCause() + "', course='" + attendant.getCourse() +
                    "', module='" + attendant.getModule() + "'WHERE student_id='" + attendant.getId() + "'");
            Connection myCon = DriverManager.getConnection(dbURL, username, password);
            PreparedStatement preparedStatement = myCon.prepareStatement(query);
            preparedStatement.execute();
        }
        System.out.println("Saved!");
    }

    public void removeAttendant() throws SQLException {
        Object selectedItems = attendanceTV.getSelectionModel().getSelectedItems().get(0);
        String dbUrl = "jdbc:mysql://localhost:3306/rmsdb";
        String username = "root";
        String password = "root";
        String query = ("DELETE FROM attendance WHERE student_id ='" + studentCol.getCellData(selectedItems) + "'");
        Connection myConnection = DriverManager.getConnection(dbUrl, username, password);
        PreparedStatement preparedStatement = myConnection.prepareStatement(query);
        preparedStatement.execute();
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
