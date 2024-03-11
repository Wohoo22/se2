package gr6.se2.controller;

import gr6.se2.controller.shared.Authorizer;
import gr6.se2.model.course.Course;
import gr6.se2.model.course.CourseRepository;
import gr6.se2.model.enrollment.Enrollment;
import gr6.se2.model.enrollment.EnrollmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@Controller
@AllArgsConstructor
public class EnrollCourseController {
    private final Authorizer authorizer;
    private final CourseRepository courseRepository;
    private final EnrollmentRepository enrollmentRepository;

    @PostMapping("/student/enroll")
    public ResponseEntity<?> enrollCourse(@RequestBody Body requestBody,
                                          @RequestHeader("Authorization") String bearerToken) {
        try {
            final int studentId = authorizer.authorizeStudent(bearerToken);
            final Course course = courseRepository.findByEnrollKey(requestBody.enrollKey);
            if (course == null)
                return ResponseEntity.badRequest().body("Cannot find course with that enroll key");
            final Enrollment enrollment = new Enrollment(studentId, course.getId());
            enrollmentRepository.save(enrollment);
            return ResponseEntity.ok().body("Enroll success");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    public static class Body {
        private String enrollKey;
    }
}


