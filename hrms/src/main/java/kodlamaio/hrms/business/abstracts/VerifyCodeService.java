package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.Result;

public interface VerifyCodeService {
    Result addWithUserIdAndCode(int userId, String code);
}
