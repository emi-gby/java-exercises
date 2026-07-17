package studyapp.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import studyapp.model.Topic;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

public final class JsonUtils {

    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final File FILE = new File("src/main/resources/topics.json");

    static {
        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        MAPPER.enable(SerializationFeature.INDENT_OUTPUT);
    }

    private JsonUtils() {
        throw new AssertionError("Utility class cannot be instantiated");
    }

    public static <T> T fromJsonToList(TypeReference<T> typeRef) throws IOException {
        return MAPPER.readValue(FILE, typeRef);
    }

    public static TreeMap<Integer, Topic> fromListToMap(List<Topic> list) {
        return new TreeMap<Integer, Topic>(
                list.stream()
                        .collect(Collectors.toMap(
                                Topic::getId,
                                topic -> topic
                        ))
        );
    }

    public static void updateJson(TreeMap<Integer, Topic> topics) throws IOException {
        MAPPER.writerWithDefaultPrettyPrinter()
                .writeValue(FILE, topics.values());
    }
}