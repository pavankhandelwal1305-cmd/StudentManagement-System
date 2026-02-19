package com.task.studentManagementSystem;

import java.io.Serializable;

public class Student implements Serializable {

    private String name;
    private int rollNumber;
    private String grade;
    private int age;

    public Student(String name, int rollNumber, String grade, int age) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public String getGrade() {
        return grade;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Roll No: " + rollNumber +
                ", Name: " + name +
                ", Age: " + age +
                ", Grade: " + grade;
    }
}

