package dextrous.app.hack.ai.cedric.model;

import java.io.Serializable;
import java.util.List;

public class TagDescription implements Serializable {
    private List<String> tags;


    private List<Caption> captions;

    public TagDescription(List<String> tags, List<Caption> captions) {
        this.tags = tags;
        this.captions = captions;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<Caption> getCaptions() {
        return captions;
    }

    public void setCaptions(List<Caption> captions) {
        this.captions = captions;
    }
    
    @Override
    public String toString() {
        return "TagDescription{" +
                "tags=" + tags +
                "captions=" + captions +
                '}';
    }
}
