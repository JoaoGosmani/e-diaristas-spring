package br.com.joaogosmani.ediaristas.api.dtos.responses;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(SnakeCaseStrategy.class)
public class ServicoResponse {
    
    private Long id;

    private String nome;

    private BigDecimal valorMinimo;

    private Integer qtdHoras;

    private BigDecimal porcentagemComissao;

    private Integer horasQuarto;

    private BigDecimal valorQuarto;

    private Integer horasSala;

    private BigDecimal valorSala;

    private Integer horasBanheiro;

    private BigDecimal valorBanheiro;

    private Integer horasCozinha;

    private BigDecimal valorCozinha;

    private Integer horasQuintal;

    private BigDecimal valorQuintal;

    private Integer horasOutros;

    private BigDecimal valorOutros;

    private String icone;
    
    private Integer posicao;
    
}
