package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.abstracts.User;
import kodlamaio.hrms.entities.concretes.JobPosting;
import kodlamaio.hrms.entities.concretes.Personnel;

public interface PersonnelService {
    DataResult<Personnel> getById(int userId);
    Result activateUserEmail(int userId, User user);
    Result activateJobPosting(int userId, int jobPostingId);
}
