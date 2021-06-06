package kodlamaio.hrms.api.concretes;

import kodlamaio.hrms.business.abstracts.CvService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.entities.dtos.JobSeekerCvDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cv")
public class CvsController {
    private CvService cvService;

    @Autowired
    public CvsController(CvService cvService) {
        this.cvService = cvService;
    }

    @GetMapping("/{jobSeekerId}")
    public DataResult<JobSeekerCvDto> getJobSeekerCV(@PathVariable int jobSeekerId){
        return this.cvService.getJobSeekerCV(jobSeekerId);
    }
}
