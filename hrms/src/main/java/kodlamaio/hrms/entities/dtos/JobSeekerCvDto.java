package kodlamaio.hrms.entities.dtos;

import kodlamaio.hrms.entities.concretes.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class JobSeekerCvDto {

    private JobSeeker jobSeeker;
    private Set<Foreword> forewords;
    private Set<JobExperience> jobExperiences;
    private Set<GraduatedSchool> graduatedSchools;
    private Set<Technology> technologies;
    private Set<KnownLanguage> knownLanguages;
    private SocialMediaAddress socialMediaAddress;
}
