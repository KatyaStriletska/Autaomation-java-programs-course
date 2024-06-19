package org.example;



public class Main {
    public static void main(String[] args) {
        try {
            Car car = new ElectricCar("Toyota", "Red", 5);
            AnnotationValidator.processAnnotations(car);
            System.out.println("Car successfully bought");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            Car oldCar = new PetrolCar("Honda", "Blue", 10);
            AnnotationValidator.processAnnotations(oldCar);
            System.out.println("Car successfully bought");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}