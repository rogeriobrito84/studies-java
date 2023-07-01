package com.studies.studiesjava.exception;

import org.springframework.http.HttpStatus;

public class NotFound extends BusinessException {
    public NotFound(MsgDefault errorsEnum) {
        super(errorsEnum);
    }

    public NotFound(Throwable throwable, MsgDefault errorsEnum) {
        super(throwable, errorsEnum);
    }

    @Override
    public int getStatus() {
        return HttpStatus.NOT_FOUND.value();
    }
}
