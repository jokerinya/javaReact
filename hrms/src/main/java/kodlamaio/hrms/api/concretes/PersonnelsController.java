package kodlamaio.hrms.api.concretes;

import kodlamaio.hrms.business.abstracts.PersonnelService;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.entities.abstracts.User;
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
import java.util.Map;

@RestController
@RequestMapping("/api/personnel")
@CrossOrigin
public class PersonnelsController {
    private PersonnelService personnelService;

    @Autowired
    public PersonnelsController(PersonnelService personnelService) {
        this.personnelService = personnelService;
    }

    @GetMapping("/{personnelId}")
    public ResponseEntity<?> getById(@PathVariable int personnelId){
        return ResponseEntity.ok(this.personnelService.getById(personnelId));
    }

    @PostMapping("/{personnelId}/activateUserEmail")
    public ResponseEntity<?> activateUserEmail(@PathVariable int personnelId, @Valid @RequestBody User user){
        return ResponseEntity.ok(this.personnelService.activateUserEmail(personnelId, user));
    }

    @PostMapping("/{personnelId}/activateJobPosting/{jobPostingId}")
    public ResponseEntity<?> activateJobPosting(@PathVariable int personnelId, @PathVariable int jobPostingId){
        return ResponseEntity.ok(this.personnelService.activateJobPosting(personnelId, jobPostingId));
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
