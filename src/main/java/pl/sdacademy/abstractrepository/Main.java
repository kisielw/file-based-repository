package pl.sdacademy.abstractrepository;

public class Main {
    public static void main(String[] args) {
        CarRepository carRepository = new CarRepository("cars.txt");
        System.out.println(carRepository.getAll());
        System.out.println(carRepository.get(2));
        Car car = new Car(300, "BMW", "czarny");
        carRepository.add(car);
    }
}
