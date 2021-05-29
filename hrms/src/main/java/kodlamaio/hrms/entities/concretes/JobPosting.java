package kodlamaio.hrms.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "job_postings")
public class JobPosting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_posting_id")
    private int jobPostingId;

    // Company O->M
//    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ManyToOne()
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private Company company;
    // Position O->M
    @ManyToOne()
    @JoinColumn(name = "position_id", referencedColumnName = "position_id")
    private Position position;
    // City O->M
    @ManyToOne()
    @JoinColumn(name = "city_id", referencedColumnName = "city_id")
    private City city;

    @Column(name = "description")
    private String description;

    @Column(name = "min_wage")
    private int minWage;

    @Column(name = "max_wage")
    private int maxWage;

    @Column(name = "open_positions")
    private int openPositions;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "last_application_date")
    private LocalDate lastApplicationDate;

    @Column(name = "is_active")
    private boolean isActive;

    @Override
    public String toString() {
        return "JobPosting{" +
                "jobPostingId=" + jobPostingId +
                ", company=" + company +
                ", position=" + position +
                ", city=" + city +
                ", description='" + description + '\'' +
                ", minWage=" + minWage +
                ", maxWage=" + maxWage +
                ", openPositions=" + openPositions +
                ", lastApplicationDate=" + lastApplicationDate +
                ", isActive=" + isActive +
                '}';
    }
}
