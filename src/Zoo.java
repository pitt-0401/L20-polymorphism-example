/*
 * Created on 2026-11-09
 *
 * Copyright (c) 2026 Nadine von Frankenberg
 */

public class Zoo {

    public static void main(String[] args) {
        String[] types = { "cat", "dog", "pig" };
        for (String type : types) {
            Animal animal = createAnimal(type); // compile-time type: Animal
            runAnimalBehavior(animal); // same call, different behavior -> polymorphism!
            // runAnimalBehavior2(animal);
            System.out.println();
        }
    }

    private static Animal createAnimal(String type) {
        // Additional practice: use switch-case instead!
        if (type.equals("cat")) {
            return new Cat("Biscuit", 3); // runtime type: Cat
        } else if (type.equals("dog")) {
            return new Dog("Sally", 5);
        } else {
            return new Pig("Peppa", 4);
        }
    }

    private static void runAnimalBehavior(Animal animal) {
        animal.makeSound(); // dynamic binding: calls the runtime type's makeSound()
        // animal.scratchFurniture(); // -> not defined for Animal!
        // Pattern matching:
        if (animal instanceof Cat cat) { // check + cast in one step
            cat.scratchFurniture();
        } else if (animal instanceof Dog) {
            Dog dog = (Dog) animal; // "old" way: explicit downcast
            dog.bark();
        }
    }

    private static void runAnimalBehavior2(Animal animal) {
        animal.makeSound(); // dynamic binding: calls the runtime type's makeSound()
        // animal.scratchFurniture(); // -> not defined for Animal!
        // Pattern matching with switch-case:
        switch (animal) { // check + cast in one step
        case Cat cat -> cat.scratchFurniture();
        case Dog _ -> { // "unnamed pattern": type check only; no variable
            Dog dog = (Dog) animal; // traditional cast: explicit downcast
            dog.bark();
        } // arrow cases don't fall through -> no break needed
        default -> System.out.println("Animal type not defined.");
        }
    }
}
