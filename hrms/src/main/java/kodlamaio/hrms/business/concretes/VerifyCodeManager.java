package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.VerifyCodeService;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.VerifyCodeDao;
import kodlamaio.hrms.entities.concretes.VerifyCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VerifyCodeManager implements VerifyCodeService {
    private VerifyCodeDao verifyCodeDao;

    @Autowired
    public VerifyCodeManager(VerifyCodeDao verifyCodeDao) {
        this.verifyCodeDao = verifyCodeDao;
    }

    @Override
    public Result addWithUserIdAndCode(int userId, String code) {
        VerifyCode verifyCode = new VerifyCode();
        verifyCode.setUserId(userId);
        verifyCode.setCode(code);
        verifyCodeDao.save(verifyCode);
        return new SuccessResult("Verify code saved to Db");
    }
}
