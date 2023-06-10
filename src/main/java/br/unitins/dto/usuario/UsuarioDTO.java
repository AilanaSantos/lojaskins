package br.unitins.dto.usuario;

import java.util.List;

import br.unitins.dto.endereco.EnderecoDTO;
import br.unitins.dto.telefone.TelefoneDTO;

public record UsuarioDTO(

                List<TelefoneDTO> telefones,

                List<EnderecoDTO> enderecos,

                String login,
                String senha

) {

}
