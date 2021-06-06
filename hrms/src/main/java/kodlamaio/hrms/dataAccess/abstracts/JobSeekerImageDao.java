package kodlamaio.hrms.dataAccess.abstracts;

import kodlamaio.hrms.entities.concretes.JobSeekerImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobSeekerImageDao extends JpaRepository<JobSeekerImage, Integer> {
    JobSeekerImage getByJobSeeker_UserId(int userId);
}
