import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorJpaRepository extends JpaRepository<Professor, Integer> {
    // You can define custom query methods here if needed
}
