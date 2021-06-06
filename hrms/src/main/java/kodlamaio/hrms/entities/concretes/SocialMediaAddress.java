package kodlamaio.hrms.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "jobSeeker"})
@Table(name = "social_media_addresses")
public class SocialMediaAddress {
    @Id
    @Column(name = "user_id")
    private int socialMediaId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private JobSeeker jobSeeker;

    @Column(name = "github_address")
    private String githubAddress;

    @Column(name = "linkedin_address")
    private String linkedinAddress;

}
