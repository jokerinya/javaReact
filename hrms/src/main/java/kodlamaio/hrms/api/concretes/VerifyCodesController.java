package kodlamaio.hrms.api.concretes;

import kodlamaio.hrms.business.abstracts.UserService;
import kodlamaio.hrms.core.utilities.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/verify")
@CrossOrigin
public class VerifyCodesController {

    private UserService userService;

    @Autowired
    public VerifyCodesController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/email/{userId}/{code}")
    public Result verifyEmail(@PathVariable("userId") Integer userId, @PathVariable("code") String code){
        return this.userService.verifyEmailWithCode(userId, code);
    }
}
