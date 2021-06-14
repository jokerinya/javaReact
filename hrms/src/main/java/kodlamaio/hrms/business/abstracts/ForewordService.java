package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Foreword;

import java.util.List;

public interface ForewordService {
    DataResult<List<Foreword>> getByJobSeekerId(int jobSeekerId);
    Result add(int jobSeekerId, Foreword foreword);
    Result delete(int jobSeekerId, Foreword foreword);
    Result update(int jobSeekerId, Foreword foreword);
}
