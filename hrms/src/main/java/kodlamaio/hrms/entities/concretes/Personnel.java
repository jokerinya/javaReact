package kodlamaio.hrms.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import kodlamaio.hrms.entities.abstracts.User;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "personnels")
@PrimaryKeyJoinColumn(name = "user_id")
public class Personnel extends User {

    @NotBlank
    @Column(name = "personnel_first_name")
    private String personnelFirstName;

    @NotBlank
    @Column(name = "personnel_last_name")
    private String personnelLastName;

    @Size(min = 11, max = 11, message = "Identity No must be 11 characters")
    @Column(name = "personnel_national_identity_no")
    private String personnelNationalIdentityNo;
}
