package com.rinhabackend.pagamento.domain.transacao;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "TRANSACAO")
public class TransacaoDB {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int valor;

    @NotNull
    @Size(min = 1, max = 1)
    private String tipo;

    @NotNull
    @Size(min = 1, max = 10)
    private String descricao;

    public TransacaoDB(){}

    public TransacaoDB(int valor, String tipo, String descricao) {
        this.valor = valor;
        this.tipo = tipo;
        this.descricao = descricao;
    }

    public TransacaoDB(TransacaoDTO transacaoDTO, Long id) {
        this.id = id;
        this.valor = transacaoDTO.getValor();
        this.tipo = transacaoDTO.getTipo();
        this.descricao = transacaoDTO.getDescricao();
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
}
