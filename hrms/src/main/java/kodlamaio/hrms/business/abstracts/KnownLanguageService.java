package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.KnownLanguage;

import java.util.List;

public interface KnownLanguageService {
    DataResult<List<KnownLanguage>> getByJobSeekerId(int userId);
    Result add(int userId, KnownLanguage knownLanguage);
}
