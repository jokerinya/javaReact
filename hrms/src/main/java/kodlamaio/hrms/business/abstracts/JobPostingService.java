package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.JobPosting;

import java.util.List;

public interface JobPostingService {
    DataResult<List<JobPosting>> getAllActive();
    Result add(int companyId, JobPosting jobPosting);
}
