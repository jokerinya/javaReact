package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.entities.concretes.School;

public interface SchoolService {
    School getBySchoolNameIfNotCreate(String schoolName);
}
