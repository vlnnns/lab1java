package lab2;

import lab1.Student;

import java.io.IOException;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws IOException {
        Student student = new Student.StudentBuilder("John")
                .dateOfBirth(LocalDate.of(2000, 1, 1))
                .build();
//        SuperPuper superPuper = new SuperPuper();
//        superPuper.setName("John");
//        superPuper.setAge(30);
//        superPuper.setChildren(2);

//        Serializer jsonSerializer = new JsonSerializer();
//
//        jsonSerializer.serialize(student, "superpuper.json");
//
//
//        Student deserializedSuperPuper = jsonSerializer.deserialize("superpuper.json", Student.class);


//        Serializer XmlSerializer = new XmlSerializer();
//        XmlSerializer.serialize(student, "superpuper.xml");
//        Student deserialized = XmlSerializer.deserialize("superpuper.xml", Student.class);

        Serializer TxtSerializer = new TxtSerializer();
        TxtSerializer.serialize(student, "superpuper.txt");
        Student txtdeserialized = TxtSerializer.deserialize("superpuper.txt", Student.class);
//
//        System.out.println(deserializedSuperPuper);
//        System.out.println(deserialized);

    }
}