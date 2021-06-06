package kodlamaio.hrms.business.adapters;

import kodlamaio.hrms.business.validators.abstracts.JobSeekerCheckService;
import kodlamaio.hrms.entities.concretes.JobSeeker;
import kodlamaio.hrms.mernis.ROIKPSPublicSoap;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class MernisJobSeekerCheckAdapter implements JobSeekerCheckService {
    @Override
    public boolean checkIfRealPerson(JobSeeker jobSeeker) {
        long nationalityId = Long.parseLong(jobSeeker.getNationalIdentityNo()); // long
        String firstName = jobSeeker.getFirstName().toUpperCase(new Locale("tr", "TR")); // local TR
        String lastName = jobSeeker.getLastName().toUpperCase(new Locale("tr", "TR")); // local TR
        int yearOfBirth = jobSeeker.getYearOfBirth(); // int

        ROIKPSPublicSoap kpsSoap = new ROIKPSPublicSoap();

        try{
            return kpsSoap.TCKimlikNoDogrula(nationalityId, firstName, lastName, yearOfBirth);
        } catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
}
