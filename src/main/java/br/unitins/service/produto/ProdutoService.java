package br.unitins.service.produto;

import java.util.List;

import br.unitins.dto.produto.ProdutoDTO;
import br.unitins.dto.produto.ProdutoResponseDTO;

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
