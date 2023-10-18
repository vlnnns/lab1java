package org.example;

import java.util.Objects;

public class Subject {
    private String name;
    private int credits;

    public Subject(String name, int credits) {
        this.name = name;
        this.credits = credits;
    }


    @Override
    public String toString() {
        return "Subject{name='" + name + "', credits=" + credits + "}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Subject subject = (Subject) obj;
        return credits == subject.credits && Objects.equals(name, subject.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, credits);
    }
}