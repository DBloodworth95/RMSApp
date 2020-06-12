package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import jfxtras.icalendarfx.VCalendar;
import jfxtras.scene.control.agenda.icalendar.ICalendarAgenda;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.Period;

public class TimetableWindow {

    public TimetableWindow() {
        VCalendar newCal = new VCalendar();
        ICalendarAgenda agenda = new ICalendarAgenda(newCal);
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 1366, 768);
        Stage stage = new Stage();
        setupComponents(root, newCal);
        root.setCenter(agenda);
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();
    }

    private void setupComponents(BorderPane root, VCalendar calendar) {
        Button saveButton = new Button("Save Timetable");

        HBox buttonBox = new HBox(saveButton);
        root.setTop(buttonBox);

        saveButton.setOnAction((e)-> {
            FileChooser fileSaver = new FileChooser();
            fileSaver.setTitle("Save cause for concern form");
            File destination = fileSaver.showSaveDialog(root.getScene().getWindow());
            try {
                if (destination != null) {
                    PrintWriter timetableFile = new PrintWriter(destination + ".txt");
                    timetableFile.println(calendar.toString());
                    System.out.println(calendar.toString());
                }
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        });
    }
}
