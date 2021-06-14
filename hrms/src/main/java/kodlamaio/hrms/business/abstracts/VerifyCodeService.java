package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.VerifyCode;

public interface VerifyCodeService {
    Result addWithUserIdAndCode(int userId, String code);
    DataResult<VerifyCode> getByUserId(int userId);
}
