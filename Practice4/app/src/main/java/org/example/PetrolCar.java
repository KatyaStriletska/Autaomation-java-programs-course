package org.example;

@GenerateElement
public class PetrolCar implements Car {
    private String model;
    private String color;
    private int age;
    private boolean isBought;

    public PetrolCar(String model, String color, int age) {
        this.model = model;
        this.color = color;
        this.age = age;
        this.isBought = false;
    }
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
    @MinAge()
    @Override
    public boolean buyCar() {
        if (!isBought) {
            isBought = true;
            return true;
        }
        return false;
    }
}
