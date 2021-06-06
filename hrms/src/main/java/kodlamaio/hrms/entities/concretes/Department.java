package kodlamaio.hrms.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "graduatedSchools"})
@Table(name = "departments")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_id")
    private int departmentId;


    @Column(name = "department_name")
    private String departmentName;

    // School <-> Department
//    @ManyToMany(mappedBy = "departments")
//    private Set<School> schools;

    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
    private Set<GraduatedSchool> graduatedSchools;
}
