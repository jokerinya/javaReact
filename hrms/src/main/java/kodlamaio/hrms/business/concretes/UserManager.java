package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.UserService;
import kodlamaio.hrms.core.utilities.results.*;
import kodlamaio.hrms.dataAccess.abstracts.UserDao;
import kodlamaio.hrms.dataAccess.abstracts.VerifyCodeDao;
import kodlamaio.hrms.entities.concretes.User;
import kodlamaio.hrms.entities.concretes.VerifyCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserManager implements UserService {

    private UserDao userDao;
    private VerifyCodeDao verifyCodeDao;

    @Autowired
    public UserManager(UserDao userDao, VerifyCodeDao verifyCodeDao) {
        this.verifyCodeDao = verifyCodeDao;
        this.userDao = userDao;
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
        VerifyCode verifyCode = this.verifyCodeDao.getByUserId(userId);
//        System.out.println(verifyCode.toString());

        if (verifyCode.getCode().equals(code)){
            User user = this.userDao.getByUserId(userId);
            user.setEmailIsVerified(true);
            this.userDao.save(user);
            return new SuccessResult("Email is activated!");
        }
        return new ErrorResult("An Error Occurred!");
    }
}
