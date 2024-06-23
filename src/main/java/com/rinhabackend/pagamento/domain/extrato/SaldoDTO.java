package com.rinhabackend.pagamento.domain.extrato;

import java.util.Date;

public class SaldoDTO {

    private int total;
    private Date dataExtrato;
    private int limite;


    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Date getDataExtrato() {
        return dataExtrato;
    }

    public Date setDataExtrato(Date dataExtrato) {
        return this.dataExtrato = dataExtrato;
    }

    public int getLimite() {
        return limite;
    }

    public void setLimite(int limite) {
        this.limite = limite;
    }
}
