package br.unitins.service.endereco;

import java.util.List;

import br.unitins.dto.endereco.EnderecoDTO;
import br.unitins.dto.endereco.EnderecoResponseDTO;

public interface EnderecoService {

    // recursos basicos
    List<EnderecoResponseDTO> getAll();

    EnderecoResponseDTO findById(Long id);

    EnderecoResponseDTO create(EnderecoDTO enderecoDTO);

    EnderecoResponseDTO update(Long id, EnderecoDTO enderecoDTO);

    void delete(Long id);

    // recursos extras

    List<EnderecoResponseDTO> findByNome(String nome);

    long count();

}