package kodlamaio.hrms.dataAccess.abstracts;

import kodlamaio.hrms.entities.concretes.KnownLanguage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KnownLanguageDao extends JpaRepository<KnownLanguage, Integer> {
    List<KnownLanguage> getByJobSeeker_UserId(int userId);
}
