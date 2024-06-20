package org.example;



public class Main {
    public static void main(String[] args) {
        try {
            Car car = new ElectricCar();
            AnnotationValidator.processAnnotations(car);
            System.out.println("Car successfully bought");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            ElectricCar oldCar = new ElectricCar();
            oldCar.setAge(2);
            oldCar.setColor("White");
            oldCar.setModel("Tesla");
            AnnotationValidator.processAnnotations(oldCar);
            System.out.println("Car successfully bought");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            PetrolCar goodCar = new PetrolCar();
            goodCar.setModel("Honda");
            goodCar.setAge(5);
            goodCar.setColor("Black");
            AnnotationValidator.processAnnotations(goodCar);
            System.out.println("Car successfully bought");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}