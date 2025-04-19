import java.util.*;

class Student {
    String name;
    String id;
    Map<String, Integer> marks = new HashMap<>();
    double percentage;
    String grade;

    public Student(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public void addMarks(String subject, int score) {
        marks.put(subject, score);
    }

    public void calculateResult() {
        int total = 0;
        for (int score : marks.values()) {
            total += score;
        }
        percentage = (double) total / marks.size();

        if (percentage >= 90) grade = "A+";
        else if (percentage >= 80) grade = "A";
        else if (percentage >= 70) grade = "B";
        else if (percentage >= 60) grade = "C";
        else if (percentage >= 50) grade = "D";
        else grade = "F";
    }

    public void printMarksheet() {
        System.out.println("\n--- Marksheet ---");
        System.out.println("Name: " + name);
        System.out.println("ID: " + id);
        for (String subject : marks.keySet()) {
            System.out.println(subject + ": " + marks.get(subject));
        }
        System.out.printf("Percentage: %.2f%%\n", percentage);
        System.out.println("Grade: " + grade);
        System.out.println("------------------\n");
    }
}

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static List<Student> students = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println("---- Student Result Management ----");
            System.out.println("1. Add Student");
            System.out.println("2. View Marksheet");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice;

            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid input. Try again.");
                continue;
            }

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    viewMarksheet();
                    break;
                case 3:
                    System.out.println("Exiting... Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    static void addStudent() {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();

        System.out.print("Enter student ID: ");
        String id = scanner.nextLine();

        Student student = new Student(name, id);

        System.out.print("How many subjects? ");
        int subjectCount;

        try {
            subjectCount = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            System.out.println("Invalid number. Returning to menu.");
            return;
        }

        for (int i = 0; i < subjectCount; i++) {
            System.out.print("Enter subject name: ");
            String subject = scanner.nextLine();

            System.out.print("Enter marks for " + subject + ": ");
            int marks;

            try {
                marks = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid marks. Try again.");
                i--;
                continue;
            }

            student.addMarks(subject, marks);
        }

        student.calculateResult();
        students.add(student);

        System.out.println("✅ Student added successfully.\n");
    }

    static void viewMarksheet() {
        System.out.print("Enter student ID to view marksheet: ");
        String id = scanner.nextLine();
        for (Student s : students) {
            if (s.id.equalsIgnoreCase(id)) {
                s.printMarksheet();
                return;
            }
        }
        System.out.println("❌ Student not found.\n");
    }
}
