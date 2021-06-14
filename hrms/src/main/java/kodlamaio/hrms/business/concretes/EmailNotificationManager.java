package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.UserNotificationService;
import kodlamaio.hrms.business.abstracts.VerifyCodeService;
import kodlamaio.hrms.dataAccess.abstracts.VerifyCodeDao;
import kodlamaio.hrms.entities.abstracts.User;
import kodlamaio.hrms.entities.concretes.VerifyCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailNotificationManager implements UserNotificationService {

    private VerifyCodeService verifyCodeService;

    @Autowired
    public EmailNotificationManager(VerifyCodeService verifyCodeService) {
        this.verifyCodeService = verifyCodeService;
    }

    public boolean sendActivationLink(User user){
        String code = "u" + (String.valueOf(Math.random() * Math.random()).substring(2));
        int userId = user.getUserId();

        // Save verify code and user id to db
        this.verifyCodeService.addWithUserIdAndCode(userId, code);

        // Send e-mail
        String link = "http://localhost:8080/api/verify/email/" + userId + "/" + code;
        System.out.println("Email has been sent to " + user.getEmail() + " with this link:\n" + link);
        return true;
    }
}
