package com.studies.studiesjava.api;

import com.studies.studiesjava.enums.ReportTypeEnum;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = RelatorioAPI.TAG, description = "Operações de Relatórios")
@CrossOrigin(origins = "*")
public interface RelatorioAPI {
    String TAG = "Relatórios";

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Relatório gerado com sucesso.",
                    content = @Content(schema = @Schema(implementation = byte[].class)))
    })
    @Operation(
            tags = RelatorioAPI.TAG,
            summary = "Relatório de tarifas",
            description = "Endpoint que gera um relatório de tarifas."
    )
    @GetMapping("/tarifas")
    ResponseEntity<byte[]> relatorioTarifa(
            @ApiParam(value = "Tipo do relatório que será gerado")
            @RequestParam(defaultValue = "PDF") ReportTypeEnum tipo
    );
}
