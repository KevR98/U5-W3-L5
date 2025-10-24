package kevinramil.U5_W3_L5.Services;

import kevinramil.U5_W3_L5.Entities.User;
import kevinramil.U5_W3_L5.Enum.UserType;
import kevinramil.U5_W3_L5.Exceptions.BadRequestException;
import kevinramil.U5_W3_L5.Exceptions.NotFoundException;
import kevinramil.U5_W3_L5.Payload.NewUserDTO;
import kevinramil.U5_W3_L5.Repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User findById(UUID id) {
        Optional<User> found = userRepository.findById(id);
        if (found.isPresent()) {
            return found.get();
        } else {
            throw new NotFoundException(id);
        }
    }

    public User findByEmail(String email) {
        Optional<User> found = userRepository.findByEmail(email);
        if (found.isPresent()) {
            return found.get();
        } else {
            throw new NotFoundException(email);
        }
    }

    public User saveDipendente(NewUserDTO payload) {
        if (userRepository.existsByEmail(payload.email())) {
            throw new BadRequestException("l'email " + payload.email() + " è già in uso");
        }
        UserType role;
        if (payload.role().equals("u")) {
            role = UserType.NORMALE_UTENTE;
        } else if (payload.role().equals("o")) {
            role = UserType.ORGANIZZATORE;
        } else {
            throw new BadRequestException("inserire u per utente, oppure o per organizzatore");
        }
        User newUser = new User(role, payload.name(), payload.surname(), payload.email(), passwordEncoder.encode(payload.password()));
        userRepository.save(newUser);
        System.out.println("utente salvato");
        return newUser;
    }


}
