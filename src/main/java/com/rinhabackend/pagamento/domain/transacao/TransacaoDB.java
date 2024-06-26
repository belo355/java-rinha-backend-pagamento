package com.rinhabackend.pagamento.domain.transacao;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import javax.xml.crypto.Data;
import java.util.Date;

@Entity
@Table(name = "TRANSACAO")
public class TransacaoDB {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private int valor;

    @NotNull
    @Size(min = 1, max = 1)
    private String tipo;

    @NotNull
    @Size(min = 1, max = 10)
    private String descricao;

    private Date realizada_em;

    public TransacaoDB(){}

    public TransacaoDB(int valor, String tipo, String descricao) {
        this.valor = valor;
        this.tipo = tipo;
        this.descricao = descricao;
        this.realizada_em = new Date();
    }

    public TransacaoDB(TransacaoDTO transacaoDTO, Long id) {
        this.userId = id;
        this.valor = transacaoDTO.getValor();
        this.tipo = transacaoDTO.getTipo();
        this.descricao = transacaoDTO.getDescricao();
        this.realizada_em = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getRealizada_em() {
        return realizada_em;
    }

    public Long getUserId() {
        return userId;
    }
}
