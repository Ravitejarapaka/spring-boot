import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorJpaService {
    @Autowired
    private ProfessorRepository professorRepository;

    public List<Professor> getAllProfessors() {
        return professorRepository.findAll();
    }

    public Professor getProfessorById(int professorId) {
        Optional<Professor> optionalProfessor = professorRepository.findById(professorId);
        return optionalProfessor
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Professor not found"));
    }

    public Professor saveProfessor(Professor professor) {
        return professorRepository.save(professor);
    }

    public Professor updateProfessor(int professorId, Professor updatedProfessor) {
        Professor existingProfessor = getProfessorById(professorId);
        existingProfessor.setProfessorName(updatedProfessor.getProfessorName());
        existingProfessor.setDepartment(updatedProfessor.getDepartment());
        return professorRepository.save(existingProfessor);
    }

    public void deleteProfessor(int professorId) {
        if (professorRepository.existsById(professorId)) {
            professorRepository.deleteById(professorId);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Professor not found");
        }
    }

    public List<Course> getCoursesByProfessorId(int professorId) {
        Professor professor = getProfessorById(professorId);
        return professor.getCourses();
    }
}
