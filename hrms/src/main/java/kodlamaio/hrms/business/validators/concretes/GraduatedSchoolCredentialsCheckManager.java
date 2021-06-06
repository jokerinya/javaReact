package kodlamaio.hrms.business.validators.concretes;

import kodlamaio.hrms.business.validators.abstracts.BaseValidator;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.GraduatedSchool;

public class GraduatedSchoolCredentialsCheckManager extends BaseValidator {
    public static Result checkValid(GraduatedSchool graduatedSchool){
        if (!requiredString(graduatedSchool.getSchool().getSchoolName())){
            return new ErrorResult("School name cannot be empty.");
        }
        if (!requiredString(graduatedSchool.getDepartment().getDepartmentName())){
            return new ErrorResult("Department name cannot be empty.");
        }
        if (!requiredLocalDate(graduatedSchool.getStartDate())){
            return new ErrorResult("Start date of school cannot be empty");
        }
        return null;
    }
}
