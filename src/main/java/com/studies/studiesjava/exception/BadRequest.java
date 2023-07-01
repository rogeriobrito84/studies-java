package com.studies.studiesjava.exception;

import org.springframework.http.HttpStatus;

public class BadRequest extends BusinessException {
    public BadRequest(MsgDefault errorsEnum) {
        super(errorsEnum);
    }

    @Override
    public int getStatus() {
        return HttpStatus.BAD_REQUEST.value();
    }
}
