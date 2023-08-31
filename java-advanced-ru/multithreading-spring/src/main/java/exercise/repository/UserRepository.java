package exercise.repository;

import exercise.model.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;


// BEGIN
@Repository
public class UserRepository implements ReactiveCrudRepository<User, Long> {

}
// END
