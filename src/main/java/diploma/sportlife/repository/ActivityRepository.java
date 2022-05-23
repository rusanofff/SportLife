package diploma.sportlife.repository;

import diploma.sportlife.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<Activity, Integer> {
    boolean existsByName(String name);
}
