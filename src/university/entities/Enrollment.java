package university.entities;

import university.enums.Grade;
import university.interfaces.Payable;

public class Enrollment implements Payable {

    private Student student;
    private Course course;
    private String semester;
    private Grade grade;
    private boolean paid;

    public Enrollment(Student student, Course course, String semester) {
        if (student == null) {
            throw new IllegalArgumentException("Студент не може бути порожнім.");
        }

        if (course == null) {
            throw new IllegalArgumentException("Курс не може бути порожнім.");
        }

        if (semester == null || semester.isBlank()) {
            throw new IllegalArgumentException("Семестр не може бути порожнім.");
        }

        this.student = student;
        this.course = course;
        this.semester = semester;
        this.grade = Grade.NA;
        this.paid = false;
    }

    public Student getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }

    public String getSemester() {
        return semester;
    }

    public Grade getGrade() {
        return grade;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setGrade(Grade grade) {
        if (grade == null) {
            throw new IllegalArgumentException("Оцінка не може бути порожньою.");
        }

        this.grade = grade;
    }

    @Override
    public void markAsPaid() {
        paid = true;
    }

    @Override
    public String toString() {
        return "Student: " + student.getFullName()
                + ", Course: " + course.getTitle()
                + ", Semester: " + semester
                + ", Grade: " + grade
                + ", Paid: " + paid;
    }
}