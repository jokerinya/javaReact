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

    @GetMapping("/getAllActiveSortedByDate")
    public DataResult<List<JobPosting>> getAllActiveSortedByDate(){
        return this.jobPostingService.getAllActiveSortedByDate();
    }

    @GetMapping("/getAllActiveWithCompanyId")
    public DataResult<List<JobPosting>> getAllActiveWithCompanyId(@RequestParam int companyId){
        return this.jobPostingService.getAllActiveWithCompanyId(companyId);
    }

    @PostMapping("/{companyId}/add")
    public Result add(@PathVariable int companyId, @RequestBody JobPosting jobPosting){
        System.out.println(companyId); // for demo purposes!
        return this.jobPostingService.add(companyId, jobPosting);
    }

    @GetMapping("/setPassive")
    public Result setPassive(@RequestParam int jobPostingId){
        return this.jobPostingService.setPassive(jobPostingId);
    }

}
