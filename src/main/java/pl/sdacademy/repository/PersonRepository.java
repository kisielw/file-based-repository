package pl.sdacademy.repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PersonRepository {
    private Set<Person> people;
    private Path filePath;

    public PersonRepository(String filename) {
        filePath = Paths.get(filename);
        try {
            people = Files.lines(filePath)
            .map(this::createPerson)            // .map(fileLine -> createPerson(fileLine)) - inaczej napisane
            .collect(Collectors.toSet());
        } catch (IOException e) {
            throw new RuntimeException("Błąd odczytu danych z pliku");
        }
        for (Person person : people) {
            person.setId(generateNextId());
        }

    }

    private Person createPerson(String fileLine) {
        String[] fileLineParts = fileLine.split(",");
        String firstName = fileLineParts[1];
        String lastName = fileLineParts[2];
        int age = Integer.parseInt(fileLineParts[3]);
        return new Person(firstName, lastName, age);

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
        if (people != null) {
            for (Person person : people) {
                if (person.getId() != null && person.getId() > id) {
                    id = person.getId();
                }
            }
        }
        return id + 1;
        /*return people.stream()
                .mapToInt(Person::getId)
                .max()
                .orElse(0) + 1;*/
    }
    //Dodaj do klasy PersonRepository prywatną metodę createFileLine, która zadziała odwrotnie do metody createPerson
    private String createFileLine(Person person) {
       return person.getId() + "," + person.getFirstName() + "," + person.getLastName() + "," + person.getAge();
       /* StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(person.getId());
        stringBuilder.append(",");
        stringBuilder.append(person.getFirstName());
        stringBuilder.append(",");
        stringBuilder.append(person.getLastName());
        stringBuilder.append(",");
        stringBuilder.append(person.getAge());
        return stringBuilder.toString(); */
    }
    //4. Dodaj do klasy PersonRepository prywatną metodę saveData, która zapisze aktualny stan listy osób do pliku
    // (przy użyciu metody createFileLine).
    private void saveData() {
        /*Path filePath = Paths.get(filename);
        List<String> personList = new ArrayList<>();
        for (Person person : people) {
            personList.add(createFileLine(person));
        }
        Files.write(filePath, personList);*/
        List<String> fileLines = people.stream()
                .map(this::createFileLine) // map(person -> createFileLine(person))
                .collect(Collectors.toList());
        try {
            Files.write(filePath,fileLines);
        } catch (IOException e) {
            throw new RuntimeException("Błąd zapisu danych", e);
        }
    }

    //6. Dodaj do klasy PersonRepository metodę add, która przyjmie jako parametr obiekt typu Person,
    // a która doda do repozytorium daną osobę.

    public void add(Person person) {
        person.setId(generateNextId());
        people.add(person);
        saveData();
    }

}
