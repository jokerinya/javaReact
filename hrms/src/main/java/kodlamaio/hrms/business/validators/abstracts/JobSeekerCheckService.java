package kodlamaio.hrms.business.validators.abstracts;

import kodlamaio.hrms.entities.concretes.JobSeeker;

public interface JobSeekerCheckService {
    public boolean checkIfRealPerson(JobSeeker jobSeeker);
}
