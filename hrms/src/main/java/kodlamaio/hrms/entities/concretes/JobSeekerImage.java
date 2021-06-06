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
@Table(name = "job_seeker_images")
public class JobSeekerImage {
    @Id
    @Column(name = "user_id")
    private int jobSeekerImageId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private JobSeeker jobSeeker;

    @Column(name = "image_url")
    private String imageUrl;
}
