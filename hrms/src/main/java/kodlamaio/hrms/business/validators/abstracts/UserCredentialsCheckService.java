package kodlamaio.hrms.business.validators.abstracts;

import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.abstracts.User;

public interface UserCredentialsCheckService {
    public Result checkUserCredentials(User user);
}
