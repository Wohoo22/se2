package gr6.se2.model.course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface CourseRepository extends JpaRepository<Course, Integer> {
    public Course findByEnrollKey(String enrollKey);
}
