package com.studies.studiesjava.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TarifaEntity {
    private String nomeFavorecido;
    private String agencia;
    private String contaSalario;
    private String cpfFavorecido;
    private BigDecimal valorTarifa;
    private String descricaoTarifa;
}
