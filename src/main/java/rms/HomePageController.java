package rms;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HomePageController {
    @FXML
    private Text loginLabel;
    @FXML
    private Text lastLogLabel;
    @FXML
    private Text dateLabel;


    public void setLoginUsername(String name) {
        loginLabel.setText(name);
    }
    public void setLastLogLabel(String timestamp) {
        lastLogLabel.setText(timestamp);
    }
    public void setDateLabel()  {
        java.util.Date date=new java.util.Date();
        dateLabel.setText(String.valueOf(date));
    }
}
