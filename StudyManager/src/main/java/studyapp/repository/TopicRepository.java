package studyapp.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import studyapp.model.Topic;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class TopicRepository {
    private static final String FILE_PATH = "src/main/resources/topics.json";
    private final ObjectMapper mapper;
    private final File file;

    public TopicRepository() {
        this.mapper = new ObjectMapper();
        this.mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.mapper.enable(SerializationFeature.INDENT_OUTPUT);
        this.file = new File(FILE_PATH);
    }

    public Map<Integer, Topic> loadTopics() {
        if (!file.exists() || file.length() == 0) {
            return new TreeMap<>();
        }
        try {
            List<Topic> list = mapper.readValue(file, new TypeReference<List<Topic>>() {});
            return list.stream().collect(Collectors.toMap(Topic::getId, t -> t, (a, b) -> a, TreeMap::new));
        } catch (IOException e) {
            System.err.println("Error reading JSON file, starting with empty topics: " + e.getMessage());
            return new TreeMap<>();
        }
    }

    public void saveTopics(Map<Integer, Topic> topics) {
        try {
            mapper.writeValue(file, new ArrayList<>(topics.values()));
        } catch (IOException e) {
            System.err.println("Failed to save data: " + e.getMessage());
        }
    }
}