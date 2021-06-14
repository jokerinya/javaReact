package kodlamaio.hrms.api.concretes;

import kodlamaio.hrms.business.abstracts.GraduatedSchoolService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.GraduatedSchool;
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
@RequestMapping("/api/graduatedSchools")
@CrossOrigin
public class GraduatedSchoolsController {
    private GraduatedSchoolService graduatedSchoolService;

    @Autowired
    public GraduatedSchoolsController(GraduatedSchoolService graduatedSchoolService) {
        this.graduatedSchoolService = graduatedSchoolService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(this.graduatedSchoolService.getAll());
    }

    @GetMapping("/{jobSeekerId}")
    public ResponseEntity<?> getByJobSeekerId(@PathVariable int jobSeekerId){
        return ResponseEntity.ok(this.graduatedSchoolService.getByJobSeekerId(jobSeekerId));
    }

    @GetMapping("/{jobSeekerId}/getByJobSeekerIdOrderByGraduationDate")
    public ResponseEntity<?> getByJobSeekerIdOrderByGraduationDate(@PathVariable int jobSeekerId){
        return ResponseEntity.ok(this.graduatedSchoolService.getByJobSeekerIdOrderByGraduationDate(jobSeekerId));
    }

    @PostMapping("/{jobSeekerId}/add")
    public ResponseEntity<?> add(@PathVariable int jobSeekerId, @Valid @RequestBody GraduatedSchool graduatedSchool){
        return ResponseEntity.ok(this.graduatedSchoolService.add(jobSeekerId ,graduatedSchool));
    }

    @DeleteMapping("/{jobSeekerId}/delete")
    public ResponseEntity<?> delete(@PathVariable int jobSeekerId, @Valid @RequestBody GraduatedSchool graduatedSchool){
        return ResponseEntity.ok(this.graduatedSchoolService.delete(jobSeekerId, graduatedSchool));
    }

    @PutMapping("/{jobSeekerId}/update")
    public ResponseEntity<?> update(@PathVariable int jobSeekerId, @Valid @RequestBody GraduatedSchool graduatedSchool){
        return ResponseEntity.ok(this.graduatedSchoolService.update(jobSeekerId, graduatedSchool));
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
