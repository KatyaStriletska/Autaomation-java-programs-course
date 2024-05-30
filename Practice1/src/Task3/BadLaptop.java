package Task3;

import java.util.Objects;

public class BadLaptop {
    private String model;
    private int releaseYear;
    private double screenSize;

    public BadLaptop(String model, int releaseYear, double screenSize) {
        this.model = model;
        this.releaseYear = releaseYear;
        this.screenSize = screenSize;
    }

    @Override
    public String toString() {
        return "BadLaptop{" +
                "model='" + model + '\'' +
                ", releaseYear=" + releaseYear +
                ", screenSize=" + screenSize +
                '}';
    }

    @Override
    public boolean equals(Object o) {
       return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(model, releaseYear, screenSize);
    }


}
