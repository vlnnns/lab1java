package lab2;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        SuperPuper superPuper = new SuperPuper();
        superPuper.setName("John");
        superPuper.setAge(30);
        superPuper.setChildren(2);

        Serializer jsonSerializer = new JsonSerializer();

        jsonSerializer.serialize(superPuper, "superpuper.json");


        SuperPuper deserializedSuperPuper = jsonSerializer.deserialize("superpuper.json", SuperPuper.class);


        Serializer XmlSerializer = new XmlSerializer();
        XmlSerializer.serialize(superPuper, "superpuper.xml");
        SuperPuper deserialized = XmlSerializer.deserialize("superpuper.xml", SuperPuper.class);

        Serializer TxtSerializer = new TxtSerializer();
        TxtSerializer.serialize(superPuper, "superpuper.txt");
        SuperPuper txtdeserialized = TxtSerializer.deserialize("superpuper.txt", SuperPuper.class);

        System.out.println(deserializedSuperPuper);
        System.out.println(deserialized);

    }
}