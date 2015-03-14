package co2go.myapplication;
import java.util.*;
/**
 * Created by Derek on 3/14/2015.
 */
public class Manufacturer {
    private String name;
    private ArrayList<Model> models = new ArrayList<Model>();

    public Manufacturer(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void addModel(Model modelToAdd) {
    }
}
}
