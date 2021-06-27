package kodlamaio.hrms.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import kodlamaio.hrms.entities.abstracts.User;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler",
        "graduatedSchools", "jobExperiences", "knownLanguages",
        "technologies" , "forewords", "socialMediaAddress" })
@Table(name = "job_seekers")
@PrimaryKeyJoinColumn(name = "user_id")
public class JobSeeker extends User {

    @NotBlank
    @Column(name = "first_name")
    private String firstName;

    @NotBlank
    @Column(name = "last_name")
    private String lastName;

    @Size(min = 11, max = 11, message = "Identity No must be 11 characters")
    @Column(name = "national_identity_no")
    private String nationalIdentityNo;

    @NotNull
    @Min(1900)
    @Max(2025)
    @Column(name = "year_of_birth")
    private int yearOfBirth;

    // GraduatedSchool <-> JobSeeker
    @OneToMany(mappedBy = "jobSeeker",  fetch = FetchType.LAZY)
    private Set<GraduatedSchool> graduatedSchools;

    // JobExperience <-> JobSeeker
    @OneToMany(mappedBy = "jobSeeker",  fetch = FetchType.LAZY)
    private Set<JobExperience> jobExperiences;

    // KnownLanguage <-> JobSeeker
    @OneToMany(mappedBy = "jobSeeker", fetch = FetchType.LAZY)
    private Set<KnownLanguage> knownLanguages;

    // SocialMedia <-> JobSeeker
    @OneToOne(mappedBy = "jobSeeker", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private SocialMediaAddress socialMediaAddress;

    // Technology <-> JobSeeker
    @ManyToMany
    @JoinTable(
            name = "used_technologies",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "technology_id"))
    private Set<Technology> technologies;

    // Foreword <-> JobSeeker
    @OneToMany(mappedBy = "jobSeeker", fetch = FetchType.LAZY)
    private Set<Foreword> forewords;

    // JobSeekerImage <-> JobSeeker
    @OneToOne(mappedBy = "jobSeeker", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private JobSeekerImage jobSeekerImage;

}
