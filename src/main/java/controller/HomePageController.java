package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import model.Session;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class HomePageController {
    @FXML
    private Text loginLabel;
    @FXML
    private Text lastLogLabel;
    @FXML
    private Text dateLabel;
    @FXML
    private TabPane homePageTabPane;
    private FXMLLoader loader;

    public void setLoginUsername(String name) {
        loginLabel.setText(name);
    }
    public void setLastLogLabel(String timestamp) {
        lastLogLabel.setText(timestamp);
    }
    public void setDateLabel() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat formatDate = new SimpleDateFormat("MMM-dd");
        String dateForLabel = formatDate.format(cal.getTime());
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                String string = new SimpleDateFormat("HH:mm:ss").format(new Date());
                dateLabel.setText(dateForLabel + " " + string);
            }
        }, 0, 1000);
    }

    public void createHomeTab(Session session) throws IOException {
        Tab homeTab = new Tab();
        if (session.getAccessLevel().hasAccessToSysAdminView() || session.getAccessLevel().hasAccessToRecView()) {
            loader = new FXMLLoader(getClass().getResource("/view/HomePageRecordsStaffTab.fxml"));
        } else {
            loader = new FXMLLoader(getClass().getResource("/view/HomePageTutorTab.fxml"));
        }
        AnchorPane homeTabContent = loader.load();
        homePageTabPane.getTabs().add(homeTab);
        homeTab.setText("Home");
        homeTab.setContent(homeTabContent);
    }

    public void createStudentTab() throws IOException {
        Tab studentTab = new Tab();
        loader = new FXMLLoader(getClass().getResource("/view/StudentTab.fxml"));
        AnchorPane studentTabContent = loader.load();
        homePageTabPane.getTabs().add(studentTab);
        studentTab.setText("Students");
        studentTab.setContent(studentTabContent);
    }

    public void createStaffTab() throws IOException {
        Tab staffTab = new Tab();
        loader = new FXMLLoader(getClass().getResource("/view/StaffTab.fxml"));
        AnchorPane staffTabContent = loader.load();
        homePageTabPane.getTabs().add(staffTab);
        staffTab.setText("Staff");
        staffTab.setContent(staffTabContent);
    }

    public void createModuleTab() throws IOException {
        Tab moduleTab = new Tab();
        loader = new FXMLLoader(getClass().getResource("/view/ModulesTab.fxml"));
        AnchorPane moduleTabContent = loader.load();
        homePageTabPane.getTabs().add(moduleTab);
        moduleTab.setText("Modules");
        moduleTab.setContent(moduleTabContent);
    }

    public void createCourseTab() throws IOException {
        Tab courseTab = new Tab();
        loader = new FXMLLoader(getClass().getResource("/view/CoursesTab.fxml"));
        AnchorPane courseTabContent = loader.load();
        homePageTabPane.getTabs().add(courseTab);
        courseTab.setText("Courses");
        courseTab.setContent(courseTabContent);
    }

    public void createAssessmentTabRS() throws IOException {
        Tab assessmentTab = new Tab();
        loader = new FXMLLoader(getClass().getResource("/view/AssessmentsTabRecordsStaff.fxml"));
        AnchorPane assessmentTabContent = loader.load();
        homePageTabPane.getTabs().add(assessmentTab);
        assessmentTab.setText("Assessments");
        assessmentTab.setContent(assessmentTabContent);
    }

    public void createAssessmentTabT() throws IOException {
        Tab assessmentTab = new Tab();
        loader = new FXMLLoader(getClass().getResource("/view/AssessmentsTabTutor.fxml"));
        AnchorPane assessmentTabContent = loader.load();
        homePageTabPane.getTabs().add(assessmentTab);
        assessmentTab.setText("Assessments");
        assessmentTab.setContent(assessmentTabContent);
    }

    public void createGradesTabRS() throws IOException {
        Tab gradesTab = new Tab();
        loader = new FXMLLoader(getClass().getResource("/view/GradesTabRecordsStaff.fxml"));
        AnchorPane gradesTabContent = loader.load();
        homePageTabPane.getTabs().add(gradesTab);
        gradesTab.setText("Grades");
        gradesTab.setContent(gradesTabContent);
    }

    public void createGradesTabT() throws IOException {
        Tab gradesTab = new Tab();
        loader = new FXMLLoader(getClass().getResource("/view/GradesTabTutor.fxml"));
        AnchorPane gradesTabContent = loader.load();
        homePageTabPane.getTabs().add(gradesTab);
        gradesTab.setText("Grades");
        gradesTab.setContent(gradesTabContent);
    }

    public void createAttendanceTabRS() throws IOException {
        Tab attendanceTab = new Tab();
        loader = new FXMLLoader(getClass().getResource("/view/AttendanceTabRecordsStaff.fxml"));
        AnchorPane attendanceTabContent = loader.load();
        homePageTabPane.getTabs().add(attendanceTab);
        attendanceTab.setText("Attendance");
        attendanceTab.setContent(attendanceTabContent);
    }

    public void createAttendanceTabT() throws IOException {
        Tab attendanceTab = new Tab();
        loader = new FXMLLoader(getClass().getResource("/view/AttendanceTabTutor.fxml"));
        AnchorPane attendanceTabContent = loader.load();
        homePageTabPane.getTabs().add(attendanceTab);
        attendanceTab.setText("Attendance");
        attendanceTab.setContent(attendanceTabContent);
    }

    public void createTutorshipTabRS() throws IOException {
        Tab tutorshipTab = new Tab();
        loader = new FXMLLoader(getClass().getResource("/view/PersonalTutorshipRecordsStaffTab.fxml"));
        AnchorPane tutorshipTabContent = loader.load();
        homePageTabPane.getTabs().add(tutorshipTab);
        tutorshipTab.setText("Personal Tutorship");
        tutorshipTab.setContent(tutorshipTabContent);
    }

    public void createTutorshipTabT() throws IOException {
        Tab tutorshipTab = new Tab();
        loader = new FXMLLoader(getClass().getResource("/view/PersonalTutorshipTutorTab.fxml"));
        AnchorPane tutorshipTabContent = loader.load();
        homePageTabPane.getTabs().add(tutorshipTab);
        tutorshipTab.setText("Personal Tutorship");
        tutorshipTab.setContent(tutorshipTabContent);
    }
}
