package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.PositionService;
import kodlamaio.hrms.core.utilities.results.*;
import kodlamaio.hrms.dataAccess.abstracts.PositionDao;
import kodlamaio.hrms.entities.concretes.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionManager implements PositionService {

    PositionDao positionDao;

    @Autowired
    public PositionManager(PositionDao positionDao) {
        this.positionDao = positionDao;
    }

    @Override
    public DataResult<List<Position>> getAll() {
        return new SuccessDataResult<>(this.positionDao.findAll(), "Positions fetched.");
    }

    @Override
    public Result add(Position position) {
        String jobName = position.getPositionName();
        if (positionDao.getByPositionName(jobName) != null) {
            return new ErrorResult("Position has been entered before!");
        }
        this.positionDao.save(position);
        return new SuccessResult("Position has been saved to DB");
    }
}
