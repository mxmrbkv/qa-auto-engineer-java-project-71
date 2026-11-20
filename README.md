# Вычислитель отличий (Java)

[![hexlet-check](https://github.com/mxmrbkv/qa-auto-engineer-java-project-71/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/mxmrbkv/qa-auto-engineer-java-project-71/actions)
[![build](https://github.com/mxmrbkv/qa-auto-engineer-java-project-71/actions/workflows/build.yml/badge.svg)](https://github.com/mxmrbkv/qa-auto-engineer-java-project-71/actions)

Консольная утилита, которая сравнивает два файла JSON или YAML и выводит разницу в трёх форматах (`stylish`, `plain`, `json`). Аргументы командной строки разбираются через `picocli`, чтение данных выполняется через `Jackson`, а форматы вывода разделены по отдельным классам.

Учебный проект Хекслета: https://ru.hexlet.io/programs/qa-auto-engineer-java

## Стек

- Java 21
- Gradle
- Picocli
- Jackson (JSON & YAML)
- Spotless (Google Java Format)
- JaCoCo
- JUnit 5 / AssertJ
- GitHub Actions

## Установка и сборка

```bash
git clone https://github.com/mxmrbkv/qa-auto-engineer-java-project-71.git
cd qa-auto-engineer-java-project-71/app
./gradlew installDist
```

## Использование

### Справка

```bash
./build/install/app/bin/app -h
```

```text
Usage: gendiff [-hV] [-f=format] filepath1 filepath2
Compares two configuration files and shows a difference.
      filepath1         path to first file
      filepath2         path to second file
  -f, --format=format   output format [default: stylish]
  -h, --help            Show this help message and exit.
  -V, --version         Print version information and exit.
```

### Формат `stylish` (по умолчанию)

```bash
./build/install/app/bin/app file1.json file2.json
```

```text
{
    chars1: [a, b, c]
  - chars2: [d, e, f]
  + chars2: false
  - checked: false
  + checked: true
  - default: null
  + default: [value1, value2]
  - id: 45
  + id: null
  - key1: value1
  + key2: value2
    numbers1: [1, 2, 3, 4]
  - numbers2: [2, 3, 4, 5]
  + numbers2: [22, 33, 44, 55]
  - numbers3: [3, 4, 5]
  + numbers4: [4, 5, 6]
  + obj1: {nestedKey=value, isNested=true}
  - setting1: Some value
  + setting1: Another value
  - setting2: 200
  + setting2: 300
  - setting3: true
  + setting3: none
}
```

### Формат `plain`

```bash
./build/install/app/bin/app -f plain file1.json file2.json
```

```text
Property 'chars2' was updated. From [complex value] to false
Property 'checked' was updated. From false to true
Property 'default' was updated. From null to [complex value]
Property 'id' was updated. From 45 to null
Property 'key1' was removed
Property 'key2' was added with value: 'value2'
Property 'numbers2' was updated. From [complex value] to [complex value]
Property 'numbers3' was removed
Property 'numbers4' was added with value: [complex value]
Property 'obj1' was added with value: [complex value]
Property 'setting1' was updated. From 'Some value' to 'Another value'
Property 'setting2' was updated. From 200 to 300
Property 'setting3' was updated. From true to 'none'
```

### Формат `json`

```bash
./build/install/app/bin/app -f json file1.json file2.json
```

```json
[{"key":"chars1","type":"unchanged","value":["a","b","c"]},{"key":"chars2","type":"changed","oldValue":["d","e","f"],"newValue":false},{"key":"checked","type":"changed","oldValue":false,"newValue":true},{"key":"default","type":"changed","oldValue":null,"newValue":["value1","value2"]},{"key":"id","type":"changed","oldValue":45,"newValue":null},{"key":"key1","type":"deleted","value":"value1"},{"key":"key2","type":"added","value":"value2"},{"key":"numbers1","type":"unchanged","value":[1,2,3,4]},{"key":"numbers2","type":"changed","oldValue":[2,3,4,5],"newValue":[22,33,44,55]},{"key":"numbers3","type":"deleted","value":[3,4,5]},{"key":"numbers4","type":"added","value":[4,5,6]},{"key":"obj1","type":"added","value":{"nestedKey":"value","isNested":true}},{"key":"setting1","type":"changed","oldValue":"Some value","newValue":"Another value"},{"key":"setting2","type":"changed","oldValue":200,"newValue":300},{"key":"setting3","type":"changed","oldValue":true,"newValue":"none"}]
```

### Демонстрация работы

[![asciicast](https://asciinema.org/a/NFIQgLVMu1ymFsqg4ESeOQeXi.svg)](https://asciinema.org/a/NFIQgLVMu1ymFsqg4ESeOQeXi)

---

<details>
<summary>Автоматические тесты Хекслета</summary>

Тесты запускаются на каждый коммит. За запуск отвечает файл `.github/workflows/hexlet-check.yml` — не удаляйте и не переименовывайте ни его, ни репозиторий.

</details>

## О Хекслете

[Хекслет](https://ru.hexlet.io/) — школа программирования: авторские программы обучения с практикой, поддержкой наставников и реальными проектами, которые остаются в резюме.
