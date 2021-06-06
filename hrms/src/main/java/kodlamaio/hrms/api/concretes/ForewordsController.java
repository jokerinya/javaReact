package kodlamaio.hrms.api.concretes;

import kodlamaio.hrms.business.abstracts.ForewordService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Foreword;
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
@RequestMapping("/api/forewords")
public class ForewordsController {
    private ForewordService forewordService;

    @Autowired
    public ForewordsController(ForewordService forewordService) {
        this.forewordService = forewordService;
    }

    @GetMapping("/{jobSeekerId}")
    public ResponseEntity<?> getByJobSeekerId(@Valid @PathVariable int jobSeekerId){
        return ResponseEntity.ok(this.forewordService.getByJobSeekerId(jobSeekerId));
    }

    @PostMapping("/{jobSeekerId}/add")
    public ResponseEntity<?> add(@Valid @PathVariable int jobSeekerId, @Valid @RequestBody Foreword foreword){
        return ResponseEntity.ok(this.forewordService.add(jobSeekerId, foreword));
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
