package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.entities.concretes.JobSeeker;

public interface JobSeekerCheckService {
    public boolean checkIfRealPerson(JobSeeker jobSeeker);
}
