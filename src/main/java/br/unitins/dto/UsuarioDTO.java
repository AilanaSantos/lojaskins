package br.unitins.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;

public record UsuarioDTO(


         List<TelefoneDTO> telefones,

         List<EnderecoDTO> enderecos,

        @NotBlank(message = "O campo deve ser informado.")
        @Size(max = 15, message = "O  deve posssuir no máximo 15 caracteres.")
        String cpf,

        @NotBlank(message = "O campo nome deve ser informado.")
        String email,

        @NotBlank(message = "O campo nome deve ser informado.")
        String senha,

        @NotBlank(message = "O campo nome deve ser informado.")
        String nome,

        @NotNull(message = "O campo nome deve ser informado.")
        Integer sexo


) {



}
