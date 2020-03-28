package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.print.Paper;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;
import model.Appointment;
import model.TablePrinter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonalTutorTabController {

    @FXML
    private TableView tutorTV;
    @FXML
    private TableColumn studentCol, studentFCol, studentSCol, tutorCol, tutorFCol, tutorSCol, courseCol, moduleCol, yearCol, appCol, patCol;

    public List<Appointment> fetchTable(boolean isArchive) throws SQLException {
        String dbURL = "jdbc:mysql://localhost:3306/rmsdb";
        String username = "root";
        String password = "root";
        Connection rmsConnection = DriverManager.getConnection(dbURL, username, password);
        Statement fetchApp = rmsConnection.createStatement();
        List<Appointment> appointments = new ArrayList<>();
        if(isArchive) {
            ResultSet result = fetchApp.executeQuery("SELECT * FROM appointments WHERE archived = 1");
            while (result.next()) {
                int studentID = Integer.parseInt(result.getString("student_id"));
                int tutorID = Integer.parseInt(result.getString("tutor_id"));
                String studentFN = result.getString("student_first_name");
                String studentS = result.getString("student_surname");
                String tutorFN = result.getString("tutor_first_name");
                String tutorS = result.getString("tutor_surname");
                String course = result.getString("course");
                String module = result.getString("module");
                String year = result.getString("year");
                String date = result.getString("date");
                String pat = result.getString("pat_form");

                appointments.add(new Appointment(studentID, tutorID, studentFN, studentS, tutorFN, tutorS, course, module, year, date, pat));
            }
        } else {
            ResultSet result = fetchApp.executeQuery("SELECT * FROM appointments WHERE archived = 0");
            while (result.next()) {
                int studentID = Integer.parseInt(result.getString("student_id"));
                int tutorID = Integer.parseInt(result.getString("tutor_id"));
                String studentFN = result.getString("student_first_name");
                String studentS = result.getString("student_surname");
                String tutorFN = result.getString("tutor_first_name");
                String tutorS = result.getString("tutor_surname");
                String course = result.getString("course");
                String module = result.getString("module");
                String year = result.getString("year");
                String date = result.getString("date");
                String pat = result.getString("pat_form");

                appointments.add(new Appointment(studentID, tutorID, studentFN, studentS, tutorFN, tutorS, course, module, year, date, pat));
            }
        }
        return appointments;
    }

    public void populateTable(List<Appointment> newAppointment) {
        studentCol.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("studentId"));
        studentFCol.setCellValueFactory(new PropertyValueFactory<Appointment, String>("studentFN"));
        studentSCol.setCellValueFactory(new PropertyValueFactory<Appointment, String>("studentS"));
        tutorCol.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("tutorId"));
        tutorFCol.setCellValueFactory(new PropertyValueFactory<Appointment, String>("tutorFN"));
        tutorSCol.setCellValueFactory(new PropertyValueFactory<Appointment, String>("tutorS"));
        courseCol.setCellValueFactory(new PropertyValueFactory<Appointment, String>("course"));
        moduleCol.setCellValueFactory(new PropertyValueFactory<Appointment, String>("module"));
        yearCol.setCellValueFactory(new PropertyValueFactory<Appointment, String>("year"));
        appCol.setCellValueFactory(new PropertyValueFactory<Appointment, String>("appDate"));
        patCol.setCellValueFactory(new PropertyValueFactory<Appointment, String>("patForm"));

        studentCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        studentFCol.setCellFactory(TextFieldTableCell.forTableColumn());
        studentSCol.setCellFactory(TextFieldTableCell.forTableColumn());
        tutorCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        tutorFCol.setCellFactory(TextFieldTableCell.forTableColumn());
        tutorSCol.setCellFactory(TextFieldTableCell.forTableColumn());
        courseCol.setCellFactory(TextFieldTableCell.forTableColumn());
        moduleCol.setCellFactory(TextFieldTableCell.forTableColumn());
        yearCol.setCellFactory(TextFieldTableCell.forTableColumn());
        appCol.setCellFactory(TextFieldTableCell.forTableColumn());
        patCol.setCellFactory(TextFieldTableCell.forTableColumn());
        ObservableList<Appointment> rows = FXCollections.observableArrayList();
        for (Appointment appointment : newAppointment) {
            rows.add(appointment);
        }
        tutorTV.setItems(rows);
        tutorTV.setEditable(true);
        tutorTV.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        editColumns();
    }

    public void populate() throws SQLException {
        List<Appointment> newAppointment = fetchTable(false);
        populateTable(newAppointment);
    }
    public void populateArchive() throws SQLException {
        List<Appointment> newAppointment = fetchTable(true);
        populateTable(newAppointment);
    }

    public void editColumns() {
        studentCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Appointment, Integer>>) appointmentIntegerCellEditEvent -> ((Appointment) appointmentIntegerCellEditEvent.getTableView().getItems().get(appointmentIntegerCellEditEvent.getTablePosition().getRow())).setStudentId(appointmentIntegerCellEditEvent.getNewValue()));
        studentFCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Appointment, String>>) appointmentStringCellEditEvent -> ((Appointment) appointmentStringCellEditEvent.getTableView().getItems().get(appointmentStringCellEditEvent.getTablePosition().getRow())).setStudentFN(appointmentStringCellEditEvent.getNewValue()));
        studentSCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Appointment, String>>) appointmentStringCellEditEvent -> ((Appointment) appointmentStringCellEditEvent.getTableView().getItems().get(appointmentStringCellEditEvent.getTablePosition().getRow())).setStudentS(appointmentStringCellEditEvent.getNewValue()));
        tutorCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Appointment, Integer>>) appointmentIntegerCellEditEvent -> ((Appointment) appointmentIntegerCellEditEvent.getTableView().getItems().get(appointmentIntegerCellEditEvent.getTablePosition().getRow())).setTutorId(appointmentIntegerCellEditEvent.getNewValue()));
        tutorFCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Appointment, String>>) appointmentStringCellEditEvent -> ((Appointment) appointmentStringCellEditEvent.getTableView().getItems().get(appointmentStringCellEditEvent.getTablePosition().getRow())).setTutorFN(appointmentStringCellEditEvent.getNewValue()));
        tutorSCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Appointment, String>>) appointmentStringCellEditEvent -> ((Appointment) appointmentStringCellEditEvent.getTableView().getItems().get(appointmentStringCellEditEvent.getTablePosition().getRow())).setTutorS(appointmentStringCellEditEvent.getNewValue()));
        courseCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Appointment, String>>) appointmentStringCellEditEvent -> ((Appointment) appointmentStringCellEditEvent.getTableView().getItems().get(appointmentStringCellEditEvent.getTablePosition().getRow())).setCourse(appointmentStringCellEditEvent.getNewValue()));
        moduleCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Appointment, String>>) appointmentStringCellEditEvent -> ((Appointment) appointmentStringCellEditEvent.getTableView().getItems().get(appointmentStringCellEditEvent.getTablePosition().getRow())).setModule(appointmentStringCellEditEvent.getNewValue()));
        yearCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Appointment, String>>) appointmentStringCellEditEvent -> ((Appointment) appointmentStringCellEditEvent.getTableView().getItems().get(appointmentStringCellEditEvent.getTablePosition().getRow())).setYear(appointmentStringCellEditEvent.getNewValue()));
        appCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Appointment, String>>) appointmentStringCellEditEvent -> ((Appointment) appointmentStringCellEditEvent.getTableView().getItems().get(appointmentStringCellEditEvent.getTablePosition().getRow())).setAppDate(appointmentStringCellEditEvent.getNewValue()));
        patCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Appointment, String>>) appointmentStringCellEditEvent -> ((Appointment) appointmentStringCellEditEvent.getTableView().getItems().get(appointmentStringCellEditEvent.getTablePosition().getRow())).setPatForm(appointmentStringCellEditEvent.getNewValue()));
    }

    public void updateTable() throws SQLException {
        Appointment appointment;
        List <List<String>> appList = new ArrayList<>();
        for (int i = 0; i < tutorTV.getItems().size(); i++) {
            appointment = (Appointment) tutorTV.getItems().get(i);
            appList.add(new ArrayList<>());
            appList.get(i).add(Integer.toString(appointment.getStudentId()));
            appList.get(i).add(appointment.getStudentFN());
            appList.get(i).add(appointment.getStudentS());
            appList.get(i).add(Integer.toString(appointment.getTutorId()));
            appList.get(i).add(appointment.getTutorFN());
            appList.get(i).add(appointment.getTutorS());
            appList.get(i).add(appointment.getCourse());
            appList.get(i).add(appointment.getModule());
            appList.get(i).add(appointment.getYear());
            appList.get(i).add(appointment.getAppDate());
            appList.get(i).add(appointment.getPatForm());

            String dbURL = "jdbc:mysql://localhost:3306/rmsdb";
            String username = "root";
            String password = "root";
            String query = ("UPDATE appointments SET student_id='" + appointment.getStudentId() + "', student_first_name='" + appointment.getStudentFN() + "', student_surname='" + appointment.getStudentS() +
                    "', tutor_id='" + appointment.getTutorId() + "', tutor_first_name='" + appointment.getTutorFN() + "', tutor_surname='" + appointment.getTutorS() +
                    "', course='" + appointment.getCourse() + "', module='" + appointment.getModule() + "', year='" + appointment.getYear() + "', date='" + appointment.getAppDate() +
                    "', pat_form='" + appointment.getPatForm() + "'WHERE student_id='" + appointment.getStudentId() + "'");
            Connection myCon = DriverManager.getConnection(dbURL, username, password);
            PreparedStatement preparedStatement = myCon.prepareStatement(query);
            preparedStatement.execute();
        }
        System.out.println("Saved!");
    }

    public void printTable() {
        TablePrinter tablePrinter = new TablePrinter();
        tablePrinter.print(Paper.A4, tutorTV);
    }
}
