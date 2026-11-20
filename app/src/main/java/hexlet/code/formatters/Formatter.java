package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class Formatter {

    public static String format(List<Map<String, Object>> diff, String formatName)
            throws Exception {
        return switch (formatName.toLowerCase()) {
            case "stylish" -> Stylish.format(diff);
            case "plain" -> Plain.format(diff);
            case "json" -> Json.format(diff);
            default -> throw new IllegalArgumentException("Unknown format: " + formatName);
        };
    }
}
