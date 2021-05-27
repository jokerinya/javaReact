package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.JobSeekerCheckService;
import kodlamaio.hrms.business.abstracts.JobSeekerService;
import kodlamaio.hrms.business.abstracts.UserCredentialsCheckService;
import kodlamaio.hrms.business.abstracts.UserNotificationService;
import kodlamaio.hrms.core.utilities.results.*;
import kodlamaio.hrms.dataAccess.abstracts.JobSeekerDao;
import kodlamaio.hrms.dataAccess.abstracts.VerifyCodeDao;
import kodlamaio.hrms.entities.concretes.JobSeeker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobSeekerManager implements JobSeekerService {
    private JobSeekerDao jobSeekerDao;
    private UserCredentialsCheckService userCredentialsCheckService;
    private UserNotificationService userNotificationService;
    private VerifyCodeDao verifyCodeDao;
    private JobSeekerCheckService jobSeekerCheckService;

    @Autowired
    public JobSeekerManager(JobSeekerDao jobSeekerDao,
                            @Qualifier("jobSeekerCredentialsCheckManager") UserCredentialsCheckService userCredentialsCheckService,
                            UserNotificationService userNotificationService,
                            VerifyCodeDao verifyCodeDao,
                            JobSeekerCheckService jobSeekerCheckService) {
        this.jobSeekerDao = jobSeekerDao;
        this.userCredentialsCheckService = userCredentialsCheckService;
        this.userNotificationService = userNotificationService;
        this.verifyCodeDao = verifyCodeDao;
        this.jobSeekerCheckService = jobSeekerCheckService;
    }

    @Override
    public DataResult<List<JobSeeker>> getAll() {
        return new SuccessDataResult<>(this.jobSeekerDao.findAll(), "Candidates fetched.");
    }

    @Override
    public Result add(JobSeeker jobSeeker) {
        if (userCredentialsCheckService.checkUserCredentials(jobSeeker) instanceof SuccessResult){
            // add to DB, before adding check National Identity Number or Email occurrences
            if (jobSeekerDao.getByEmail(jobSeeker.getEmail()) != null){
                return new ErrorResult("Email is already used. Please check your email address!");
            }
            if (jobSeekerDao.getByNationalIdentityNo(jobSeeker.getNationalIdentityNo()) != null){
                return new ErrorResult("This ID number is already recorded please check it!");
            }
            // Check Mernis Record
            if (!this.jobSeekerCheckService.checkIfRealPerson(jobSeeker)){
                return new ErrorResult("This is not a real person, please check your credentials.");
            }
            // Email Link
            jobSeeker.setEmailIsVerified(false);
            this.jobSeekerDao.save(jobSeeker);
            // Send Notification Link
            this.userNotificationService.sendActivationLink(jobSeeker);

            return new SuccessResult("Person successfully added to DB, please activate by a link.");
        }
        return userCredentialsCheckService.checkUserCredentials(jobSeeker);
    }

}
