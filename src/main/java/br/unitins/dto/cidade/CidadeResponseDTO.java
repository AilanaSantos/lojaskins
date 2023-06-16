package br.unitins.dto.cidade;

import br.unitins.dto.estado.EstadoResponseDTO;
import br.unitins.model.Cidade;


    
    public record CidadeResponseDTO (
    
        Long id,
        String nome,
        EstadoResponseDTO estado
    
    ) {
        public CidadeResponseDTO(Cidade city){
    
            this(city.getId(), city.getNome(), new EstadoResponseDTO(city.getEstado()));

        }

    
    }


