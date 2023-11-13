package lab1;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    private String name;

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
            return new Student(this);
        }
    }
}
