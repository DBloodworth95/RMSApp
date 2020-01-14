package rms;

public class Staff {
    private int staffID, noteID, medicalID;
    private String status, dormancyReason, firstName, middleName, surname, gender, address,
    phoneNumber, emailAddress, emergencyPhone, emergencyEmail, specialism;

    public Staff(int staffID, int noteID, int medicalID, String status, String dormancyReason, String firstName, String middleName, String surname, String gender, String address, String phoneNumber, String emailAddress, String emergencyPhone, String emergencyEmail, String specialism) {
        this.staffID = staffID;
        this.noteID = noteID;
        this.medicalID = medicalID;
        this.status = status;
        this.dormancyReason = dormancyReason;
        this.firstName = firstName;
        this.middleName = middleName;
        this.surname = surname;
        this.gender = gender;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.emergencyPhone = emergencyPhone;
        this.emergencyEmail = emergencyEmail;
        this.specialism = specialism;
    }

    public void setStaffID(int staffID) {
        this.staffID = staffID;
    }

    public void setNoteID(int noteID) {
        this.noteID = noteID;
    }

    public void setMedicalID(int medicalID) {
        this.medicalID = medicalID;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDormancyReason(String dormancyReason) {
        this.dormancyReason = dormancyReason;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setEmergencyPhone(String emergencyPhone) {
        this.emergencyPhone = emergencyPhone;
    }

    public void setEmergencyEmail(String emergencyEmail) {
        this.emergencyEmail = emergencyEmail;
    }

    public void setSpecialism(String specialism) {
        this.specialism = specialism;
    }

    public int getStaffID() {
        return staffID;
    }

    public int getNoteID() {
        return noteID;
    }

    public int getMedicalID() {
        return medicalID;
    }

    public String getStatus() {
        return status;
    }

    public String getDormancyReason() {
        return dormancyReason;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getSurname() {
        return surname;
    }

    public String getGender() {
        return gender;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getEmergencyPhone() {
        return emergencyPhone;
    }

    public String getEmergencyEmail() {
        return emergencyEmail;
    }

    public String getSpecialism() {
        return specialism;
    }
}
