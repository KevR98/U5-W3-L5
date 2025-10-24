package kevinramil.U5_W3_L5.Exceptions;

import java.util.List;

public class ValidationException extends RuntimeException {
    private List<String> errorsMessages;

    public ValidationException(List<String> errorsMessages) {
        super("Ci sono stati errori di validazione");
        this.errorsMessages = errorsMessages;
    }
}
