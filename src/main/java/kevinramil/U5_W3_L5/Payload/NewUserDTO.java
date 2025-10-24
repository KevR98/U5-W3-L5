package kevinramil.U5_W3_L5.Payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record NewUserDTO(
        @NotBlank(message = "Il nome è obbligatorio")
        @Size(min = 2, max = 20, message = "Il nome deve avere una lunghezza compresa tra 2 e 20 caratteri")
        String name,
        @NotBlank(message = "Il cognome è obbligatorio!")
        @Size(min = 2, max = 30, message = "Il cognome deve avere una lunghezza compresa tra 2 e 30 caratteri")
        String surname,
        @NotBlank(message = "L'email è obbligatoria!")
        @Email(message = "L'indirizzo email inserito non è nel formato corretto!")
        String email,
        @Size(min = 2, max = 30, message = "la password deve avere dai 2 ai 30 caratteri")
        String password,
        @NotBlank
        @Size(min = 1, max = 1, message = "i valori concessi sono 'u' (utente) oppure 'o' (organizzatore)")
        String role
) {
}
