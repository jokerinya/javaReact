package kodlamaio.hrms.api.concretes;


import kodlamaio.hrms.business.abstracts.JobSeekerImageService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.JobSeekerImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/jobSeekerImage")
@CrossOrigin
public class JobSeekerImagesController {
    private JobSeekerImageService jobSeekerImageService;

    @Autowired
    public JobSeekerImagesController(JobSeekerImageService jobSeekerImageService) {
        this.jobSeekerImageService = jobSeekerImageService;
    }

    @GetMapping("/{jobSeekerId}")
    public ResponseEntity<?> getByJobSeekerId(@PathVariable int jobSeekerId){
        return ResponseEntity.ok(this.jobSeekerImageService.getByJobSeekerId(jobSeekerId));
    }

    @PostMapping("/{jobSeekerId}/add")
    public ResponseEntity<?> add(@PathVariable int jobSeekerId, @Valid @RequestBody MultipartFile multipartFile){
        return ResponseEntity.ok(this.jobSeekerImageService.add(jobSeekerId, multipartFile));
    }

    @PostMapping("/{jobSeekerId}/update")
    public ResponseEntity<?> update(@PathVariable int jobSeekerId, @Valid @RequestBody MultipartFile multipartFile){
        // will use the same method "add"
        return ResponseEntity.ok(this.jobSeekerImageService.add(jobSeekerId, multipartFile));
    }

    @DeleteMapping ("/{jobSeekerId}/delete")
    public ResponseEntity<?> delete(@PathVariable int jobSeekerId){
        return ResponseEntity.ok(this.jobSeekerImageService.delete(jobSeekerId));
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
