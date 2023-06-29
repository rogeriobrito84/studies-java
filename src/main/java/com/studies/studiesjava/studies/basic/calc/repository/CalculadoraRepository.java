package com.studies.studiesjava.studies.basic.calc.repository;

import com.studies.studiesjava.studies.basic.calc.enums.Operacoes;

import java.math.BigDecimal;
import java.util.List;
//contrato
public interface CalculadoraRepository {
    boolean salvarHistorico(BigDecimal n1, BigDecimal n2, Operacoes oper, BigDecimal resultado);
    List<String> listarHistorico();
    List<String> listarHistorico(Operacoes oper);
}
