package br.unitins.dto;

import br.unitins.model.Cidade;
import br.unitins.model.Estado;

    
    public record CidadeResponseDTO (
    
        Long id,
        String nome,
        Estado estado
    
    ) {
        public CidadeResponseDTO(Cidade city){
    
            this(city.getId(), city.getNome(), city.getEstado());

        }

    
    }


