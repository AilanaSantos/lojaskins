package br.unitins.converterjpa;

import br.unitins.model.Raridade;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class RaridadeConverter implements AttributeConverter<Raridade, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Raridade raridade) {
        return raridade == null ? null : raridade.getId();
    }

    @Override
    public Raridade convertToEntityAttribute(Integer id) {
        return Raridade.valueOf(id);
    }
}
