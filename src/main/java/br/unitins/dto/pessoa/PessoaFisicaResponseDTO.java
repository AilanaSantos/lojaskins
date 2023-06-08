package br.unitins.dto.pessoa;

import br.unitins.model.PessoaFisica;
import br.unitins.model.Sexo;
import com.fasterxml.jackson.annotation.JsonInclude;

public record PessoaFisicaResponseDTO(

        Long id,
        String cpf,
        String nome,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        Sexo sexo
) {

    public PessoaFisicaResponseDTO(PessoaFisica pf) {
        this(pf.getId(), pf.getCpf(), pf.getNome(), pf.getSexo());
    }


}
