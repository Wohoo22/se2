package gr6.se2.controller.auth;

import com.fasterxml.jackson.annotation.JsonAlias;
import gr6.se2.service.auth.LoginService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@AllArgsConstructor
public class LoginController {
    private final LoginService loginService;

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody Input body) {
        try {
            final String accessToken = loginService.login(body.username, body.password);
            return ResponseEntity.ok().body(new Output(accessToken));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    public static class Input {
        private String username;
        private String password;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    public static class Output {
        @JsonAlias("access_token")
        private String accessToken;
    }

}
