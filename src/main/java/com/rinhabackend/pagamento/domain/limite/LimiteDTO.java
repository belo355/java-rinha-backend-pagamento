package com.rinhabackend.pagamento.domain.limite;

public class LimiteDTO {

    private int limite;
    private int saldo;

    public LimiteDTO(LimiteDB limiteDB) {
        this.limite = limiteDB.getLimite();
        this.saldo = limiteDB.getSaldo();
    }

    public LimiteDTO(int limite, int saldo) {
        this.limite = limite;
        this.saldo = saldo;
    }

    public int getLimite() {
        return limite;
    }

    public void setLimite(int limite) {
        this.limite = limite;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }
}
