package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.PositionService;
import kodlamaio.hrms.core.utilities.results.*;
import kodlamaio.hrms.dataAccess.abstracts.PositionDao;
import kodlamaio.hrms.entities.concretes.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

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
    public Position getByPositionNameIfNotCreate(String positionName) {
        positionName = positionName.toLowerCase(Locale.ROOT);
        Position position = this.positionDao.getByPositionName(positionName);
        if (position != null){
            return position;
        }
        // Means a new position
        position = new Position();
        position.setPositionName(positionName);
        // Save to db
        this.positionDao.save(position);
        // refresh query to get all obj
        return this.positionDao.getByPositionName(positionName);
    }

    @Override
    public Result add(Position position) {
        String jobName = position.getPositionName().toLowerCase(Locale.ROOT);
        if (this.positionDao.getByPositionName(jobName) != null) {
            return new ErrorResult("Position has been entered before!");
        }
        this.positionDao.save(position);
        return new SuccessResult("Position has been saved to DB");
    }
}
