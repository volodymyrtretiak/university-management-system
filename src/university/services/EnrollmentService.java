package university.services;

import university.entities.Enrollment;
import university.enums.Grade;

public class EnrollmentService {

    private Enrollment[] enrollments = new Enrollment[100];
    private int count = 0;

    public void addEnrollment(Enrollment enrollment) {
        enrollments[count] = enrollment;
        count++;
    }

    public void showAllEnrollments() {
        if (count == 0) {
            System.out.println("Зарахувань немає.");
            return;
        }

        for (int i = 0; i < count; i++) {
            System.out.println(i + ". " + enrollments[i]);
        }
    }

    public void setGrade(int index, Grade grade) {
        if (index >= 0 && index < count) {
            enrollments[index].setGrade(grade);
            System.out.println("Оцінку встановлено.");
        } else {
            System.out.println("Невірний індекс.");
        }
    }

    public void markAsPaid(int index) {
        if (index >= 0 && index < count) {
            enrollments[index].markAsPaid();
            System.out.println("Оплату підтверджено.");
        } else {
            System.out.println("Невірний індекс.");
        }
    }

    public void showEnrollmentsByStudentId(int studentId) {
        boolean found = false;

        for (int i = 0; i < count; i++) {
            if (enrollments[i].getStudent().getId() == studentId) {
                System.out.println(enrollments[i]);
                found = true;
            }
        }

        if (!found) {
            System.out.println("Зарахувань для цього студента не знайдено.");
        }
    }

    public void showUnpaidEnrollments() {
        boolean found = false;

        for (int i = 0; i < count; i++) {
            if (!enrollments[i].isPaid()) {
                System.out.println(enrollments[i]);
                found = true;
            }
        }

        if (!found) {
            System.out.println("Неоплачених курсів немає.");
        }
    }

    public void printStudentTranscript(int studentId) {
        boolean found = false;

        System.out.println("===== Transcript =====");

        for (int i = 0; i < count; i++) {
            if (enrollments[i].getStudent().getId() == studentId) {
                System.out.println("Student: " + enrollments[i].getStudent().getFullName());
                System.out.println("Course: " + enrollments[i].getCourse().getTitle());
                System.out.println("Semester: " + enrollments[i].getSemester());
                System.out.println("Grade: " + enrollments[i].getGrade());
                System.out.println("Paid: " + enrollments[i].isPaid());
                System.out.println("----------------------");
                found = true;
            }
        }

        if (!found) {
            System.out.println("Транскрипт не знайдено.");
        }
    }

    public double calculateStudentGPA(int studentId) {
        int totalPoints = 0;
        int subjects = 0;

        for (int i = 0; i < count; i++) {
            if (enrollments[i].getStudent().getId() == studentId) {
                int points = gradeToPoints(enrollments[i].getGrade());

                if (points >= 0) {
                    totalPoints += points;
                    subjects++;
                }
            }
        }

        if (subjects == 0) {
            return 0;
        }

        return (double) totalPoints / subjects;
    }

    public double calculateCourseGPA(int courseId) {
        int totalPoints = 0;
        int subjects = 0;

        for (int i = 0; i < count; i++) {
            if (enrollments[i].getCourse().getId() == courseId) {
                int points = gradeToPoints(enrollments[i].getGrade());

                if (points >= 0) {
                    totalPoints += points;
                    subjects++;
                }
            }
        }

        if (subjects == 0) {
            return 0;
        }

        return (double) totalPoints / subjects;
    }

    public void showTopStudentsByGPA(int limit) {
        System.out.println("Top students by GPA:");

        for (int i = 0; i < count && i < limit; i++) {
            int studentId = enrollments[i].getStudent().getId();
            double gpa = calculateStudentGPA(studentId);

            System.out.println(enrollments[i].getStudent().getFullName() + " - GPA: " + gpa);
        }
    }

    private int gradeToPoints(Grade grade) {
        switch (grade) {
            case A:
                return 5;
            case B:
                return 4;
            case C:
                return 3;
            case D:
                return 2;
            case F:
                return 1;
            case NA:
            default:
                return -1;
        }
    }
}