package lab2;
import java.io.Serializable;

public class SuperPuper implements Serializable {
    private String name;
    private int age;
    private int children;

    public SuperPuper(String name, int age, int children) {
        this.name = name;
        this.age = age;
        this.children = children;

    }

    public SuperPuper() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getChildren() {
        return children;
    }

    public void setChildren(int children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "SuperPuper{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", children=" + children +
                '}';
    }
}
