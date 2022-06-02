package zero.programmer.test.coding.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import zero.programmer.test.coding.entitites.User;
import zero.programmer.test.coding.models.UserResponse;
import zero.programmer.test.coding.repositories.UserRepository;
import zero.programmer.test.coding.services.UserService;

import java.nio.charset.Charset;
import java.util.Random;

public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private BCryptPasswordEncoder encoder;

    @Override
    public UserResponse createUser(User user) {
        if(userIsExists(user.getEmail())){
            return null;
        }
//        String encodedPassword = encoder.encode(user.getPassword());
//        user.setPassword(encodedPassword);

        userRepository.save(user);

        UserResponse response = new UserResponse();
        response.setEmail(user.getEmail());
        response.setUsername(user.getUsername());
        response.setToken("ABC");
        return response;
    }

    private boolean userIsExists(String email){
        return userRepository.findByEmail(email).isPresent();
    }

    private String getToken() {
        byte[] array = new byte[7]; // length is bounded by 7
        new Random().nextBytes(array);
        String generatedString = new String(array, Charset.forName("UTF-8"));

        return generatedString;
    }
}
