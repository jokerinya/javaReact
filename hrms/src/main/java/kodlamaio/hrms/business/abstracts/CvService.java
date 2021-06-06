package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.entities.dtos.JobSeekerCvDto;

public interface CvService {
    DataResult<JobSeekerCvDto> getJobSeekerCV(int userId);
}
