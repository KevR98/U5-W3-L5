package kevinramil.U5_W3_L5.Controllers;

import kevinramil.U5_W3_L5.Payload.LoginDTO;
import kevinramil.U5_W3_L5.Payload.LoginResponseDTO;
import kevinramil.U5_W3_L5.Services.AuthService;
import kevinramil.U5_W3_L5.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthService authorizeService;

    @PostMapping("login")
    public LoginResponseDTO login(@RequestBody LoginDTO body) {
        return new LoginResponseDTO(authorizeService.checkAndGenerate(body));
    }

    @PostMapping("register")
    @ResponseStatus(HttpStatus.CREATED)
    public Utente postDipendente(@RequestBody @Validated NewUtPayload body, BindingResult valRes) {
        if (valRes.hasErrors()) {
            List<String> errList = new ArrayList<>();
            for (int i = 0; i < valRes.getFieldErrors().size(); i++) {
                errList.add(valRes.getFieldErrors().get(i).getDefaultMessage());
            }
            throw new ValidazioneFallitaExeption(errList);
        }
        return utServ.saveDipendente(body);

    }
}