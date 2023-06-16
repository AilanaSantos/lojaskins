package br.unitins.dto.telefone;

import br.unitins.model.Telefone;

public record TelefoneResponseDTO(

        Long id,

        String numero,

        String codigoarea

) {

    public TelefoneResponseDTO(Telefone tele) {

        this(tele.getId(), tele.getNumero(), tele.getCodigoArea());
    }

}
