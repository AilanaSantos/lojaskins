package br.unitins.service;

import java.util.List;

import br.unitins.dto.ProdutoDTO;
import br.unitins.dto.ProdutoResponseDTO;

public interface ProdutoService {

    // recursos basicos
    List<ProdutoResponseDTO> getAll();

    ProdutoResponseDTO findById(Long id);

    ProdutoResponseDTO create(ProdutoDTO produtoDTO);

    ProdutoResponseDTO update(Long id, ProdutoDTO produtoDTO);

    void delete(Long id);

    // recursos extras

    List<ProdutoResponseDTO> findByNome(String nome);

    long count();

}
