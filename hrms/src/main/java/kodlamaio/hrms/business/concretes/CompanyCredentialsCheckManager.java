package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Company;
import kodlamaio.hrms.entities.concretes.User;
import org.springframework.stereotype.Service;



@Service
public class CompanyCredentialsCheckManager extends UserCredentialsCheckManager {
    @Override
    public Result checkUserCredentials(User user) {
        Company company = (Company) user;
        // Name
        String name = company.getCompanyName();
        if (name == null || name.length() == 0){
            return new ErrorResult("Name cannot be empty!");
        }
        // Website
        String webSite = company.getCompanyWebsite();
        if (webSite == null || webSite.length() == 0){
            return new ErrorResult("Website cannot be empty!");
        }
        // Phone
        String phone = company.getCompanyPhone();
        if (phone == null || phone.length() == 0){
            return new ErrorResult("Phone cannot be empty!");
        }
        // Email and password
        return super.checkUserCredentials(company);
    }
}
