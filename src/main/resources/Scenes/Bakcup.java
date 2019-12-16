
/*
package rms;

public class Bakcuppackage rms;

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
        import java.text.SimpleDateFormat;
        import java.util.Calendar;

public class Bakcup extends Application {
    Stage window;
    Scene loginScene;
    Stage primaryStage;
    Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
    @FXML
    private Text loginLabel;
    private TextField userField;
    private PasswordField passwordField;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        window = primaryStage;
        String backgroundURL = "file:images/university.jpeg";
        Image backgroundImg = new Image(backgroundURL);
        ImageView backgroundImage = new ImageView(backgroundImg);
        backgroundImage.setFitWidth(screenSize.getWidth());
        backgroundImage.setFitHeight(screenSize.getHeight());
        primaryStage.setTitle("Woodlands University - RMS System");
        BackgroundImage myBI= new BackgroundImage(new Image("file:images/WoodlandBG.png",screenSize.getWidth()/0,screenSize.getHeight()/0,false,true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);


        GridPane loginGrid = new GridPane();
        loginGrid.setBackground(new Background(myBI));
        loginGrid.setAlignment(Pos.CENTER);
        loginGrid.setHgap(5);
        loginGrid.setVgap(10);
        loginGrid.setMinHeight(screenSize.getHeight());
        loginGrid.setMinWidth(screenSize.getWidth());
        loginGrid.setPadding(new Insets(0,0,0,0));
        Text loginTitle = new Text("Welcome to Woodlands University RMS System.");
        loginTitle.setFont(Font.font("Ubuntu", FontWeight.BOLD, 20));
        loginGrid.add(loginTitle,1,1,1,1);
        Label userLabel = new Label("Username: ");
        Label passLabel = new Label("Password: ");
        TextField userField = new TextField();
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Your password...");
        loginGrid.add(userLabel, 0, 2);
        loginGrid.add(passLabel,0,3);
        loginGrid.add(userField,1,2);
        loginGrid.add(passwordField,1,3);
        HBox buttonBox = new HBox(10);
        HBox logoBox = new HBox(10);
        Button loginButton = new Button("Login");
        Button helpButton = new Button("Help");
        buttonBox.setAlignment(Pos.CENTER);
        logoBox.setAlignment(Pos.TOP_CENTER);
        logoBox.setPadding(new Insets(0,0,0,0));
        buttonBox.getChildren().addAll(loginButton, helpButton);
        loginGrid.add(logoBox,1,0);
        loginGrid.add(buttonBox, 1, 5);
        loginScene = new Scene(loginGrid,screenSize.getWidth()/0,screenSize.getHeight() / 0);
        String logoURL = "file:images/Woodlands.png";
        Image logo = new Image(logoURL);
        ImageView logoImg = new ImageView(logo);
        logoImg.setFitHeight(300);
        logoImg.setFitWidth(300);
        logoBox.getChildren().add(logoImg);
        RowConstraints r1 = new RowConstraints();
        RowConstraints r2 = new RowConstraints();
        r2.setPercentHeight(50);
        loginGrid.getRowConstraints().addAll(r1,r2);
        primaryStage.setScene(loginScene);
        primaryStage.show();
        Text loginFail = new Text();
        loginGrid.add(loginFail,1,6);



        loginButton.setOnAction(e -> {
            try {
                loginAttempt(e, passwordField, loginFail);
            } catch (SQLException | IOException ex) {
                ex.printStackTrace();
            }
        });
    }
    @FXML
    public void loginAttempt(ActionEvent e, TextField textField, Text text) throws SQLException, IOException {

        text.setFill(Color.FIREBRICK);
        text.setText("Incorrect username or password! Please try again...");

        String dbUrl = "jdbc:mysql://localhost:3306/rmsdb";
        String username = "root";
        String password = "root";
        String query = "SELECT * FROM users WHERE username ='" + textField.getText() + "'";
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

            if (pass.equals(textField.getText())) {

                Session session = new Session(userName, AccessLevel.fromInt(accesslevel), lastlogged, userId);

                Parent homePage = FXMLLoader.load(getClass().getResource("/Scenes/HomePagev2.fxml"));




                Calendar currentDate = Calendar.getInstance();
                SimpleDateFormat formatter= new SimpleDateFormat("YYYY-MM-dd hh:mm:ss");
                String dateNow = formatter.format(currentDate.getTime());
                System.out.println(dateNow);
                String updateLog = "UPDATE users SET last_logged='" + dateNow + "' WHERE user_id='" + session.getId() + "'";
                PreparedStatement updateLoggedin = myConnection.prepareStatement(updateLog);
                updateLoggedin.execute();

                Scene scene = new Scene(homePage);
                window.setScene(scene);
                window.show();
                System.out.println("Works");

            }
        }

    }
    @FXML
    public void test(ActionEvent event) {
        loginLabel.setText("Hi");
    }
}
 {
}
