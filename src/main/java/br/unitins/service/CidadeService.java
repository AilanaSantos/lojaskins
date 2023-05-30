package br.unitins.service;

import java.util.List;

import br.unitins.dto.CidadeDTO;
import br.unitins.dto.CidadeResponseDTO;


public interface CidadeService {

    // recursos basicos
    List<CidadeResponseDTO> getAll();

    CidadeResponseDTO findById(Long id);

    CidadeResponseDTO create(CidadeDTO cidadeDTO);

    CidadeResponseDTO update(Long id, CidadeDTO cidadeDTO);

    void delete(Long id);

    // recursos extras

    List<CidadeResponseDTO> findByNome(String nome);

    long count();

}
