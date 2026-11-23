package hexlet.code.formatters;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

class Plain {

    static String format(List<Map<String, Object>> diff) {
        List<String> lines = new ArrayList<>();

        for (Map<String, Object> node : diff) {
            String key = (String) node.get("key");
            String type = (String) node.get("type");

            switch (type) {
                case "added" -> {
                    String value = formatValue(node.get("value"));
                    lines.add("Property '" + key + "' was added with value: " + value);
                }
                case "deleted" -> {
                    lines.add("Property '" + key + "' was removed");
                }
                case "changed" -> {
                    String oldValue = formatValue(node.get("oldValue"));
                    String newValue = formatValue(node.get("newValue"));
                    lines.add(
                            "Property '"
                                    + key
                                    + "' was updated. From "
                                    + oldValue
                                    + " to "
                                    + newValue);
                }
                case "unchanged" -> {
                    // Do nothing for unchanged
                }
                default -> throw new RuntimeException("Unknown type: " + type);
            }
        }

        return String.join("\n", lines);
    }

    private static String formatValue(Object value) {
        if (value == null) {
            return "null";
        }
        if (value instanceof Map || value instanceof Collection || value instanceof Object[]) {
            return "[complex value]";
        }
        if (value instanceof String) {
            return "'" + value + "'";
        }
        return String.valueOf(value);
    }
}
