package exercise.dto;

import exercise.model.Category;
import lombok.Getter;
import lombok.Setter;

// BEGIN
@Getter
@Setter
public class ArticleDto {
    public long id;
    public String name;
    public String body;
    public Category category;
}
// END
