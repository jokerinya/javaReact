package kodlamaio.hrms.dataAccess.abstracts;

import kodlamaio.hrms.entities.concretes.GraduatedSchool;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GraduatedSchoolDao extends JpaRepository<GraduatedSchool, Integer> {
    List<GraduatedSchool> getByJobSeeker_UserId(int userId);
    List<GraduatedSchool> getByJobSeeker_UserIdOrderByGraduationDateDesc(int userId);
}
