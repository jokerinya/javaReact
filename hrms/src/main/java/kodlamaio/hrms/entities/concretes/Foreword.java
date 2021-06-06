package kodlamaio.hrms.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "forewords")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "jobSeeker"})
public class Foreword {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "foreword_id")
    private int forewordId;

    @Size(min = 10, max = 200, message
            = "Foreword must be between 10 and 200 characters")
    @NotBlank
    @Column(name = "foreword_body")
    private String forewordBody;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private JobSeeker jobSeeker;
}
