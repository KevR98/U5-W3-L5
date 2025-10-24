package kevinramil.U5_W3_L5.Entities;

import jakarta.persistence.*;
import kevinramil.U5_W3_L5.Enum.UserType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE) // serve per non richiamarlo nel setter
    private UUID id;
    @Enumerated(EnumType.STRING)
    @Column(name = "user_type")
    private UserType userType;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    // LISTA COSTRUTTORI
    public User(UserType userType, String name, String surname, String email, String password) {
        this.userType = userType;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
    }

    // LISTA METODI

    // GETTER/SETTER
    // generati automaticamente con il lombok

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.userType.name()));
    }

    @Override
    public String getUsername() {
        return this.email;
    }
}
