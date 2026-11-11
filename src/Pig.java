public class Pig extends Animal {

    public Pig(String name, int age) {
        super(name, age, "Pig");
    }

    @Override
    public void makeSound() {
        System.out.println("Oink" + " (" + getName() + ")");
    }
}
