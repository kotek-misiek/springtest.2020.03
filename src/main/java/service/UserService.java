package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import repository.UserRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static java.util.Objects.nonNull;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private Map<String, UserDetails> tokenMap = new HashMap<>();

    private String generateToken() {
        return UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
    }

    public void logout(String token) {
        tokenMap.remove(token);
    }

    public Optional<String> login(String userName, String password) {
        UserDetails userDetails = userRepository.findByUserNameAndPassword(userName, password);
        if (nonNull(userDetails)) {
            String token = generateToken();
            while (tokenMap.containsKey(token)) {
                token = generateToken();
            }
            tokenMap.put(token, findByUsername(userDetails.getUsername()).get());
            return Optional.of(token);
        }
        return Optional.empty();
    }

    public Optional<UserDetails> findByUsername(String userName) {
        UserDetails user = userRepository.findByUserName(userName);
        if (nonNull(user)) {
            return Optional.of(user);
        }
        return Optional.empty();
    }

    public Optional<UserDetails> findByToken(String token) {
        return tokenMap.containsKey(token)
                ? Optional.of(tokenMap.get(token))
                : Optional.empty();
    }
}
