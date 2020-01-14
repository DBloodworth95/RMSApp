package rms;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    @FXML
    private Button copyrightBtn, woodlandsBtn, twitterBtn, googleBtn, linkedInBtn, facebookBtn, wildeBtn,
            backBtn, fwdBtn, faqBtn, homeBtn, redoBtn, refreshBtn, undoBtn, zoomInBtn, zoomOutBtn;

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
    public void getBtnImages() {

        //copyrightBtn.setId("copyrightBtn");

        Image copyrightImage = new Image(getClass().getResourceAsStream("/Scenes/Images/copyrightBtn.png"));
        copyrightBtn.setGraphic(new ImageView(copyrightImage));
        Image woodlandsImage = new Image(getClass().getResourceAsStream("/Scenes/Images/woodlandsBtn.png"));
        woodlandsBtn.setGraphic(new ImageView(woodlandsImage));
        Image facebookImage = new Image(getClass().getResourceAsStream("/Scenes/Images/facebookBtn.png"));
        facebookBtn.setGraphic(new ImageView(facebookImage));
        Image googleImage = new Image(getClass().getResourceAsStream("/Scenes/Images/googleBtn.png"));
        googleBtn.setGraphic(new ImageView(googleImage));
        Image linkedInImage = new Image(getClass().getResourceAsStream("/Scenes/Images/linkedInBtn.png"));
        linkedInBtn.setGraphic(new ImageView(linkedInImage));
        Image twitterImage = new Image(getClass().getResourceAsStream("/Scenes/Images/twitterBtn.png"));
        twitterBtn.setGraphic(new ImageView(twitterImage));
        Image wildeImage = new Image(getClass().getResourceAsStream("/Scenes/Images/wildeBtn.png"));
        wildeBtn.setGraphic(new ImageView(wildeImage));
        Image backImage = new Image(getClass().getResourceAsStream("/Scenes/Images/backBtn.png"));
        backBtn.setGraphic(new ImageView(backImage));
        Image fwdImage = new Image(getClass().getResourceAsStream("/Scenes/Images/fwdBtn.png"));
        fwdBtn.setGraphic(new ImageView(fwdImage));
        Image faqImage = new Image(getClass().getResourceAsStream("/Scenes/Images/faqBtn.png"));
        faqBtn.setGraphic(new ImageView(faqImage));
        Image homeImage = new Image(getClass().getResourceAsStream("/Scenes/Images/homeBtn.png"));
        homeBtn.setGraphic(new ImageView(homeImage));
        Image redoImage = new Image(getClass().getResourceAsStream("/Scenes/Images/redoBtn.png"));
        redoBtn.setGraphic(new ImageView(redoImage));
        Image refreshImage = new Image(getClass().getResourceAsStream("/Scenes/Images/refreshBtn.png"));
        refreshBtn.setGraphic(new ImageView(refreshImage));
        Image undoImage = new Image(getClass().getResourceAsStream("/Scenes/Images/undoBtn.png"));
        undoBtn.setGraphic(new ImageView(undoImage));
        Image zoomInImage = new Image(getClass().getResourceAsStream("/Scenes/Images/zoomInBtn.png"));
        zoomInBtn.setGraphic(new ImageView(zoomInImage));
        Image zoomOutImage = new Image(getClass().getResourceAsStream("/Scenes/Images/zoomOutBtn.png"));
        zoomOutBtn.setGraphic(new ImageView(zoomOutImage));

    }
}
