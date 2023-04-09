package exercise;

import java.util.Map;
import java.util.stream.Collectors;

// BEGIN
public class SingleTag extends Tag {
    public SingleTag(String tagName, Map<String, String> attrs) {
        super(tagName, attrs, false);
    }

    public String toString() {
        return "<" + super.getTagName()  + (super.getAttrs() != null && !super.getAttrs().isEmpty() ? " " : "")
                + super.getAttrs().entrySet()
                .stream()
                .map(e -> e.getKey() + "=\"" + e.getValue() + "\"")
                .collect(Collectors.joining("\s"))
                + ">";
    }
}
// END
