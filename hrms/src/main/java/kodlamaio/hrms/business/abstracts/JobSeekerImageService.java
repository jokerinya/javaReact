package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.JobSeekerImage;
import org.springframework.web.multipart.MultipartFile;

public interface JobSeekerImageService {
    DataResult<JobSeekerImage> getByJobSeekerId(int userId);
    Result add(int userId, MultipartFile file);
}
