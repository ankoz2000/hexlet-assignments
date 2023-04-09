package exercise;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Map;

// BEGIN
public abstract class Tag {
    private String tagName;
    private String tagValue;
    private Map<String, String> attrs;
    private List<Tag> child;
    private final Boolean hasPair;

    public Tag(Boolean hasPair) {
        this.hasPair = hasPair;
    }

    public Tag(Map<String, String> attrs, Boolean hasPair) {
        this.attrs = attrs;
        this.hasPair = hasPair;
    }

    public Tag(String tagName, Map<String, String> attrs, Boolean hasPair) {
        this.tagName = tagName;
        this.attrs = attrs;
        this.hasPair = hasPair;
    }

    public Tag(String tagName, String tagValue, Map<String, String> attrs, List<Tag> child, Boolean hasPair) {
        this.tagName = tagName;
        this.tagValue = tagValue;
        this.attrs = attrs;
        this.child = child;
        this.hasPair = hasPair;
    }

    public String getTagName() {
        return tagName;
    }

    public String getTagValue() {
        return tagValue;
    }

    public Map<String, String> getAttrs() {
        return attrs;
    }

    public List<Tag> getChild() {
        return child;
    }

    public Boolean getHasPair() {
        return hasPair;
    }
}
// END
