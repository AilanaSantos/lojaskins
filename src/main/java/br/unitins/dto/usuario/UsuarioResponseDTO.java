package br.unitins.dto.usuario;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.unitins.model.Endereco;
import br.unitins.model.Sexo;
import br.unitins.model.Telefone;
import br.unitins.model.Usuario;

import java.util.List;

public record UsuarioResponseDTO (

        Long id,
        
        List<Telefone> telefones,

        List<Endereco> enderecos,

        String cpf,
        String nome,
        String email,
        String login,

        @JsonInclude(JsonInclude.Include.NON_NULL)
    Sexo sexo

) {

    public static UsuarioResponseDTO valueOf(Usuario u) {
        if (u.getPessoaFisica() == null)
            return new UsuarioResponseDTO(u.getId(), null, null, null, u.getLogin(), null, null, null);

            //return new UsuarioResponseDTO(u.getId(), u.getTelefone(), u.getEndereco(), u.getCpf(),u.getNome(),u.getEmail(),u.getLogin(),u.getSexo());

        return new UsuarioResponseDTO(u.getId(),
                u.getTelefone(),
                u.getEndereco(),
                u.getPessoaFisica().getCpf(),
                u.getPessoaFisica().getNome(),
                u.getPessoaFisica().getEmail(),
                u.getLogin(),
                u.getPessoaFisica().getSexo());
    }



}


