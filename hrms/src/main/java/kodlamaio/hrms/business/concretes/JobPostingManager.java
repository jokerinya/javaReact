package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.CompanyService;
import kodlamaio.hrms.business.abstracts.JobPostingService;
import kodlamaio.hrms.business.abstracts.JobSeekerService;
import kodlamaio.hrms.business.abstracts.PositionService;
import kodlamaio.hrms.business.validators.concretes.JobPostingCredentialsCheckManager;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.JobPostingDao;
import kodlamaio.hrms.entities.concretes.Company;
import kodlamaio.hrms.entities.concretes.JobPosting;
import kodlamaio.hrms.entities.concretes.JobSeeker;
import kodlamaio.hrms.entities.concretes.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobPostingManager implements JobPostingService {

    private JobPostingDao jobPostingDao;
    private CompanyService companyService;
    private PositionService positionService;

    @Autowired
    public JobPostingManager(JobPostingDao jobPostingDao, CompanyService companyService,
                             PositionService positionService) {
        this.jobPostingDao = jobPostingDao;
        this.companyService = companyService;
        this.positionService = positionService;
    }

    @Override
    public DataResult<List<JobPosting>> getAllActive() {
        return new SuccessDataResult<>
                (this.jobPostingDao.findAllByActiveTrue(), "All active job postings fetched.");
    }

    @Override
    public DataResult<List<JobPosting>> getAllActiveSortedByDate() {
        return new SuccessDataResult<>
            (this.jobPostingDao.findAllByActiveTrueOrderByLastApplicationDateAsc(), "Job postings date order.");
    }

    @Override
    public DataResult<List<JobPosting>> getAllActiveWithCompanyId(int companyId) {
        return new SuccessDataResult<>
            (this.jobPostingDao.findAllByActiveTrueAndCompany_UserId(companyId), "Job postings from the company");
    }

    @Override
    public Result add(int companyId, JobPosting jobPosting) {
        var validation = JobPostingCredentialsCheckManager.checkValid(jobPosting);
        if (validation != null){
            return validation;
        }
        // Set User and Position correctly
        Company company = this.companyService.getById(companyId).getData();
        jobPosting.setCompany(company);
        Position position = this.positionService.getByPositionNameIfNotCreate(jobPosting.getPosition().getPositionName());
        jobPosting.setPosition(position);

        this.jobPostingDao.save(jobPosting);
        return new SuccessResult("Job posting has been saved.");
    }

    @Override
    public Result update(int companyId, int jobPostingId, JobPosting jobPosting) {
        var validation = JobPostingCredentialsCheckManager.checkValid(jobPosting);
        if (validation != null){
            return validation;
        }
        JobPosting oldJobPosting = this.jobPostingDao.getOne(jobPostingId);
        // Set fields
        Position position = this.positionService.getByPositionNameIfNotCreate(jobPosting.getPosition().getPositionName());
        oldJobPosting.setPosition(position);
        oldJobPosting.setCity(jobPosting.getCity());
        oldJobPosting.setDescription(jobPosting.getDescription());
        oldJobPosting.setLastApplicationDate(jobPosting.getLastApplicationDate());
        oldJobPosting.setMaxWage(jobPosting.getMaxWage());
        oldJobPosting.setMinWage(jobPosting.getMinWage());
        oldJobPosting.setOpenPositions(jobPosting.getOpenPositions());
        // check status and add to db
        this.jobPostingDao.save(oldJobPosting);
        return new SuccessResult("Job posting has been updated.");
    }

    @Override
    public Result delete(int jobPostingId) {
        this.jobPostingDao.delete(this.jobPostingDao.getOne(jobPostingId));
        return new SuccessResult("Job posting has been deleted!");
    }

    @Override
    public Result setPassive(int userId, int jobPostingId) {
        JobPosting jobPosting = this.jobPostingDao.getOne(jobPostingId);
        jobPosting.setActive(false);
        this.jobPostingDao.save(jobPosting);
        return new SuccessResult("Job Posting has been deactivated!");
    }

}
