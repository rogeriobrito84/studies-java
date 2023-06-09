package com.studies.studiesjava.studies.basic.calc.service;

import java.math.BigDecimal;
import java.util.List;

public interface CalculadoraService {

    BigDecimal somar(BigDecimal n1, BigDecimal n2);
    BigDecimal subtrair(BigDecimal n1, BigDecimal n2);
    BigDecimal multiplicar(BigDecimal n1, BigDecimal n2);
    BigDecimal dividir(BigDecimal n1, BigDecimal n2);

    List<String> listarHistorico();

}
