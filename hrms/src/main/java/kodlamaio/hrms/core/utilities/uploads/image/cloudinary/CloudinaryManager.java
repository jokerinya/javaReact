package kodlamaio.hrms.core.utilities.uploads.image.cloudinary;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.uploads.image.abstracts.ImageUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Service
public class CloudinaryManager implements ImageUploadService {
    private Cloudinary cloudinary;

    @Autowired
    public CloudinaryManager(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    @Override
    public DataResult<Map> savePhoto(MultipartFile file) {
        // -> Map = Dictionary -> "image": "url" / "createdAt" : "00.00.0000"
        try {
            Map imageUpload = this.cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            return new SuccessDataResult<Map>(imageUpload);
        } catch (Exception e) {
            return new ErrorDataResult<>(e.getMessage());
        }
    }
}
