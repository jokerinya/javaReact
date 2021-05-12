package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.entities.concretes.Position;

import java.util.List;

public interface PositionService {
    List<Position> getAll();
}
