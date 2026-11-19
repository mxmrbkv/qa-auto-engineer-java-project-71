package hexlet.code;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class DifferTest {
    private static Path getFixturePath(String fileName) {
        return Paths.get("src", "test", "resources", fileName).toAbsolutePath().normalize();
    }

    @Test
    void testParseFile1() throws Exception {
        Path path = getFixturePath("file1.json");
        Map<String, Object> data = Differ.getData(path.toString());

        assertThat(data)
                .containsEntry("host", "hexlet.io")
                .containsEntry("timeout", 50)
                .containsEntry("proxy", "123.234.53.22")
                .containsEntry("follow", false);
    }

    @Test
    void testParseFile2() throws Exception {
        Path path = getFixturePath("file2.json");
        Map<String, Object> data = Differ.getData(path.toString());

        assertThat(data)
                .containsEntry("timeout", 20)
                .containsEntry("verbose", true)
                .containsEntry("host", "hexlet.io");
    }

    @Test
    void testRelativePath() throws Exception {
        Map<String, Object> data = Differ.getData("file1.json");
        assertThat(data).containsKey("host");
    }

    @Test
    void testFileNotFound() {
        assertThatThrownBy(() -> Differ.getData("non_existent_file.json"))
                .isInstanceOf(Exception.class)
                .hasMessageContaining("does not exist");
    }
}
