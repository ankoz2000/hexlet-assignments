package exercise.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/people")
@RequiredArgsConstructor
public class PeopleController {


    private final JdbcTemplate jdbc;


    @PostMapping(path = "")
    public void createPerson(@RequestBody Map<String, Object> person) {
        String query = "INSERT INTO person (first_name, last_name) VALUES (?, ?)";
        jdbc.update(query, person.get("first_name"), person.get("last_name"));
    }

    // BEGIN
    @GetMapping(path = "/{id}")
    public void createPerson(@PathVariable Integer id) {
        String query = "SELECT id, first_name, last_name FROM person WHERE id = ?";
        ResultSet rs = stmt.executeQuery(query);
        return jdbcTemplate.query(
                sql,
                (rs, rowNum) -> {
                    Map<String, Object> res = new HashMap<>();
                    res.put("id", rs.getLong("id"));
                    res.put("first_name", rs.getLong("first_name"));
                    res.put("last_name", rs.getLong("last_name"));
                    return res;
                }
        );
    }

    // END
}
