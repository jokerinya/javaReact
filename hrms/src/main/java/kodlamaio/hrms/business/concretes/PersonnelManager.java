package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.JobPostingService;
import kodlamaio.hrms.business.abstracts.PersonnelService;
import kodlamaio.hrms.business.abstracts.UserService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.dataAccess.abstracts.PersonnelDao;
import kodlamaio.hrms.entities.abstracts.User;
import kodlamaio.hrms.entities.concretes.JobPosting;
import kodlamaio.hrms.entities.concretes.Personnel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonnelManager implements PersonnelService {

    private PersonnelDao personnelDao;
    private UserService userService;
    private JobPostingService jobPostingService;

    @Autowired
    public PersonnelManager(PersonnelDao personnelDao ,UserService userService, JobPostingService jobPostingService) {
        this.personnelDao = personnelDao;
        this.userService = userService;
        this.jobPostingService = jobPostingService;
    }


    @Override
    public DataResult<Personnel> getById(int userId) {
        return new SuccessDataResult<>(this.personnelDao.getOne(userId));
    }

    @Override
    public Result activateUserEmail(int userId, User user) {
        return this.userService.verifyEmailDirectly(user);
    }

    @Override
    public Result activateJobPosting(int userId, int jobPostingId) {
        return this.jobPostingService.activate(jobPostingId);
    }
}
