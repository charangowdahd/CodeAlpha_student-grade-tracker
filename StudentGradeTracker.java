import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class StudentGradeTracker {
    private static final ArrayList<Student> students = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        System.out.println("=========================================");
        System.out.println("       STUDENT GRADE TRACKER");
        System.out.println("=========================================");

        while (running) {
            printMenu();
            int choice = readIntInRange("Enter your choice: ", 1, 6);

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    viewAllStudents();
                    break;
                case 3:
                    showStatistics();
                    break;
                case 4:
                    showSummaryReport();
                    break;
                case 5:
                    removeStudent();
                    break;
                case 6:
                    running = false;
                    System.out.println("Exiting... Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice, try again.");
            }
        }

        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n----------------- MENU -----------------");
        System.out.println("1. Add Student Grade");
        System.out.println("2. View All Students");
        System.out.println("3. Show Statistics (Average/Highest/Lowest)");
        System.out.println("4. Show Summary Report");
        System.out.println("5. Remove a Student");
        System.out.println("6. Exit");
        System.out.println("-----------------------------------------");
    }

    private static void addStudent() {
        scanner.nextLine();
        System.out.print("Enter student name: ");
        String name = scanner.nextLine().trim();

        while (name.isEmpty()) {
            System.out.print("Name cannot be empty. Enter student name: ");
            name = scanner.nextLine().trim();
        }

        double score = readDoubleInRange("Enter score (0-100): ", 0, 100);
        students.add(new Student(name, score));
        System.out.println("Added: " + name + " (" + score + ")");
    }

    private static void viewAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No student records yet.");
            return;
        }

        System.out.println("\n--- All Students ---");
        System.out.printf("%-5s %-20s %-10s%n", "No.", "Name", "Score");
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            System.out.printf("%-5d %-20s %-10.2f%n", i + 1, student.name, student.score);
        }
    }

    private static void showStatistics() {
        if (students.isEmpty()) {
            System.out.println("No student records yet. Add some first.");
            return;
        }

        double sum = 0;
        Student highest = students.get(0);
        Student lowest = students.get(0);

        for (Student student : students) {
            sum += student.score;
            if (student.score > highest.score) {
                highest = student;
            }
            if (student.score < lowest.score) {
                lowest = student;
            }
        }

        double average = sum / students.size();

        System.out.println("\n--- Statistics ---");
        System.out.printf("Total Students : %d%n", students.size());
        System.out.printf("Average Score  : %.2f%n", average);
        System.out.printf("Highest Score  : %.2f (%s)%n", highest.score, highest.name);
        System.out.printf("Lowest Score   : %.2f (%s)%n", lowest.score, lowest.name);
    }

    private static void showSummaryReport() {
        if (students.isEmpty()) {
            System.out.println("No student records yet. Add some first.");
            return;
        }

        double sum = 0;
        Student highest = students.get(0);
        Student lowest = students.get(0);

        for (Student student : students) {
            sum += student.score;
            if (student.score > highest.score) {
                highest = student;
            }
            if (student.score < lowest.score) {
                lowest = student;
            }
        }

        double average = sum / students.size();

        System.out.println("\n=========== SUMMARY REPORT ===========");
        System.out.printf("%-5s %-20s %-10s %-10s%n", "No.", "Name", "Score", "Grade");
        System.out.println("----------------------------------------");

        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            System.out.printf("%-5d %-20s %-10.2f %-10s%n",
                    i + 1, student.name, student.score, letterGrade(student.score));
        }

        System.out.println("----------------------------------------");
        System.out.printf("Average Score : %.2f%n", average);
        System.out.printf("Highest Score : %.2f (%s)%n", highest.score, highest.name);
        System.out.printf("Lowest Score  : %.2f (%s)%n", lowest.score, lowest.name);
        System.out.println("=========================================");
    }

    private static void removeStudent() {
        if (students.isEmpty()) {
            System.out.println("No student records to remove.");
            return;
        }

        viewAllStudents();
        int index = readIntInRange("Enter the No. of the student to remove: ", 1, students.size());
        Student removed = students.remove(index - 1);
        System.out.println("Removed: " + removed.name);
    }

    private static String letterGrade(double score) {
        if (score >= 90) return "A";
        if (score >= 80) return "B";
        if (score >= 70) return "C";
        if (score >= 60) return "D";
        return "F";
    }

    private static int readIntInRange(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            try {
                int value = scanner.nextInt();
                if (value < min || value > max) {
                    System.out.println("Please enter a number between " + min + " and " + max + ".");
                    continue;
                }
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a whole number.");
                scanner.next();
            }
        }
    }

    private static double readDoubleInRange(String prompt, double min, double max) {
        while (true) {
            System.out.print(prompt);
            try {
                double value = scanner.nextDouble();
                if (value < min || value > max) {
                    System.out.println("Please enter a value between " + min + " and " + max + ".");
                    continue;
                }
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
            }
        }
    }

    private static class Student {
        private final String name;
        private final double score;

        public Student(String name, double score) {
            this.name = name;
            this.score = score;
        }
    }
}
