package kodlamaio.hrms.api.concretes;

import kodlamaio.hrms.business.abstracts.CompanyService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spring.web.json.Json;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/companies")
@CrossOrigin
public class CompaniesController {

    private CompanyService companyService;

    @Autowired
    public CompaniesController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(this.companyService.getAll());
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody Company company){
        return ResponseEntity.ok(this.companyService.add(company));
    }

    @GetMapping("/{companyId}")
    public ResponseEntity<?> getByCompanyId(@PathVariable int companyId){
        return ResponseEntity.ok(this.companyService.getById(companyId));
    }

    @PutMapping("/{companyId}/updateFields")
    public ResponseEntity<?> updateFields(@PathVariable int companyId,@Valid @RequestBody Company company){
        return ResponseEntity.ok(this.companyService.updateFields(companyId, company));
    }

    @PutMapping("/{companyId}/updatePassword")
    public ResponseEntity<?> updatePassword(@PathVariable int companyId, @RequestBody String newPassword){
        return ResponseEntity.ok(this.companyService.updatePassword(companyId, newPassword));
    }

    @PutMapping("/{companyId}/updateEmail")
    public ResponseEntity<?> updateEmail(@PathVariable int companyId, @RequestBody String email){
        return ResponseEntity.ok(this.companyService.updateEmail(companyId, email));
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
