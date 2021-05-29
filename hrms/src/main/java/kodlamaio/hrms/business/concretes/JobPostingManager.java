package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.JobPostingService;
import kodlamaio.hrms.business.validators.concretes.JobPostingCredentialsCheckManager;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.JobPostingDao;
import kodlamaio.hrms.entities.concretes.JobPosting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobPostingManager implements JobPostingService {

    private JobPostingDao jobPostingDao;

    @Autowired
    public JobPostingManager(JobPostingDao jobPostingDao) {
        this.jobPostingDao = jobPostingDao;
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
        return this.save(jobPosting);
    }

    @Override
    public Result setPassive(int jobPostingId) {
        JobPosting jobPosting = this.jobPostingDao.getOne(jobPostingId);
        jobPosting.setActive(false);
        return save(jobPosting);
    }

    public Result save(JobPosting jobPosting){
        var validation = JobPostingCredentialsCheckManager.checkValid(jobPosting);
        if (validation != null){
            return validation;
        }
        this.jobPostingDao.save(jobPosting);
        return new SuccessResult("Job posting has been saved.");
    }
}
