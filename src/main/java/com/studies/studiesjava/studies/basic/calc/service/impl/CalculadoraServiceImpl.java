package com.studies.studiesjava.studies.basic.calc.service.impl;

import com.studies.studiesjava.studies.basic.calc.enums.Operacoes;
import com.studies.studiesjava.studies.basic.calc.repository.CalculadoraRepository;
import com.studies.studiesjava.studies.basic.calc.repository.impl.CalculadoraListRepository;
import com.studies.studiesjava.studies.basic.calc.service.CalculadoraService;

import java.lang.invoke.SwitchPoint;
import java.math.BigDecimal;
import java.util.List;

public class CalculadoraServiceImpl implements CalculadoraService {
    CalculadoraRepository repository;

    public CalculadoraServiceImpl(CalculadoraRepository repository) {
        this.repository = repository;
    }

    @Override
    public BigDecimal somar(BigDecimal n1, BigDecimal n2) {
        return calcular(n1, n2, Operacoes.SOMA);
    }

    @Override
    public BigDecimal subtrair(BigDecimal n1, BigDecimal n2) {
        return calcular(n1, n2, Operacoes.SUBTRACAO);
    }

    @Override
    public BigDecimal multiplicar(BigDecimal n1, BigDecimal n2) {
        return calcular(n1, n2, Operacoes.MULTIPLICACAO);
    }

    @Override
    public BigDecimal dividir(BigDecimal n1, BigDecimal n2) {
        return calcular(n1, n2, Operacoes.DIVISAO);
    }

    private BigDecimal calcular(BigDecimal n1, BigDecimal n2, Operacoes oper) {
        BigDecimal retorno = BigDecimal.ZERO;
        switch (oper) {
            case SOMA: retorno = n1.add(n2); break;
            case SUBTRACAO: retorno = n1.subtract(n2); break;
            case MULTIPLICACAO: retorno = n1.multiply(n2); break;
            case DIVISAO: retorno = n1.divide(n2); break;
        }
        repository.salvarHistorico(n1, n2, oper, retorno);
        return retorno;
    }
    @Override
    public List<String> listarHistorico() {
        return repository.listarHistorico();
    }
}
