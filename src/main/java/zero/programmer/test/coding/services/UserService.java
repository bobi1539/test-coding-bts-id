package zero.programmer.test.coding.services;

import zero.programmer.test.coding.entitites.User;
import zero.programmer.test.coding.models.LoginRequest;
import zero.programmer.test.coding.models.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse createUser(User user);

    List<User> getAllUser();

    UserResponse signIn(LoginRequest loginRequest);
}
