package lab5;


import lab1.Student;

import java.time.LocalDate;
import java.util.List;

public class Main {

    public static void main(String[] args) {


        new Manager().dropDatabaseStructure();

//        StudentDB.createStudentTable();

//
//        // Retrieve all students and display their information
//        List<Student> students = StudentDB.getAllStudents();

//        Student studentToUpdate = students.get(1);
//        StudentDB.updateStudent(2, "Updated John", LocalDate.of(1990, 3, 20));


//
//        StudentDB.deleteStudent(2);


//        SubjectDB.createSubjectTable(); // Create the subjects table if it doesn't exist
//


//      SubjectDB.updateSubjectName(1, "Algebra");
//        SubjectDB.deleteSubject(1);

//        SubjectDB.dropSubjectTable();

        StudentDB.createStudentTable();
        StudentDB.addStudent("John Doe", LocalDate.of(1998, 5, 15));
        StudentDB.addStudent("Alice Smith", LocalDate.of(1997, 8, 21));
        StudentDB.addStudent("Bob Johnson", LocalDate.of(1999, 4, 10));
        StudentDB.addStudent("Eva Williams", LocalDate.of(2000, 11, 30));

        List<Student> students = StudentDB.getAllStudents();



        SubjectDB.createSubjectTable();
        SubjectDB.addSubject("Mathematics");
        SubjectDB.addSubject("Physic");
        SubjectDB.addSubject("English");

        List<String> subjects = SubjectDB.getAllSubjects();
        System.out.println("All Subjects:");
        subjects.forEach(System.out::println);

        GradeDB.createGradeTable();
        GradeDB.addGrade(1, 2, 85.5f);

        GradeDB.updateGradeValue(1, 90.0f);

        GradeDB.addGrade(2, 1, 75.5f);
        GradeDB.addGrade(3, 3, 80.0f);
        GradeDB.addGrade(4, 3, 70.0f);

        List<Float> grades = GradeDB.getGradesByStudentId(1);
        System.out.println("Grades for student ID 1:");
        for (float grade : grades) {
            System.out.println(grade);
        }

        List<Float> updatedGrades = GradeDB.getGradesByStudentId(1);
        System.out.println("\nUpdated grades for student ID 1:");
        for (float grade : updatedGrades) {
            System.out.println(grade);
        }

        System.out.println("Student by id");
        StudentDB.getStudentById(1);

        System.out.println("Student by part of name");
        StudentDB.getByPartName("Jo");

        System.out.println("Student by current month");
        StudentDB.getByCurrentMonth();

        System.out.println("Sorted by average grade");
        StudentDB.sortStudentsByAverageGrade();

        System.out.println("Sorted by date of birth");
        StudentDB.sortStudentsByDateOfBirth();

        System.out.println("Sorted by name");
        StudentDB.sortStudentsByName();
    }
}
