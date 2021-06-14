package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.SocialMediaAddress;

public interface SocialMediaAddressService {
    DataResult<SocialMediaAddress> getByJobSeekerId(int userId);
    Result add(int userId, SocialMediaAddress socialMediaAddress);
    Result update(int userId, SocialMediaAddress socialMediaAddress);
    Result delete(int userId);
}
