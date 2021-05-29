package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.UserNotificationService;
import kodlamaio.hrms.dataAccess.abstracts.VerifyCodeDao;
import kodlamaio.hrms.entities.abstracts.User;
import kodlamaio.hrms.entities.concretes.VerifyCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailNotificationManager implements UserNotificationService {

    private VerifyCodeDao verifyCodeDao;

    @Autowired
    public EmailNotificationManager(VerifyCodeDao verifyCodeDao) {
        this.verifyCodeDao = verifyCodeDao;
    }

    public boolean sendActivationLink(User user){
        String code = "u" + (String.valueOf(Math.random() * Math.random()).substring(2));
        int userId = user.getUserId();

        // Save verify code and user id to db
        VerifyCode verifyCode = new VerifyCode();
        verifyCode.setUserId(userId);
        verifyCode.setCode(code);
        verifyCodeDao.save(verifyCode);

        String link = "http://localhost:8080/api/verify/email/" + userId + "/" + code;
        System.out.println("Email has been sent to " + user.getEmail() + " with this link:\n" + link);
        return true;
    }
}
