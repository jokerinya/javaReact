package kodlamaio.hrms.api.concretes;

import kodlamaio.hrms.business.abstracts.JobSeekerService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.entities.concretes.JobSeeker;
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
@RequestMapping("/api/jobseekers")
@CrossOrigin
public class JobSeekersController {
    private JobSeekerService jobSeekerService;

    @Autowired
    public JobSeekersController(JobSeekerService jobSeekerService) {
        this.jobSeekerService = jobSeekerService;
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(this.jobSeekerService.getAll());
    }

    @GetMapping("/{jobSeekerId}")
    public ResponseEntity<?> getById(@PathVariable int jobSeekerId){
        return ResponseEntity.ok(this.jobSeekerService.getById(jobSeekerId));
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody JobSeeker jobSeeker){
        return ResponseEntity.ok(this.jobSeekerService.add(jobSeeker));
    }

    @PutMapping("/{jobSeekerId}/updateEmail")
    public ResponseEntity<?> updateEmail(@PathVariable int jobSeekerId, @RequestBody String email){
        return ResponseEntity.ok(this.jobSeekerService.updateEmail(jobSeekerId, email));
    }

    @PutMapping("/{jobSeekerId}/updatePassword")
    public ResponseEntity<?> updatePassword(@PathVariable int jobSeekerId, @RequestBody String password){
        return ResponseEntity.ok(this.jobSeekerService.updatePassword(jobSeekerId, password));
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
