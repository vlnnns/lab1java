package lab1;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        //Create subjects
//        Subject math = new Subject("Math", 4, 1);
//        Subject physics = new Subject("Physics", 3, 2);
//        Subject chemistry = new Subject("Chemistry", 3, 3);
//
//        // Create a student using the Builder pattern
//        Student student = new Student.StudentBuilder("John")
//                .dateOfBirth(LocalDate.of(2000, 1, 1))
//                .build();
//
//        // Enroll the student in subjects and assign grades
//        student.enroll(math, 90.0, 1);
//        student.enroll(physics, 85.5, 2);
//        student.enroll(chemistry, 78.0, 3);
//
//        // Output information about the student, subjects, and grades
//        System.out.println("Student Info:");
//        System.out.println(student);
//
//        try {
//            Student student1 = new Student.StudentBuilder("Kate")
//                    .dateOfBirth(LocalDate.of(2025, 10, 15))
//                    .build();
//        } catch (IllegalArgumentException e) {
//            System.out.println("Caught IllegalArgumentException: " + e.getMessage());
//        }

        // Create tables
        Subject.createSubjectsTable();
        Student.createTable();
        Enrollment.createTable();


    }
}
