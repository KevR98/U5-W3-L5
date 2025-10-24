package kevinramil.U5_W3_L5.Payload;

import java.time.LocalDateTime;

public record ErrorsDTO(
        String message,
        LocalDateTime date) {
}
