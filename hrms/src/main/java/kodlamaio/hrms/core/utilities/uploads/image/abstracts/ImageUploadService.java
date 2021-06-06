package kodlamaio.hrms.core.utilities.uploads.image.abstracts;

import kodlamaio.hrms.core.utilities.results.DataResult;
import org.springframework.web.multipart.MultipartFile;

public interface ImageUploadService {
    DataResult<?> savePhoto(MultipartFile file);
}
