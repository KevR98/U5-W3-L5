package kevinramil.U5_W3_L5.Payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record EventoDTO(
        @NotBlank(message = "la descrizione è obbligatoria")
        @Size(min = 2, max = 30, message = "la descrizione deve avere dai 2 ai 30 caratteri")
        String description,
        @NotBlank(message = "la data deve essere nel formato YYYY-MM-DD")
        @Size(min = 10, message = "la data deve essere nel formato YYYY-MM-DD")
        String date,
        @NotBlank(message = "il luogo è obbligatoria")
        @Size(min = 2, max = 30, message = "il luogo deve avere dai 2 ai 30 caratteri")
        String place,
        @NotNull
        int numMax
) {
}
