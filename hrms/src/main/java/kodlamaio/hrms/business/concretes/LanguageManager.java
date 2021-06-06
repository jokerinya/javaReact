package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.LanguageService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.dataAccess.abstracts.LanguageDao;
import kodlamaio.hrms.entities.concretes.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LanguageManager implements LanguageService {
    private LanguageDao languageDao;
    @Autowired
    public LanguageManager(LanguageDao languageDao) {
        this.languageDao = languageDao;
    }

    @Override
    public DataResult<List<Language>> getAll() {
        return new SuccessDataResult<>(this.languageDao.findAll(), "All Languages");
    }

    @Override
    public Language getByLanguageNameIfNotCreate(String name) {
        Language language = this.languageDao.getByLanguageName(name);
        if (language != null){
            return language;
        }
        // Means a new language
        language = new Language();
        language.setLanguageName(name);
        this.languageDao.save(language);
        // renew the query
        return this.languageDao.getByLanguageName(name);
    }
}
