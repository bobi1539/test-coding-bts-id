package zero.programmer.test.coding.services;

import zero.programmer.test.coding.entitites.User;
import zero.programmer.test.coding.models.UserResponse;

public interface UserService {
    UserResponse createUser(User user);
}
