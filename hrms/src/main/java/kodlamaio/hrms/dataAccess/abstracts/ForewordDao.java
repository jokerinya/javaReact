package kodlamaio.hrms.dataAccess.abstracts;

import kodlamaio.hrms.entities.concretes.Foreword;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ForewordDao extends JpaRepository<Foreword, Integer> {
    List<Foreword> getByJobSeeker_UserId(int userId);
}
