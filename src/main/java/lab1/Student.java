package lab1;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

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

    @NotBlank(message = "Name cannot be blank")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;

    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;

    private List<Enrollment> enrollments;

    private Student(StudentBuilder builder) {
        this.enrollments = new ArrayList<>();
        this.name = builder.name;
        this.dateOfBirth = builder.dateOfBirth;
//        this.dateOfBirth = builder.build().dateOfBirth;
    }

    private Student (){
    }

    public void enroll(Subject subject, double grade) {
        enrollments.add(new Enrollment(subject, grade));
    }


    public List<Enrollment> getEnrollments() {
        return enrollments;
    }



    @Override
    public String toString() {
        return "Student{name='" + name + "', age=" + dateOfBirth + ", enrollments=" + enrollments + "}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Student student = (Student) obj;
        return dateOfBirth == student.dateOfBirth && Objects.equals(name, student.name) && Objects.equals(enrollments, student.enrollments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, dateOfBirth, enrollments);
    }

    @Override
    public int compareTo(Student student) {
        return this.name.compareTo(student.name);
    }


    public boolean hasEnrollmentForSubject(String subjectName) {
        return getEnrollments().stream()
                .anyMatch(enrollment -> enrollment.getSubject().getName().equals(subjectName));
    }

    // Метод, який повертає оцінку студента за предмет
    public double getEnrollmentGradeForSubject(String subjectName) {
        return getEnrollments().stream()
                .filter(enrollment -> enrollment.getSubject().getName().equals(subjectName))
                .findFirst()
                .map(Enrollment::getGrade)
                .orElse(0.0); // За замовчуванням, якщо предмет "Math" відсутній, повертається 0.0
    }

    public double getAverageGrade(){

        double totalGrade = 0.0;
        for (Enrollment enrollment : enrollments) {
            totalGrade += enrollment.getGrade();
        }

        return totalGrade / enrollments.size();
    }


    public static class StudentBuilder {

        private String name;

        private LocalDate dateOfBirth;

        public StudentBuilder(String name) {
            this.name = name;
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
