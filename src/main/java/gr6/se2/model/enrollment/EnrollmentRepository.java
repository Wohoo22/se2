package gr6.se2.model.enrollment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface EnrollmentRepository extends JpaRepository<Enrollment, String> {
}
