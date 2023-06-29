package com.studies.studiesjava.api.controller;

import com.studies.studiesjava.api.RelatorioAPI;
import com.studies.studiesjava.api.dto.ReportDTO;
import com.studies.studiesjava.enums.ReportTypeEnum;
import com.studies.studiesjava.service.RelatorioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("v1")
public class RelatorioController implements RelatorioAPI {
    final RelatorioService service;

    @Override
    public ResponseEntity<byte[]> relatorioTarifa(ReportTypeEnum tipo) {
        ReportDTO dto = service.gerarRelatorio(tipo);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", dto.getName());

        return ResponseEntity.ok()
                .headers(headers)
                .body(dto.getData());
    }
}