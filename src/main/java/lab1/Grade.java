package lab1;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class Grade {
    private int id;
    private Subject subject;
    private double grade;

    public Grade(Subject subject, double grade, int id) {
        this.id = id;
        this.subject = subject;
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Enrollment{id ="+ id + ", subject=" + subject + ", grade=" + grade + "}";
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        Grade enrollment = (Grade) obj;
        return Objects.equals(subject, enrollment.subject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subject);
    }




}