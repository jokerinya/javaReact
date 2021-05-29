package kodlamaio.hrms.api.concretes;

import kodlamaio.hrms.business.abstracts.JobPostingService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.JobPosting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobPostings")
public class JobPostingsController {

    private JobPostingService jobPostingService;

    @Autowired
    public JobPostingsController(JobPostingService jobPostingService) {
        this.jobPostingService = jobPostingService;
    }

    @GetMapping("/getAllActive")
    public DataResult<List<JobPosting>> getAllActive(){
        return this.jobPostingService.getAllActive();
    }

    @PostMapping("/{companyId}/add")
    public Result add(@PathVariable int companyId, @RequestBody JobPosting jobPosting){
        System.out.println(companyId); // for demo purposes!
        return this.jobPostingService.add(companyId, jobPosting);
    }
}
