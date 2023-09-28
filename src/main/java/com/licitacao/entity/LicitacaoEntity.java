package com.licitacao.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Entity
@Table(name = "tb_licitacao")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LicitacaoEntity {


    @Column(length = 600)
    private String descricaoLicitacao;

    @Column(length = 600)
    private String objeto;
    private String dataEdital;

    @Column(length = 600)
    private String endereco;
    private String telefone;
    private String fax;
    private String dataEntregaProposta;
    private Boolean visualizado;

    @Id
    @Column(unique = true)
    private Long codUASG;
}

