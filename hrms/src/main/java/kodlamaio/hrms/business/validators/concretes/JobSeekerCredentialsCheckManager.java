package kodlamaio.hrms.business.validators.concretes;

import kodlamaio.hrms.business.validators.abstracts.BaseValidator;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.JobSeeker;
import kodlamaio.hrms.entities.abstracts.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class JobSeekerCredentialsCheckManager extends UserCredentialsCheckManager {

    @Override
    public Result checkUserCredentials(User user) {
        JobSeeker jobSeeker = (JobSeeker) user;
        // firstName & lastName
        if (!BaseValidator.requiredString(jobSeeker.getFirstName()) ||
            !BaseValidator.requiredString(jobSeeker.getLastName())){
            return new ErrorResult("First name or last name cannot be empty!");
        }
        // Tc No
        String nationalIdentityNo = jobSeeker.getNationalIdentityNo();
        if (!BaseValidator.requiredString(nationalIdentityNo) || nationalIdentityNo.length() != 11){
            return new ErrorResult("National identity number must be 11 chars!");
        }
        // Birth date
        int yearOfBirth = jobSeeker.getYearOfBirth();
        int year = LocalDate.now().getYear();
        int jobSeekerAge = year - yearOfBirth;
        if ((jobSeekerAge > 99 ) || (yearOfBirth < 18)){
            return new ErrorResult("Year of birth must be between " + (year - 99) + " and " + (year - 18) + ".");
        }
        // Email and password
       return super.checkUserCredentials(jobSeeker);
    }


}
