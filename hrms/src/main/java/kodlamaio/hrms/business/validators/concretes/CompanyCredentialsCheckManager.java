package kodlamaio.hrms.business.validators.concretes;

import kodlamaio.hrms.business.validators.abstracts.BaseValidator;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Company;
import kodlamaio.hrms.entities.abstracts.User;
import org.springframework.stereotype.Service;

import java.util.Locale;


@Service
public class CompanyCredentialsCheckManager extends UserCredentialsCheckManager {
    @Override
    public Result checkUserCredentials(User user) {
        Company company = (Company) user;
        // Name
        if (!BaseValidator.requiredString(company.getCompanyName())){
            return new ErrorResult("Name cannot be empty!");
        }
        // Website
        if (!BaseValidator.requiredString(company.getCompanyWebsite())){
            return new ErrorResult("Website cannot be empty!");
        }
        // Phone
        if (!BaseValidator.requiredString(company.getCompanyPhone())){
            return new ErrorResult("Phone cannot be empty!");
        }
        // Email and password
        if (BaseValidator.requiredString(company.getEmail()) &&
            !checkEmailDomain(company.getEmail(), company.getCompanyWebsite())){
            return new ErrorResult("Email domain must be your company website domain.");
        }
        return super.checkUserCredentials(company);
    }
    private boolean checkEmailDomain(String email, String webSite) {
        var emailParameters = email.split("@");
        var emailDomain = emailParameters[emailParameters.length - 1].toLowerCase(Locale.ROOT);
        // http(s)://www.google.com -> google.com, www.google.com -> google.com
        String webSiteDomain = webSite.replaceFirst("^(https?://)?(www\\.)?", "");
        return emailDomain.equals(webSiteDomain.toLowerCase(Locale.ROOT));
    }
}
