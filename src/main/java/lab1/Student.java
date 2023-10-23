package lab1;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Student {
    private String name;
    private LocalDate dateOfBirth;
    private List<Enrollment> enrollments;

    private Student(StudentBuilder builder) {
        this.name = builder.name;
        this.dateOfBirth = builder.build().dateOfBirth;
        this.enrollments = new ArrayList<>();
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
            return new Student(this);
        }
    }
}
