package com.studies.studiesjava;

import com.studies.studiesjava.repository.entity.RelatorioEntity;
import com.studies.studiesjava.repository.entity.TarifaEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class MockRelatorio {
    public static void main(String[] args) {
        var tarifas = gerarTarifas();

        Map<String, BigDecimal> groupSum = getTotalForma(tarifas);

        groupSum.forEach((setor, salario) ->
                System.err.printf("Setor: %s Soma: %s%n", setor, salario));
    }

    private static Map<String, BigDecimal> getTotalForma(List<TarifaEntity> tarifas) {
        return tarifas.stream().collect(Collectors.groupingBy(
                TarifaEntity::getDescricaoTarifa,
                Collectors.mapping(
                        TarifaEntity::getValorTarifa,
                        Collectors.reducing(BigDecimal.ZERO, BigDecimal::add)
                )));
    }

    public static RelatorioEntity gerarRelatorio() {
        return RelatorioEntity.builder()
                .codigoEmpresa("codEmpresasa")
                .cpfCnpj("cpfCnpj")
                .nomeEmpresa("NomeEmpresa")
                .dataConsulta(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .dataGeracao(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .totalRegistros(100L)
                .tarifaDTOS(gerarTarifas())
                .build();
    }

    public static List<TarifaEntity> gerarTarifas() {
        List<TarifaEntity> tarifas = new ArrayList<>();
        String[] names = {
                "Maria Ceíclia",
                "Rogério Brito",
                "José Maria",
                "Rogério Brito",
                "Luiz Farias",
                "Sophie Vitória",
                "Claudiana Andrade"
        };

        String[] formas = {
                "Conta salário para conta corrente",
                "Conta corrente para conta Salário",
                "Conta corrente para conta Digital",
                "Conta Salário para Conta Digital",
                "Conta Digital para conta salário"
        };

        TarifaEntity tarifa;
        int tamanho = 90;
        String cpfCnpj = null;
        String agencia = null;
        String conta = null;
        String forma = null;
        for (int i = 0; i < tamanho; i++) {
            int random = ThreadLocalRandom.current().nextInt(0, tamanho);
            if (random % 2 == 0) {
                cpfCnpj = completeZeroLeft(String.valueOf(ThreadLocalRandom.current().nextLong(0, 999999))
                        .concat(String.valueOf(ThreadLocalRandom.current().nextLong(0, 99999))), 11);
            } else {
                cpfCnpj = completeZeroLeft(String.valueOf(ThreadLocalRandom.current().nextLong(0, 9999999))
                        .concat(String.valueOf(ThreadLocalRandom.current().nextLong(0, 9999999))), 14);
            }
            agencia = completeZeroLeft(String.valueOf(ThreadLocalRandom.current().nextInt(0, 9999)), 4);
            conta = completeZeroLeft(String.valueOf(ThreadLocalRandom.current().nextInt(0, 999999)), 6);
            forma = formas[ThreadLocalRandom.current().nextInt(0, 5)];

            tarifa = new TarifaEntity(names[ThreadLocalRandom.current().nextInt(0, 7)], agencia, conta, cpfCnpj,
                    new BigDecimal("223." + i), forma);
            tarifas.add(tarifa);
        }
        return tarifas;
    }

    private static String completeZeroLeft(String text, int size) {
        int repete = (size - text.length());
        StringBuilder textWithZero = new StringBuilder();
        for (int i = 0; i < repete; i++) {
            textWithZero.append("0");
        }
        textWithZero.append(text);
        return textWithZero.toString();
    }
}
