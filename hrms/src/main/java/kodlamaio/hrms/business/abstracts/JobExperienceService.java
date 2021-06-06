package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.JobExperience;

import java.util.List;

public interface JobExperienceService {
    DataResult<List<JobExperience>> getAll();
    DataResult<List<JobExperience>> getByJobSeekerId(int userId);
    DataResult<List<JobExperience>> getByJobSeekerIdOrderByEndDate(int userId);
    Result add(int jobSeekerId, JobExperience jobExperience);

}
