package kodlamaio.hrms.api.concretes;

import kodlamaio.hrms.business.abstracts.SocialMediaAddressService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.SocialMediaAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/socialMediaAddress")
public class SocialMediaAddressesController {
    private SocialMediaAddressService socialMediaAddressService;

    @Autowired
    public SocialMediaAddressesController(SocialMediaAddressService socialMediaAddressService) {
        this.socialMediaAddressService = socialMediaAddressService;
    }

    @GetMapping("/{jobSeekerId}")
    public DataResult<SocialMediaAddress> getByJobSeekerId(@PathVariable int jobSeekerId){
        return this.socialMediaAddressService.getByJobSeekerId(jobSeekerId);
    };

    @PostMapping("/{jobSeekerId}/add/")
    Result add(@PathVariable int jobSeekerId,@RequestBody SocialMediaAddress socialMediaAddress){
        return this.socialMediaAddressService.add(jobSeekerId, socialMediaAddress);
    }

    @PostMapping("/{jobSeekerId}/add/github")
    Result addOnlyGithubAddress(@PathVariable int jobSeekerId,@RequestBody String githubAddress){
        return this.socialMediaAddressService.addOnlyGithubAddress(jobSeekerId, githubAddress);
    };

    @PostMapping("/{jobSeekerId}/add/linkedIn")
    Result addOnlyLinkedInAddress(@PathVariable int jobSeekerId,@RequestBody String linkedInAddress){
        return this.socialMediaAddressService.addOnlyLinkedInAddress(jobSeekerId, linkedInAddress);
    }


}
