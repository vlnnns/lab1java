package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Student {
    private String name;
    private int age;
    private List<Enrollment> enrollments;

    private Student(StudentBuilder builder) {
        this.name = builder.name;
        this.age = builder.age;
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
        return "Student{name='" + name + "', age=" + age + ", enrollments=" + enrollments + "}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Student student = (Student) obj;
        return age == student.age && Objects.equals(name, student.name) && Objects.equals(enrollments, student.enrollments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, enrollments);
    }

    public static class StudentBuilder {
        private String name;
        private int age;

        public StudentBuilder(String name) {
            this.name = name;
        }

        public StudentBuilder age(int age) {
            this.age = age;
            return this;
        }

        public Student build() {
            return new Student(this);
        }
    }
}
