package kodlamaio.hrms.dataAccess.abstracts;

import kodlamaio.hrms.entities.concretes.SocialMediaAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocialMediaAddressDao extends JpaRepository<SocialMediaAddress, Integer> {
    SocialMediaAddress getByJobSeeker_UserId(int userid);
}
