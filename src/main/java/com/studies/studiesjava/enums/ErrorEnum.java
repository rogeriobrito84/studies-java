package com.studies.studiesjava.enums;

import com.studies.studiesjava.exception.MsgDefault;

public enum ErrorEnum implements MsgDefault {
    EXCEPTION_SYSTEM("Erro no sistema!"),
    EXCEPTION_SYSTEM_EXCEPTION_CAPTURED("Exceção de sistema capturada!"),
    EXCEPTION_UNEXPECTED("Ops! Ocorreu um erro inesperado! Estamos trabalhando para resolver o mais rápido possível."),
    EXCEPTION_BUSINESS_RULE("Erro em nossas regras de negócio!");

    final String message;

    ErrorEnum(String message) {
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
