package kodlamaio.hrms.business.validators.concretes;

import kodlamaio.hrms.business.validators.abstracts.BaseValidator;
import kodlamaio.hrms.business.validators.abstracts.UserCredentialsCheckService;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.entities.abstracts.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserCredentialsCheckManager implements UserCredentialsCheckService {
    @Override
    public Result checkUserCredentials(User user) {
        // Email
        String email = user.getEmail();
        if(!BaseValidator.requiredString(email) || !isEmailValid(email)){
            return new ErrorResult("Email address error, please check your email address!");
        }
        // Password
        String password = user.getPassword();
        if (!BaseValidator.requiredString(password) || (password.length() < 6)){
            return new ErrorResult("Password should be at least 6 chars, please check your password!");
        }
        return new SuccessResult("Email and Password are valid!");
    }
    public static boolean isEmailValid(String email) {
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
