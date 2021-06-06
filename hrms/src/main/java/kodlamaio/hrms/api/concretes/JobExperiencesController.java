package kodlamaio.hrms.api.concretes;

import kodlamaio.hrms.business.abstracts.JobExperienceService;
import kodlamaio.hrms.business.abstracts.JobSeekerService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.JobExperience;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/jobExperiences")
public class JobExperiencesController {
    private JobExperienceService jobExperienceService;

    @Autowired
    public JobExperiencesController(JobExperienceService jobExperienceService) {
        this.jobExperienceService = jobExperienceService;
    }

    @GetMapping("/getAll")
    public DataResult<List<JobExperience>> getAll(){
        return this.jobExperienceService.getAll();
    }

    @GetMapping("/{jobSeekerId}")
    public DataResult<List<JobExperience>> getByJobSeekerId(@PathVariable int jobSeekerId){
        return this.jobExperienceService.getByJobSeekerId(jobSeekerId);
    }

    @GetMapping("/{jobSeekerId}/getByJobSeekerIdOrderByEndDate")
    public DataResult<List<JobExperience>> getByJobSeekerIdOrderByEndDate(@PathVariable int jobSeekerId){
        return this.jobExperienceService.getByJobSeekerIdOrderByEndDate(jobSeekerId);
    }

    @PostMapping("/{jobSeekerId}/add")
    public Result add(@PathVariable int jobSeekerId, @Valid @RequestBody JobExperience jobExperience){
        return this.jobExperienceService.add(jobSeekerId, jobExperience);
    }

}
