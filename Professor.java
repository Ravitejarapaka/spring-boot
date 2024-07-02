import javax.persistence.*;

@Entity
@Table(name = "professor")
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "professor_id")
    private int professorId;

    @Column(name = "professor_name")
    private String professorName;

    @Column(name = "department")
    private String department;

    // Getters and setters
    // Omitted for brevity
}
