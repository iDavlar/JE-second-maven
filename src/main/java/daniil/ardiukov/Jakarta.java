package daniil.ardiukov;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Jakarta {
    private String version;
    private String description;
    private List<Technology> technologies = new ArrayList<>();

    public static Jakarta readFromJson(String path) {
        try (FileInputStream fis = new FileInputStream(path)) {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(fis, Jakarta.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeToJson(String path) {
        try (FileOutputStream fis = new FileOutputStream(path)) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(fis, this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void updateTechnology(Technology technology) {
        if (!technologies.contains(technology)) {
            technologies.add(technology);
        }
    }
}
