package kodlamaio.hrms.api.concretes;

import kodlamaio.hrms.business.abstracts.SocialMediaAddressService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.SocialMediaAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/socialMediaAddress")
@CrossOrigin
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

    @PostMapping("/{jobSeekerId}/add")
    public Result add(@PathVariable int jobSeekerId,@RequestBody SocialMediaAddress socialMediaAddress){
        return this.socialMediaAddressService.add(jobSeekerId, socialMediaAddress);
    }

    @PutMapping("/{jobSeekerId}/update")
    public Result update(@PathVariable int jobSeekerId,@RequestBody SocialMediaAddress socialMediaAddress){
        return this.socialMediaAddressService.update(jobSeekerId, socialMediaAddress);
    }

    @DeleteMapping("/{jobSeekerId}/delete")
    public Result delete(@PathVariable int jobSeekerId){
        return this.socialMediaAddressService.delete(jobSeekerId);
    }



}
