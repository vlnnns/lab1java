package lab3;

import lab1.Student;
import lab1.Subject;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StreamTest {

    public static void main(String[] args) {
        Student student1 = new Student.StudentBuilder("Luis")
                .dateOfBirth(LocalDate.of(2000, 1, 1))
                .build();

        Student student2 = new Student.StudentBuilder("John")
                .dateOfBirth(LocalDate.of(2002, 6, 30))
                .build();
        Student student3 = new Student.StudentBuilder("Katy")
                .dateOfBirth(LocalDate.of(1999, 4, 17))
                .build();
        // Enroll the student in subjects and assign grades

        Subject math = new Subject("Math", 3);
        Subject physics = new Subject("Physics", 4);
        Subject chemistry = new Subject("Chemistry", 3);

        // Створення декількох студентів для тестування
        student1.enroll(math, 90.0);
        student1.enroll(physics, 85.5);
        student1.enroll(chemistry, 78.0);

        student2.enroll(math, 75.0);
        student2.enroll(physics, 80.5);
        student2.enroll(chemistry, 92.0);

        student3.enroll(math, 88.0);
        student3.enroll(physics, 63.5);
        student3.enroll(chemistry, 41.0);
        // Додавання студентів до списку
        List<Student> students = new ArrayList<>();
        students.add(student1);
        students.add(student2);
        students.add(student3);

        // Вивід несортованих студентів
        System.out.println("Unsorted students:");
        students.forEach(student -> System.out.println(student.getName() + " - " + student.getDateOfBirth()));


        // Create an instance of SchoolServiceStream
        SchoolServiceStream schoolServiceStream = new SchoolServiceStream(students);

        // Test sorting by name
        System.out.println("Sorted by name:");
        schoolServiceStream.sortStudentsByName().forEach(student -> System.out.println(student.getName()));

        // Test sorting by date of birth
        System.out.println("\nSorted by date of birth:");
        schoolServiceStream.sortStudentsByDateOfBirth().forEach(student -> System.out.println(student.getName()));

        // Test sorting by average grade
        System.out.println("\nSorted by average grade:");
        schoolServiceStream.sortStudentsByAverageGrade().forEach(student ->
                System.out.println(student.getName() + " - " + student.getAverageGrade()));

        // Test filtering by part of the name
        System.out.println("\nFiltered by part of the name:");
        schoolServiceStream.getByPartName("Jo").forEach(student -> System.out.println(student.getName()));

        // Test filtering by current month
        System.out.println("\nFiltered by current month:");
        schoolServiceStream.getByCurrentMonth().forEach(student -> System.out.println(student.getName()));
    }
}
