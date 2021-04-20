package pl.sdacademy;

public class Main {
    public static void main(String[] args) {
        PersonRepository personRepository = new PersonRepository("people.txt");
        System.out.println(personRepository.getAll());
        System.out.println(personRepository.get(6));
    }
}
