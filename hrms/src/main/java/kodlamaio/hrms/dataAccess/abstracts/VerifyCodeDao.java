package kodlamaio.hrms.dataAccess.abstracts;

import kodlamaio.hrms.entities.concretes.VerifyCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerifyCodeDao extends JpaRepository<VerifyCode, Integer> {
    VerifyCode getByUserId(Integer id);
}
