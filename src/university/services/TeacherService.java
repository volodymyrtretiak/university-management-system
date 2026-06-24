package university.services;

import university.entities.Teacher;
import university.enums.TeacherPosition;

public class TeacherService {

    private Teacher[] teachers = new Teacher[100];
    private int count = 0;

    public void addTeacher(Teacher teacher) {
        teachers[count] = teacher;
        count++;
    }

    public void showAllTeachers() {
        if (count == 0) {
            System.out.println("Викладачів немає.");
            return;
        }

        for (int i = 0; i < count; i++) {
            System.out.println(teachers[i]);
        }
    }

    public Teacher findTeacherById(int id) {
        for (int i = 0; i < count; i++) {
            if (teachers[i].getId() == id) {
                return teachers[i];
            }
        }

        return null;
    }

    public void updateTeacher(int id, String fullName, String email, TeacherPosition position) {
        Teacher teacher = findTeacherById(id);

        if (teacher == null) {
            System.out.println("Викладача не знайдено.");
            return;
        }

        teacher.setFullName(fullName);
        teacher.setEmail(email);
        teacher.setPosition(position);

        System.out.println("Дані викладача оновлено.");
    }

    public void deleteTeacher(int id) {
        for (int i = 0; i < count; i++) {

            if (teachers[i].getId() == id) {

                for (int j = i; j < count - 1; j++) {
                    teachers[j] = teachers[j + 1];
                }

                teachers[count - 1] = null;
                count--;

                System.out.println("Викладача видалено.");
                return;
            }
        }

        System.out.println("Викладача не знайдено.");
    }

    public Teacher[] getTeachers() {
        return teachers;
    }

    public int getCount() {
        return count;
    }
}