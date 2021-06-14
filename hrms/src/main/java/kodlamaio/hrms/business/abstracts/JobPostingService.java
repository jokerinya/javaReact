package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.JobPosting;

import java.util.List;

public interface JobPostingService {
    DataResult<List<JobPosting>> getAllActive();
    DataResult<List<JobPosting>> getAllActiveSortedByDate();
    DataResult<List<JobPosting>> getAllActiveWithCompanyId(int companyId);
    Result add(int companyId, JobPosting jobPosting);
    Result update(int companyId, int jobPostingId, JobPosting jobPosting);
    Result delete(int jobPostingId);
    Result setPassive(int userId, int jobPostingId);
}
