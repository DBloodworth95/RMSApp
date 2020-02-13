package rms;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import org.graalvm.compiler.nodeinfo.StructuralInput;

//import java.awt.*;
import java.awt.Dimension;
import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CreateGUI extends Application {
    Stage window;
    Scene loginScene;
    Stage primaryStage;
    Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
    @FXML
    private Text passwordError;
    @FXML
    private TextField userField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        window = primaryStage;
        Parent loginPage = FXMLLoader.load(getClass().getResource("/Scenes/Login.fxml"));
        Scene scene = new Scene(loginPage);
        scene.getStylesheets().add("main.css");
        window.setScene(scene);
        window.show();
        window.setTitle("Woodlands RMS");

    }
    @FXML
    public void loginAttempt(ActionEvent event) throws SQLException, IOException, ParseException {

        //passwordError.setFill(Color.FIREBRICK);
       // passwordError.setText("Incorrect username or password! Please try again...");

        String dbUrl = "jdbc:mysql://localhost:3306/rmsdb";
        String username = "root";
        String password = "rootroot";
        String query = "SELECT * FROM users WHERE username ='" + userField.getText() + "'";
        System.out.println(query);
        Connection myConnection = DriverManager.getConnection(dbUrl, username, password);
        PreparedStatement myStatement = myConnection.prepareStatement(query);
        ResultSet myResultSet = myStatement.executeQuery();

        while (myResultSet.next()) {
            String userName = myResultSet.getString("username");
            Timestamp lastlogged = myResultSet.getTimestamp("last_logged");
            int accesslevel = myResultSet.getInt("user_level");
            String pass = myResultSet.getString("password");
            int userId = myResultSet.getInt("user_id");

            if (pass.equals(passwordField.getText())) {
//must check access level and open corresponding home page
                Session session = new Session(userName, AccessLevel.fromInt(accesslevel), lastlogged, userId);
                //Parent homePage = FXMLLoader.load(getClass().getResource("/Scenes/HomePagev2.fxml"));
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Scenes/HomePageRecordsStaff.fxml"));

                //HomePageRecordsStaff
                Parent homePage = loader.load();
                loginButton.getScene().setRoot(homePage);
                Object temp = loader.getController();
                HomePageController controller = (HomePageController) temp;
                controller.setLoginUsername("Logged in as " + session.getUsername());
                controller.setLastLogLabel("Last logged in: " + session.getLastLogged());
                controller.setDateLabel();
                controller.createTab();
                //controller.getBtnImages();

                System.out.println("Works");

                Calendar currentDate = Calendar.getInstance();
                SimpleDateFormat formatter= new SimpleDateFormat("YYYY-MM-dd hh:mm:ss");
                String dateNow = formatter.format(currentDate.getTime());
                System.out.println(dateNow);
                String updateLog = "UPDATE users SET last_logged='" + dateNow + "' WHERE user_id='" + session.getId() + "'";
                PreparedStatement updateLoggedin = myConnection.prepareStatement(updateLog);
                updateLoggedin.execute();


                //loginStage.setScene(new Scene(homePage));
                //loginStage.show();

                System.out.println("Works");

            }
        }

    }
    @FXML
    public void test(ActionEvent event) {

    }
}
