package com.studies.studiesjava.studies.basic.calc;

import com.studies.studiesjava.studies.basic.calc.service.CalculadoraService;
import com.studies.studiesjava.studies.basic.calc.service.impl.CalculadoraServiceImpl;

import java.math.BigDecimal;

public class Calculadora {

    public static void main(String[] args) {
        CalculadoraService calc = new CalculadoraServiceImpl();
        System.out.println(calc.somar(BigDecimal.valueOf(2), BigDecimal.valueOf(3)));
    }
}
