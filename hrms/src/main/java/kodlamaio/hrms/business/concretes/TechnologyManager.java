package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.JobSeekerService;
import kodlamaio.hrms.business.abstracts.TechnologyService;
import kodlamaio.hrms.core.utilities.results.*;
import kodlamaio.hrms.dataAccess.abstracts.TechnologyDao;
import kodlamaio.hrms.entities.concretes.JobSeeker;
import kodlamaio.hrms.entities.concretes.Technology;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TechnologyManager implements TechnologyService {
    private TechnologyDao technologyDao;
    private JobSeekerService jobSeekerService;


    @Autowired
    public TechnologyManager(TechnologyDao technologyDao, JobSeekerService jobSeekerService) {
        this.technologyDao = technologyDao;
        this.jobSeekerService = jobSeekerService;
    }

    @Override
    public DataResult<List<Technology>> getByJobSeekerId(int jobSeekerId) {
        JobSeeker jobSeeker = this.jobSeekerService.getById(jobSeekerId).getData();
        if (jobSeeker == null){
            return new ErrorDataResult<>(null, "User can't be found");
        }
        return new SuccessDataResult<>
                (this.technologyDao.getTechnologiesByJobSeekersIs(jobSeeker), "Technologies");
    }

    @Override
    public Result add(Technology technology) {
        String technologyName = technology.getTechnologyName().toLowerCase(Locale.ROOT);
        technology.setTechnologyName(technologyName);
        // check there is any record
        Technology technologyFromDB = this.technologyDao.getByTechnologyName(technologyName);
        if (technologyFromDB != null){
            return new ErrorResult("Technology has already been recorded.");
        }
        this.technologyDao.save(technology);
        return new SuccessResult("Technology added.");
    }

    @Override
    public Result addToJobSeeker(int jobSeekerId, Technology newTechnology) {
        // check user
        JobSeeker jobSeeker = this.jobSeekerService.getById(jobSeekerId).getData();
        if (jobSeeker == null){
            return new ErrorResult("User can't be found.");
        }
        // check technology
        String newTechnologyName = newTechnology.getTechnologyName().toLowerCase(Locale.ROOT);
        Technology oldTechnology = this.technologyDao.getByTechnologyName(newTechnologyName);
        // New technology
        if (oldTechnology == null){
            // update jobSeekers
            newTechnology.setJobSeekers(new HashSet<>());
            newTechnology.getJobSeekers().add(jobSeeker);
            // add technology
            jobSeeker.getTechnologies().add(newTechnology);

            this.technologyDao.save(newTechnology);
            return new SuccessResult("Technology added to user.");
        }
        // Old technology
        Set<JobSeeker> oldTechnologyJobSeekers = oldTechnology.getJobSeekers();
        if (oldTechnologyJobSeekers == null){
            oldTechnologyJobSeekers = new HashSet<>();
        }
        // update jobSeekers reference
        oldTechnologyJobSeekers.add(jobSeeker);
        // update jobSeeker reference
        jobSeeker.getTechnologies().add(oldTechnology);

        this.technologyDao.save(oldTechnology);
        return new SuccessResult("Technology added to user's technologies");
    }

    @Override
    public Result removeFromJobSeeker(int jobSeekerId, Technology technology) {
        // check user
        JobSeeker jobSeeker = this.jobSeekerService.getById(jobSeekerId).getData();
        if (jobSeeker == null){
            return new ErrorResult("User can't be found.");
        }
        // get technology obj from db
        technology = this.technologyDao.getOne(technology.getTechnologyId());
        // Set JobSeeker's technologies
        Set<Technology> jobSeekerTechnologies = jobSeeker.getTechnologies();
        jobSeekerTechnologies.remove(technology);
        jobSeeker.setTechnologies(jobSeekerTechnologies);
        // Set Technology's job seekers
        Set<JobSeeker> technologyJobSeekers = technology.getJobSeekers();
        technologyJobSeekers.remove(jobSeeker);
        technology.setJobSeekers(technologyJobSeekers);
        // save to db
        this.technologyDao.save(technology);
        return new SuccessResult("Technology has been removed from the user!");
    }
}
