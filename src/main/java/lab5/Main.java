package lab5;


import lab1.Student;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import static lab5.SubjectDB.getSubjectById;

public class Main {

    public static void main(String[] args) throws SQLException {



        Student student = new StudentDB().getStudentById(3);

        student.setDateOfBirth(LocalDate.of(1999,4, 9));
        System.out.println(student);
        StudentDB.updateStudent(student);
    }
}
