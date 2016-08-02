package dextrous.app.hack.ai.cedric.model;


import java.io.Serializable;

public class Caption implements Serializable{
    private String text;
    private double confidence;
    public Caption(String text, double confidence) {
        this.text = text;
        this.confidence = confidence;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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
                "text='" + text + '\'' +
                ", confidence=" + confidence +
                '}';
    }
}
