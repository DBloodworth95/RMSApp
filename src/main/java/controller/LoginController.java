package controller;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.AccessLevel;
import model.Session;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import view.PasswordHelpWindow;

import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class LoginController {
    @FXML
    private TextField userField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;
    private FXMLLoader loader;
    @FXML
    private Label invalidIDLabel, invalidPWLabel;
    private boolean accountFound = false;


    public void loginAttempt() throws SQLException, IOException, ParseException {
        initial();
        String dbUrl = "jdbc:mysql://localhost:3306/rmsdb";
        String username = "root";
        String password = "root";
        String query = "SELECT * FROM users WHERE username ='" + userField.getText() + "'";
        System.out.println(query);
        Connection myConnection = DriverManager.getConnection(dbUrl, username, password);
        PreparedStatement myStatement = myConnection.prepareStatement(query);
        ResultSet myResultSet = myStatement.executeQuery();

        while (myResultSet.next()) {
            initial();
            accountFound = true;
            String userName = myResultSet.getString("username");
            Timestamp lastlogged = myResultSet.getTimestamp("last_logged");
            int accesslevel = myResultSet.getInt("user_level");
            String pass = myResultSet.getString("password");
            int userId = myResultSet.getInt("user_id");

            if (pass.equals(passwordField.getText())) {
                Session session = new Session(userName, AccessLevel.fromInt(accesslevel), lastlogged, userId);
                if (session.getAccessLevel().hasAccessToSysAdminView() || session.getAccessLevel().hasAccessToRecView()) {
                    loader = new FXMLLoader(getClass().getResource("/FXMLview/HomePageRecordsStaff.fxml"));
                } else if (session.getAccessLevel().hasAccessToCourseLeadView()) {
                    loader = new FXMLLoader(getClass().getResource("/FXMLview/HomePageCourseLeader.fxml"));
                } else {
                    loader = new FXMLLoader(getClass().getResource("/FXMLview/HomePageTutor.fxml"));
                }
                Parent homePage = loader.load();
                loginButton.getScene().setRoot(homePage);
                Object temp = loader.getController();
                HomePageController controller = (HomePageController) temp;
                controller.setLoginUsername(session.getUsername());
                controller.setLastLogLabel("Last logged in: " + session.getLastLogged());
                controller.setGhostSessionL(Integer.toString(session.getId()));
                controller.setDateLabel();
                controller.createHomeTab(session);
                Calendar currentDate = Calendar.getInstance();
                SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd hh:mm:ss");
                String dateNow = formatter.format(currentDate.getTime());
                System.out.println(dateNow);
                String updateLog = "UPDATE users SET last_logged='" + dateNow + "' WHERE user_id='" + session.getId() + "'";
                PreparedStatement updateLoggedin = myConnection.prepareStatement(updateLog);
                updateLoggedin.execute();
            } else {
                invalidPWLabel.setVisible(true);
            }
        }
        if(!accountFound)
        invalidIDLabel.setVisible(true);
    }
    public void initial() {
        invalidPWLabel.setVisible(false);
        invalidIDLabel.setVisible(false);
        accountFound = false;
    }

    public void keyPressed(KeyEvent key) throws ParseException, SQLException, IOException {
        if(key.getCode() == KeyCode.ENTER) {
            loginAttempt();
        }
    }

    public void showPasswordHelp() {
        PasswordHelpWindow.showPasswordWindow();
    }


}
