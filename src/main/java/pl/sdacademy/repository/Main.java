package pl.sdacademy.repository;

public class Main {
    public static void main(String[] args) {
        PersonRepository personRepository = new PersonRepository("people.txt");
        System.out.println(personRepository.getAll());
        System.out.println(personRepository.get(6));
        Person person = new Person("Adam", "Nowak", 85);
        personRepository.add(person);

        CarRepository carRepository = new CarRepository("cars.txt");
        System.out.println(carRepository.getAll());
        System.out.println(carRepository.get(1));
        Car car = new Car(250, "Seat", "czarny");
        carRepository.add(car);

    }
}
