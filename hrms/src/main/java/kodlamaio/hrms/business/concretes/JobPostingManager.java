package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.JobPostingService;
import kodlamaio.hrms.business.validators.concretes.JobPostingCredentialsCheckManager;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.CompanyDao;
import kodlamaio.hrms.dataAccess.abstracts.JobPostingDao;
import kodlamaio.hrms.entities.concretes.JobPosting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobPostingManager implements JobPostingService {

    private JobPostingDao jobPostingDao;
    private CompanyDao companyDao;

    @Autowired
    public JobPostingManager(JobPostingDao jobPostingDao, CompanyDao companyDao) {
        this.jobPostingDao = jobPostingDao;
        this.companyDao =  companyDao;
    }

    @Override
    public DataResult<List<JobPosting>> getAllActive() {
        return new SuccessDataResult<>
                (this.jobPostingDao.findByIsActiveTrue(), "All job postings fetched.");
    }

    @Override
    public Result add(int companyId, JobPosting jobPosting) {
        return this.save(jobPosting);
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
