package hello.exception.api;

import hello.exception.custom.UserException;
import hello.exception.exhandler.ErrorResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api2")
public class ApiExceptionV2Controller {

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

    @GetMapping("/members/{id}")
    public MemberDto getMember(@PathVariable String id) {
        if (id.equals("ex")) {
            throw new RuntimeException("잘못된 사용자");
        }
        if (id.equals("bad")) {
            throw new IllegalArgumentException("잘못된 입력값");
        }
        if (id.equals("user-ex")) {
            throw new UserException("사용자 오류");
        }
        return new MemberDto(id, "hello " + id);
    }

}
