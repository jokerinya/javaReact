package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.SchoolService;
import kodlamaio.hrms.dataAccess.abstracts.SchoolDao;
import kodlamaio.hrms.entities.concretes.School;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SchoolManager implements SchoolService {

    private SchoolDao schoolDao;

    @Autowired
    public SchoolManager(SchoolDao schoolDao) {
        this.schoolDao = schoolDao;
    }

    @Override
    public School getBySchoolNameIfNotCreate(String schoolName) {
        School school = this.schoolDao.getBySchoolName(schoolName);
        if (school != null){
            return school;
        }
        // Means a new school
        school = new School();
        school.setSchoolName(schoolName);
        // Save to db
        this.schoolDao.save(school);
        // refresh query
        return this.schoolDao.getBySchoolName(schoolName);
    }
}
