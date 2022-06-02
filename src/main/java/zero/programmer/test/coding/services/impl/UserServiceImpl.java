package zero.programmer.test.coding.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zero.programmer.test.coding.entitites.User;
import zero.programmer.test.coding.models.LoginRequest;
import zero.programmer.test.coding.models.UserResponse;
import zero.programmer.test.coding.repositories.UserRepository;
import zero.programmer.test.coding.services.UserService;

import javax.transaction.Transactional;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@Transactional
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
        response.setToken(getToken());
        return response;
    }

    @Override
    public List<User> getAllUser() {
        List<User> listUser = userRepository.findAll();
        if (listUser.isEmpty()){
            return null;
        }
        return listUser;

    }

    @Override
    public UserResponse signIn(LoginRequest loginRequest) {
        Optional<User> user = userRepository.findByEmail(loginRequest.getEmail());
        if(user.isPresent()){
            if(user.get().getPassword().equals(loginRequest.getPassword())){
                UserResponse userResponse = new UserResponse();
                userResponse.setEmail(user.get().getEmail());
                userResponse.setToken(getToken());
                userResponse.setUsername(user.get().getUsername());
                return userResponse;
            }
            return null;
        }
        return null;
    }

    private boolean userIsExists(String email){
        return userRepository.findByEmail(email).isPresent();
    }

    private String getToken() {
        // create a string of all characters
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        // create random string builder
        StringBuilder sb = new StringBuilder();

        // create an object of Random class
        Random random = new Random();

        // specify length of random string
        int length = 50;

        for(int i = 0; i < length; i++) {

            // generate random index number
            int index = random.nextInt(alphabet.length());

            // get character specified by index
            // from the string
            char randomChar = alphabet.charAt(index);

            // append the character to string builder
            sb.append(randomChar);
        }

        String randomString = sb.toString();
        return randomString;


    }
}
