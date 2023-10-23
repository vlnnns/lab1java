package lab1;

import java.time.LocalDate;

public class main {
    public static void main(String[] args) {
        // Create subjects
        Subject math = new Subject("Math", 4);
        Subject physics = new Subject("Physics", 3);
        Subject chemistry = new Subject("Chemistry", 3);

        // Create a student using the Builder pattern
        Student student = new Student.StudentBuilder("John")
                .dateOfBirth(LocalDate.of(2000, 1, 1))
                .build();

        // Enroll the student in subjects and assign grades
        student.enroll(math, 90.0);
        student.enroll(physics, 85.5);
        student.enroll(chemistry, 78.0);

        // Output information about the student, subjects, and grades
        System.out.println("Student Info:");
        System.out.println(student);

        System.out.println("\nEnrollments:");
        for (Enrollment enrollment : student.getEnrollments()) {
            System.out.println(enrollment);
        }
    }
}
