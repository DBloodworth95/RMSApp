package model;

public class PATForm {
    private String studentID, studentName, tutorID,
    tutorName, status, meetingType, nextMeetingDate, startTime, endTime, summary, actionPoints,
            tutorSig, studentSig;

    public PATForm(String studentID, String studentName, String tutorID, String tutorName, String status, String meetingType, String nextMeetingDate, String startTime, String endTime, String summary, String actionPoints, String tutorSig, String studentSig) {
        this.studentID = studentID;
        this.studentName = studentName;
        this.tutorID = tutorID;
        this.tutorName = tutorName;
        this.status = status;
        this.meetingType = meetingType;
        this.nextMeetingDate = nextMeetingDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.summary = summary;
        this.actionPoints = actionPoints;
        this.tutorSig = tutorSig;
        this.studentSig = studentSig;
    }
}
