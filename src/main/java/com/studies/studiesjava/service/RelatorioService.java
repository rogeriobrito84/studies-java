package com.studies.studiesjava.service;

import com.studies.studiesjava.api.dto.ReportDTO;
import com.studies.studiesjava.enums.ReportTypeEnum;

public interface RelatorioService {
    String NOME_JASPER = "/studies-relatorio.jrxml";
    String NOME_IMAGEM = "/imagem_relatorio.png";
    String PASTA_RELATORIO = "/relatorio/tarifa";

    ReportDTO gerarRelatorio(ReportTypeEnum tipo);
}
