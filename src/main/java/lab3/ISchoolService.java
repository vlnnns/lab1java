package lab3;

import lab1.Enrollment;
import lab1.Student;

import java.util.List;

public interface ISchoolService {

    List<Student> sortStudentsByName();

    List<Student> sortStudentsByDateOfBirth();

    List<Student> sortStudentsByAverageGrade();


    List<Student> getByPartName(String name);

    List<Student> getByCurrentMonth();



}
