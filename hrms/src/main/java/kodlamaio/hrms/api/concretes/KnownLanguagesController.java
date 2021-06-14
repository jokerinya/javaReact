package kodlamaio.hrms.api.concretes;

import kodlamaio.hrms.business.abstracts.KnownLanguageService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.KnownLanguage;
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
@RequestMapping("/api/knownLanguages")
@CrossOrigin
public class KnownLanguagesController {
    private KnownLanguageService knownLanguageService;
    @Autowired
    public KnownLanguagesController(KnownLanguageService knownLanguageService) {
        this.knownLanguageService = knownLanguageService;
    }

    @GetMapping("/{jobSeekerId}")
    public ResponseEntity<?> getByJobSeekerId(@PathVariable int jobSeekerId){
        return ResponseEntity.ok(this.knownLanguageService.getByJobSeekerId(jobSeekerId));
    }

    @PostMapping("/{jobSeekerId}/add")
    public ResponseEntity<?> add(@PathVariable int jobSeekerId, @Valid @RequestBody KnownLanguage knownLanguage){
        return ResponseEntity.ok(this.knownLanguageService.add(jobSeekerId, knownLanguage));
    }

    @PutMapping("/{jobSeekerId}/update")
    public ResponseEntity<?> update(@PathVariable int jobSeekerId, @Valid @RequestBody KnownLanguage knownLanguage){
        return ResponseEntity.ok(this.knownLanguageService.update(jobSeekerId, knownLanguage));
    }

    @DeleteMapping("/{jobSeekerId}/delete")
    public ResponseEntity<?> delete(@PathVariable int jobSeekerId, @Valid @RequestBody KnownLanguage knownLanguage){
        return ResponseEntity.ok(this.knownLanguageService.delete(jobSeekerId, knownLanguage));
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
