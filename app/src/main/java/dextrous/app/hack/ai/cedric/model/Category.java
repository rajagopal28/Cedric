package dextrous.app.hack.ai.cedric.model;


import java.io.Serializable;

public class Category implements Serializable{
    private String name;
    private double coincidence;

    public Category(String name, double coincidence) {
        this.name = name;
        this.coincidence = coincidence;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCoincidence() {
        return coincidence;
    }

    public void setCoincidence(double coincidence) {
        this.coincidence = coincidence;
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", coincidence=" + coincidence +
                '}';
    }
}
