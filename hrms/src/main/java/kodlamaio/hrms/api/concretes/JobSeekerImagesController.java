package kodlamaio.hrms.api.concretes;


import kodlamaio.hrms.business.abstracts.JobSeekerImageService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.JobSeekerImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/jobSeekerImage")
public class JobSeekerImagesController {
    private JobSeekerImageService jobSeekerImageService;

    @Autowired
    public JobSeekerImagesController(JobSeekerImageService jobSeekerImageService) {
        this.jobSeekerImageService = jobSeekerImageService;
    }

    @GetMapping("/{jobSeekerId}")
    DataResult<JobSeekerImage> getByJobSeekerId(@PathVariable int jobSeekerId){
        return this.jobSeekerImageService.getByJobSeekerId(jobSeekerId);
    }

    @PostMapping("/{jobSeekerId}/add")
    public Result add(@PathVariable int jobSeekerId, @RequestBody MultipartFile multipartFile){
        return this.jobSeekerImageService.add(jobSeekerId, multipartFile);
    }

}
