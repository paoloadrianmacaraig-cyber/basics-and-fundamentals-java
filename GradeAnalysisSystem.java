import java.util.Scanner;

public class GradeAnalysisSystem {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int classNum;

        System.out.println("==============================================================");
        System.out.println("                     GRADE ANALYSIS SYSTEM");
        System.out.println("==============================================================\n");

        // Number of Students
        while (true) {
            System.out.print("Enter number of students: ");

            if (!sc.hasNextInt()) {
                System.out.println("Invalid Input. Please enter a number.");
                sc.next();
                continue;
            }

            classNum = sc.nextInt();

            if (classNum < 2) {
                System.out.println("Number of students should not be less than 2.");
                continue;
            }

            sc.nextLine();
            System.out.println("______________________________________________________________\n");
            break;
        }

        // Student Names
        String[] student = new String[classNum];

        for (int i = 0; i < classNum; i++) {
            while (true) {
                System.out.print("Enter Student Name " + (i + 1) + " (LAST NAME, FIRST NAME): ");
                student[i] = sc.nextLine();

                if (student[i].matches("[A-Z ]+,\\s*[A-Z ]+")) {
                    break;
                }

                System.out.println("Invalid format. Please use: LAST NAME, FIRST NAME");
            }
        }

        // Student Grades
        int[] grades = new int[classNum];

        System.out.println("\n______________________________________________________________");

        for (int i = 0; i < classNum; i++) {
            while (true) {
                System.out.print("Enter grade of " + student[i] + ": ");

                if (!sc.hasNextInt()) {
                    System.out.println("Invalid input. Please enter a number.");
                    sc.next();
                    continue;
                }

                grades[i] = sc.nextInt();

                if (grades[i] < 0 || grades[i] > 100) {
                    System.out.println("Grade must be between 0 and 100.");
                    continue;
                }

                break;
            }
        }

        // Display ranking and highest performer(s)
        topPerforming(student, grades);

        // Display class average
        classAverage(grades);

        // Display number of students who passed and failed
        studentsPassFail(grades);
    }

    // Class list and highest performing student(s)
    public static void topPerforming(String[] student, int[] grades) {

        // Sort from highest to lowest
        for (int i = 0; i < grades.length - 1; i++) {
            for (int j = i + 1; j < grades.length; j++) {

                if (grades[j] > grades[i]) {

                    // Swap grades
                    int tempGrade = grades[i];
                    grades[i] = grades[j];
                    grades[j] = tempGrade;

                    // Swap student names
                    String tempStudent = student[i];
                    student[i] = student[j];
                    student[j] = tempStudent;
                }
            }
        }

        System.out.println("\n==============================================================");
        System.out.println("                        CLASS RANKING");
        System.out.println("==============================================================");

        System.out.printf("%-5s %-30s %-7s %-8s %-8s%n",
                "Rank", "Student Name", "Grade", "Letter", "Status");

        System.out.println("--------------------------------------------------------------");

        for (int i = 0; i < grades.length; i++) {
            String status = (grades[i] >= 60) ? "Passed" : "Failed";

            System.out.printf("%-5d %-30s %-7d %-8s %-8s%n",
                    i + 1,
                    student[i],
                    grades[i],
                    getLetterGrade(grades[i]),
                    status);
        }

        System.out.println("==============================================================");

        System.out.println("\nTop Performing Student(s):");

        int highest = grades[0];

        for (int i = 0; i < grades.length; i++) {
            if (grades[i] == highest) {
                System.out.println(student[i]
                        + " - " + grades[i]
                        + " (" + getLetterGrade(grades[i]) + ")");
            } else {
                break;
            }
        }
    }

    // Calculates class average
    public static double classAverage(int[] grades) {

        int sum = 0;

        // Compute the sum of all grades
        for (int i = 0; i < grades.length; i++) {
            sum += grades[i];
        }

        double average = (double) sum / grades.length;

        boolean belowSeventy = average < 70;

        System.out.println("\n==============================================================");
        System.out.println("                        CLASS AVERAGE");
        System.out.println("==============================================================");
        System.out.printf("Class Average: %.2f out of 100%n", average);
        System.out.println("Did the class average fall below 70? " + belowSeventy);

        return average;
    }

    // Counts the number of passed and failed students
    public static void studentsPassFail(int[] grades) {

        int passedCtr = 0;
        int failedCtr = 0;

        for (int i = 0; i < grades.length; i++) {

            if (grades[i] >= 60) {
                passedCtr++;
            } else {
                failedCtr++;
            }
        }

        System.out.println("\n==============================================================");
        System.out.println("                       PASSED / FAILED");
        System.out.println("==============================================================");
        System.out.println("Students Passed : " + passedCtr);
        System.out.println("Students Failed : " + failedCtr);
    }

    // Calculates letter grade of student
    public static String getLetterGrade(int grade) {

        if (grade >= 98)
            return "A+";
        else if (grade >= 92)
            return "A";
        else if (grade >= 87)
            return "B+";
        else if (grade >= 81)
            return "B";
        else if (grade >= 77)
            return "C+";
        else if (grade >= 71)
            return "C";
        else if (grade >= 60)
            return "D";
        else
            return "F";
    }
}