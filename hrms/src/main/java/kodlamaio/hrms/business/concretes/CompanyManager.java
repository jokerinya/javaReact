package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.CompanyService;
import kodlamaio.hrms.business.abstracts.UserCredentialsCheckService;
import kodlamaio.hrms.business.abstracts.UserNotificationService;
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

    @Autowired
    public CompanyManager(CompanyDao companyDao, @Qualifier("companyCredentialsCheckManager") UserCredentialsCheckService userCredentialsCheckService,
                          UserNotificationService userNotificationService) {
        this.companyDao = companyDao;
        this.userCredentialsCheckService = userCredentialsCheckService;
        this.userNotificationService = userNotificationService;
    }

    @Override
    public DataResult<List<Company>> getAll() {
        return new SuccessDataResult<>(this.companyDao.findAll(), "Companies fetched.");
    }

    @Override
    public Result add(Company company) {
        if (userCredentialsCheckService.checkUserCredentials(company) instanceof SuccessResult){
            // add to DB, before adding check Email occurrences
            if (companyDao.getByEmail(company.getEmail()) != null){
                return new ErrorResult("Email is already used. Please check your email address!");
            }
            // Email Link
            company.setEmailIsVerified(false);
            this.companyDao.save(company);
            // Send Notification Link
            this.userNotificationService.sendActivationLink(company);
            //
            return new SuccessResult("Company successfully added to DB, please activate by a link or " +
                    "apply to site admin.");

        }
        return userCredentialsCheckService.checkUserCredentials(company);
    }
}
