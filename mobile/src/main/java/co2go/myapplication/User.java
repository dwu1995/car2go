package co2go.myapplication;

/**
 * Created by Derek on 3/14/2015.
 */
public class User {
    private String name;
    private Manufacturer manufacturer;
    private Model car;
    //Needs a field for lcoation information, but not sure how to do that yet

    public User(String name,  Manufacturer manufact, Model car) {
        this.name = name;
        this.manufacturer = manufact;
        this.car = car;
    }

    public void changeCar(Manufacturer newManufacturer, Model newModel) {
        manufacturer = newManufacturer;
        car = newModel;
    }

    public String getName() {
        return name;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public Model getModel(){
        return car;
    }
}
