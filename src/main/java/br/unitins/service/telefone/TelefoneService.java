package br.unitins.service.telefone;

import java.util.List;

import br.unitins.dto.telefone.TelefoneDTO;
import br.unitins.dto.telefone.TelefoneResponseDTO;


public interface TelefoneService {

// recursos basicos
List<TelefoneResponseDTO> getAll();

TelefoneResponseDTO findById(Long id);

TelefoneResponseDTO create(TelefoneDTO telefoneDTO);

TelefoneResponseDTO update(Long id, TelefoneDTO telefoneDTO);

void delete(Long id);

// recursos extras

List<TelefoneResponseDTO> findByNome(String nome);

long count();

}