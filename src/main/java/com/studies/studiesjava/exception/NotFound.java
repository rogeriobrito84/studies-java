package com.studies.studiesjava.exception;

import com.studies.studiesjava.enums.MsgEnum;
import org.springframework.http.HttpStatus;

public class NotFound extends BusinessException {
    public NotFound(MsgEnum errorsEnum) {
        super(errorsEnum);
    }

    public NotFound(Throwable throwable, MsgEnum errorsEnum) {
        super(throwable, errorsEnum);
    }

    @Override
    public int getStatus() {
        return HttpStatus.NOT_FOUND.value();
    }
}
