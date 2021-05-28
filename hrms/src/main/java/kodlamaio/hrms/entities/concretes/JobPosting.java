package kodlamaio.hrms.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "job_postings")
public class JobPosting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_posting_id")
    private int jobPostingId;

    // Company O->M
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", referencedColumnName = "user_id")
    private Company company;
    // Position O->M
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "position_id", referencedColumnName = "position_id")
    private Position position;
    // City O->M
    @ManyToOne(fetch = FetchType.LAZY)
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

    @Column(name = "last_application_date")
    private LocalDate lastApplicationDate;

    @Column(name = "is_active")
    private boolean isActive;
}
