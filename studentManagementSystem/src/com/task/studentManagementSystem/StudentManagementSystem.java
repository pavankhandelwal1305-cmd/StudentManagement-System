package com.task.studentManagementSystem;

import java.io.*;
import java.util.*;

public class StudentManagementSystem {

    private static List<Student> students = new ArrayList<>();
    private static final String FILE_NAME = "students.dat";
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        loadFromFile();

        int choice;

        do {
            System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Search Student");
            System.out.println("4. Display All Students");
            System.out.println("5. Edit Student");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            while (!sc.hasNextInt()) {
                System.out.println("Invalid input! Enter number only.");
                sc.next();
            }

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    removeStudent();
                    break;
                case 3:
                    searchStudent();
                    break;
                case 4:
                    displayAllStudents();
                    break;
                case 5:
                    editStudent();
                    break;
                case 6:
                    saveToFile();
                    System.out.println("Data saved. Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 6);
    }

    private static void addStudent() {

        System.out.print("Enter Roll Number: ");
        int roll = sc.nextInt();
        sc.nextLine();

        if (findStudent(roll) != null) {
            System.out.println("Student with this Roll Number already exists!");
            return;
        }

        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        if (name.trim().isEmpty()) {
            System.out.println("Name cannot be empty!");
            return;
        }

        System.out.print("Enter Age: ");
        int age = sc.nextInt();
        sc.nextLine();

        if (age <= 0) {
            System.out.println("Invalid age!");
            return;
        }

        System.out.print("Enter Grade: ");
        String grade = sc.nextLine();

        students.add(new Student(name, roll, grade, age));
        System.out.println("Student added successfully!");
    }

    private static void removeStudent() {

        System.out.print("Enter Roll Number to remove: ");
        int roll = sc.nextInt();

        Student student = findStudent(roll);
        if (student != null) {
            students.remove(student);
            System.out.println("Student removed successfully!");
        } else {
            System.out.println("Student not found!");
        }
    }

    private static void searchStudent() {

        System.out.print("Enter Roll Number to search: ");
        int roll = sc.nextInt();

        Student student = findStudent(roll);
        if (student != null) {
            System.out.println(student);
        } else {
            System.out.println("Student not found!");
        }
    }

    private static void displayAllStudents() {

        if (students.isEmpty()) {
            System.out.println("No students available.");
            return;
        }

        for (Student s : students) {
            System.out.println(s);
        }
    }

    private static void editStudent() {

        System.out.print("Enter Roll Number to edit: ");
        int roll = sc.nextInt();
        sc.nextLine();

        Student student = findStudent(roll);

        if (student != null) {

            System.out.print("Enter new Name: ");
            String name = sc.nextLine();
            if (!name.trim().isEmpty()) {
                student.setName(name);
            }

            System.out.print("Enter new Age: ");
            int age = sc.nextInt();
            sc.nextLine();
            if (age > 0) {
                student.setAge(age);
            }

            System.out.print("Enter new Grade: ");
            String grade = sc.nextLine();
            if (!grade.trim().isEmpty()) {
                student.setGrade(grade);
            }

            System.out.println("Student updated successfully!");

        } else {
            System.out.println("Student not found!");
        }
    }

    private static Student findStudent(int roll) {

        for (Student s : students) {
            if (s.getRollNumber() == roll) {
                return s;
            }
        }
        return null;
    }

    private static void saveToFile() {

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(students);
        } catch (IOException e) {
            System.out.println("Error saving data!");
        }
    }

    private static void loadFromFile() {

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            students = (List<Student>) ois.readObject();
        } catch (Exception e) {
            students = new ArrayList<>();
        }
    }
}

