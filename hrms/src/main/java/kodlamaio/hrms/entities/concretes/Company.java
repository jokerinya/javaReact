package kodlamaio.hrms.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "companies")
@PrimaryKeyJoinColumn(name = "user_id")
public class Company extends  User{

    @Column(name = "name")
    private String name;

    @Column(name = "website")
    private String website;

    @Column(name = "phone")
    private String phone;
}
