package university.services;

import university.entities.Student;
import university.enums.StudentStatus;

public class StudentService {

    private Student[] students = new Student[100];
    private int count = 0;

    public void addStudent(Student student) {
        students[count] = student;
        count++;
    }

    public void showAllStudents() {
        if (count == 0) {
            System.out.println("Студентів немає.");
            return;
        }

        for (int i = 0; i < count; i++) {
            System.out.println(students[i]);
        }
    }

    public Student findStudentById(int id) {
        for (int i = 0; i < count; i++) {
            if (students[i].getId() == id) {
                return students[i];
            }
        }

        return null;
    }

    public void updateStudent(int id, String fullName, String email, int studyYear) {
        Student student = findStudentById(id);

        if (student == null) {
            System.out.println("Студента не знайдено.");
            return;
        }

        student.setFullName(fullName);
        student.setEmail(email);
        student.setStudyYear(studyYear);

        System.out.println("Дані студента оновлено.");
    }

    public void changeStudentStatus(int id, StudentStatus status) {
        Student student = findStudentById(id);

        if (student == null) {
            System.out.println("Студента не знайдено.");
            return;
        }

        student.setStatus(status);

        System.out.println("Статус студента змінено.");
    }

    public void deleteStudent(int id) {
        for (int i = 0; i < count; i++) {
            if (students[i].getId() == id) {

                for (int j = i; j < count - 1; j++) {
                    students[j] = students[j + 1];
                }

                students[count - 1] = null;
                count--;

                System.out.println("Студента видалено.");
                return;
            }
        }

        System.out.println("Студента не знайдено.");
    }

    public void showStudentsByStatus(StudentStatus status) {
        boolean found = false;

        for (int i = 0; i < count; i++) {
            if (students[i].getStatus() == status) {
                System.out.println(students[i]);
                found = true;
            }
        }

        if (!found) {
            System.out.println("Студентів з таким статусом не знайдено.");
        }
    }

    public void showStudentsByYear(int studyYear) {
        boolean found = false;

        for (int i = 0; i < count; i++) {
            if (students[i].getStudyYear() == studyYear) {
                System.out.println(students[i]);
                found = true;
            }
        }

        if (!found) {
            System.out.println("Студентів з таким роком навчання не знайдено.");
        }
    }

    public void sortStudentsByName() {
        for (int i = 0; i < count - 1; i++) {
            for (int j = 0; j < count - i - 1; j++) {
                if (students[j].getFullName().compareToIgnoreCase(students[j + 1].getFullName()) > 0) {
                    Student temp = students[j];
                    students[j] = students[j + 1];
                    students[j + 1] = temp;
                }
            }
        }

        System.out.println("Студентів відсортовано за ПІБ.");
        showAllStudents();
    }

    public void searchStudent(String keyword) {
        boolean found = false;

        for (int i = 0; i < count; i++) {
            String name = students[i].getFullName().toLowerCase();
            String email = students[i].getEmail().toLowerCase();
            String search = keyword.toLowerCase();

            if (name.contains(search) || email.contains(search)) {
                System.out.println(students[i]);
                found = true;
            }
        }

        if (!found) {
            System.out.println("Студентів за пошуком не знайдено.");
        }
    }

    public Student[] getStudents() {
        return students;
    }

    public int getCount() {
        return count;
    }
}