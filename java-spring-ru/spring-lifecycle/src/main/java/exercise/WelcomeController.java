package exercise;

import exercise.daytimes.Daytime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// BEGIN
@RestController
public class WelcomeController {

    public Meal meal;
    public Daytime daytime;

    @Autowired
    public WelcomeController(Meal meal, Daytime daytime) {
        this.meal = meal;
        this.daytime = daytime;
    }

    @GetMapping("/daytime")
    public String great() {
        return "It is " + daytime.getName() + " now. Enjoy your breakfast";
    }
}
// END
