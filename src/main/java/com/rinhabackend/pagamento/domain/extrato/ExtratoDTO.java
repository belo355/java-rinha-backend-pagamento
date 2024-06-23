package com.rinhabackend.pagamento.domain.extrato;

public class ExtratoDTO {

    private SaldoDTO saldoDto;
    private UltimasTransacoesDTO ultimasTransacoesDTO;

    public SaldoDTO getSaldoDto() {
        return saldoDto;
    }

    public void setSaldoDto(SaldoDTO saldoDto) {
        this.saldoDto = saldoDto;
    }

    public UltimasTransacoesDTO getUltimasTransacoes() {
        return ultimasTransacoesDTO;
    }

    public void setUltimasTransacoes(UltimasTransacoesDTO ultimasTransacoesDTO) {
        this.ultimasTransacoesDTO = ultimasTransacoesDTO;
    }
}
