package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class Parser {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static Map<String, Object> parse(String content, String dataFormat) throws Exception {
        if ("json".equalsIgnoreCase(dataFormat)) {
            return OBJECT_MAPPER.readValue(content, new TypeReference<Map<String, Object>>() { });
        }
        throw new IllegalArgumentException("Unsupported data format: " + dataFormat);
    }
}
