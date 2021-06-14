package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.UserNotificationService;
import kodlamaio.hrms.business.abstracts.UserService;
import kodlamaio.hrms.business.abstracts.VerifyCodeService;
import kodlamaio.hrms.business.validators.abstracts.BaseValidator;
import kodlamaio.hrms.business.validators.concretes.UserCredentialsCheckManager;
import kodlamaio.hrms.core.utilities.results.*;
import kodlamaio.hrms.dataAccess.abstracts.UserDao;
import kodlamaio.hrms.entities.abstracts.User;
import kodlamaio.hrms.entities.concretes.VerifyCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserManager implements UserService {

    private UserDao userDao;
    private VerifyCodeService verifyCodeService;
    private UserNotificationService userNotificationService;


    @Autowired
    public UserManager(UserDao userDao, VerifyCodeService verifyCodeService,
                       UserNotificationService userNotificationService) {
        this.verifyCodeService = verifyCodeService;
        this.userDao = userDao;
        this.userNotificationService = userNotificationService;
    }

    @Override
    public DataResult<List<User>> getAll() {
        return new SuccessDataResult<>(this.userDao.findAll(), "Users list fetched.");
    }

    @Override
    public DataResult<User> getByEmail(String email) {
        return new SuccessDataResult<>(this.userDao.findByEmail(email), "User fetched.");
    }


    @Override
    public Result verifyEmail(Integer userId, String code) {
        VerifyCode verifyCode = this.verifyCodeService.getByUserId(userId).getData();
        // check if db code and request code are equal
        if (verifyCode.getCode().equals(code)){
            User user = this.userDao.getOne(userId);
            user.setEmailIsVerified(true);
            this.userDao.save(user);
            return new SuccessResult("Email is activated!");
        }
        return new ErrorResult("An Error Occurred!");
    }

    @Override
    public Result updateEmail(int userId, String email) {
        if (this.userDao.findByEmail(email) != null){
            return new ErrorResult("Email is already used. Please check your email address!");
        }
        if (!UserCredentialsCheckManager.isEmailValid(email)){
            return new ErrorResult("Provide an appropriate email address please!");
        }
        User user = this.userDao.getOne(userId);
        user.setEmail(email);
        user.setEmailIsVerified(false);
        this.userDao.save(user);
        // Send Notification Link
        this.userNotificationService.sendActivationLink(user);
        return new SuccessResult("Email is updated, please activate by a link or apply to site admin.");
    }

    @Override
    public Result updatePassword(int userId, String newPassword) {
        User user = this.userDao.getOne(userId);
        user.setPassword(newPassword);
        this.userDao.save(user);
        return new SuccessResult("Password has been updated");
    }
}
