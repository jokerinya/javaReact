package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.entities.abstracts.User;

public interface UserNotificationService {
    boolean sendActivationLink(User user);
}
