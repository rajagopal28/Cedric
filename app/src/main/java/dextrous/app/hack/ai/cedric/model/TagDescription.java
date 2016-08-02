package dextrous.app.hack.ai.cedric.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TagDescription implements Serializable {
    private List<String> tags;
    private List<Caption> captions;

    public TagDescription(List<String> tags) {
        if(tags == null) {
            tags = new ArrayList<>();
        }
        this.tags = tags;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "TagDescription{" +
                "tags=" + tags +
                '}';
    }
}
