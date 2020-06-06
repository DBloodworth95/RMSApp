package view;

import controller.BookingController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class BookingWindow {

    public BookingWindow() throws IOException {
        FXMLLoader createCourseLoader = new FXMLLoader();
        createCourseLoader.setLocation(getClass().getResource("/FXMLview/BookRoomTab.fxml"));
        Scene scene = new Scene(createCourseLoader.load(), 1448, 580);
        Stage stage = new Stage();
        stage.setTitle("Settings");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setMaximized(false);
        stage.show();
        Object temp = createCourseLoader.getController();
        BookingController controller = (BookingController) temp;
        controller.populateCB();
    }
}
