package kodlamaio.hrms.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "jobPostings"})
@Table(name = "companies")
@PrimaryKeyJoinColumn(name = "user_id")
public class Company extends  User{

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "company_website")
    private String companyWebsite;

    @Column(name = "company_phone")
    private String companyPhone;

    // One to Many -> JobPosting
    @OneToMany(mappedBy = "company")
    private List<JobPosting> jobPostings;
}
