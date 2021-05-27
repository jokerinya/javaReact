package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Company;

import java.util.List;

public interface CompanyService {
    DataResult<List<Company>> getAll();
    Result add(Company company);
}
