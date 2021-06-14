package kodlamaio.hrms.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "jobPostings", "jobExperiences"})
@Table(name = "positions")
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "position_id")
    private int positionId;

    @NotBlank
    @Column(name = "position_name")
    private String positionName;

    // One to Many -> JobPosting
    @OneToMany(mappedBy = "position")
    private List<JobPosting> jobPostings;

    // JobExperience <-> JobSeeker
    @OneToMany(mappedBy = "position",  fetch = FetchType.LAZY)
    private Set<JobExperience> jobExperiences;
}
