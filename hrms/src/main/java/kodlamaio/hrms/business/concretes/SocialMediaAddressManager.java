package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.JobSeekerService;
import kodlamaio.hrms.business.abstracts.SocialMediaAddressService;
import kodlamaio.hrms.business.validators.abstracts.BaseValidator;
import kodlamaio.hrms.core.utilities.results.*;
import kodlamaio.hrms.dataAccess.abstracts.SocialMediaAddressDao;
import kodlamaio.hrms.entities.concretes.JobSeeker;
import kodlamaio.hrms.entities.concretes.SocialMediaAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SocialMediaAddressManager implements SocialMediaAddressService {
    private SocialMediaAddressDao socialMediaAddressDao;
    private JobSeekerService jobSeekerService;

    @Autowired
    public SocialMediaAddressManager(SocialMediaAddressDao socialMediaAddressDao, JobSeekerService jobSeekerService) {
        this.socialMediaAddressDao = socialMediaAddressDao;
        this.jobSeekerService = jobSeekerService;
    }

    @Override
    public DataResult<SocialMediaAddress> getByJobSeekerId(int userId) {
        return new SuccessDataResult<>
                (this.socialMediaAddressDao.getByJobSeeker_UserId(userId), "User social media accounts");
    }

    @Override
    public Result add(int userId, SocialMediaAddress socialMediaAddress) {
        JobSeeker jobSeeker = this.jobSeekerService.getById(userId).getData();
        if (jobSeeker != null){
            return this.save(jobSeeker, socialMediaAddress);
        }
        // null
        return new ErrorResult("There is not a user!");
    }

    @Override
    public Result addOnlyGithubAddress(int userId, String githubAddress) {
        SocialMediaAddress socialMediaAddress = this.getOrCreateANew(userId);
        socialMediaAddress.setGithubAddress(githubAddress);
        return add(userId, socialMediaAddress);
    }

    @Override
    public Result addOnlyLinkedInAddress(int userId, String linkedInAddress) {
        SocialMediaAddress socialMediaAddress = this.getOrCreateANew(userId);
        socialMediaAddress.setLinkedinAddress(linkedInAddress);
        return add(userId, socialMediaAddress);
    }
    // Check if any record before
    private SocialMediaAddress getOrCreateANew(int userId){
        SocialMediaAddress socialMediaAddress = this.socialMediaAddressDao.getByJobSeeker_UserId(userId);
        if (socialMediaAddress == null){
            socialMediaAddress = new SocialMediaAddress();
        }
        return socialMediaAddress;
    }

    private Result save(JobSeeker jobSeeker,SocialMediaAddress socialMediaAddress){
        socialMediaAddress.setJobSeeker(jobSeeker);
        this.socialMediaAddressDao.save(socialMediaAddress);
        return new SuccessResult("Saved to Db");
    }

}
