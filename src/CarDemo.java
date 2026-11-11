
public class CarDemo {
    public static void main(String[] args) {
        Car myCar = new Car("BMW");
        try {
            Car car = myCar.clone(); // copy of myCar, but with its own serial number
            car.start();
        } catch (CloneNotSupportedException e) { // this is a so-called "checked exception" -> must be handled
            e.printStackTrace();
        }
        myCar.start();
    }
}

// Interfaces can provide default implementations
interface Engine {

    default void start() {
        System.out.println("Engine starting...");
    }
}

interface Battery {
    default void start() {
        System.out.println("Battery starting...");
    }
}

// TODO: Add "implements Engine, Battery" -> both define a default start() method,
// so Car must override start() (once!) and can call Engine.super.start() and/or
// Battery.super.start() inside it (see lecture slides)
class Car implements Battery, Cloneable { // Cloneable: marker interface, allows super.clone()
    private String model;
    private int serialNumber;
    private static int number = 1; // shared by all Car objects

    Car(String model) {
        this.model = model;
        this.serialNumber = number;
        number++;
    }

    String getModel() {
        return model;
    }

    void setModel(String model) {
        this.model = model;
    }

    @Override
    public void start() {
        Battery.super.start(); // calls Battery's default start()
        // Engine.super.start();
        System.out.printf("%s (#%d) started successfully.\n", model, serialNumber);
    }

    @Override
    public Car clone() throws CloneNotSupportedException {
        Car car = (Car) super.clone(); // shallow copy
        car.serialNumber = number; // needed as otherwise the clone would have the same serial number
        number++;
        return car;
    }
}
