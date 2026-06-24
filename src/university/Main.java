package university;

import java.util.Scanner;

import university.entities.Course;
import university.entities.Enrollment;
import university.entities.Student;
import university.entities.Teacher;
import university.enums.Grade;
import university.enums.StudentStatus;
import university.enums.TeacherPosition;
import university.services.CourseService;
import university.services.EnrollmentService;
import university.services.StudentService;
import university.services.TeacherService;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        StudentService studentService = new StudentService();
        TeacherService teacherService = new TeacherService();
        CourseService courseService = new CourseService();
        EnrollmentService enrollmentService = new EnrollmentService();

        try {
            Teacher teacher1 = new Teacher(1, "Ivan Petrenko", "ivan@uni.com", TeacherPosition.PROFESSOR);
            Teacher teacher2 = new Teacher(2, "Olena Kovalenko", "olena@uni.com", TeacherPosition.LECTURER);

            Student student1 = new Student(1, "Oleh Shevchenko", "oleh@uni.com", StudentStatus.ACTIVE, 2);
            Student student2 = new Student(2, "Anna Bondarenko", "anna@uni.com", StudentStatus.ACTIVE, 1);
            Student student3 = new Student(3, "Maksym Tkachenko", "maksym@uni.com", StudentStatus.ON_LEAVE, 3);

            Course course1 = new Course(1, "Java Programming", 5, teacher1);
            Course course2 = new Course(2, "Databases", 4, teacher2);

            teacherService.addTeacher(teacher1);
            teacherService.addTeacher(teacher2);

            studentService.addStudent(student1);
            studentService.addStudent(student2);
            studentService.addStudent(student3);

            courseService.addCourse(course1);
            courseService.addCourse(course2);

            enrollmentService.addEnrollment(new Enrollment(student1, course1, "2026 Spring"));
            enrollmentService.addEnrollment(new Enrollment(student2, course1, "2026 Spring"));
            enrollmentService.addEnrollment(new Enrollment(student3, course2, "2026 Spring"));

        } catch (IllegalArgumentException e) {
            System.out.println("Помилка створення тестових даних: " + e.getMessage());
        }

        int choice = -1;

        do {
            printMenu();

            try {
                System.out.print("Оберіть пункт меню: ");
                choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {

                    case 1:
                        studentService.showAllStudents();
                        break;

                    case 2:
                        System.out.print("ID студента: ");
                        int newStudentId = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("ПІБ студента: ");
                        String studentName = scanner.nextLine();

                        System.out.print("Email студента: ");
                        String studentEmail = scanner.nextLine();

                        System.out.print("Рік навчання: ");
                        int studyYear = scanner.nextInt();
                        scanner.nextLine();

                        Student newStudent = new Student(
                                newStudentId,
                                studentName,
                                studentEmail,
                                StudentStatus.ACTIVE,
                                studyYear
                        );

                        studentService.addStudent(newStudent);
                        System.out.println("Студента додано.");
                        break;

                    case 3:
                        System.out.print("ID студента для оновлення: ");
                        int updateStudentId = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Новий ПІБ: ");
                        String updatedName = scanner.nextLine();

                        System.out.print("Новий email: ");
                        String updatedEmail = scanner.nextLine();

                        System.out.print("Новий рік навчання: ");
                        int updatedYear = scanner.nextInt();
                        scanner.nextLine();

                        studentService.updateStudent(updateStudentId, updatedName, updatedEmail, updatedYear);
                        break;

                    case 4:
                        System.out.print("ID студента для видалення: ");
                        int deleteStudentId = scanner.nextInt();
                        scanner.nextLine();

                        studentService.deleteStudent(deleteStudentId);
                        break;

                    case 5:
                        System.out.print("ID студента: ");
                        int statusStudentId = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Новий статус (ACTIVE, ON_LEAVE, EXPELLED, GRADUATED): ");
                        String statusText = scanner.nextLine();

                        studentService.changeStudentStatus(
                                statusStudentId,
                                StudentStatus.valueOf(statusText.toUpperCase())
                        );
                        break;

                    case 6:
                        System.out.print("Введіть частину ПІБ або email: ");
                        String keyword = scanner.nextLine();

                        studentService.searchStudent(keyword);
                        break;

                    case 7:
                        System.out.print("Статус (ACTIVE, ON_LEAVE, EXPELLED, GRADUATED): ");
                        String filterStatus = scanner.nextLine();

                        studentService.showStudentsByStatus(StudentStatus.valueOf(filterStatus.toUpperCase()));
                        break;

                    case 8:
                        System.out.print("Рік навчання: ");
                        int filterYear = scanner.nextInt();
                        scanner.nextLine();

                        studentService.showStudentsByYear(filterYear);
                        break;

                    case 9:
                        studentService.sortStudentsByName();
                        break;

                    case 10:
                        teacherService.showAllTeachers();
                        break;

                    case 11:
                        System.out.print("ID викладача: ");
                        int newTeacherId = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("ПІБ викладача: ");
                        String teacherName = scanner.nextLine();

                        System.out.print("Email викладача: ");
                        String teacherEmail = scanner.nextLine();

                        System.out.print("Посада (ASSISTANT, LECTURER, PROFESSOR): ");
                        String teacherPosition = scanner.nextLine();

                        Teacher newTeacher = new Teacher(
                                newTeacherId,
                                teacherName,
                                teacherEmail,
                                TeacherPosition.valueOf(teacherPosition.toUpperCase())
                        );

                        teacherService.addTeacher(newTeacher);
                        System.out.println("Викладача додано.");
                        break;

                    case 12:
                        System.out.print("ID викладача для оновлення: ");
                        int updateTeacherId = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Новий ПІБ: ");
                        String newTeacherName = scanner.nextLine();

                        System.out.print("Новий email: ");
                        String newTeacherEmail = scanner.nextLine();

                        System.out.print("Нова посада (ASSISTANT, LECTURER, PROFESSOR): ");
                        String newPosition = scanner.nextLine();

                        teacherService.updateTeacher(
                                updateTeacherId,
                                newTeacherName,
                                newTeacherEmail,
                                TeacherPosition.valueOf(newPosition.toUpperCase())
                        );
                        break;

                    case 13:
                        System.out.print("ID викладача для видалення: ");
                        int deleteTeacherId = scanner.nextInt();
                        scanner.nextLine();

                        teacherService.deleteTeacher(deleteTeacherId);
                        break;

                    case 14:
                        courseService.showAllCourses();
                        break;

                    case 15:
                        System.out.print("ID курсу: ");
                        int newCourseId = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Назва курсу: ");
                        String courseTitle = scanner.nextLine();

                        System.out.print("Кількість кредитів: ");
                        int credits = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("ID викладача: ");
                        int teacherIdForCourse = scanner.nextInt();
                        scanner.nextLine();

                        Teacher courseTeacher = teacherService.findTeacherById(teacherIdForCourse);

                        if (courseTeacher != null) {
                            Course newCourse = new Course(newCourseId, courseTitle, credits, courseTeacher);
                            courseService.addCourse(newCourse);
                            System.out.println("Курс додано.");
                        } else {
                            System.out.println("Викладача не знайдено.");
                        }
                        break;

                    case 16:
                        System.out.print("ID курсу для оновлення: ");
                        int updateCourseId = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Нова назва курсу: ");
                        String updatedCourseTitle = scanner.nextLine();

                        System.out.print("Нова кількість кредитів: ");
                        int updatedCredits = scanner.nextInt();
                        scanner.nextLine();

                        courseService.updateCourse(updateCourseId, updatedCourseTitle, updatedCredits);
                        break;

                    case 17:
                        System.out.print("ID курсу для видалення: ");
                        int deleteCourseId = scanner.nextInt();
                        scanner.nextLine();

                        courseService.deleteCourse(deleteCourseId);
                        break;

                    case 18:
                        System.out.print("Кількість кредитів: ");
                        int creditsFilter = scanner.nextInt();
                        scanner.nextLine();

                        courseService.showCoursesByCredits(creditsFilter);
                        break;

                    case 19:
                        System.out.print("ПІБ або частина ПІБ викладача: ");
                        String teacherSearch = scanner.nextLine();

                        courseService.showCoursesByTeacher(teacherSearch);
                        break;

                    case 20:
                        enrollmentService.showAllEnrollments();
                        break;

                    case 21:
                        System.out.print("ID студента: ");
                        int enrollStudentId = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("ID курсу: ");
                        int enrollCourseId = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Семестр: ");
                        String semester = scanner.nextLine();

                        Student enrollStudent = studentService.findStudentById(enrollStudentId);
                        Course enrollCourse = courseService.findCourseById(enrollCourseId);

                        if (enrollStudent != null && enrollCourse != null) {
                            Enrollment enrollment = new Enrollment(enrollStudent, enrollCourse, semester);
                            enrollmentService.addEnrollment(enrollment);
                            System.out.println("Студента зараховано на курс.");
                        } else {
                            System.out.println("Студента або курс не знайдено.");
                        }
                        break;

                    case 22:
                        System.out.print("Індекс зарахування: ");
                        int gradeIndex = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Оцінка (A, B, C, D, F, NA): ");
                        String gradeText = scanner.nextLine();

                        enrollmentService.setGrade(gradeIndex, Grade.valueOf(gradeText.toUpperCase()));
                        break;

                    case 23:
                        System.out.print("Індекс зарахування: ");
                        int paidIndex = scanner.nextInt();
                        scanner.nextLine();

                        enrollmentService.markAsPaid(paidIndex);
                        break;

                    case 24:
                        System.out.print("ID студента: ");
                        int studentEnrollmentsId = scanner.nextInt();
                        scanner.nextLine();

                        enrollmentService.showEnrollmentsByStudentId(studentEnrollmentsId);
                        break;

                    case 25:
                        enrollmentService.showUnpaidEnrollments();
                        break;

                    case 26:
                        System.out.print("ID студента: ");
                        int transcriptStudentId = scanner.nextInt();
                        scanner.nextLine();

                        enrollmentService.printStudentTranscript(transcriptStudentId);
                        break;

                    case 27:
                        System.out.print("ID студента: ");
                        int gpaStudentId = scanner.nextInt();
                        scanner.nextLine();

                        System.out.println("GPA студента: " + enrollmentService.calculateStudentGPA(gpaStudentId));
                        break;

                    case 28:
                        System.out.print("ID курсу: ");
                        int gpaCourseId = scanner.nextInt();
                        scanner.nextLine();

                        System.out.println("Середній GPA по курсу: " + enrollmentService.calculateCourseGPA(gpaCourseId));
                        break;

                    case 29:
                        System.out.print("Кількість студентів у топі: ");
                        int limit = scanner.nextInt();
                        scanner.nextLine();

                        enrollmentService.showTopStudentsByGPA(limit);
                        break;

                    case 0:
                        System.out.println("Завершення роботи...");
                        break;

                    default:
                        System.out.println("Невірний вибір.");
                }

            } catch (IllegalArgumentException e) {
                System.out.println("Помилка: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Помилка: введено некоректні дані.");
                scanner.nextLine();
            }

        } while (choice != 0);

        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n===== UNIVERSITY MANAGEMENT SYSTEM =====");

        System.out.println("\n--- Студенти ---");
        System.out.println("1. Показати всіх студентів");
        System.out.println("2. Додати студента");
        System.out.println("3. Оновити студента");
        System.out.println("4. Видалити студента");
        System.out.println("5. Змінити статус студента");
        System.out.println("6. Пошук студента за ПІБ або email");
        System.out.println("7. Фільтр студентів за статусом");
        System.out.println("8. Фільтр студентів за роком навчання");
        System.out.println("9. Сортувати студентів за ПІБ");

        System.out.println("\n--- Викладачі ---");
        System.out.println("10. Показати всіх викладачів");
        System.out.println("11. Додати викладача");
        System.out.println("12. Оновити викладача");
        System.out.println("13. Видалити викладача");

        System.out.println("\n--- Курси ---");
        System.out.println("14. Показати всі курси");
        System.out.println("15. Додати курс");
        System.out.println("16. Оновити курс");
        System.out.println("17. Видалити курс");
        System.out.println("18. Фільтр курсів за кредитами");
        System.out.println("19. Фільтр курсів за викладачем");

        System.out.println("\n--- Зарахування / Звіти ---");
        System.out.println("20. Показати всі зарахування");
        System.out.println("21. Зарахувати студента на курс");
        System.out.println("22. Виставити оцінку");
        System.out.println("23. Позначити оплату");
        System.out.println("24. Зарахування конкретного студента");
        System.out.println("25. Список неоплачених курсів");
        System.out.println("26. Транскрипт студента");
        System.out.println("27. GPA студента");
        System.out.println("28. GPA по курсу");
        System.out.println("29. Top-N студентів за GPA");

        System.out.println("\n0. Вихід");
    }
}