package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import java.io.IOException;
import java.sql.SQLException;
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
    private Button copyrightBtn, woodlandsBtn, twitterBtn, googleBtn, linkedInBtn, facebookBtn, wildeBtn,
            backBtn, fwdBtn, faqBtn, homeBtn, redoBtn, refreshBtn, undoBtn, zoomInBtn, zoomOutBtn, profileBtn, notifBtn, logoutBtn;
    @FXML
    private TabPane homePageTab;
    private Tab studentTab;



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


    public void createTab() throws IOException, SQLException {
        Tab studentTab = new Tab();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/HomePageTutorTab.fxml"));
        AnchorPane studentTabContent = loader.load();
        homePageTab.getTabs().add(studentTab);
        studentTab.setText("Home");
        studentTab.setContent(studentTabContent);
    }
}
