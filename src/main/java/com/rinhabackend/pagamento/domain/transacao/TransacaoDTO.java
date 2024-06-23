package com.rinhabackend.pagamento.domain.transacao;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class TransacaoDTO {

    private int valor;

    @NotNull
    @Size(min = 1, max = 1)
    private String tipo;

    @NotNull
    @Size(min = 1, max = 10)
    private String descricao;

    public TransacaoDTO(int valor, String tipo, String descricao) {
        this.valor = valor;
        this.tipo = tipo;
        this.descricao = descricao;
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
