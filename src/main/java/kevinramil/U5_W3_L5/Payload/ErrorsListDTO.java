package kevinramil.U5_W3_L5.Payload;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorsListDTO(
        String message,
        LocalDateTime timestamp,
        List<String> errorsList) {
}
