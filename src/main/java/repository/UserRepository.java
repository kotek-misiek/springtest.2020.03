package repository;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.security.core.authority.AuthorityUtils.createAuthorityList;

@Service
public class UserRepository {
    private List<UserDetails> userList = new ArrayList<>();
    {
        userList.add(new User("user", "password", true, true, true,true, createAuthorityList("USER")));
        userList.add(new User("moderator", "password", true, true, true,true, createAuthorityList("MODERATOR")));
        userList.add(new User("admin", "password", true, true, true,true, createAuthorityList("USER", "MODERATOR", "ADMIN")));
    }

    public UserDetails findByUserNameAndPassword(String userName, String password) {
        return userList
                .stream()
                .filter(user -> userName.equals(user.getUsername()) && password.equals(user.getPassword()))
                .map(user -> new User(
                        user.getUsername(), user.getPassword(), user.isEnabled(), user.isAccountNonExpired(),
                        user.isCredentialsNonExpired(), user.isAccountNonLocked(), user.getAuthorities()))
                .findFirst()
                .orElse(null);
    }

    public UserDetails findByUserName(String userName) {
        return userList
                .stream()
                .filter(user -> user.getUsername().equals(userName))
                .map(user -> (UserDetails) new User(
                            user.getUsername(), user.getPassword(), user.isEnabled(), user.isAccountNonExpired(),
                            user.isCredentialsNonExpired(), user.isAccountNonLocked(), user.getAuthorities()))
                .findFirst()
                .orElse(null);
    }
}
