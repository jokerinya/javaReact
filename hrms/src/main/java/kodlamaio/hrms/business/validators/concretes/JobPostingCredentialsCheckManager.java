package kodlamaio.hrms.business.validators.concretes;

import kodlamaio.hrms.business.validators.abstracts.BaseValidator;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.JobPosting;

import java.time.LocalDate;

public class JobPostingCredentialsCheckManager {
    public static Result checkValid(JobPosting jobPosting){
        if (!BaseValidator.requiredInteger(jobPosting.getCompany().getUserId())){
            return new ErrorResult("Company user id cannot be empty");
        }
        if (!BaseValidator.requiredInteger(jobPosting.getPosition().getPositionId())){
            return new ErrorResult("Position id cannot be empty");
        }
        if (!BaseValidator.requiredInteger(jobPosting.getCity().getCityId())){
            return new ErrorResult("City id cannot be empty");
        }
        if (!BaseValidator.requiredString(jobPosting.getDescription())){
            return new ErrorResult("Description cannot be empty");
        }
        if (!BaseValidator.requiredInteger(jobPosting.getOpenPositions()) || (jobPosting.getOpenPositions() <= 0)){
            return new ErrorResult("Open positions must be bigger than zero");
        }
        if (!BaseValidator.requiredLocalDate(jobPosting.getLastApplicationDate()) ||
            jobPosting.getLastApplicationDate().isBefore(LocalDate.now())){
            return new ErrorResult("Last application date cannot be a past time.");
        }
        // There is no problem with data until now!
        return null;
    }
}
