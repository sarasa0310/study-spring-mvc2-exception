package hello.exception.exhandler;

import lombok.Data;

@Data
public class ErrorResult {

    private final String code;
    private final String message;

}
