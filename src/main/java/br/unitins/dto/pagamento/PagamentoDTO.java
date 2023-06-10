package br.unitins.dto.pagamento;

import br.unitins.model.Compra;

public record PagamentoDTO(

        Compra compra,

        Float valor

) {

}
