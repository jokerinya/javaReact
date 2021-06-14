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
        if (jobSeeker == null){
            return new ErrorResult("There is not a user!");
        }
        socialMediaAddress.setJobSeeker(jobSeeker);
        this.socialMediaAddressDao.save(socialMediaAddress);
        return new SuccessResult("Added to DB");
    }

    @Override
    public Result update(int userId, SocialMediaAddress socialMediaAddress) {
        SocialMediaAddress oldDocialMediaAddress = this.socialMediaAddressDao.getOne(userId);
        // Set fields
        oldDocialMediaAddress.setGithubAddress(socialMediaAddress.getGithubAddress());
        oldDocialMediaAddress.setLinkedinAddress(socialMediaAddress.getLinkedinAddress());
        // save
        this.socialMediaAddressDao.save(oldDocialMediaAddress);
        return new SuccessResult("Social media accounts updated!");
    }


    @Override
    public Result delete(int userId) {
        SocialMediaAddress socialMediaAddress = this.socialMediaAddressDao.getOne(userId);
        // make JobSeeker Reference empty too
        JobSeeker jobSeeker = this.jobSeekerService.getById(userId).getData();
        jobSeeker.setSocialMediaAddress(null);
        this.socialMediaAddressDao.delete(socialMediaAddress);
        return new SuccessResult("All social media account names have been deleted!");
    }

}
