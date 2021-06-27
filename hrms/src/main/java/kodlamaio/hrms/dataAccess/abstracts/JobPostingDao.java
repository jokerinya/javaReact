package kodlamaio.hrms.dataAccess.abstracts;

import kodlamaio.hrms.entities.concretes.JobPosting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobPostingDao extends JpaRepository<JobPosting, Integer> {
    List<JobPosting> findAllByActiveTrue();
    List<JobPosting> findAllByActiveTrueOrderByLastApplicationDateAsc();
    List<JobPosting> findAllByActiveTrueAndCompany_UserId(int id);
    List<JobPosting> findAllByActiveTrueAndCity_CityId(int cityId);
    List<JobPosting> findAllByActiveTrueAndPosition_PositionId(int positionId);
    List<JobPosting> findAllByActiveTrueAndCompany_UserIdAndCity_CityId(int userId, int cityId);
    List<JobPosting> findAllByActiveTrueAndCompany_UserIdAndPosition_PositionId(int userId, int positionId);
    List<JobPosting> findAllByActiveTrueAndCity_CityIdAndPosition_PositionId(int cityId, int positionId);

    List<JobPosting> findAllByActiveTrueAndCompany_UserIdAndCity_CityIdAndPosition_PositionId(int userId, int cityId, int positionId);

}
