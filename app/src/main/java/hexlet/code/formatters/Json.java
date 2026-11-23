package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.Map;

class Json {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static String format(List<Map<String, Object>> diff) throws JsonProcessingException {
        return OBJECT_MAPPER.writeValueAsString(diff);
    }
}
