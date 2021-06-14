package kodlamaio.hrms.api.concretes;

import kodlamaio.hrms.business.abstracts.JobExperienceService;
import kodlamaio.hrms.business.abstracts.JobSeekerService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.JobExperience;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/jobExperiences")
@CrossOrigin
public class JobExperiencesController {
    private JobExperienceService jobExperienceService;

    @Autowired
    public JobExperiencesController(JobExperienceService jobExperienceService) {
        this.jobExperienceService = jobExperienceService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(this.jobExperienceService.getAll());
    }

    @GetMapping("/{jobSeekerId}")
    public ResponseEntity<?> getByJobSeekerId(@PathVariable int jobSeekerId){
        return ResponseEntity.ok(this.jobExperienceService.getByJobSeekerId(jobSeekerId));
    }

    @GetMapping("/{jobSeekerId}/getByJobSeekerIdOrderByEndDate")
    public ResponseEntity<?> getByJobSeekerIdOrderByEndDate(@PathVariable int jobSeekerId){
        return ResponseEntity.ok(this.jobExperienceService.getByJobSeekerIdOrderByEndDate(jobSeekerId));
    }

    @PostMapping("/{jobSeekerId}/add")
    public ResponseEntity<?> add(@PathVariable int jobSeekerId, @Valid @RequestBody JobExperience jobExperience){
        return ResponseEntity.ok(this.jobExperienceService.add(jobSeekerId, jobExperience));
    }

    @DeleteMapping("/{jobSeekerId}/delete")
    public ResponseEntity<?> delete(@PathVariable int jobSeekerId, @Valid @RequestBody JobExperience jobExperience){
        return ResponseEntity.ok(this.jobExperienceService.delete(jobSeekerId, jobExperience));
    }

    @PutMapping("/{jobSeekerId}/update")
    public ResponseEntity<?> update(@PathVariable int jobSeekerId, @Valid @RequestBody JobExperience jobExperience){
        return ResponseEntity.ok(this.jobExperienceService.update(jobSeekerId, jobExperience));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorDataResult<Object> handleValidationException(@NotNull MethodArgumentNotValidException exceptions){
        Map<String, String> validationErrors = new HashMap<>();
        for (FieldError fieldError : exceptions.getBindingResult().getFieldErrors()){
            validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return new ErrorDataResult<>(validationErrors, "Errors");
    }

}
