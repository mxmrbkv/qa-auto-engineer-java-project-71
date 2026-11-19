package hexlet;

import hexlet.code.Differ;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class DifferTest {

    private static String expectedFlat;

    private static Path getFixturePath(String fileName) {
        return Paths.get("src", "test", "resources", fileName).toAbsolutePath().normalize();
    }

    private static String readFixture(String fileName) throws Exception {
        Path path = getFixturePath(fileName);
        return Files.readString(path).replace("\r\n", "\n");
    }

    @BeforeAll
    static void setUp() throws Exception {
        expectedFlat = readFixture("expected_flat.txt");
    }

    @Test
    void testGenerateFlatJsonDefaultFormat() throws Exception {
        String path1 = getFixturePath("file1.json").toString();
        String path2 = getFixturePath("file2.json").toString();

        String actual = Differ.generate(path1, path2);
        assertThat(actual).isEqualTo(expectedFlat);
    }

    @Test
    void testGenerateFlatJsonStylishFormat() throws Exception {
        String path1 = getFixturePath("file1.json").toString();
        String path2 = getFixturePath("file2.json").toString();

        String actual = Differ.generate(path1, path2, "stylish");
        assertThat(actual).isEqualTo(expectedFlat);
    }

    @Test
    void testGenerateFlatYamlDefaultFormat() throws Exception {
        String path1 = getFixturePath("file1.yml").toString();
        String path2 = getFixturePath("file2.yml").toString();

        String actual = Differ.generate(path1, path2);
        assertThat(actual).isEqualTo(expectedFlat);
    }

    @Test
    void testGenerateFlatYamlStylishFormat() throws Exception {
        String path1 = getFixturePath("file1.yaml").toString();
        String path2 = getFixturePath("file2.yaml").toString();

        String actual = Differ.generate(path1, path2, "stylish");
        assertThat(actual).isEqualTo(expectedFlat);
    }

    @Test
    void testGenerateMixedJsonAndYaml() throws Exception {
        String path1 = getFixturePath("file1.json").toString();
        String path2 = getFixturePath("file2.yml").toString();

        String actual = Differ.generate(path1, path2);
        assertThat(actual).isEqualTo(expectedFlat);
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
    void testParseYamlFile1() throws Exception {
        Path path = getFixturePath("file1.yml");
        Map<String, Object> data = Differ.getData(path.toString());

        assertThat(data)
                .containsEntry("host", "hexlet.io")
                .containsEntry("timeout", 50)
                .containsEntry("proxy", "123.234.53.22")
                .containsEntry("follow", false);
    }

    @Test
    void testParseYamlFile2() throws Exception {
        Path path = getFixturePath("file2.yml");
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
