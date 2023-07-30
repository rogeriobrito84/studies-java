package com.studies.studiesjava.enums;

public enum UtilMSGEnum implements MsgEnum {
    ORIGIN_FOLDER_NOT_EXIST("Pasta de origem informada não existe."),
    DESTINY_FOLDER_NOT_EXIST("Pasta de destino informada não existe."),
    OLD_NAME_NOT_NULL("Informe o nome que irá será substituído."),
    NEW_NAME_NOT_NULL("Informe o novo nome do arquivo."),
    ERROR_RENAME_FILE("Erro ao tentar renomear arquivo.");
    final String message;

    UtilMSGEnum(String message) {
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
