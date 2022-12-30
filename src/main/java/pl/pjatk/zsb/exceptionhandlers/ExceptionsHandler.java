package pl.pjatk.zsb.exceptionhandlers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.net.ConnectException;

@RestControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(HttpClientErrorException.NotFound.class)
    public ResponseEntity<String> notFound(HttpClientErrorException.NotFound exception){
        return ResponseEntity.status(404).body("Not found" + exception.getLocalizedMessage());
    }

    @ExceptionHandler(HttpClientErrorException.BadRequest.class)
    public ResponseEntity<String> badRequest(HttpClientErrorException.BadRequest exception){
        return ResponseEntity.status(400).body("Bad request" + exception.getLocalizedMessage());
    }

    @ExceptionHandler(HttpServerErrorException.InternalServerError.class)
    public ResponseEntity<String> internalError(HttpServerErrorException.InternalServerError exception){
        return ResponseEntity.status(502).body("Internal serer error" + exception.getLocalizedMessage());
    }

    @ExceptionHandler(ConnectException.class)
    public ResponseEntity<String> connectException(ConnectException exception){
        return ResponseEntity.status(504).body("Connection exception" + exception.getLocalizedMessage());
    }
}
