package dextrous.app.hack.ai.cedric.model;


import java.io.Serializable;

public class Category implements Serializable{
    private String name;
    private double coincidence;
    private double score;

    public Category(String name, double coincidence, double score) {
        this.name = name;
        this.coincidence = coincidence;
        this.score = score;
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


    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", coincidence=" + coincidence +
                ", score=" + score +
                '}';
    }
}
