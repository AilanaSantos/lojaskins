package br.unitins.dto.usuario;


import br.unitins.model.Endereco;
import br.unitins.model.Perfil;
import br.unitins.model.Telefone;
import br.unitins.model.Usuario;

import java.util.List;
import java.util.Set;

public record UsuarioResponseDTO(

        Long id,
        String nome,
        String cpf,

        List<Endereco> enderecos,
        List<Telefone> telefones,

        String login,
        String senha,
        String nomeImagem,
        Set<Perfil> perfis

) {

    public UsuarioResponseDTO(Usuario usuario) {

        this(
                usuario.getId(),
                usuario.getNome(),
                usuario.getCpf(),
                usuario.getEndereco(),
                usuario.getTelefone(),
                usuario.getLogin(),
                usuario.getSenha(),
                usuario.getNomeImagem(),
                usuario.getPerfis());

    }

   

}
