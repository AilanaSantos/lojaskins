package br.unitins.service.compra;

import br.unitins.dto.compra.CompraDTO;
import br.unitins.dto.compra.CompraResponseDTO;

import java.util.List;

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
