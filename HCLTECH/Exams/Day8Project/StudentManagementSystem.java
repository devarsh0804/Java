package Exams.Day8Project;
import java.util.ArrayList;
import java.util.Scanner;

// Parent class
abstract class Student {
    int id;
    String name;
    String course;
    double marks;
    String grade;

    Student(int id, String name, String course, double marks) {
        this.id = id;
        this.name = name;
        this.course = course;
        this.marks = marks;
    }

    // Polymorphic method
    abstract void calculateGrade();

    void display() {
        System.out.println("ID     : " + id);
        System.out.println("Name   : " + name);
        System.out.println("Course : " + course);
        System.out.println("Marks  : " + marks);
        System.out.println("Grade  : " + grade);
        System.out.println("-------------------------");
    }
}

// Regular student
class RegularStudent extends Student {

    RegularStudent(int id, String name, String course, double marks) {
        super(id, name, course, marks);
        calculateGrade();
    }

    @Override
    void calculateGrade() {
        if (marks >= 90)
            grade = "A+";
        else if (marks >= 80)
            grade = "A";
        else if (marks >= 70)
            grade = "B";
        else if (marks >= 60)
            grade = "C";
        else if (marks >= 50)
            grade = "D";
        else
            grade = "F";
    }
}

// Scholarship student
class ScholarshipStudent extends Student {

    ScholarshipStudent(int id, String name, String course, double marks) {
        super(id, name, course, marks);
        calculateGrade();
    }

    @Override
    void calculateGrade() {
        if (marks >= 85)
            grade = "A+";
        else if (marks >= 75)
            grade = "A";
        else if (marks >= 65)
            grade = "B";
        else if (marks >= 50)
            grade = "C";
        else
            grade = "F";
    }
}

// Main class
public class StudentManagementSystem {

    static ArrayList<Student> students = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    // Add student
    static void addStudent() {

        System.out.print("Enter Student ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        // Check duplicate ID
        for (Student s : students) {
            if (s.id == id) {
                System.out.println("Student ID already exists!");
                return;
            }
        }

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Course: ");
        String course = sc.nextLine();

        System.out.print("Enter Marks: ");
        double marks = sc.nextDouble();

        if (marks < 0 || marks > 100) {
            System.out.println("Marks should be between 0 and 100.");
            return;
        }

        System.out.println("Select Student Category:");
        System.out.println("1. Regular Student");
        System.out.println("2. Scholarship Student");
        System.out.print("Enter choice: ");
        int choice = sc.nextInt();

        if (choice == 1) {
            students.add(new RegularStudent(id, name, course, marks));
        } 
        else if (choice == 2) {
            students.add(new ScholarshipStudent(id, name, course, marks));
        } 
        else {
            System.out.println("Invalid category!");
            return;
        }

        System.out.println("Student added successfully!");
    }

    // Display all students
    static void displayStudents() {

        if (students.isEmpty()) {
            System.out.println("No student records found.");
            return;
        }

        System.out.println("\n===== STUDENT RECORDS =====");

        for (Student s : students) {
            s.display();
        }
    }

    // Search student
   

    // Update student
    static void updateStudent() {

        System.out.print("Enter Student ID to update: ");
        int id = sc.nextInt();
        sc.nextLine();

        for (Student s : students) {

            if (s.id == id) {

                System.out.print("Enter new name: ");
                s.name = sc.nextLine();

                System.out.print("Enter new course: ");
                s.course = sc.nextLine();

                System.out.print("Enter new marks: ");
                double marks = sc.nextDouble();

                if (marks < 0 || marks > 100) {
                    System.out.println("Invalid marks.");
                    return;
                }

                s.marks = marks;

                // Polymorphism
                s.calculateGrade();

                System.out.println("Student updated successfully!");
                return;
            }
        }

        System.out.println("Student not found.");
    }

    // Delete student
    static void deleteStudent() {

        System.out.print("Enter Student ID to delete: ");
        int id = sc.nextInt();

        for (int i = 0; i < students.size(); i++) {

            if (students.get(i).id == id) {
                students.remove(i);
                System.out.println("Student deleted successfully!");
                return;
            }
        }

        System.out.println("Student not found.");
    }

    // Main menu
    public static void main(String[] args) {

        int choice;

        do {

            System.out.println("\n==============================");
            System.out.println(" STUDENT MANAGEMENT SYSTEM");
            System.out.println("==============================");
            System.out.println("1. Add Student");
            System.out.println("2. Display Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Exit");
            System.out.println("==============================");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    addStudent();
                    break;

                case 2:
                    displayStudents();
                    break;

                case 3:
                    searchStudent();
                    break;

                case 4:
                    updateStudent();
                    break;

                case 5:
                    deleteStudent();
                    break;

                case 6:
                    System.out.println("Thank you for using Student Management System!");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 6);

        sc.close();
    }
}
