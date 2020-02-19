package model.student;

public class TempMedicalStorage {

    private String allergies, religious, medicalHistory;

    public TempMedicalStorage() {
        this.allergies = "N/A";
        this.religious = "N/A";
        this.medicalHistory = "N/A";
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public String getReligious() {
        return religious;
    }

    public void setReligious(String religious) {
        this.religious = religious;
    }

    public String getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }
}
