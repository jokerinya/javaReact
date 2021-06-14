package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.DepartmentService;
import kodlamaio.hrms.business.abstracts.GraduatedSchoolService;
import kodlamaio.hrms.business.abstracts.JobSeekerService;
import kodlamaio.hrms.business.abstracts.SchoolService;
import kodlamaio.hrms.business.validators.concretes.GraduatedSchoolCredentialsCheckManager;
import kodlamaio.hrms.core.utilities.results.*;
import kodlamaio.hrms.dataAccess.abstracts.GraduatedSchoolDao;
import kodlamaio.hrms.entities.concretes.Department;
import kodlamaio.hrms.entities.concretes.GraduatedSchool;
import kodlamaio.hrms.entities.concretes.JobSeeker;
import kodlamaio.hrms.entities.concretes.School;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
public class GraduatedSchoolManager implements GraduatedSchoolService {

    private GraduatedSchoolDao graduatedSchoolDao;
    private SchoolService schoolService;
    private DepartmentService departmentService;
    private JobSeekerService jobSeekerService;

    @Autowired
    public GraduatedSchoolManager(GraduatedSchoolDao graduatedSchoolDao, SchoolService schoolService,
                                  DepartmentService departmentService, JobSeekerService jobSeekerService) {
        this.graduatedSchoolDao = graduatedSchoolDao;
        this.schoolService = schoolService;
        this.departmentService = departmentService;
        this.jobSeekerService = jobSeekerService;
    }

    @Override
    public DataResult<List<GraduatedSchool>> getAll() {
        return new SuccessDataResult<>(this.graduatedSchoolDao.findAll(), "All schools.");
    }

    @Override
    public DataResult<List<GraduatedSchool>> getByJobSeekerId(int userId) {
        return new SuccessDataResult<>(this.graduatedSchoolDao.getByJobSeeker_UserId(userId));
    }

    @Override
    public DataResult<List<GraduatedSchool>> getByJobSeekerIdOrderByGraduationDate(int userId) {
        return new SuccessDataResult<>(this.graduatedSchoolDao.getByJobSeeker_UserIdOrderByGraduationDateDesc(userId));
    }

    @Override
    public Result add(int jobSeekerId, GraduatedSchool graduatedSchool) {
        // Set Joobseeker
        JobSeeker jobSeeker = this.jobSeekerService.getById(jobSeekerId).getData();
        if (jobSeeker == null){
            return new ErrorResult("User can't be found!");
        }
        graduatedSchool.setJobSeeker(jobSeeker);
        // Check Validity
        var validation = GraduatedSchoolCredentialsCheckManager.checkValid(graduatedSchool);
        if (validation != null){
            return validation;
        }

        // School name check
        String graduatedSchoolName = graduatedSchool.getSchool().getSchoolName().toLowerCase(Locale.ROOT);
        School school = this.schoolService.getBySchoolNameIfNotCreate(graduatedSchoolName);
        // Department name and id check
        String graduatedDepartmentName = graduatedSchool.getDepartment().getDepartmentName().toLowerCase(Locale.ROOT);
        Department department = this.departmentService.getByDepartmentNameIfNotCreate(graduatedDepartmentName);
        // Set new Entities
        graduatedSchool.setSchool(school);
        graduatedSchool.setDepartment(department);
        this.graduatedSchoolDao.save(graduatedSchool);
        return new SuccessResult("Education school added.");
    }

    @Override
    public Result delete(int jobSeekerId, GraduatedSchool graduatedSchool) {
        this.graduatedSchoolDao.delete(graduatedSchool);
        return new SuccessResult("Education school deleted");
    }

    @Override
    public Result update(int jobSeekerId, GraduatedSchool graduatedSchool) {
        // get db record
        GraduatedSchool oldGraduatedSchool = this.graduatedSchoolDao.getOne(graduatedSchool.getGraduatedSchoolId());
        // set new fields except jobSeeker and id fields
        // School and Department
        School school = this.schoolService.getBySchoolNameIfNotCreate(graduatedSchool.getSchool().getSchoolName());
        oldGraduatedSchool.setSchool(school);
        Department department = this.departmentService.getByDepartmentNameIfNotCreate(graduatedSchool.getDepartment().getDepartmentName());
        oldGraduatedSchool.setDepartment(department);
        // Dates
        oldGraduatedSchool.setStartDate(graduatedSchool.getStartDate());
        oldGraduatedSchool.setGraduationDate(graduatedSchool.getGraduationDate());
        // save to db
        this.graduatedSchoolDao.save(oldGraduatedSchool);
        return new SuccessResult("Graduated School Updated");
    }
}
