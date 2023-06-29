package com.studies.studiesjava.exception;

import com.studies.studiesjava.api.dto.ErrorDTO;
import com.studies.studiesjava.api.dto.MsgDTO;
import com.studies.studiesjava.enums.ErrorEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.SystemException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class HandlerException {

    private static final ZoneId FUSO = ZoneId.of(ZoneId.SHORT_IDS.get("BET"));

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorDTO> handlerNotValid(MethodArgumentNotValidException e, HttpServletRequest request) {
        List<MsgDTO> msgDTOs = e.getBindingResult().getFieldErrors().stream()
                .map(error -> Objects.requireNonNull(
                        MsgDTO.builder()
                                .code(codeFormat(Objects.requireNonNull(error.getCodes())[1]))
                                .message(error.getDefaultMessage()).build()
                )).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                ErrorDTO.builder()
                        .path(request.getRequestURL().toString())
                        .timestamp(LocalDateTime.now(FUSO))
                        .messages(msgDTOs)
                        .build()
        );
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorDTO> handlerBusiness(BusinessException e, HttpServletRequest request) {
        log.error(ErrorEnum.EXCEPTION_BUSINESS_RULE.getMessage(), e);

        return ResponseEntity.status(e.getStatus()).body(
                ErrorDTO.builder()
                        .path(request.getRequestURL().toString())
                        .timestamp(LocalDateTime.now(FUSO))
                        .status(e.getStatus())
                        .messages(Arrays.stream(e.getErrors()).map(msg -> new MsgDTO(msg.getCode(),
                                        msg.getMessage()))
                                .collect(Collectors.toList()))
                        .build()
        );
    }

    @ExceptionHandler(Throwable.class)
    public final ResponseEntity<ErrorDTO> handlerThrowable(Throwable e, HttpServletRequest request) {
        log.error(ErrorEnum.EXCEPTION_SYSTEM.getMessage(), e);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                ErrorDTO.builder()
                        .path(request.getRequestURL().toString())
                        .timestamp(LocalDateTime.now(FUSO))
                        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .messages(List.of(
                                new MsgDTO(ErrorEnum.EXCEPTION_UNEXPECTED.getCode(),
                                        ErrorEnum.EXCEPTION_UNEXPECTED.getMessage())
                        ))
                        .build()
        );
    }

    @ExceptionHandler(SystemException.class)
    public final ResponseEntity<ErrorDTO> handlerSystem(SystemException e, HttpServletRequest request) {
        log.error(ErrorEnum.EXCEPTION_SYSTEM_EXCEPTION_CAPTURED.getMessage(), e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                ErrorDTO.builder()
                        .path(request.getRequestURL().toString())
                        .timestamp(LocalDateTime.now(FUSO))
                        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .messages(List.of(
                                new MsgDTO(ErrorEnum.EXCEPTION_UNEXPECTED.getCode(),
                                        ErrorEnum.EXCEPTION_UNEXPECTED.getMessage())
                        ))
                        .build()
        );
    }

    private String codeFormat(String codeJavax) {
        return codeJavax.replaceAll("([a-z])([A-Z])", "$1_$2").replace(".", "_").toUpperCase();
    }
}
