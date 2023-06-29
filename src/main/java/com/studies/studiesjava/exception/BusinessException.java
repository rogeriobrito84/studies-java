package com.studies.studiesjava.exception;

import com.studies.studiesjava.enums.MsgEnum;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BusinessException extends RuntimeException {
    private final int status = HttpStatus.INTERNAL_SERVER_ERROR.value();
    private final transient MsgEnum[] errors;

    public BusinessException(MsgEnum... errorsEnum) {
        super();
        this.errors = errorsEnum;
    }

    public BusinessException(Throwable throwable, MsgEnum... errorsEnum) {
        super(throwable);
        this.errors = errorsEnum;
    }
}
