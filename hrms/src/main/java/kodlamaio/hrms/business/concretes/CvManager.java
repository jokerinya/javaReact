package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.*;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.entities.concretes.JobSeeker;
import kodlamaio.hrms.entities.dtos.JobSeekerCvDto;
import org.springframework.stereotype.Service;

@Service
public class CvManager implements CvService {

    private JobSeekerService jobSeekerService;

    public CvManager(JobSeekerService jobSeekerService) {
        this.jobSeekerService = jobSeekerService;
    }

    @Override
    public DataResult<JobSeekerCvDto> getJobSeekerCV(int userId) {
        JobSeeker jobSeeker = this.jobSeekerService.getById(userId).getData();
        if (jobSeeker == null){
            return new ErrorDataResult<>(null, "User can't be found!");
        }

        JobSeekerCvDto jobSeekerCVDto = new JobSeekerCvDto();

        jobSeekerCVDto.setJobSeeker(jobSeeker);
        jobSeekerCVDto.setForewords(jobSeeker.getForewords());
        jobSeekerCVDto.setJobExperiences(jobSeeker.getJobExperiences());
        jobSeekerCVDto.setGraduatedSchools(jobSeeker.getGraduatedSchools());
        jobSeekerCVDto.setTechnologies(jobSeeker.getTechnologies());
        jobSeekerCVDto.setKnownLanguages(jobSeeker.getKnownLanguages());
        jobSeekerCVDto.setSocialMediaAddress(jobSeeker.getSocialMediaAddress());

        return new SuccessDataResult<>(jobSeekerCVDto, "Cv of the User");
    }
}
