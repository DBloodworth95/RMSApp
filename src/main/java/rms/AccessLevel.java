package rms;

public enum AccessLevel {
    SYSTEM_ADMIN, RECORDS_ADMIN, ACADEMICS, STUDENT;

    public static AccessLevel fromInt(int userId) {
        switch (userId) {
            case 1: return SYSTEM_ADMIN;
            case 2: return RECORDS_ADMIN;
            case 3: return ACADEMICS;
            case 4: return STUDENT;
            default: throw new IllegalArgumentException("Access ID not supported.");
        }
    }

    public boolean hasAccessToSysAdminView() {
        if (this == SYSTEM_ADMIN)
            return true;
        else
            return false;
    }
    public boolean hasAccessToRecAdminView() {
        if (this == RECORDS_ADMIN)
            return true;
        else
            return false;
    }
    public boolean hasAccessToAcademicView() {
        if (this == ACADEMICS)
            return true;
        else
            return false;
    }
    public boolean hasAccessToStudentView() {
        if (this == STUDENT)
            return true;
        else
            return false;
    }
}
