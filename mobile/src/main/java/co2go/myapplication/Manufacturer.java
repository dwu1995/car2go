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

    /*
        returns the name
     */
    public String getName() {
        return this.name;
    }

    /*
        Adds specified model to models
     */
    public void addModel(Model modelToAdd) {
        boolean isInside = false;
        for (int i = 0; i < models.size(); i++) {
            if (models.get(i).getModelNumber() == modelToAdd.getModelNumber()) {
                isInside = true;
                break;
            }
        }
        if (!isInside) {
            models.add(modelToAdd);
        }
    }
    /*
        Remove specified model from models
     */
    public void removeModel(Model modeltoRemove) {
        boolean isInside = false;
        for (int i = 0; i < models.size(); i++) {
            if (models.get(i).getModelNumber() == modeltoRemove.getModelNumber()) {
                models.remove(i);
                break;
            }
        }
    }
    /*
        Finds the index of the element in models that has the model number
        returns -1 if the model wasn't found.
     */
    public int findModel(Model modelToFind) {
        for (int i = 0; i < models.size(); i++) {
            if(models.get(i).getModelNumber() == modelToFind.getModelNumber()){
                return i;
            }
        }
        return -1;
    }
}

