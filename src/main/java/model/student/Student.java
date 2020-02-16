package model.student;

public class Student {
    private int id, personalTutor;
    private String password, status, dormancyReason, firstName, middleName, surname, image, phoneNumber,
    currCourseCode, currYear, enrolYear, entryQual, emergContactPhone, emergContactEmail, employer, addNotes,
    medicalHistory, medicalAllergies, medicalReligious, termAddress, homeAddress;
    private Gender gender;

    public Student(int id, int personalTutor, String password, String status, String dormancyReason, String firstName, String middleName, String surname, String image, Gender gender, String phoneNumber, String currCourseCode, String currYear, String enrolYear, String entryQual, String emergContactPhone, String emergContactEmail, String employer, String addNotes, String medicalHistory, String medicalAllergies, String medicalReligious, String termAddress, String homeAddress) {
        this.id = id;
        this.personalTutor = personalTutor;
        this.password = password;
        this.status = status;
        this.dormancyReason = dormancyReason;
        this.firstName = firstName;
        this.middleName = middleName;
        this.surname = surname;
        this.image = image;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.currCourseCode = currCourseCode;
        this.currYear = currYear;
        this.enrolYear = enrolYear;
        this.entryQual = entryQual;
        this.emergContactPhone = emergContactPhone;
        this.emergContactEmail = emergContactEmail;
        this.employer = employer;
        this.addNotes = addNotes;
        this.medicalHistory = medicalHistory;
        this.medicalAllergies = medicalAllergies;
        this.medicalReligious = medicalReligious;
        this.termAddress = termAddress;
        this.homeAddress = homeAddress;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPersonalTutor() {
        return personalTutor;
    }

    public void setPersonalTutor(int personalTutor) {
        this.personalTutor = personalTutor;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDormancyReason() {
        return dormancyReason;
    }

    public void setDormancyReason(String dormancyReason) {
        this.dormancyReason = dormancyReason;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCurrCourseCode() {
        return currCourseCode;
    }

    public void setCurrCourseCode(String currCourseCode) {
        this.currCourseCode = currCourseCode;
    }

    public String getCurrYear() {
        return currYear;
    }

    public void setCurrYear(String currYear) {
        this.currYear = currYear;
    }

    public String getEnrolYear() {
        return enrolYear;
    }

    public void setEnrolYear(String enrolYear) {
        this.enrolYear = enrolYear;
    }

    public String getEntryQual() {
        return entryQual;
    }

    public void setEntryQual(String entryQual) {
        this.entryQual = entryQual;
    }

    public String getEmergContactPhone() {
        return emergContactPhone;
    }

    public void setEmergContactPhone(String emergContactPhone) {
        this.emergContactPhone = emergContactPhone;
    }

    public String getEmergContactEmail() {
        return emergContactEmail;
    }

    public void setEmergContactEmail(String emergContactEmail) {
        this.emergContactEmail = emergContactEmail;
    }

    public String getEmployer() {
        return employer;
    }

    public void setEmployer(String employer) {
        this.employer = employer;
    }

    public String getAddNotes() {
        return addNotes;
    }

    public void setAddNotes(String addNotes) {
        this.addNotes = addNotes;
    }

    public String getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    public String getMedicalAllergies() {
        return medicalAllergies;
    }

    public void setMedicalAllergies(String medicalAllergies) {
        this.medicalAllergies = medicalAllergies;
    }

    public String getMedicalReligious() {
        return medicalReligious;
    }

    public void setMedicalReligious(String medicalReligious) {
        this.medicalReligious = medicalReligious;
    }

    public String getTermAddress() {
        return termAddress;
    }

    public void setTermAddress(String termAddress) {
        this.termAddress = termAddress;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
