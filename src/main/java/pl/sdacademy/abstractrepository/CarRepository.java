package pl.sdacademy.abstractrepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CarRepository extends AbstractRepository<Car> {
    public CarRepository(String filename) {
        super(filename);
    }

    @Override
    protected Car createEntity(String fileLine) {
        String[] fileLineParts = fileLine.split(",");
        int id = Integer.parseInt(fileLineParts[0]);
        int maxSpeed = Integer.parseInt(fileLineParts[1]);
        String model = fileLineParts[2];
        String color = fileLineParts[3];
        return new Car(id, maxSpeed, model, color);
    }

    @Override
    protected String createFileLine(Car entity) {
        return entity.getId() + "," + entity.getMaxSpeed()
                + "," + entity.getModel() + "," + entity.getColor();
    }


}
