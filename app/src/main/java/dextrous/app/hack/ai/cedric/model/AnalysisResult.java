package dextrous.app.hack.ai.cedric.model;


import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class AnalysisResult implements Serializable {
    private List<Category> categories;
    private Map<String, Object> adult;
    private List<Tag> tags;
    private TagDescription description;
    private String requestId;
    private Map<String, Object> metadata;
    private List<Map<String, Object>> faces;
    private Map<String, Object> color;
    private Map<String, Object> imageType;

    public AnalysisResult(List<Category> categories, Map<String, Object> adult,
                          List<Tag> tags, TagDescription description,
                          String requestId, Map<String, Object> metadata,
                          List<Map<String, Object>> faces, Map<String, Object> color,
                          Map<String, Object> imageType) {
        this.categories = categories;
        this.adult = adult;
        this.tags = tags;
        this.description = description;
        this.requestId = requestId;
        this.metadata = metadata;
        this.faces = faces;
        this.color = color;
        this.imageType = imageType;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public Map<String, Object> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, Object> metadata) {
        this.metadata = metadata;
    }

    public Map<String, Object> getAdult() {
        return adult;
    }

    public void setAdult(Map<String, Object> adult) {
        this.adult = adult;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public TagDescription getDescription() {
        return description;
    }

    public void setDescription(TagDescription description) {
        this.description = description;
    }

    public Map<String, Object> getFaces() {
        return (Map<String, Object>) faces;
    }

    public void setFaces(List<Map<String, Object>> faces) {
        this.faces = faces;
    }

    public Map<String, Object> getColor() {
        return color;
    }

    public void setColor(Map<String, Object> color) {
        this.color = color;
    }

    public Map<String, Object> getImageType() {
        return imageType;
    }

    public void setImageType(Map<String, Object> imageType) {
        this.imageType = imageType;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    @Override
    public String toString() {
        return "AnalysisResult{" +
                "categories=" + categories +
                ", metadata=" + metadata +
                ", adult=" + adult +
                ", tags=" + tags +
                ", description=" + description +
                ", faces=" + faces +
                ", color=" + color +
                ", imageType=" + imageType +
                ", requestId=" + requestId +
                '}';
    }

}
