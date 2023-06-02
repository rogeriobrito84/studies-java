package com.studies.studiesjava.studies.basic.calc.controller;

import com.studies.studiesjava.studies.basic.calc.repository.CalculadoraRepository;
import com.studies.studiesjava.studies.basic.calc.repository.impl.CalculadoraListRepository;
import com.studies.studiesjava.studies.basic.calc.service.CalculadoraService;
import com.studies.studiesjava.studies.basic.calc.service.impl.CalculadoraServiceImpl;

import javax.swing.*;
import java.math.BigDecimal;

public class CalculadoraController {

    public static void main(String[] args) {
        CalculadoraRepository repository = new CalculadoraListRepository(); //inversao de dependencia
        CalculadoraService calc = new CalculadoraServiceImpl(repository);
//        System.out.println(calc.somar(BigDecimal.valueOf(2), BigDecimal.valueOf(3)));
//        List<String> historicos = calc.listarHistorico();
//        for (String historicoTemp: historicos) {
//            System.out.println(historicoTemp);
//        }
          somar(calc);
    }

    public static void somar(CalculadoraService calc) {
        String v1s = JOptionPane.showInputDialog("Digite o valor 1: ");
        String v2s = JOptionPane.showInputDialog("Digite o valor 2: ");
        try {
            BigDecimal v1 = new BigDecimal(v1s);
            BigDecimal v2 = new BigDecimal(v2s);
            BigDecimal soma = calc.somar(v1, v2);
            String msg = "Resultado: " + soma.doubleValue();
            JOptionPane.showMessageDialog(null, msg);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Digite numeros válidos");

        }
    }
}
