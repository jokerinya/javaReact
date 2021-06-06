package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.JobSeekerService;
import kodlamaio.hrms.business.abstracts.KnownLanguageService;
import kodlamaio.hrms.business.abstracts.LanguageService;
import kodlamaio.hrms.business.validators.concretes.KnownLanguageCredentialsCheckManager;
import kodlamaio.hrms.core.utilities.results.*;
import kodlamaio.hrms.dataAccess.abstracts.KnownLanguageDao;
import kodlamaio.hrms.entities.concretes.JobSeeker;
import kodlamaio.hrms.entities.concretes.KnownLanguage;
import kodlamaio.hrms.entities.concretes.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
public class KnownLanguageManager implements KnownLanguageService {
    private KnownLanguageDao knownLanguageDao;
    private LanguageService languageService;
    private JobSeekerService jobSeekerService;

    @Autowired
    public KnownLanguageManager(KnownLanguageDao knownLanguageDao,
                                LanguageService languageService, JobSeekerService jobSeekerService) {
        this.knownLanguageDao = knownLanguageDao;
        this.languageService = languageService;
        this.jobSeekerService = jobSeekerService;
    }

    @Override
    public DataResult<List<KnownLanguage>> getByJobSeekerId(int userId) {
        return new SuccessDataResult<>(this.knownLanguageDao.getByJobSeeker_UserId(userId));
    }

    @Override
    public Result add(int userId, KnownLanguage knownLanguage) {
        // Set Joobseeker
        JobSeeker jobSeeker = this.jobSeekerService.getById(userId).getData();
        if (jobSeeker == null){
            return new ErrorResult("User can't be found!");
        }
        knownLanguage.setJobSeeker(jobSeeker);
        // Check validity
        var validation = KnownLanguageCredentialsCheckManager.checkValid(knownLanguage);
        if (validation != null){
            return validation;
        }
        // Language name check and setting
        String languageName = knownLanguage.getLanguage().getLanguageName().toLowerCase(Locale.ROOT);
        Language language = this.languageService.getByLanguageNameIfNotCreate(languageName);
        knownLanguage.setLanguage(language);
        // Save to db
        this.knownLanguageDao.save(knownLanguage);
        return new SuccessResult("Known Language has been added.");
    }
}
