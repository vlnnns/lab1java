package lab2;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.IOException;
import java.nio.file.Paths;

public class XmlSerializer implements Serializer {
    private XmlMapper xmlMapper = new XmlMapper();

    @Override
    public <T> void serialize(T entity, String filename) throws IOException {
        xmlMapper.writeValue(Paths.get(filename).toFile(), entity);
    }

    @Override
    public <T> T deserialize(String filename, Class<T> entityType) throws IOException {
        return xmlMapper.readValue(Paths.get(filename).toFile(), entityType);
    }
}