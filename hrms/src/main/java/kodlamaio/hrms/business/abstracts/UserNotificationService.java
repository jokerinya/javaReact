package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.entities.concretes.User;

public interface UserNotificationService {
    boolean sendActivationLink(User user);
}
