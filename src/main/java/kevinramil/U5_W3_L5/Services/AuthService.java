package kevinramil.U5_W3_L5.Services;

import kevinramil.U5_W3_L5.Entities.User;
import kevinramil.U5_W3_L5.Exceptions.UnauthorizedException;
import kevinramil.U5_W3_L5.Payload.LoginDTO;
import kevinramil.U5_W3_L5.Security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserService userService;
    @Autowired
    private JWTTools jwtTools;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public String checkAndGenerate(LoginDTO body) {
        User found = userService.findByEmail(body.email());
        if (passwordEncoder.matches(body.password(), found.getPassword())) {
            return jwtTools.createToken(found);
        } else {
            throw new UnauthorizedException("credenziali non corrette");
        }
    }
}
