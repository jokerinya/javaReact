package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Company;

import java.util.List;

public interface CompanyService {
    DataResult<List<Company>> getAll();
    Result add(Company company);
    Result updateFields(int userId, Company company);
    Result updateEmail(int userId, String email);
    Result updatePassword(int userId, String newPassword);
    DataResult<Company> getById(int userId);
}
