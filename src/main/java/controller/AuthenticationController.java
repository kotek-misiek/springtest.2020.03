package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.UserService;

import java.util.Optional;

import static java.util.Objects.nonNull;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.OK;

@RestController
public class AuthenticationController {
    @Autowired
    private UserService userService;

    @PostMapping(value = "/noauth/api/token", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> getToken(@RequestParam(name = "username") String userName, @RequestParam String password) {
        Optional<String> optionalToken = userService.login(userName, password);
        return optionalToken.isPresent()
            ? new ResponseEntity<>(optionalToken.get(), OK)
            : new ResponseEntity<>("no token found", FORBIDDEN);
    }

    @GetMapping("/api/sort/logout")
    public void logOut(@RequestHeader(required = false) String authorization) {
        if (nonNull(authorization)) {
            userService.logout(authorization.replaceAll("Bearer", "").trim());
        }
    }
}
