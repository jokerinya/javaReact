package kodlamaio.hrms.business.validators.concretes;

import kodlamaio.hrms.business.validators.abstracts.BaseValidator;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.KnownLanguage;

public class KnownLanguageCredentialsCheckManager extends BaseValidator {
    public static Result checkValid(KnownLanguage knownLanguage){
        if (!requiredString(knownLanguage.getLanguage().getLanguageName())){
            return new ErrorResult("Language name cannot be empty");
        }
        int level = knownLanguage.getLanguageLevel();
        if (!requiredInteger(level) || (level>5 || level<1)){
            return new ErrorResult("Language level must be between 1-5");
        }
        // means valid
        return null;
    }
}
