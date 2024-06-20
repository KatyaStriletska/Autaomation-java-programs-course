package org.example;

@GenerateElement(CarTypes.ELECTRIC)
public class ElectricCar implements Car {
    @NotNull
    private String model;

    @NotNull
    private String color;
    @NotNull
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
    }
    public ElectricCar(){}

    public void setModel(String model) {
        this.model = model;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setAge(int age) {
        this.age = age;
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
