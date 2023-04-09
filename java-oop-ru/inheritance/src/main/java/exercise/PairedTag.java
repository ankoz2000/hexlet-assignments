package exercise;

import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN
public class PairedTag extends Tag {

    public PairedTag(String tagName, Map<String, String> attrs, String tagValue, List<Tag> child) {
        super(tagName, tagValue, attrs, child, true);
    }

    public String toString() {
        return "<" + super.getTagName() + (super.getAttrs() != null && !super.getAttrs().isEmpty() ? " " : "")
                + super.getAttrs()
                .entrySet()
                .stream()
                .map(e -> e.getKey() + "=\"" + e.getValue() + "\"")
                .collect(Collectors.joining("\s"))
                + ">"
                + super.getTagValue()
                + super.getChild()
                .stream()
                .map(Tag::toString)
                .collect(Collectors.joining())
                + "</" + super.getTagName() + ">";
    }
}
// END
