package hello.exception.exhandler.advice;

import hello.exception.custom.UserException;
import hello.exception.exhandler.ErrorResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResult handleIllegalArgumentException(IllegalArgumentException e) {
        log.error("handle IllegalArgumentException", e);
        return new ErrorResult(
                HttpStatus.BAD_REQUEST.toString(), e.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResult> handleUserException(UserException e) {
        log.error("handle UserException", e);
        ErrorResult result = new ErrorResult(HttpStatus.BAD_REQUEST.toString(), e.getMessage());
        return ResponseEntity.badRequest()
                .body(result);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResult handleException(Exception e) {
        log.error("handle Exception", e);
        return new ErrorResult(
                HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getMessage());
    }

}
