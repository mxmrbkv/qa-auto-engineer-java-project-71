package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class Stylish {
    public static String format(List<Map<String, Object>> diff) {
        StringBuilder result = new StringBuilder("{\n");

        for (Map<String, Object> node : diff) {
            String key = (String) node.get("key");
            String type = (String) node.get("type");

            switch (type) {
                case "deleted" -> result.append("  - ").append(key).append(": ").append(node.get("value")).append("\n");
                case "added" -> result.append("  + ").append(key).append(": ").append(node.get("value")).append("\n");
                case "unchanged" -> result.append("    ").append(key).append(": ").append(node.get("value")).append("\n");
                case "changed" -> {
                    result.append("  - ").append(key).append(": ").append(node.get("oldValue")).append("\n");
                    result.append("  + ").append(key).append(": ").append(node.get("newValue")).append("\n");
                }
                default -> throw new RuntimeException("Unknown type: " + type);
            }
        }

        result.append("}");
        return result.toString();
    }
}
