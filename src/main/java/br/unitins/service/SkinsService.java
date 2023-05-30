package br.unitins.service;

import java.util.List;

import br.unitins.dto.SkinsDTO;
import br.unitins.dto.SkinsResponseDTO;


public interface SkinsService {

// recursos basicos
List<SkinsResponseDTO> getAll();

SkinsResponseDTO findById(Long id);

SkinsResponseDTO create(SkinsDTO skinsDTO);

SkinsResponseDTO update(Long id, SkinsDTO skinsDTO);

void delete(Long id);

// recursos extras

List<SkinsResponseDTO> findByNome(String nome);

long count();

}