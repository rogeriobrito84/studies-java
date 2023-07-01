package com.studies.studiesjava.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BusinessException extends RuntimeException {
    private final int status = HttpStatus.INTERNAL_SERVER_ERROR.value();
    private final transient MsgDefault[] errors;

    public BusinessException(MsgDefault... errorsEnum) {
        super();
        this.errors = errorsEnum;
    }

    public BusinessException(Throwable throwable, MsgDefault... errorsEnum) {
        super(throwable);
        this.errors = errorsEnum;
    }
}
