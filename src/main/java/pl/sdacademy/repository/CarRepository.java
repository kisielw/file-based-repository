package pl.sdacademy.repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CarRepository {
    private Set<Car> cars;
    private Path filePath;

    public CarRepository(String filename) {
        filePath = Paths.get(filename);
        try {
            cars = Files.lines(filePath)
                    .map(this::createCar)
                    .collect(Collectors.toSet());
        } catch (IOException e) {
            throw new RuntimeException("Błąd odczytu danych z pliku");
        }
        for (Car car : cars) {
            car.setId(generateNextId());
        }
    }

    private Car createCar(String fileLine) {
        String[] fileLineParts = fileLine.split(",");
        int maxSpeed = Integer.parseInt(fileLineParts[1]);
        String model = fileLineParts[2];
        String color= fileLineParts[3];
        return new Car(maxSpeed, model, color);
    }

    public Set<Car> getAll() {
        return cars;
    }

    public Car get(int id) {
        return cars.stream()
                .filter(car -> car.getId() == id)
                .findFirst()
                .orElse(null);
    }

    private int generateNextId() {
        int id = 0;
        if (cars != null) {
            for (Car car : cars) {
                if (car.getId() != null && car.getId() > id) {
                    id = car.getId();
                }
            }
        }
        return id + 1;
    }

    private String createFileLine(Car car) {
        return car.getId() + "," + car.getMaxSpeed() + "," + car.getModel() + "," + car.getColor();
    }

    private void saveData() {
        List<String> fileLines = cars.stream()
                .map(this::createFileLine)
                .collect(Collectors.toList());
        try {
            Files.write(filePath,fileLines);
        } catch (IOException e) {
            throw new RuntimeException("Błąd zapisu danych", e);
        }
    }

    public void add(Car car) {
        car.setId(generateNextId());
        cars.add(car);
        saveData();
    }
}
