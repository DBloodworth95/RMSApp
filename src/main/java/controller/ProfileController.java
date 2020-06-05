package controller;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.sql.*;


public class ProfileController {
    @FXML
    private TextField usernameTF, passwordTF;
    @FXML
    private Label passwordChangeL;
    @FXML
    private ImageView profilePictureIMG;
    private int userID = 0;

    public void loadProfile(int id) throws SQLException {
        String dbURL = "jdbc:mysql://localhost:3306/rmsdb";
        String username = "root";
        String password = "root";
        Connection rmsConnection = DriverManager.getConnection(dbURL, username, password);
        Statement fetchStaff = rmsConnection.createStatement();
        ResultSet result = fetchStaff.executeQuery("SELECT * FROM users WHERE user_id ='" + id + "'");
        while (result.next()) {
            usernameTF.setText(result.getString("username"));
            passwordTF.setText(result.getString("password"));

        }
        profilePictureIMG.setImage(new Image("/FXMLview/Images/noprofile.png"));
        userID = id;
    }

    public void changePassword() throws SQLException {
        String dbURL = "jdbc:mysql://localhost:3306/rmsdb";
        String username = "root";
        String password = "root";
        String query = ("UPDATE users SET password='" + passwordTF.getText() + "'WHERE user_id='" + userID + "'");
        Connection myCon = DriverManager.getConnection(dbURL, username, password);
        PreparedStatement preparedStatement = myCon.prepareStatement(query);
        preparedStatement.execute();
        passwordChangeL.setVisible(true);
    }
}
