package com.studies.studiesjava.service.impl;

import com.studies.studiesjava.api.dto.ReportDTO;
import com.studies.studiesjava.enums.ParamRelTarifaEnum;
import com.studies.studiesjava.enums.ReportMSGEnum;
import com.studies.studiesjava.enums.ReportTypeEnum;
import com.studies.studiesjava.exception.BusinessException;
import com.studies.studiesjava.jasper.ReportJasper;
import com.studies.studiesjava.repository.RelatorioRepository;
import com.studies.studiesjava.repository.entity.RelatorioEntity;
import com.studies.studiesjava.repository.entity.TarifaEntity;
import com.studies.studiesjava.service.RelatorioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RelatorioServiceImpl implements RelatorioService {
    public static final String PREFIX_NOME_ARQUIVO = "consulta-tarifa";

    private final RelatorioRepository repository;

    @Override
    public ReportDTO gerarRelatorio(ReportTypeEnum tipo) {
        try {
            ReportJasper reportService = tipo.getService();
            RelatorioEntity relatorio = repository.getRelatorio();
            Map<String, BigDecimal> mapTotais = getTotaisForma(relatorio.getTarifaDTOS());
            String imagem = Objects.requireNonNull(
                    getClass().getResource(PASTA_RELATORIO)).toString().concat(NOME_IMAGEM);

            Map<String, Object> params = criarParametros(relatorio, mapTotais, imagem);

            return reportService.reportGenerate(tipo, PASTA_RELATORIO.concat(NOME_JASPER), params, relatorio.getTarifaDTOS(),
                    PREFIX_NOME_ARQUIVO, relatorio.getDataConsulta().replaceAll("/", "-"),
                    relatorio.getCodigoEmpresa());
        } catch (Exception e) {
            throw new BusinessException(e, ReportMSGEnum.ERROR_GENERATE_REPORT);
        }
    }

    private Map<String, Object> criarParametros(RelatorioEntity relatorio, Map<String, BigDecimal> mapTotais, String imagem) {
        Map<String, Object> params = new HashMap<>();
        params.put(ParamRelTarifaEnum.CODIGO_EMPRESA.name(), relatorio.getCodigoEmpresa());
        params.put(ParamRelTarifaEnum.NOME_EMPRESA.name(), relatorio.getNomeEmpresa());
        params.put(ParamRelTarifaEnum.CPF_CNPJ.name(), relatorio.getCpfCnpj());
        params.put(ParamRelTarifaEnum.DATA_CONSULTA.name(), relatorio.getDataConsulta());
        params.put(ParamRelTarifaEnum.DATA_GERACAO.name(), relatorio.getDataGeracao());
        params.put(ParamRelTarifaEnum.IMAGEM.name(), imagem);
        params.put(ParamRelTarifaEnum.MAP_TOTAIS.name(), mapTotais);
        return params;
    }

    private Map<String, BigDecimal> getTotaisForma(List<TarifaEntity> tarifas) {
        return tarifas.stream().collect(Collectors.groupingBy(
                TarifaEntity::getDescricaoTarifa,
                Collectors.mapping(
                        TarifaEntity::getValorTarifa,
                        Collectors.reducing(BigDecimal.ZERO, BigDecimal::add)
                )));
    }
}
