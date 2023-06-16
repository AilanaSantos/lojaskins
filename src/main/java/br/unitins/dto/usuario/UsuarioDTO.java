package br.unitins.dto.usuario;

import java.util.List;

import br.unitins.dto.endereco.EnderecoDTO;
import br.unitins.dto.telefone.TelefoneDTO;
import jakarta.validation.constraints.NotBlank;

public record UsuarioDTO(

        @NotBlank(message = "O campo precisa ser preenchido.") 
        String nome,
        String cpf,

        Integer idSexo,

        List<TelefoneDTO> telefones,

        List<EnderecoDTO> enderecos

        

) {

}
