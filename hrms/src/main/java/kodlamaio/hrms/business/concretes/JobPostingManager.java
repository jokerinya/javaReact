package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.CompanyService;
import kodlamaio.hrms.business.abstracts.JobPostingService;
import kodlamaio.hrms.business.abstracts.JobSeekerService;
import kodlamaio.hrms.business.abstracts.PositionService;
import kodlamaio.hrms.business.validators.concretes.JobPostingCredentialsCheckManager;
import kodlamaio.hrms.core.utilities.results.*;
import kodlamaio.hrms.dataAccess.abstracts.JobPostingDao;
import kodlamaio.hrms.entities.concretes.Company;
import kodlamaio.hrms.entities.concretes.JobPosting;
import kodlamaio.hrms.entities.concretes.JobSeeker;
import kodlamaio.hrms.entities.concretes.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
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
    public DataResult<JobPosting> getById(int jobPostingId) {
        return new SuccessDataResult<>(this.jobPostingDao.getOne(jobPostingId), "Job posting fetched");
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
    public DataResult<List<JobPosting>> getAllFiltered(Integer companyId, Integer cityId, Integer positionId) {
        if (companyId == null && cityId == null && positionId == null) {
            return getAllActive();
        }
        // position
        if (companyId == null && cityId == null) {
            return this.getAllActiveWithPositionId(positionId);
        }
        // city
        if (companyId == null && positionId == null) {
            return this.getAllActiveWithCityId(cityId);
        }
        // company
        if (cityId == null && positionId == null) {
            return this.getAllActiveWithCompanyId(companyId);
        }
        // company & city
        if (positionId == null) {
            return this.getAllActiveWithCompanyIdCityId(companyId, cityId);
        }
        // company & position
        if (cityId == null) {
            return this.getAllActiveWithCompanyIdPositionId(companyId, positionId);
        }
        // city & position
        if (companyId == null) {
            return this.getAllActiveWithCityIdPositionId(cityId, positionId);
        }
        // city & position & company
        return this.getAllActiveWithCompanyIdCityIdPositionId(companyId, cityId, positionId);
    }

    @Override
    public DataResult<List<JobPosting>> getAllActiveWithCompanyId(int companyId) {
        return new SuccessDataResult<>
                (this.jobPostingDao.findAllByActiveTrueAndCompany_UserId(companyId), "Job postings from the company");
    }

    @Override
    public DataResult<List<JobPosting>> getAllActiveWithCityId(int cityId) {
        return new SuccessDataResult<>
                (this.jobPostingDao.findAllByActiveTrueAndCity_CityId(cityId), "Job postings at the city");
    }

    @Override
    public DataResult<List<JobPosting>> getAllActiveWithPositionId(int postionId) {
        return new SuccessDataResult<>
                (this.jobPostingDao.findAllByActiveTrueAndPosition_PositionId(postionId), "Job postings in the position");
    }

    @Override
    public DataResult<List<JobPosting>> getAllActiveWithCompanyIdCityId(int companyId, int cityId) {
        return new SuccessDataResult<>
                (this.jobPostingDao.findAllByActiveTrueAndCompany_UserIdAndCity_CityId(companyId, cityId));
    }

    @Override
    public DataResult<List<JobPosting>> getAllActiveWithCompanyIdPositionId(int companyId, int positionId) {
        return new SuccessDataResult<>
                (this.jobPostingDao.findAllByActiveTrueAndCompany_UserIdAndPosition_PositionId(companyId, positionId));
    }

    @Override
    public DataResult<List<JobPosting>> getAllActiveWithCityIdPositionId(int cityId, int positionId) {
        return new SuccessDataResult<>
                (this.jobPostingDao.findAllByActiveTrueAndCity_CityIdAndPosition_PositionId(cityId, positionId));
    }

    @Override
    public DataResult<List<JobPosting>> getAllActiveWithCompanyIdCityIdPositionId(int userId, int cityId, int positionId) {
        return new SuccessDataResult<>(
                this.jobPostingDao.findAllByActiveTrueAndCompany_UserIdAndCity_CityIdAndPosition_PositionId(
                        userId, cityId, positionId), "List fetched.");
    }

    @Override
    public Result add(int companyId, JobPosting jobPosting) {
        var validation = JobPostingCredentialsCheckManager.checkValid(jobPosting);
        if (validation != null) {
            return validation;
        }
        // Set User and Position correctly
        Company company = this.companyService.getById(companyId).getData();
        jobPosting.setCompany(company);
        Position position = this.positionService.getByPositionNameIfNotCreate(jobPosting.getPosition().getPositionName());
        jobPosting.setPosition(position);
        // Set as an inactive jobPosting
        jobPosting.setActive(false);
        // save to db
        this.jobPostingDao.save(jobPosting);
        return new SuccessResult("Job posting has been saved.");
    }

    @Override
    public Result update(int companyId, int jobPostingId, JobPosting jobPosting) {
        var validation = JobPostingCredentialsCheckManager.checkValid(jobPosting);
        if (validation != null) {
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
        oldJobPosting.setRemote(jobPosting.isRemote());
        oldJobPosting.setPartTime(jobPosting.isPartTime());
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

    @Override
    public Result activate(int jobPostingId) {
        // get from db
        JobPosting jobPosting = this.jobPostingDao.getOne(jobPostingId);
        // set field
        jobPosting.setActive(true);
        // save
        this.jobPostingDao.save(jobPosting);
        return new SuccessResult("Job Posting activated!");
    }

}
