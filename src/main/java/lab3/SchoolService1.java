package lab3;

import lab1.Student;

import java.time.LocalDate;
import java.util.*;

public class SchoolService1 implements ISchoolService{

    private List<Student> students;

    public SchoolService1(List<Student> students){
        this.students = students;
    }

    @Override
    public List<Student> sortStudentsByName() {
        List<Student> result = new ArrayList<>(students);
        result.sort(Comparator.comparing(Student::getName));
        return result;
    }

    @Override
    public List<Student> sortStudentsByDateOfBirth() {
        List<Student> result = new ArrayList<>(students);
        result.sort(Comparator.comparing(Student::getDateOfBirth));
        return result;
    }


    @Override
    public List<Student> sortStudentsByAverageGrade() {
        List<Student> result = new ArrayList<>(students);
        result.sort((student1, student2) -> {
            return Double.compare(student2.getAverageGrade(), student1.getAverageGrade());
        });
        return result;
    }


    @Override
    public List<Student> getByPartName(String name) {
        List<Student> matchingStudents = new ArrayList<>();

        for (Student student : students) {
            if (student.getName().contains(name)) {
                matchingStudents.add(student);
            }
        }

        Collections.sort(matchingStudents, Comparator.comparing(Student::getName));
        return matchingStudents;
    }

    @Override
    public List<Student> getByCurrentMonth() {
        LocalDate currentDate = LocalDate.now();
        int currentMonth = currentDate.getMonthValue();

        List<Student> studentsInCurrentMonth = new ArrayList<>();

        for (Student student : students) {
            LocalDate studentDateOfBirth = student.getDateOfBirth();
            if (studentDateOfBirth.getMonthValue() == currentMonth) {
                studentsInCurrentMonth.add(student);
            }
        }

        // Sorting the studentsInCurrentMonth list by student name
        studentsInCurrentMonth.sort(Comparator.comparing(Student::getName));

        return studentsInCurrentMonth;
    }
}
