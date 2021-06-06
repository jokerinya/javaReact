package kodlamaio.hrms.business.validators.concretes;

import kodlamaio.hrms.business.validators.abstracts.BaseValidator;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.JobExperience;

public class JobExperienceCredentialsCheckManager extends BaseValidator {
    public static Result checkValid(JobExperience jobExperience){
        if (!requiredString(jobExperience.getPosition().getPositionName())){
            return new ErrorResult("Position name cannot be empty");
        }
        if (!requiredLocalDate(jobExperience.getStartDate())){
            return new ErrorResult("Please provide a YYYY-mm-dd formatted date.");
        }
        if (!requiredString(jobExperience.getCompanyName())){
            return new ErrorResult("Please enter a company name");
        }
        // valid result
        return null;
    }
}
