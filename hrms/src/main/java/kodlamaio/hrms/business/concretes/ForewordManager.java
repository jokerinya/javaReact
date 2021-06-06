package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.ForewordService;
import kodlamaio.hrms.business.abstracts.JobSeekerService;
import kodlamaio.hrms.core.utilities.results.*;
import kodlamaio.hrms.dataAccess.abstracts.ForewordDao;
import kodlamaio.hrms.entities.concretes.Foreword;
import kodlamaio.hrms.entities.concretes.JobSeeker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ForewordManager implements ForewordService {
    private ForewordDao forewordDao;
    private JobSeekerService jobSeekerService;

    @Autowired
    public ForewordManager(ForewordDao forewordDao, JobSeekerService jobSeekerService) {
        this.forewordDao = forewordDao;
        this.jobSeekerService = jobSeekerService;
    }

    @Override
    public DataResult<List<Foreword>> getByJobSeekerId(int jobSeekerId) {
        JobSeeker jobSeeker = this.jobSeekerService.getById(jobSeekerId).getData();
        if (jobSeeker == null){
            return new ErrorDataResult<>(null, "User can't be found");
        }
        return new SuccessDataResult<>
                (this.forewordDao.getByJobSeeker_UserId(jobSeekerId), "JobSeeker forewords");
    }

    @Override
    public Result add(int jobSeekerId, Foreword foreword) {
        JobSeeker jobSeeker = this.jobSeekerService.getById(jobSeekerId).getData();
        if (jobSeeker == null){
            return new ErrorDataResult<>(null, "User can't be found");
        }
        foreword.setJobSeeker(jobSeeker);
        this.forewordDao.save(foreword);
        return new SuccessResult("Foreword Recorded");
    }
}
