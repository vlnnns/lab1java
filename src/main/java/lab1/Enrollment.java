package lab1;

import java.util.Objects;

public class Enrollment {
    private Subject subject;
    private double grade;

    public Enrollment(Subject subject, double grade) {
        this.subject = subject;
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Enrollment{subject=" + subject + ", grade=" + grade + "}";
    }

    public Subject getSubject() {
        return subject;
    }

    public double getGrade() {
        return grade;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Enrollment enrollment = (Enrollment) obj;
        return Objects.equals(subject, enrollment.subject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subject);
    }

}