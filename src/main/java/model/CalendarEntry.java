package model;

import javafx.scene.control.Button;

import java.time.LocalDate;
import java.util.Date;

public class CalendarEntry extends Button {
    private int entId;
    private String title, description;
    private int startTime, endTime;
    private LocalDate date;

    public CalendarEntry(int entId, String title, String description, int startTime, int endTime, LocalDate date) {
        CalendarEntry entry = new CalendarEntry();
        this.entId = entId;
        this.title = title;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.date = date;
    }

    public CalendarEntry() {

    }


    public int getentId() {
        return entId;
    }

    public void setEntId(int entId) {
        this.entId = entId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
