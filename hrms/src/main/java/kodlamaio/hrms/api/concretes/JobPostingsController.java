package kodlamaio.hrms.api.concretes;

import kodlamaio.hrms.business.abstracts.JobPostingService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.JobPosting;
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
@RequestMapping("/api/jobPostings")
@CrossOrigin
public class JobPostingsController {

    private JobPostingService jobPostingService;

    @Autowired
    public JobPostingsController(JobPostingService jobPostingService) {
        this.jobPostingService = jobPostingService;
    }

    @GetMapping("/getAllActive")
    public ResponseEntity<?> getAllActive(){
        return ResponseEntity.ok(this.jobPostingService.getAllActive());
    }

    @GetMapping("/getAllActiveSortedByDate")
    public ResponseEntity<?> getAllActiveSortedByDate(){
        return ResponseEntity.ok(this.jobPostingService.getAllActiveSortedByDate());
    }

    @GetMapping("/{companyId}/getAllActive")
    public ResponseEntity<?> getAllActiveWithCompanyId(@PathVariable int companyId){
        return ResponseEntity.ok(this.jobPostingService.getAllActiveWithCompanyId(companyId));
    }

    @PostMapping("/{companyId}/add")
    public ResponseEntity<?> add(@PathVariable int companyId,@Valid @RequestBody JobPosting jobPosting){
        return ResponseEntity.ok(this.jobPostingService.add(companyId, jobPosting));
    }

    @PutMapping("/{companyId}/update/{jobPostingId}")
    public ResponseEntity<?> update(@PathVariable int companyId,@PathVariable int jobPostingId,@Valid @RequestBody JobPosting jobPosting){
        return ResponseEntity.ok(this.jobPostingService.update(companyId, jobPostingId, jobPosting));
    }

    @GetMapping("/{companyId}/setPassive")
    public ResponseEntity<?> setPassive(@PathVariable int companyId, @RequestParam int jobPostingId){
        return ResponseEntity.ok(this.jobPostingService.setPassive(companyId, jobPostingId));
    }

    @DeleteMapping("/{companyId}/delete/{jobPostingId}")
    public ResponseEntity<?> delete(@PathVariable int companyId, @PathVariable int jobPostingId){
        return ResponseEntity.ok(this.jobPostingService.delete(jobPostingId));
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
