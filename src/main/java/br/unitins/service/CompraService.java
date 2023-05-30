package br.unitins.service;

import java.util.List;

import br.unitins.dto.CompraDTO;
import br.unitins.dto.CompraResponseDTO;

public interface CompraService {
    
    // recursos basicos
    List<CompraResponseDTO> getAll();

    CompraResponseDTO findById(Long id);

    CompraResponseDTO create(CompraDTO compraDTO);

    CompraResponseDTO update(Long id, CompraDTO compraDTO);

    void delete(Long id);

    // recursos extras

    List<CompraResponseDTO> findByNome(String nome);

    long count();
}
