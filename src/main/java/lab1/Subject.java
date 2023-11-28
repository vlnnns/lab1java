package lab1;


import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class Subject {
    private int id;
    private String name;
    private int credits;

    public Subject(String name, int credits, int id) {
        this.id = id;
        this.name = name;
        this.credits = credits;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @Override
    public String toString() {
        return "Subject{id = ' "+ id + " ' , name='" + name + "', credits=" + credits + "}";
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
        return Objects.hash(id, name, credits);
    }

    public Object getName() {
        return this.name;
    }


}