package com.rinhabackend.pagamento.domain.extrato;

import java.util.Date;

public class TransacoesDTO {

    private int valor;
    private String tipo;
    private String descricao;
    private Date realizadaEm;

    public TransacoesDTO(int valor, String tipo, String descricao, Date date) {
        this.valor = valor;
        this.tipo = tipo ;
        this.descricao = descricao;
        this.realizadaEm = date;
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

    public Date getRealizadaEm() {
        return realizadaEm;
    }

    public void setRealizadaEm(Date realizadaEm) {
        this.realizadaEm = realizadaEm;
    }
}
