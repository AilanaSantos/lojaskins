package br.unitins.service.pagamento;

import java.util.List;

import br.unitins.dto.pagamento.PagamentoDTO;
import br.unitins.dto.pagamento.PagamentoResponseDTO;

public interface PagamentoService {

    // recursos basicos
    List<PagamentoResponseDTO> getAll();

    PagamentoResponseDTO findById(Long id);

    PagamentoResponseDTO create(PagamentoDTO pagamentoDTO);

    PagamentoResponseDTO update(Long id, PagamentoDTO pagamentoDTO);

    void delete(Long id);

    // recursos extras

    List<PagamentoResponseDTO> findByNome(String nome);

    long count();

}
