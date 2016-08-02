package dextrous.app.hack.ai.cedric.model;


import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class ImageDescription implements Serializable {
    private String requestId;
    private List<TagDescription> description;
    private List<Caption> captions;
    private Map<String, Object> metadata;
    private String code;
    private String message;

    public ImageDescription(String requestId, List<TagDescription> description, List<Caption> captions, Map<String, Object> metadata, String code, String message) {
        this.requestId = requestId;
        this.description = description;
        this.captions = captions;
        this.metadata = metadata;
        this.code = code;
        this.message = message;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public List<TagDescription> getDescription() {
        return description;
    }

    public void setDescription(List<TagDescription> description) {
        this.description = description;
    }

    public List<Caption> getCaptions() {
        return captions;
    }

    public void setCaptions(List<Caption> captions) {
        this.captions = captions;
    }

    public Map<String, Object> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, Object> metadata) {
        this.metadata = metadata;
    }

    @Override
    public String toString() {
        return "ImageDescription{" +
                "requestId='" + requestId + '\'' +
                ", description=" + description +
                ", captions=" + captions +
                ", metadata=" + metadata +
                ", code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
