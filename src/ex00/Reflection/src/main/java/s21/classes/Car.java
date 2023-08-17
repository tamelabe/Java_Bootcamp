package s21.classes;

import java.util.StringJoiner;

public class Car {
    private String brand;
    private String model;
    private boolean isElectic;
    private double power;

    public Car() {
        this.brand = "Default brand";
        this.model = "Default model";
        this.isElectic = false;
        this.power = 0.0;
    }

    public Car(String brand, String model, boolean isElectic, double power) {
        this.brand = brand;
        this.model = model;
        this.power = power;
        this.isElectic = isElectic;
    }

    public double upgrade(double power) {
        this.power += power;
        return this.power;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Car.class.getSimpleName() + "[", "]")
                .add("brand='" + brand + "'")
                .add("model='" + model + "'")
                .add("power=" + power)
                .add("isElectric=" + isElectic)
                .toString();
    }
}
