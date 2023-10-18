package org.example;

public class main {
    public static void main(String[] args) {
        // Create subjects
        org.example.Subject math = new org.example.Subject("Math", 4);
        org.example.Subject physics = new org.example.Subject("Physics", 3);
        org.example.Subject chemistry = new org.example.Subject("Chemistry", 3);

        // Create a student using the Builder pattern
        org.example.Student student = new org.example.Student.StudentBuilder("John")
                .age(20)
                .build();

        // Enroll the student in subjects and assign grades
        student.enroll(math, 90.0);
        student.enroll(physics, 85.5);
        student.enroll(chemistry, 78.0);

        // Output information about the student, subjects, and grades
        System.out.println("Student Info:");
        System.out.println(student);

        System.out.println("\nEnrollments:");
        for (org.example.Enrollment enrollment : student.getEnrollments()) {
            System.out.println(enrollment);
        }
    }
}
