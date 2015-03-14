package co2go.myapplication;

/**
 * Created by Derek on 3/14/2015.
 */
public class Model {
    private int co2emission;
    private String modelNumber;


    public Model(int emission, String modelNumber) {
        co2emission = emission;
        this.modelNumber = modelNumber;
    }

    /*
        Changes the CO2 Emission of the model
     */
    public void updateEmission(int newEmission) {
        co2emission = newEmission;
    }
    /*
        Returns the C02 Emission
     */
    public int getEmission() {
        return co2emission;
    }
    /*
        Returns Model Number
     */
    public String getModelNumber() {
        return modelNumber;
    }
}


