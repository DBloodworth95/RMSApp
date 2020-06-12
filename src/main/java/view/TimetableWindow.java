package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import jfxtras.icalendarfx.VCalendar;
import jfxtras.scene.control.agenda.icalendar.ICalendarAgenda;

import java.time.LocalDateTime;
import java.time.Period;

public class TimetableWindow {

    public TimetableWindow() {
        VCalendar newCal = new VCalendar();
        ICalendarAgenda agenda = new ICalendarAgenda(newCal);
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 1366, 768);
        Stage stage = new Stage();
        setupComponents(root, agenda);
        root.setCenter(agenda);
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();
    }

    private void setupComponents(BorderPane root, ICalendarAgenda agenda) {
        Button saveButton = new Button("Save Timetable");

        HBox buttonBox = new HBox(saveButton);
        root.setTop(buttonBox);

        saveButton.setOnAction((e)-> {
            LocalDateTime newLocalDateTime = agenda.getDisplayedLocalDateTime().plus(Period.ofWeeks(1));
            agenda.setDisplayedLocalDateTime(newLocalDateTime);
        });
    }
}
