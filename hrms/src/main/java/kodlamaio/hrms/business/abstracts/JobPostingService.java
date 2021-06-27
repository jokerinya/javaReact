package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.JobPosting;

import javax.xml.crypto.Data;
import java.util.List;

public interface JobPostingService {
    DataResult<JobPosting> getById(int jobPostingId);
    DataResult<List<JobPosting>> getAllActive();
    DataResult<List<JobPosting>> getAllActiveSortedByDate();

    DataResult<List<JobPosting>> getAllFiltered(Integer companyId, Integer cityId, Integer positionId);
    DataResult<List<JobPosting>> getAllActiveWithCompanyId(int companyId);
    DataResult<List<JobPosting>> getAllActiveWithCityId(int cityId);
    DataResult<List<JobPosting>> getAllActiveWithPositionId(int postionId);
    DataResult<List<JobPosting>> getAllActiveWithCompanyIdCityId(int companyId, int cityId);
    DataResult<List<JobPosting>> getAllActiveWithCompanyIdPositionId(int companyId, int positionId);
    DataResult<List<JobPosting>> getAllActiveWithCityIdPositionId(int cityId, int positionId);
    DataResult<List<JobPosting>> getAllActiveWithCompanyIdCityIdPositionId(int userId, int cityId, int positionId);

    Result add(int companyId, JobPosting jobPosting);
    Result update(int companyId, int jobPostingId, JobPosting jobPosting);
    Result delete(int jobPostingId);
    Result setPassive(int userId, int jobPostingId);
    Result activate(int jobPostingId);
}
