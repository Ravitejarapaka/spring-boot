import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

@Service
public class CourseJpaService {
    @Autowired
    private CourseRepository courseRepository;

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseById(int courseId) {
        Optional<Course> optionalCourse = courseRepository.findById(courseId);
        return optionalCourse.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found"));
    }

    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    public Course updateCourse(int courseId, Course updatedCourse) {
        Course existingCourse = getCourseById(courseId);
        existingCourse.setCourseName(updatedCourse.getCourseName());
        existingCourse.setCredits(updatedCourse.getCredits());
        existingCourse.setProfessor(updatedCourse.getProfessor());
        existingCourse.setStudents(updatedCourse.getStudents());
        return courseRepository.save(existingCourse);
    }

    public void deleteCourse(int courseId) {
        if (courseRepository.existsById(courseId)) {
            courseRepository.deleteById(courseId);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found");
        }
    }

    public Professor getProfessorByCourseId(int courseId) {
        Course course = getCourseById(courseId);
        return course.getProfessor();
    }

    public List<Student> getStudentsByCourseId(int courseId) {
        Course course = getCourseById(courseId);
        return course.getStudents();
    }
}
