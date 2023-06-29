package com.studies.studiesjava.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
@AllArgsConstructor
public class RelatorioEntity {
    private String codigoEmpresa;
    private String nomeEmpresa;
    private String cpfCnpj;
    private String dataConsulta;
    private String dataGeracao;
    private Long totalRegistros;
    private int totalPaginas;
    private List<TarifaEntity> tarifaDTOS;
}
