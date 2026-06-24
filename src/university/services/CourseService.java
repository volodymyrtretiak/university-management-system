package university.services;

import university.entities.Course;

public class CourseService {

    private Course[] courses = new Course[100];
    private int count = 0;

    public void addCourse(Course course) {
        courses[count] = course;
        count++;
    }

    public void showAllCourses() {
        if (count == 0) {
            System.out.println("Курсів немає.");
            return;
        }

        for (int i = 0; i < count; i++) {
            System.out.println(courses[i]);
        }
    }

    public Course findCourseById(int id) {
        for (int i = 0; i < count; i++) {
            if (courses[i].getId() == id) {
                return courses[i];
            }
        }

        return null;
    }

    public void updateCourse(int id, String title, int credits) {
        Course course = findCourseById(id);

        if (course == null) {
            System.out.println("Курс не знайдено.");
            return;
        }

        course.setTitle(title);
        course.setCredits(credits);

        System.out.println("Курс оновлено.");
    }

    public void deleteCourse(int id) {
        for (int i = 0; i < count; i++) {

            if (courses[i].getId() == id) {

                for (int j = i; j < count - 1; j++) {
                    courses[j] = courses[j + 1];
                }

                courses[count - 1] = null;
                count--;

                System.out.println("Курс видалено.");
                return;
            }
        }

        System.out.println("Курс не знайдено.");
    }

    public void showCoursesByCredits(int credits) {
        boolean found = false;

        for (int i = 0; i < count; i++) {
            if (courses[i].getCredits() == credits) {
                System.out.println(courses[i]);
                found = true;
            }
        }

        if (!found) {
            System.out.println("Курсів з такою кількістю кредитів не знайдено.");
        }
    }

    public void showCoursesByTeacher(String teacherName) {
        boolean found = false;

        for (int i = 0; i < count; i++) {
            if (courses[i].getTeacher().getFullName()
                    .toLowerCase()
                    .contains(teacherName.toLowerCase())) {

                System.out.println(courses[i]);
                found = true;
            }
        }

        if (!found) {
            System.out.println("Курсів для цього викладача не знайдено.");
        }
    }

    public Course[] getCourses() {
        return courses;
    }

    public int getCount() {
        return count;
    }
}