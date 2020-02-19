package model.student;

public class TempMedicalStorage {

    private String allergies, religious, medicalHistory;

    public TempMedicalStorage(String allergies, String religious, String medicalHistory) {
        this.allergies = allergies;
        this.religious = religious;
        this.medicalHistory = medicalHistory;
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
