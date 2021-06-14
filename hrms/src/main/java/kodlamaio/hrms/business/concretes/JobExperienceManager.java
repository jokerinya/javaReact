package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.JobExperienceService;
import kodlamaio.hrms.business.abstracts.JobSeekerService;
import kodlamaio.hrms.business.abstracts.PositionService;
import kodlamaio.hrms.business.validators.concretes.JobExperienceCredentialsCheckManager;
import kodlamaio.hrms.core.utilities.results.*;
import kodlamaio.hrms.dataAccess.abstracts.JobExperienceDao;
import kodlamaio.hrms.entities.concretes.JobExperience;
import kodlamaio.hrms.entities.concretes.JobSeeker;
import kodlamaio.hrms.entities.concretes.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
public class JobExperienceManager implements JobExperienceService {
    private JobExperienceDao jobExperienceDao;
    private JobSeekerService jobSeekerService;
    private PositionService positionService;

    @Autowired
    public JobExperienceManager(JobExperienceDao jobExperienceDao, JobSeekerService jobSeekerService,
                                PositionService positionService) {
        this.jobExperienceDao = jobExperienceDao;
        this.jobSeekerService = jobSeekerService;
        this.positionService = positionService;

    }

    @Override
    public DataResult<List<JobExperience>> getAll() {
        return new SuccessDataResult<>(this.jobExperienceDao.findAll(), "Job Experiences listed.");
    }

    @Override
    public DataResult<List<JobExperience>> getByJobSeekerId(int userId) {
        return new SuccessDataResult<>(this.jobExperienceDao.getByJobSeeker_UserId(userId),
                "Job Experiences listed.");
    }

    @Override
    public DataResult<List<JobExperience>> getByJobSeekerIdOrderByEndDate(int userId) {
        return new SuccessDataResult<>(this.jobExperienceDao.getByJobSeeker_UserIdOrderByEndDateDesc(userId),
                "Job Experiences listed.");
    }

    @Override
    public Result add(int jobSeekerId, JobExperience jobExperience) {
        // Set Joobseeker
        JobSeeker jobSeeker = this.jobSeekerService.getById(jobSeekerId).getData();
        if (jobSeeker == null){
            return new ErrorResult("User can't be found!");
        }
        jobExperience.setJobSeeker(jobSeeker);
        // Check validity of the fields
        var validation = JobExperienceCredentialsCheckManager.checkValid(jobExperience);
        if (validation != null){
            return validation;
        }
        // Position check and setting
        String positionName = jobExperience.getPosition().getPositionName().toLowerCase(Locale.ROOT);
        Position position = this.positionService.getByPositionNameIfNotCreate(positionName);
        jobExperience.setPosition(position);
        // Make company name lower letter
        jobExperience.setCompanyName(jobExperience.getCompanyName().toLowerCase(Locale.ROOT));
        // Save to db
        this.jobExperienceDao.save(jobExperience);
        return new SuccessResult("Job Experience has been added.");
    }

    @Override
    public Result delete(int jobSeekerId, JobExperience jobExperience) {
        this.jobExperienceDao.delete(jobExperience);
        return new SuccessResult("Job Experience has been deleted.");
    }

    @Override
    public Result update(int jobSeekerId, JobExperience jobExperience) {
        // get from db the older one
        JobExperience oldJobExperience = this.jobExperienceDao.getOne(jobExperience.getJobExperienceId());
        // set position, position connected to this obj with a foreign key
        Position position = this.positionService.getByPositionNameIfNotCreate(jobExperience.getPosition().getPositionName());
        oldJobExperience.setPosition(position);
        oldJobExperience.setCompanyName(jobExperience.getCompanyName());
        oldJobExperience.setStartDate(jobExperience.getStartDate());
        oldJobExperience.setEndDate(jobExperience.getEndDate());
        // save to db
        this.jobExperienceDao.save(oldJobExperience);
        return new SuccessResult("Job Experience has been updated!");
    }
}
