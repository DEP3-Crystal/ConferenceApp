package al.crystal.conferenceApp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IllegalException extends IllegalArgumentException {

    public IllegalException() {
    }

    public IllegalException(String message) {
        super(message);
    }
}
