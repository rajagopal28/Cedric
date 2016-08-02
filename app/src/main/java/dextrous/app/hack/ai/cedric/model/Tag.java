package dextrous.app.hack.ai.cedric.model;


import java.io.Serializable;

public class Tag implements Serializable{
    private String name;
    private double confidence;
    public Tag(String name, double confidence) {
        this.name = name;
        this.confidence = confidence;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getConfidence() {
        return confidence;
    }

    public void setConfidence(double confidence) {
        this.confidence = confidence;
    }


    @Override
    public String toString() {
        return "Caption{" +
                "name='" + name + '\'' +
                ", confidence=" + confidence +
                '}';
    }
}
