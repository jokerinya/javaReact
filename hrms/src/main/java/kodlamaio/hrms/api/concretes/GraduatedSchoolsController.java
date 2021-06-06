package kodlamaio.hrms.api.concretes;

import kodlamaio.hrms.business.abstracts.GraduatedSchoolService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.GraduatedSchool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/graduatedSchools")
public class GraduatedSchoolsController {
    private GraduatedSchoolService graduatedSchoolService;

    @Autowired
    public GraduatedSchoolsController(GraduatedSchoolService graduatedSchoolService) {
        this.graduatedSchoolService = graduatedSchoolService;
    }

    @GetMapping("/getAll")
    public DataResult<List<GraduatedSchool>> getAll(){
        return this.graduatedSchoolService.getAll();
    }

    @GetMapping("/{jobSeekerId}")
    public DataResult<List<GraduatedSchool>> getByJobSeekerId(@PathVariable int jobSeekerId){
        return this.graduatedSchoolService.getByJobSeekerId(jobSeekerId);
    }

    @GetMapping("/{jobSeekerId}/getByJobSeekerIdOrderByGraduationDate")
    DataResult<List<GraduatedSchool>> getByJobSeekerIdOrderByGraduationDate(@PathVariable int jobSeekerId){
        return this.graduatedSchoolService.getByJobSeekerIdOrderByGraduationDate(jobSeekerId);
    }

    @PostMapping("/{jobSeekerId}/add")
    public Result add(@PathVariable int jobSeekerId, @Valid @RequestBody GraduatedSchool graduatedSchool){
        return this.graduatedSchoolService.add(jobSeekerId ,graduatedSchool);
    }
}
