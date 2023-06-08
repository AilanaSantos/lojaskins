package br.unitins.service.estado;

import java.util.List;

import br.unitins.dto.estado.EstadoDTO;
import br.unitins.dto.estado.EstadoResponseDTO;

public interface EstadoService {

    // recursos basicos
    List<EstadoResponseDTO> getAll();

    EstadoResponseDTO findById(Long id);

    EstadoResponseDTO create(EstadoDTO estadoDTO);

    EstadoResponseDTO update(Long id, EstadoDTO estadoDTO);

    void delete(Long id);

    // recursos extras

    List<EstadoResponseDTO> findByNome(String nome);

    long count();
}