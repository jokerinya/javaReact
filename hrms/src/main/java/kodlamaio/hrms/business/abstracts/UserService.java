package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.abstracts.User;

import java.util.List;

public interface UserService {
    DataResult<List<User>> getAll();
    DataResult<User> getByEmail(String email);
    Result verifyEmailWithCode(Integer userId, String code);
    Result verifyEmailDirectly(User user);
    Result updateEmail(int userId, String email);
    Result updatePassword(int userId, String newPassword);
}
