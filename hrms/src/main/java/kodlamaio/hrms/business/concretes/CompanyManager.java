package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.CompanyService;
import kodlamaio.hrms.business.abstracts.UserService;
import kodlamaio.hrms.business.validators.abstracts.UserCredentialsCheckService;
import kodlamaio.hrms.business.abstracts.UserNotificationService;
import kodlamaio.hrms.business.validators.concretes.UserCredentialsCheckManager;
import kodlamaio.hrms.core.utilities.results.*;
import kodlamaio.hrms.dataAccess.abstracts.CompanyDao;
import kodlamaio.hrms.entities.concretes.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyManager implements CompanyService {

    private CompanyDao companyDao;
    private UserCredentialsCheckService userCredentialsCheckService;
    private UserNotificationService userNotificationService;
    private UserService userService;

    @Autowired
    public CompanyManager(CompanyDao companyDao,
                          @Qualifier("companyCredentialsCheckManager") UserCredentialsCheckService userCredentialsCheckService,
                          UserNotificationService userNotificationService,
                          UserService userService) {
        this.companyDao = companyDao;
        this.userCredentialsCheckService = userCredentialsCheckService;
        this.userNotificationService = userNotificationService;
        this.userService = userService;
    }

    @Override
    public DataResult<List<Company>> getAll() {
        return new SuccessDataResult<>(this.companyDao.findAll(), "Companies fetched.");
    }

    @Override
    public Result add(Company company) {
        if (userCredentialsCheckService.checkUserCredentials(company) instanceof SuccessResult){
            // add to DB, before adding check all Email occurrences
            if (this.userService.getByEmail(company.getEmail()).getData() != null){
                return new ErrorResult("Email is already used. Please check your email address!");
            }
            // Email Link
            company.setEmailIsVerified(false);
            this.companyDao.save(company);
            // Send Notification Link
            this.userNotificationService.sendActivationLink(company);
            return new SuccessResult("Company successfully added to DB, please activate by a link or " +
                    "apply to site admin.");
        }
        return userCredentialsCheckService.checkUserCredentials(company);
    }

    @Override
    public Result updateFields(int userId, Company company) {
        Company oldCompany = this.companyDao.getOne(userId);
        // set fields
        oldCompany.setCompanyName(company.getCompanyName());
        oldCompany.setCompanyPhone(company.getCompanyPhone());
        oldCompany.setCompanyWebsite(company.getCompanyWebsite());
        // save to db
        this.companyDao.save(oldCompany);
        return new SuccessResult("Company fields have been updated!");
    }

    @Override
    public Result updateEmail(int userId, String email) {
        if (this.companyDao.existsById(userId)){
            return this.userService.updateEmail(userId, email);
        }
        return new ErrorResult("Company couldn't be found");
    }

    @Override
    public Result updatePassword(int userId, String newPassword) {
        if (this.companyDao.existsById(userId)){
            return this.userService.updatePassword(userId, newPassword);
        }
        return new ErrorResult("Company couldn't be found");
    }

    @Override
    public DataResult<Company> getById(int userId) {
        return new SuccessDataResult<>(this.companyDao.getOne(userId), "Company has been fetched");
    }

}
