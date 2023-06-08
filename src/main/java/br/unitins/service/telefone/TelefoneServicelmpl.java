package br.unitins.service.telefone;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import jakarta.ws.rs.NotFoundException;
import br.unitins.dto.telefone.TelefoneDTO;
import br.unitins.dto.telefone.TelefoneResponseDTO;
import br.unitins.model.Telefone;
import br.unitins.repository.TelefoneRepository;


@ApplicationScoped
public class TelefoneServicelmpl implements TelefoneService{

    @Inject
    TelefoneRepository telefoneRepository;


    @Inject
    Validator validator;

    @Override
    public List<TelefoneResponseDTO> getAll() {
        

        List<Telefone> list = telefoneRepository.listAll();
        return list.stream().map(TelefoneResponseDTO::new).collect(Collectors.toList());
    }

    @Override
    public TelefoneResponseDTO findById(Long id) {
        
        Telefone telefone = telefoneRepository.findById(id);
        if (telefone == null)
            throw new NotFoundException("Telefone não encontrado.");
        return new TelefoneResponseDTO(telefone);

    }

    @Override
    @Transactional
    public TelefoneResponseDTO create(TelefoneDTO telefoneDTO)throws ConstraintViolationException {
        
        validar(telefoneDTO);

        Telefone entity = new Telefone();
        entity.setCodigoArea(telefoneDTO.codigoArea());
        entity.setNumero(telefoneDTO.numero());

        telefoneRepository.persist(entity);

        return new TelefoneResponseDTO(entity);


    }

    private void validar(TelefoneDTO telefoneDTO) throws ConstraintViolationException {
        Set<ConstraintViolation<TelefoneDTO>> violations = validator.validate(telefoneDTO);
        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);

    }


    @Override
    @Transactional
    public TelefoneResponseDTO update(Long id, TelefoneDTO telefoneDTO)throws ConstraintViolationException{
        validar(telefoneDTO);
        Telefone telefoneBanco = telefoneRepository.findById(id);
        if (telefoneBanco == null) {
            throw new NotFoundException("Telefone não encontrado pelo id");
        }

        telefoneBanco.setCodigoArea(telefoneDTO.codigoArea());
        telefoneBanco.setNumero(telefoneDTO.numero());

        telefoneRepository.persist(telefoneBanco);

        return new TelefoneResponseDTO(telefoneBanco);

    }

    @Override
    @Transactional
    public void delete(Long id) {
        telefoneRepository.deleteById(id);
    }

    @Override
    public List<TelefoneResponseDTO> findByNome(String nome) {
        
        List<Telefone> list = telefoneRepository.findByNome(nome);
        return list.stream().map(TelefoneResponseDTO::new).collect(Collectors.toList());


    }

    @Override
    public long count() {
        return telefoneRepository.count();
    }
    

}
