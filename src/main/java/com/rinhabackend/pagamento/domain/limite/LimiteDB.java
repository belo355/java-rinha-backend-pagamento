package com.rinhabackend.pagamento.domain.limite;

import jakarta.persistence.*;

@Entity
@Table(name = "Limite")
public class LimiteDB {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int limite;

    private int saldo;

    public LimiteDB() {
    }

    public LimiteDB(Long id, int limite, int saldo){
        this.id = id;
        this.limite = limite;
        this.saldo = saldo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void updateCredSaldo(int saldo) {
        this.saldo +=saldo;
    }

    public boolean validDebitSaldo(int valor) {
        int saldoRestante = this.saldo - valor;
        if (saldoRestante < this.limite) {
            return false; //saldo deve ser sempre acima ao limite
        }
        return true;
    }

    public void updateDebitSaldo(int valor) {
        this.saldo -= valor;
    }
}
