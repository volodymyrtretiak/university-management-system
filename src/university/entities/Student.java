package university.entities;

import university.enums.StudentStatus;

public class Student extends Person {

    private StudentStatus status;
    private int studyYear;

    public Student(int id, String fullName, String email,
                   StudentStatus status, int studyYear) {

        super(id, fullName, email);

        if (studyYear < 1) {
            throw new IllegalArgumentException("Рік навчання має бути більше 0.");
        }

        if (status == null) {
            throw new IllegalArgumentException("Статус студента не може бути порожнім.");
        }

        this.status = status;
        this.studyYear = studyYear;
    }

    public StudentStatus getStatus() {
        return status;
    }

    public void setStatus(StudentStatus status) {
        if (status == null) {
            throw new IllegalArgumentException("Статус студента не може бути порожнім.");
        }

        this.status = status;
    }

    public int getStudyYear() {
        return studyYear;
    }

    public void setStudyYear(int studyYear) {
        if (studyYear < 1) {
            throw new IllegalArgumentException("Рік навчання має бути більше 0.");
        }

        this.studyYear = studyYear;
    }

    @Override
    public String toString() {
        return super.toString()
                + ", Status: " + status
                + ", Year: " + studyYear;
    }
}