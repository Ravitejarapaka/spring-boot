import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/professors")
public class ProfessorController {
    @Autowired
    private ProfessorJpaService professorService;

    @GetMapping
    public List<Professor> getAllProfessors() {
        return professorService.getAllProfessors();
    }

    @PostMapping
    public ResponseEntity<Professor> createProfessor(@RequestBody Professor professor) {
        Professor createdProfessor = professorService.saveProfessor(professor);
        return new ResponseEntity<>(createdProfessor, HttpStatus.CREATED);
    }

    @GetMapping("/{professorId}")
    public Professor getProfessorById(@PathVariable int professorId) {
        return professorService.getProfessorById(professorId);
    }

    @PutMapping("/{professorId}")
    public Professor updateProfessor(@PathVariable int professorId, @RequestBody Professor updatedProfessor) {
        return professorService.updateProfessor(professorId, updatedProfessor);
    }

    @DeleteMapping("/{professorId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProfessor(@PathVariable int professorId) {
        professorService.deleteProfessor(professorId);
    }

    @GetMapping("/{professorId}/courses")
    public List<Course> getCoursesByProfessorId(@PathVariable int professorId) {
        return professorService.getCoursesByProfessorId(professorId);
    }
}
