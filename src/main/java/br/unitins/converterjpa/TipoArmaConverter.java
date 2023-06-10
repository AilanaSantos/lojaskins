package br.unitins.converterjpa;


import br.unitins.model.TipoArma;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;


@Converter(autoApply = true)
public class TipoArmaConverter  implements AttributeConverter<TipoArma, Integer> {
 
     @Override
    public Integer convertToDatabaseColumn(TipoArma tipoArma) {
        return tipoArma == null ? null : tipoArma.getId();
    }

    @Override
    public TipoArma convertToEntityAttribute(Integer id) {
        return TipoArma.valueOf(id);
    }
}
