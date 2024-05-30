package Task3;

import java.util.Objects;

public class Laptop {
    private String model;
    private int releaseYear;
    private double screenSize;

    public Laptop(String model, int releaseYear, double screenSize) {
        this.model = model;
        this.releaseYear = releaseYear;
        this.screenSize = screenSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Laptop laptop = (Laptop) o;
        return releaseYear == laptop.releaseYear && screenSize == laptop.screenSize && Objects.equals(model, laptop.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model, releaseYear, screenSize);
    }

    @Override
    public String toString() {
        return "Laptop{" +
                "model='" + model + '\'' +
                ", releaseYear=" + releaseYear +
                ", screenSize=" + screenSize +
                '}';
    }
}
