package kodlamaio.hrms.api.concretes;

import kodlamaio.hrms.business.abstracts.KnownLanguageService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.KnownLanguage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/knownLanguages")
public class KnownLanguagesController {
    private KnownLanguageService knownLanguageService;
    @Autowired
    public KnownLanguagesController(KnownLanguageService knownLanguageService) {
        this.knownLanguageService = knownLanguageService;
    }

    @GetMapping("/{jobSeekerId}")
    public DataResult<List<KnownLanguage>> getByJobSeekerId(@PathVariable int jobSeekerId){
        return this.knownLanguageService.getByJobSeekerId(jobSeekerId);
    }

    @PostMapping("/{jobSeekerId}/add")
    public Result add(@PathVariable int jobSeekerId, @RequestBody KnownLanguage knownLanguage){
        return this.knownLanguageService.add(jobSeekerId, knownLanguage);
    }
}
