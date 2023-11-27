package lab1;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class StudentTest {

    @Test
    public void testEnrollStudentInSubject() {
        Student student = new Student.StudentBuilder("John").dateOfBirth(LocalDate.of(1998, 12, 20)).build();
        Subject math = new Subject("Math", 4, 1);
        student.enroll(math, 90.0, 1);

        assertEquals(1, student.getEnrollments().size());
        assertEquals(math, student.getEnrollments().get(0).getSubject());
        assertEquals(90.0, student.getEnrollments().get(0).getGrade(), 0.01);
    }

    @Test
    public void testStudentEqualsAndHashCode() {
        Student student1 = new Student.StudentBuilder("John").dateOfBirth(LocalDate.of(2000, 1, 1)).build();
        Student student2 = new Student.StudentBuilder("John").dateOfBirth(LocalDate.of(2000, 1, 1)).build();
        Student student3 = new Student.StudentBuilder("Alice").dateOfBirth(LocalDate.of(2000, 1, 1)).build();

        assertEquals(student1, student2);
        assertNotEquals(student1, student3);
        assertEquals(student1.hashCode(), student2.hashCode());
        assertNotEquals(student1.hashCode(), student3.hashCode());
    }

    @Test
    public void testSubjectEqualsAndHashCode() {
        Subject math1 = new Subject("Math", 4, 1);
        Subject math2 = new Subject("Math", 4, 1;
        Subject physics = new Subject("Physics", 3, 2);

        assertEquals(math1, math2);
        assertNotEquals(math1, physics);
        assertEquals(math1.hashCode(), math2.hashCode());
        assertNotEquals(math1.hashCode(), physics.hashCode());
    }

    @Test
    public void testEnrollmentEqualsAndHashCode() {
        Subject math = new Subject("Math", 4, 1);
        Enrollment enrollment1 = new Enrollment(math, 90.0, 1);
        Enrollment enrollment2 = new Enrollment(math, 90.0, 1);
        Subject physics = new Subject("Physics", 3, 2);
        Enrollment enrollment3 = new Enrollment(physics, 85.5, 2);

        assertEquals(enrollment1, enrollment2);
        assertNotEquals(enrollment1, enrollment3);
        assertEquals(enrollment1.hashCode(), enrollment2.hashCode());
        assertNotEquals(enrollment1.hashCode(), enrollment3.hashCode());
    }
}
