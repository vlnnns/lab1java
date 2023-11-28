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

//        StudentDB.createStudentTable();
//        StudentDB.addStudent("John", LocalDate.of(1995, 5, 15));
//        StudentDB.addStudent("John1", LocalDate.of(1967, 2, 12));
//        StudentDB.addStudent("John2", LocalDate.of(1995, 2, 21));
//        SubjectDB.createSubjectTable();
//        SubjectDB.addSubject("Mathematics");
//        SubjectDB.addSubject("Physic");
//        SubjectDB.addSubject("English");
//        GradeDB.createGradeTable();
//        GradeDB.addGrade(1, 2, 85.5f);
//
//        GradeDB.updateGradeValue(1, 90.0f);
//
//        GradeDB.addGrade(2, 1, 75.5f);
//        GradeDB.addGrade(3, 3, 60.5f);
//
//        List<Float> updatedGrades = GradeDB.getGradesByStudentId(1);
//        System.out.println("\nUpdated grades for student ID 1:");
//        for (float grade : updatedGrades) {
//            System.out.println(grade);
//        }
//
//        GradeDB.deleteGrade(1);
//
//        GradeDB.dropGradeTable();
    }
}
