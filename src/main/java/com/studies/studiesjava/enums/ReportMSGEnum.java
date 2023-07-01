package com.studies.studiesjava.enums;

import com.studies.studiesjava.exception.MsgDefault;

public enum ReportMSGEnum implements MsgDefault {
    ERROR_GENERATE_REPORT("Erro ao tentar gerar relatório.");
    final String message;

    ReportMSGEnum(String message) {
        this.message = message;
    }

    @Override
    public String getCode() {
        return this.name();
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
