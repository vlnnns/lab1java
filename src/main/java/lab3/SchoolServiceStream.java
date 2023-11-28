package lab3;

import lab1.Grade;
import lab1.Student;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SchoolServiceStream implements ISchoolService{

    private List<Student> students;

    public SchoolServiceStream(List<Student> students){
        this.students = students;
    }

    @Override
    public List<Student> sortStudentsByName() {
        List<Student> result = new ArrayList<>(students);
        return result.stream()
                .sorted(Comparator.comparing(Student::getName))
                .collect(Collectors.toList());
    }

    @Override
    public List<Student> sortStudentsByDateOfBirth() {
        List<Student> result = new ArrayList<>(students);
        return result.stream()
                .sorted(Comparator.comparing(Student::getDateOfBirth))
                .collect(Collectors.toList());
    }

    @Override
    public List<Student> sortStudentsByAverageGrade() {
        List<Student> result = new ArrayList<>(students);
        return result.stream()
                .sorted((student1, student2) ->
                        Double.compare(student2.getAverageGrade(), student1.getAverageGrade()))
                .collect(Collectors.toList());
    }


    @Override
    public List<Student> getByPartName(String name) {
        List<Student> result = new ArrayList<>(students);
        return result.stream()
                .filter(student -> student.getName().contains(name))
                .sorted(Comparator.comparing(Student::getName))
                .collect(Collectors.toList());
    }

    @Override
    public List<Student> getByCurrentMonth() {
        List<Student> result = new ArrayList<>(students);
        LocalDate currentDate = LocalDate.now();
        int currentMonth = currentDate.getMonthValue();

        return result.stream()
                .filter(student -> student.getDateOfBirth().getMonthValue() == currentMonth)
                .sorted(Comparator.comparing(Student::getName))
                .collect(Collectors.toList());
    }
}
