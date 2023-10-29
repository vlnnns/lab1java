package lab2;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Paths;

public class JsonSerializer implements Serializer {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public <T> void serialize(T entity, String filename) throws IOException {
        objectMapper.writeValue(Paths.get(filename).toFile(), entity);
    }

    @Override
    public <T> T deserialize(String filename, Class<T> entityType) throws IOException {
        return objectMapper.readValue(Paths.get(filename).toFile(), entityType);
    }
}
