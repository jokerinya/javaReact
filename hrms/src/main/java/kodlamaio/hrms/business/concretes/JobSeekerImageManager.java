package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.JobSeekerImageService;
import kodlamaio.hrms.business.abstracts.JobSeekerService;
import kodlamaio.hrms.core.utilities.results.*;
import kodlamaio.hrms.core.utilities.uploads.image.abstracts.ImageUploadService;
import kodlamaio.hrms.dataAccess.abstracts.JobSeekerImageDao;
import kodlamaio.hrms.entities.concretes.JobSeeker;
import kodlamaio.hrms.entities.concretes.JobSeekerImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Service
public class JobSeekerImageManager implements JobSeekerImageService {
    private JobSeekerImageDao jobSeekerImageDao;
    private JobSeekerService jobSeekerService;
    private ImageUploadService imageUploadService;

    @Autowired
    public JobSeekerImageManager(JobSeekerImageDao jobSeekerImageDao, JobSeekerService jobSeekerService,
                                 ImageUploadService imageUploadService) {
        this.jobSeekerImageDao = jobSeekerImageDao;
        this.jobSeekerService = jobSeekerService;
        this.imageUploadService = imageUploadService;
    }

    @Override
    public DataResult<JobSeekerImage> getByJobSeekerId(int userId) {
        return new SuccessDataResult<>(this.jobSeekerImageDao.getByJobSeeker_UserId(userId));
    }

    @Override
    public Result add(int userId, MultipartFile file) {
        // Check user
        JobSeeker jobSeeker = this.jobSeekerService.getById(userId).getData();
        if (jobSeeker == null){
            return new ErrorResult("User can't be found.");
        }
        // Check before record
        JobSeekerImage jobSeekerImage = this.jobSeekerImageDao.getByJobSeeker_UserId(userId);
        // Define as a new image if there is no record
        if (jobSeekerImage == null){
            jobSeekerImage = new JobSeekerImage();
            jobSeekerImage.setJobSeeker(jobSeeker);
        }

        // Upload the image and get a map of data
        Map result = (Map) this.imageUploadService.savePhoto(file).getData();
        System.out.println(result);
        String imageUrl = result.get("url").toString();
        jobSeekerImage.setImageUrl(imageUrl);

        this.jobSeekerImageDao.save(jobSeekerImage);
        return new SuccessResult("Image Uploaded");
    }

    @Override
    public Result delete(int userId) {
        JobSeekerImage jobSeekerImage = this.jobSeekerImageDao.getOne(userId);
        JobSeeker jobSeeker = this.jobSeekerService.getById(userId).getData();
        // set reference
        jobSeeker.setJobSeekerImage(null);
        // delete from db
        this.jobSeekerImageDao.delete(jobSeekerImage);
        return new SuccessResult("Image Deleted!");
    }
}
