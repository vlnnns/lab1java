package lab2;


import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import javax.sound.midi.Patch;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TxtSerializer implements Serializer{

    @Override
    public <T> void serialize(T entity, String filename) throws IOException {
        String stepler = entity.toString();
        Files.write(Paths.get(filename),stepler.getBytes());
    }

    @Override
    public <T> T deserialize(String filename, Class<T> entityType) throws IOException {
        String stepler = new String(Files.readAllBytes(Paths.get(filename)));
        return null;
    }
}
