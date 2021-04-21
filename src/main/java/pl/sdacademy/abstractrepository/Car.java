package pl.sdacademy.abstractrepository;

public class Car implements Entity {
    private Integer id;
    private int maxSpeed;
    private String model;
    private String color;

    public Car(Integer id, int maxSpeed, String model, String color) {
        this.id = id;
        this.maxSpeed = maxSpeed;
        this.model = model;
        this.color = color;
    }

    public Car(int maxSpeed, String model, String color) {
        this.maxSpeed = maxSpeed;
        this.model = model;
        this.color = color;
    }
    @Override
    public Integer getId() {
        return id;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public String getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", maxSpeed=" + maxSpeed +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
