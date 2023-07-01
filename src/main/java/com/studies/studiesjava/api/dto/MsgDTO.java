package com.studies.studiesjava.api.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.studies.studiesjava.exception.MsgDefault;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@Getter
@Builder
@ToString
public class MsgDTO implements MsgDefault {
    private String code;
    private String message;
}
