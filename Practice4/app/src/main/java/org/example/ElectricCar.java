package org.example;

public class ElectricCar implements Car {
    //    @NotNull
    private String model;

    //    @NotNull
//    @MaxLength(10)
    private String color;

    private int age;
    private boolean isBought;

    @Override
    public String getModel() {
        return model;
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public int getAge() {
        return age;
    }

    public ElectricCar(String model, String color, int age) {
        this.model = model;
        this.color = color;
        this.age = age;
        this.isBought = false;
    }

    @MinAge(7)
    @Override
    public boolean buyCar() {
        if (!isBought) {
            isBought = true;
            return true;
        }
        return false;
    }
}
