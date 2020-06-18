package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Appointment;
import model.PATForm;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.*;

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
//13
            String dbURL = "jdbc:mysql://localhost:3306/rmsdb";
            String username = "root";
            String password = "root";
            String query = ("INSERT INTO pats (student_id, student_name, tutor_id, tutor_name, status, meeting_type, next_meeting_date, start_time, end_time, summary, action_points, tutor_sig, student_sig, appointment_id) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            Connection myCon = null;
            try {
                myCon = DriverManager.getConnection(dbURL, username, password);
                PreparedStatement preparedStatement = myCon.prepareStatement(query);
                preparedStatement.setString(1, patForm.getStudentID());
                preparedStatement.setString(2, patForm.getStudentName());
                preparedStatement.setString(3, patForm.getTutorID());
                preparedStatement.setString(4, patForm.getTutorName());
                preparedStatement.setString(5, patForm.getStatus());
                preparedStatement.setString(6, patForm.getMeetingType());
                preparedStatement.setString(7, patForm.getNextMeetingDate());
                preparedStatement.setString(8, patForm.getStartTime());
                preparedStatement.setString(9, patForm.getEndTime());
                preparedStatement.setString(10, patForm.getSummary());
                preparedStatement.setString(11, patForm.getActionPoints());
                preparedStatement.setString(12, patForm.getStudentSig());
                preparedStatement.setString(13, patForm.getTutorSig());
                preparedStatement.setInt(14, appointmentID);
                preparedStatement.executeUpdate();
                System.out.println(patForm.getActionPoints());
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            appointmentBoxController.setUploadBtnToUploaded();
            Stage stage = (Stage) saveBtn.getScene().getWindow();
            stage.close();
            System.out.println(appointmentID);
        });
    }

    public void populateFields(String studentID, String studentName, String tutorID, String[] tutorName) {
        studentIDTF.setText(studentID);
        studentNameTF.setText(studentName);
        tutorIDTF.setText(tutorID);
        tutorNameTF.setText(tutorName[0] + " " + tutorName[1]);
    }

    public void loadFields(int appointmentID) throws SQLException {
        String dbURL = "jdbc:mysql://localhost:3306/rmsdb";
        String username = "root";
        String password = "root";
        Connection rmsConnection = DriverManager.getConnection(dbURL, username, password);
        Statement fetchStaff = rmsConnection.createStatement();
        ResultSet result;
        System.out.println(appointmentID);
        result = fetchStaff.executeQuery("SELECT * FROM pats WHERE appointment_id='" + appointmentID + "'");
        while (result.next()) {
            studentIDTF.setText(result.getString("student_id"));
        }
    }
}
