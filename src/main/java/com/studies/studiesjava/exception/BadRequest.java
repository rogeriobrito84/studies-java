package com.studies.studiesjava.exception;

import com.studies.studiesjava.enums.MsgEnum;
import org.springframework.http.HttpStatus;

public class BadRequest extends BusinessException {
    public BadRequest(MsgEnum errorsEnum) {
        super(errorsEnum);
    }

    @Override
    public int getStatus() {
        return HttpStatus.BAD_REQUEST.value();
    }
}
