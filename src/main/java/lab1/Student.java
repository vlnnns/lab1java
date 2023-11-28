package lab1;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;


import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.*;

public class Student implements Comparable<Student>{

    public String getName() {
        return name;
    }
   @JsonFormat (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setEnrollments(List<Grade> enrollments) {
        this.grades = enrollments;
    }
    private int id;

    @NotBlank(message = "Name cannot be blank")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;

    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;

    private List<Grade> grades;

    private Student(StudentBuilder builder) {
        this.id = builder.id;
        this.grades = new ArrayList<>();
        this.name = builder.name;
        this.dateOfBirth = builder.dateOfBirth;

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public Student(int id, String name, LocalDate birthDate){
    }

    public void enroll(Subject subject, double grade, int id) {
        grades.add(new Grade(subject, grade, id));
    }


    public List<Grade> getEnrollments() {
        return grades;
    }



    @Override
    public String toString() {
        return "Student{id='"+ id +"', name='" + name + "', age=" + dateOfBirth + ", enrollments=" + grades + "}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Student student = (Student) obj;
        return dateOfBirth == student.dateOfBirth && Objects.equals(name, student.name) && Objects.equals(grades, student.grades) && Objects.equals(id, student.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, dateOfBirth, grades);
    }

    @Override
    public int compareTo(Student student) {
        return this.name.compareTo(student.name);
    }


    public double getAverageGrade(){

        double totalGrade = 0.0;
        for (Grade grade : grades) {
            totalGrade += grade.getGrade();
        }

        return totalGrade / grades.size();
    }



    public static class StudentBuilder {
        private int id;

        private String name;

        private LocalDate dateOfBirth;

        public StudentBuilder(String name) {
            this.name = name;
        }

        public StudentBuilder id(int id) {
            this.id = id;
            return this;
        }

        public StudentBuilder dateOfBirth(LocalDate dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }

        public Student build() {
            Student student = new Student(this);
            validate(student);
            return student;
        }

        private void validate(Student student) {

            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();

            Set<String> validationMessages = new HashSet<>();
            Set<ConstraintViolation<Student>> violations = validator.validate(student);

            for (ConstraintViolation<Student> violation : violations) {
                validationMessages.add(violation.getInvalidValue() + ": " + violation.getMessage());
            }

            if (!validationMessages.isEmpty()) {
                throw new IllegalArgumentException("Invalid fields: " + String.join(", ", validationMessages));
            }
        }

    }
}
