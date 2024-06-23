package com.rinhabackend.pagamento.domain.extrato;

import java.util.List;

public class UltimasTransacoesDTO {

    private List<TransacoesDTO> list;

    public List<TransacoesDTO> getList() {
        return list;
    }

    public void setList(List<TransacoesDTO> list) {
        this.list = list;
    }
}
