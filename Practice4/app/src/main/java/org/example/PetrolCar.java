package org.example;

@GenerateElement(CarTypes.PETROL)
public class PetrolCar implements Car {
    @NotNull
    private String model;
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

    public void setModel(String model) {
        this.model = model;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setAge(int age) {
        this.age = age;
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
