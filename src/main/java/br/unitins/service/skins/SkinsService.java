package br.unitins.service.skins;

import java.util.List;

import br.unitins.dto.skins.SkinsDTO;
import br.unitins.dto.skins.SkinsResponseDTO;


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