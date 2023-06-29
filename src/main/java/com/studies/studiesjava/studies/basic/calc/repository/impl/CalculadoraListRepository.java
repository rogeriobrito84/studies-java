package com.studies.studiesjava.studies.basic.calc.repository.impl;

import com.studies.studiesjava.studies.basic.calc.enums.Operacoes;
import com.studies.studiesjava.studies.basic.calc.repository.CalculadoraRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
//implementacao do contrato
public class CalculadoraListRepository implements CalculadoraRepository {

    private static List<String> bancoHistorico = new ArrayList<>();
    @Override
    public boolean salvarHistorico(BigDecimal n1, BigDecimal n2, Operacoes oper, BigDecimal resultado) {
        String data = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/mm/yyyy hh:mm:ss"));
        String historico = String.format("Operação: %s, Valor 1: %s, Valor 2: %s, Resultado: %s, Data: %s",
                oper.name(), n1, n2, resultado, data);
        bancoHistorico.add(historico);
        return true;
    }

    @Override
    public List<String> listarHistorico() {
        return bancoHistorico;
    }

    @Override
    public List<String> listarHistorico(Operacoes oper) {
        return bancoHistorico.stream().filter(historicoTemp -> historicoTemp.contains(oper.name()))
                .collect(Collectors.toList());
    }
}
