package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.GraduatedSchool;

import java.util.List;

public interface GraduatedSchoolService {
    DataResult<List<GraduatedSchool>> getAll();
    DataResult<List<GraduatedSchool>> getByJobSeekerId(int userId);
    DataResult<List<GraduatedSchool>> getByJobSeekerIdOrderByGraduationDate(int userId);
    Result add(int jobSeekerId,GraduatedSchool graduatedSchool);
    Result delete(int jobSeekerId, GraduatedSchool graduatedSchool);
    Result update(int jobSeekerId, GraduatedSchool graduatedSchool);


}
