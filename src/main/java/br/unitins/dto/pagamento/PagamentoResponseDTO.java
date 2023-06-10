package br.unitins.dto.pagamento;

import br.unitins.model.Compra;
import br.unitins.model.Pagamento;

public record PagamentoResponseDTO(

        Compra compra,

        Float valor

) {

    public PagamentoResponseDTO(Pagamento pg) {
        
        this(pg.getCompra(), pg.getValor());

    }

}