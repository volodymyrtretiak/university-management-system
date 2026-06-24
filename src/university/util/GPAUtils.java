package university.util;

public class GPAUtils {

    public static double calculateGPA(int totalPoints, int subjects) {

        if (subjects == 0) {
            return 0;
        }

        return (double) totalPoints / subjects;
    }
}