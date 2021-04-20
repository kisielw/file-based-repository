package pl.sdacademy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
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
// 1. Dodaj metodę get, która przyjmie za parametr wartość typu int, a która zwróci osobę o zadanym identyfikatorze.
// Jeśli takiej osoby nie będzie zwróć null.

    public Person get(int id) {
        /*Person findPerson = null;
        for (Person person : people) {
            if (person.getId() == id) {
                findPerson = person;
            }
        }
        return findPerson;*/
        return people.stream()
                .filter(person -> person.getId() == id)
                .findFirst()//trzeba orElse bo mamy Optional Person, tzn. że może czegoś nie znaleźć i co w takim przypdku trzeba zainicjować
                .orElse(null);
    }



    // 2. Dodaj do klasy PersonRepository prywatną metodę generateNextId, która nie przyjmie żadnego parametru,
    // a która zwróci pierwszą "wolną" wartość identyfikatora osoby.
    //Niech metoda działa następująco - znajdujemy maksymalny identyfikator i dodajemy do niego 1.
    private int generateNextId() {
        int id = 0;
        for (Person person : people) {
            if (person.getId() > id) {
                id = person.getId();
            }
        }
        id++;
        return id;
    }
    //Dodaj do klasy PersonRepository prywatną metodę createFileLine, która zadziała odwrotnie do metody createPerson
    private String createFileLine(Person person) {
       // return person.getId() + "," + person.getFirstName() + "," + person.getLastName() + "," + person.getAge();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(person.getId());
        stringBuilder.append(",");
        stringBuilder.append(person.getFirstName());
        stringBuilder.append(",");
        stringBuilder.append(person.getLastName());
        stringBuilder.append(",");
        stringBuilder.append(person.getAge());
        return stringBuilder.toString();
    }

}
