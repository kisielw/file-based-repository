package pl.sdacademy.abstractrepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class AbstractRepository<T extends Entity> { // typ generyczny nie implements, tylko extends, dlatego że T może być też interfejsem
    private Set<T> entities; //encje
    private Path filePath;

    public AbstractRepository(String filename) {
        filePath = Paths.get(filename);
        try {
            entities = Files.lines(filePath)
                    .map(this::createEntity)
                    .collect(Collectors.toSet());
        } catch (IOException e) {
            throw new RuntimeException("Błąd odczytu danych z pliku");
        }
    }

    protected abstract T createEntity(String fileLine); // protected - mogą się odwołać do tej metody tylko klasy rozszerzające tę metodę

    public Set<T> getAll() {
        return entities;
    }

    public T get(int id) {
        return entities.stream()
                .filter(entity -> entity.getId() == id)
                .findFirst()
                .orElse(null);
    }

    private int generateNextId() {
        return entities.stream()
                .mapToInt(Entity::getId)
                .max()
                .orElse(0) + 1;
    }

    protected abstract String createFileLine(T entity);

    private void saveData() {
        List<String> fileLines = entities.stream()
                .map(this::createFileLine)
                .collect(Collectors.toList());
        try {
            Files.write(filePath, fileLines);
        } catch (IOException e) {
            throw new RuntimeException("Błąd zapisu danych", e);
        }
    }

    public void add(T entity) {
        entity.setId(generateNextId());
        entities.add(entity);
        saveData();
    }

}
