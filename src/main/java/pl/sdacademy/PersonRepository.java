package pl.sdacademy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import java.util.stream.Collectors;

public class PersonRepository {
    private Set<Person> people;

    public PersonRepository(String filename) {
        Path filePath = Paths.get(filename);
        try {
            people = Files.lines(filePath)
            .map(this::createPerson)            // .map(fileLine -> createPerson(fileLine)) - inaczej napisane
            .collect(Collectors.toSet());
        } catch (IOException e) {
            throw new RuntimeException("Błąd odczytu danych z pliku");
        }
    }

    private Person createPerson(String fileLine) {
        String[] fileLineParts = fileLine.split(",");
        int id = Integer.parseInt(fileLineParts[0]);
        String firstName = fileLineParts[1];
        String lastName = fileLineParts[2];
        int age = Integer.parseInt(fileLineParts[3]);
        return new Person(id, firstName, lastName, age);
    }

    public Set<Person> getAll() {
        return people;
    }

}
