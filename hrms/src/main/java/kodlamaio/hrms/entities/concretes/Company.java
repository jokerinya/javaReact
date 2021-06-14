package kodlamaio.hrms.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import kodlamaio.hrms.entities.abstracts.User;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "jobPostings"})
@Table(name = "companies")
@PrimaryKeyJoinColumn(name = "user_id")
public class Company extends User {

    @NotBlank
    @Column(name = "company_name")
    private String companyName;

    @NotBlank
    @Column(name = "company_website")
    private String companyWebsite;

    @NotBlank
    @Column(name = "company_phone")
    private String companyPhone;

    // One to Many -> JobPosting
    @OneToMany(mappedBy = "company")
    private List<JobPosting> jobPostings;
}
