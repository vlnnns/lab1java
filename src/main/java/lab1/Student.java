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

    public void setEnrollments(List<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }
    private int id;

    @NotBlank(message = "Name cannot be blank")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;

    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;

    private List<Enrollment> enrollments;

    private Student(StudentBuilder builder) {
        this.id = builder.id;
        this.enrollments = new ArrayList<>();
        this.name = builder.name;
        this.dateOfBirth = builder.dateOfBirth;

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    private Student (){
    }

    public void enroll(Subject subject, double grade, int id) {
        enrollments.add(new Enrollment(subject, grade, id));
    }


    public List<Enrollment> getEnrollments() {
        return enrollments;
    }



    @Override
    public String toString() {
        return "Student{id='"+ id +"', name='" + name + "', age=" + dateOfBirth + ", enrollments=" + enrollments + "}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Student student = (Student) obj;
        return dateOfBirth == student.dateOfBirth && Objects.equals(name, student.name) && Objects.equals(enrollments, student.enrollments) && Objects.equals(id, student.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, dateOfBirth, enrollments);
    }

    @Override
    public int compareTo(Student student) {
        return this.name.compareTo(student.name);
    }


    public double getAverageGrade(){

        double totalGrade = 0.0;
        for (Enrollment enrollment : enrollments) {
            totalGrade += enrollment.getGrade();
        }

        return totalGrade / enrollments.size();
    }

    public static void createTable() {
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement()) {
            String createTableQuery = "CREATE TABLE IF NOT EXISTS student (" +
                    "id SERIAL PRIMARY KEY," +
                    "name VARCHAR(100) NOT NULL," +
                    "date_of_birth DATE NOT NULL," +
                    "enrollment_id INTEGER," +
                    "FOREIGN KEY (enrollment_id) REFERENCES enrollments(id)" + // Змінено назву таблиці на enrollments
                    ");";

            statement.execute(createTableQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
